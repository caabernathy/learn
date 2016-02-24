package com.facebook.samples.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Home view fragment
 */
public class HomeFragment extends Fragment {
    private static final String ARG_SUBJECT = "subject";

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        (getActivity().findViewById(R.id.button_math)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showQuizList("Math");
                    }
                });
        (getActivity().findViewById(R.id.button_history)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showQuizList("History");
                    }
                });
    }

    private void showQuizList(String subjectName) {
        Intent intent = new Intent(getActivity(), QuizListActivity.class);
        intent.putExtra(ARG_SUBJECT, subjectName);
        startActivity(intent);
    }
}
