package com.facebook.samples.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment used to display the score summary
 */
public class ScoreFragment extends Fragment {
    private static final String ARG_TOPIC = "topic";
    private static final String ARG_SCORE = "score";
    private String mTopic;
    private int mScore;
    private int mHighScore;

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param topic Topic for the quiz
     * @param score Latest score for the topic
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance(String topic, int score) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putInt(ARG_SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTopic = getArguments().getString(ARG_TOPIC);
            mScore = getArguments().getInt(ARG_SCORE);
            ModelUtils utils = ModelUtils.getInstance();
            mHighScore = utils.getHighScore(getActivity(), mTopic);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_score, container, false);
        String messageScore = String.format(
                getActivity().getResources().getString(R.string.score_message), mScore);
        String messageHighScore =
                String.format(getActivity().getResources()
                        .getString(R.string.high_score_message), mHighScore);
        ((TextView) v.findViewById(R.id.score_summary)).setText(messageScore);
        ((TextView) v.findViewById(R.id.high_score_summary)).setText(messageHighScore);
        return v;
    }
}
