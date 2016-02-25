package com.facebook.samples.learn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Fragment for showing questions
 */
public class QuestionFragment extends Fragment {
    private static final String ARG_TOPIC = "topic";
    private static final String ARG_QUESTION_INDEX = "questionIndex";

    private String mTopic;
    private int mQuestionIndex;

    ArrayList<Question> mQuestions;
    private Question mQuestion;
    private int mSelectedOption = -1;

    private boolean mLastQuestion;

    private OnQuestionSelectedListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param topic Topic question belongs to.
     * @param questionIndex Question index.
     * @return A new instance of fragment QuestionFragment.
     */
    public static QuestionFragment newInstance(String topic, int questionIndex) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putInt(ARG_QUESTION_INDEX, questionIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTopic = getArguments().getString(ARG_TOPIC);
            mQuestionIndex = getArguments().getInt(ARG_QUESTION_INDEX);
        }
        ModelUtils utils = ModelUtils.getInstance();
        ArrayList<Question> questions = utils.getQuestions(mTopic);
        mQuestion = questions.get(mQuestionIndex);
        mLastQuestion = (questions.size() == mQuestionIndex + 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_question, container, false);
        ((TextView) v.findViewById(R.id.question_text)).setText(mQuestion.getQuestion());
        ((TextView) v.findViewById(R.id.option_1)).setText(mQuestion.getChoices().get(0));
        ((TextView) v.findViewById(R.id.option_2)).setText(mQuestion.getChoices().get(1));
        ((TextView) v.findViewById(R.id.option_3)).setText(mQuestion.getChoices().get(2));
        ((TextView) v.findViewById(R.id.option_4)).setText(mQuestion.getChoices().get(3));

        if (mLastQuestion) {
            ((Button) v.findViewById(R.id.button_save)).setText(getActivity()
                    .getResources().getString(R.string.done_button));
        }

        return v;
    }

    public void onSaveButtonPressed(final int position) {
        if (mListener != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mListener.onAnswerSelected(position);
                }
            };
            handler.postDelayed(runnable, 2000); // Small delay to show answer result
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuestionSelectedListener) {
            mListener = (OnQuestionSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuestionSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((Button) getActivity().findViewById(R.id.button_save)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mSelectedOption >= 0) {
                            markAnswer(mSelectedOption);
                            onSaveButtonPressed(mSelectedOption);
                            if (mLastQuestion) {
                                ((Button) v.findViewById(R.id.button_save))
                                        .setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
        ((RadioGroup) getActivity().findViewById(R.id.radio_group_question))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId) {
                            case R.id.option_1:
                                mSelectedOption = 0;
                                break;
                            case R.id.option_2:
                                mSelectedOption = 1;
                                break;
                            case R.id.option_3:
                                mSelectedOption = 2;
                                break;
                            case R.id.option_4:
                                mSelectedOption = 3;
                                break;
                        }
                    }
                });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnQuestionSelectedListener {
        void onAnswerSelected(int position);
    }

    private void markAnswer(int selectedAnswer) {
        Snackbar snackbar;
        Drawable border;
        if (mQuestion.getAnswer() == selectedAnswer) {
            snackbar = Snackbar.make(
                    getActivity().findViewById(R.id.question_fragment_container),
                    R.string.answer_correct_message,
                    Snackbar.LENGTH_SHORT);
            border = ContextCompat.getDrawable(getActivity(), R.drawable.answer_correct_border);
        } else {
            snackbar = Snackbar.make(
                    getActivity().findViewById(R.id.question_fragment_container),
                    R.string.answer_wrong_message,
                    Snackbar.LENGTH_SHORT);
            border = ContextCompat.getDrawable(getActivity(), R.drawable.answer_wrong_border);
        }
        int radioButtonIdentifier;
        switch (selectedAnswer) {
            case 0:
                radioButtonIdentifier = R.id.option_1;
                break;
            case 1:
                radioButtonIdentifier = R.id.option_2;
                break;
            case 2:
                radioButtonIdentifier = R.id.option_3;
                break;
            case 3:
                radioButtonIdentifier = R.id.option_4;
                break;
            default:
                radioButtonIdentifier = R.id.option_1;
                break;
        }
        RadioButton radioButton = ((RadioButton) getActivity().findViewById(radioButtonIdentifier));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            radioButton.setBackgroundDrawable(border);
        } else {
            radioButton.setBackground(border);
        }
        snackbar.show();
    }
}
