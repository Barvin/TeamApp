package teamapp.id2212.ict.kth.se.teamapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PersonalProfile extends AppCompatActivity {
    private ImageButton mimgBtn;
    private Button mSaveBtn;
    private TextView mNameField;
    private TextView mEmailField;
    private TextView mDescField;
    private Uri mImguri=null;
    private static final int Gallery_request = 1;
    private DatabaseReference mDbUsers;
    private StorageReference mStorageImg;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        String user_id_fromSignup = getIntent().getStringExtra("user_id");
        String user_email_fromSignup = getIntent().getStringExtra("user_email");
        mAuth=FirebaseAuth.getInstance();
        mStorageImg= FirebaseStorage.getInstance().getReference().child("profile_images");
        mDbUsers= FirebaseDatabase.getInstance().getReference().child("users");
        mProgress = new ProgressDialog(this);


        mimgBtn = (ImageButton) findViewById(R.id.personal_profile_imgBtn);
        mSaveBtn= (Button) findViewById(R.id.personal_profile_saveBtn);
        mNameField= (TextView) findViewById(R.id.personal_profile_name_text);
        mEmailField =(TextView) findViewById(R.id.personal_profile_email_text);
        mDescField = (TextView) findViewById(R.id.personal_profile_desc_text);
        mEmailField.setText(mAuth.getCurrentUser().getEmail());

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavingAccountInfo();
            }
        });
        mimgBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            Intent galleryInetnet = new Intent();
                galleryInetnet.setAction(Intent.ACTION_GET_CONTENT);
                galleryInetnet.setType("image/*");
            startActivityForResult(galleryInetnet,Gallery_request);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_request && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1 , 1)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mImguri = result.getUri();
                mimgBtn.setImageURI(mImguri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
    private void SavingAccountInfo (){
        final String Name = mNameField.getText().toString().trim();
        final String user_id = mAuth.getCurrentUser().getUid();
        final String Desc = mDescField.getText().toString().trim();

        if(!TextUtils.isEmpty(Name ) && mImguri!= null ) {
            mProgress.setMessage("Saving Information");
            mProgress.show();

            StorageReference filepath = mStorageImg.child(mImguri.getLastPathSegment());
            filepath.putFile(mImguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String downloadUrl = taskSnapshot.getDownloadUrl().toString();
                    mDbUsers.child("user_id").child("name").setValue(Name);
                    mDbUsers.child("user_id").child("Desc").setValue(Desc);
                    mDbUsers.child(user_id).child("image").setValue(downloadUrl);
                    mProgress.dismiss();

                    Intent mainIntent = new Intent(PersonalProfile.this, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);

                }

            });
        }


    }
}
