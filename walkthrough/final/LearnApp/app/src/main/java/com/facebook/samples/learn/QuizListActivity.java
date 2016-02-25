package com.facebook.samples.learn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Activity for displaying topics for a given subject
 */
public class QuizListActivity extends AppCompatActivity {
    private static final String ARG_SUBJECT = "subject";
    private static final String ARG_TOPIC = "topic";
    private String[] mTopicNames;
    private String mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizlist);

        if (savedInstanceState != null &&
                savedInstanceState.getString(ARG_SUBJECT) != null) {
            mSubject = savedInstanceState.getString(ARG_SUBJECT);
        } else {
            mSubject = getIntent().getStringExtra(ARG_SUBJECT);
        }

        if (mSubject != null) {
            ModelUtils utils = ModelUtils.getInstance();
            ArrayList<Topic> topics = utils.getTopics(mSubject);
            mTopicNames = new String[topics.size()];
            for (int i = 0; i < topics.size(); i++) {
                mTopicNames[i] = topics.get(i).getName();
            }

            ListView listView = (ListView) findViewById(R.id.quiz_list);
            ArrayAdapter<String> quizListAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTopicNames);
            listView.setAdapter(quizListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showQuiz(mTopicNames[position]);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(ARG_SUBJECT, mSubject);
    }

    private void showQuiz(String topicName) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(ARG_TOPIC, topicName);
        startActivity(intent);
    }
}
