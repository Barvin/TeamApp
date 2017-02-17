package teamapp.id2212.ict.kth.se.teamapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SeeFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Feedback");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.button_given).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewGivenFeedback();
            }
        });

        findViewById(R.id.button_received).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewReceivedFeedback();
            }
        });
    }

    public void viewGivenFeedback(){
        findViewById(R.id.given_feedbacks).setVisibility(View.VISIBLE);
        findViewById(R.id.received_feedbacks).setVisibility(View.GONE);
    }

    public void viewReceivedFeedback(){
        findViewById(R.id.received_feedbacks).setVisibility(View.VISIBLE);
        findViewById(R.id.given_feedbacks).setVisibility(View.GONE);
    }

}
