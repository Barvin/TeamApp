package teamapp.id2212.ict.kth.se.teamapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.cunoraz.tagview.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import teamapp.id2212.ict.kth.se.teamapp.Models.*;
import teamapp.id2212.ict.kth.se.teamapp.Constants.Constants;


public class UserSkills extends AppCompatActivity {

    private TagView tagGroup;
    private ArrayList<TagClass> tagList = new ArrayList<>();
    private EditText editText;

    /**
     * sample country list
     */
    private static final String[] SKILLS = new String[] {
            "SQL", "SCRUM", "MySQL", "Bash", "Hibernate", "Ruby on Rails"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_skills);
        tagGroup = (TagView) findViewById(R.id.tag_group);
        editText = (EditText) findViewById(R.id.editTagText);
        ArrayList<Tag> tags = new ArrayList<>();

        tagList.add(new TagClass("1", "SQL"));
        tagList.add(new TagClass("2", "SCRUM"));
        tagList.add(new TagClass("3", "MySQL"));
        tagList.add(new TagClass("4", "Bash"));
        tagList.add(new TagClass("5", "Hibernate"));
        tagList.add(new TagClass("6", "Ruby on Rails"));
        tagList.add(new TagClass("7", "JEE"));
        tagList.add(new TagClass("8", "C++"));
        tagList.add(new TagClass("9", "Cisco Packet Tracer"));
        tagList.add(new TagClass("10", "MATLAB"));
        tagList.add(new TagClass("11", "AutoCAD"));

        //tag.layoutColor = Color.parseColor(tagList.get(i).getColor());

        for (int i = 0; i<tagList.size(); i++){
            Tag t = new Tag(tagList.get(i).getName());
            t.isDeletable = true;
            t.tagTextSize = 16.0f;
            t.layoutColor = Color.parseColor("#4799E8");
            tags.add(t);
        }

        tagGroup.addTags(tags);
    }


    private void setTags(CharSequence cs) {
        /**
         * for empty edittext
         */
        if (cs.toString().equals("")) {
            tagGroup.addTags(new ArrayList<Tag>());
            return;
        }

        String text = cs.toString();
        ArrayList<Tag> tags = new ArrayList<>();
        Tag tag;


        for (int i = 0; i < tagList.size(); i++) {
            if (tagList.get(i).getName().toLowerCase().startsWith(text.toLowerCase())) {
                tag = new Tag(tagList.get(i).getName());
                tag.radius = 10f;
                tag.layoutColor = Color.parseColor(tagList.get(i).getColor());
                if (i % 2 == 0) // you can set deletable or not
                    tag.isDeletable = true;
                tags.add(tag);
            }
        }
        tagGroup.addTags(tags);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
