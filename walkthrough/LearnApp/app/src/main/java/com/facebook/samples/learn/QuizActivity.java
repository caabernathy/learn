package com.facebook.samples.learn;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

/**
 * Activity for showing a quiz for a given topic
 */
public class QuizActivity extends AppCompatActivity implements QuestionFragment.OnQuestionSelectedListener {
    private static final String ARG_TOPIC = "topic";
    private int mQuestionIndex = 0;
    private String mTopic;
    private ArrayList<Question> mQuestions;
    private int[] mSelectedAnswers;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTopic = getIntent().getStringExtra(ARG_TOPIC);

        ModelUtils utils = ModelUtils.getInstance();
        mQuestions = utils.getQuestions(mTopic);
        mSelectedAnswers = new int[mQuestions.size()];

        if (findViewById(R.id.question_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            QuestionFragment questionFragment =
                    QuestionFragment.newInstance(mTopic, mQuestionIndex);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.question_fragment_container, questionFragment).commit();
        }

    }

    @Override
    public void onAnswerSelected(int position) {
        mSelectedAnswers[mQuestionIndex] = position;
        mQuestionIndex++;
        if (mQuestionIndex < mQuestions.size()) {
            // Show the next question in the topic
            showNextQuestionFragment();
        } else {
            // Done with questions for the topic
            // Save score
            saveScore();
            // Show done message
            String message = String.format(getResources().getString(R.string.done_message), mTopic);
            Snackbar.make(
                    findViewById(R.id.question_fragment_container),
                    message,
                    Snackbar.LENGTH_INDEFINITE).show();
            showScoreFragment();

        }
    }

    private void showNextQuestionFragment() {
        QuestionFragment questionFragment =
                QuestionFragment.newInstance(mTopic, mQuestionIndex);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.question_fragment_container, questionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showScoreFragment() {
        ScoreFragment scoreFragment = ScoreFragment.newInstance(mTopic, mScore);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.question_fragment_container, scoreFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void saveScore() {
        int correct = 0;
        for (int i = 0; i < mSelectedAnswers.length; i++) {
            Question question = mQuestions.get(i);
            if (question.getAnswer() == mSelectedAnswers[i]) {
                correct++;
            }
        }
        mScore = (int) (((double)correct / (double) mSelectedAnswers.length) * 100.0);
        ModelUtils utils = ModelUtils.getInstance();
        utils.addScore(QuizActivity.this, mScore, mTopic);
    }
}
