package com.facebook.samples.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

/**
 * Fragment for handing Profile view
 */
public class ProfileFragment extends Fragment {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView profileTextView;
    private ProfileTracker profileTracker;
    private ProfilePictureView profilePictureView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUI();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Successful login
                updateUI();
                Log.d("ProfileFragment", "Login success");
            }

            @Override
            public void onCancel() {
                // Login canceled
                updateUI();
                Log.d("ProfileFragment", "Login canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                // Error during login
                updateUI();
                Log.d("ProfileFragment", "Login error");
            }
        });

        profileTextView = (TextView) view.findViewById(R.id.profile_text);
        profilePictureView = (ProfilePictureView) view.findViewById(R.id.profile_picture);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    private void updateUI() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            String loggedInGreeting = String.format(
                    getActivity().getResources().getString(
                            R.string.greeting_profile_logged_in), profile.getFirstName());
            profileTextView.setText(loggedInGreeting);
            profilePictureView.setProfileId(profile.getId());
        } else {
            profileTextView.setText(
                    getActivity().getResources().getString(R.string.greeting_profile));
            profilePictureView.setProfileId(null);
        }
    }

}
