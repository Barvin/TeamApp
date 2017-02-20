package teamapp.id2212.ict.kth.se.teamapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SeeFeedback extends AppCompatActivity {

    TextView mGivenFeedbackTextView;
    TextView mReceivedFeedbackTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Feedback");

        mGivenFeedbackTextView = (TextView) findViewById(R.id.given_feedbacks);
        mReceivedFeedbackTextView = (TextView) findViewById(R.id.received_feedbacks);

        findViewById(R.id.given_feedbacks_scrollview).setVisibility(View.VISIBLE);
        findViewById(R.id.received_feedbacks_scrollview).setVisibility(View.GONE);


        String[] givenFeedback = {"Respectful", "Helpful", "Considerate", "On time"};
        String[] receivedFeedback = {"Hard worker", "Responsible", "Understanding", "Flexible"};


        for (String s : receivedFeedback){
            mReceivedFeedbackTextView.append(s + "\n\n\n");
        }
        for (String s : givenFeedback){
            mGivenFeedbackTextView.append(s + "\n\n\n");
        }

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
        findViewById(R.id.given_feedbacks_scrollview).setVisibility(View.VISIBLE);
        findViewById(R.id.received_feedbacks_scrollview).setVisibility(View.GONE);
        findViewById(R.id.given_feedbacks).setVisibility(View.VISIBLE);
        findViewById(R.id.received_feedbacks).setVisibility(View.GONE);
    }

    public void viewReceivedFeedback(){
        findViewById(R.id.received_feedbacks_scrollview).setVisibility(View.VISIBLE);
        findViewById(R.id.received_feedbacks).setVisibility(View.VISIBLE);
        findViewById(R.id.given_feedbacks).setVisibility(View.GONE);
    }

}
