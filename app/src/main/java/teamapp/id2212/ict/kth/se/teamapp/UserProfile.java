package teamapp.id2212.ict.kth.se.teamapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    private EditText mail_field;
    private EditText name_field;
    Intent i = new Intent(this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);
        mail_field = (EditText)findViewById(R.id.signup_email_text);
        name_field = (EditText)findViewById(R.id.personal_profile_name_text);


        final FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail_field.getText().toString()});
                i.putExtra(Intent.EXTRA_SUBJECT, "TeamApp with me!");
                i.putExtra(Intent.EXTRA_TEXT   , "Hi " + name_field.getText().toString() + ",\n\nI want to team up with you for !");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(UserProfile.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
