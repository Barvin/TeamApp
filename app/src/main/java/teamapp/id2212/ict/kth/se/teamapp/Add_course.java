package teamapp.id2212.ict.kth.se.teamapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Add_course extends AppCompatActivity {


    private FirebaseDatabase mDbcourses;
    private DatabaseReference mRef;
    private ArrayList mCourses = new ArrayList();

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        mRef =FirebaseDatabase.getInstance().getReference().child("courses");
        mListView = (ListView) findViewById(R.id.add_course_list_view);


        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                mRef) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);

            }

        };
        mListView.setAdapter(firebaseListAdapter);
            }

}

