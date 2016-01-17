package com.psychoapp.iliev.psychoapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.psychoapp.iliev.psychoapp.helpers.BackGroundChanger;
import com.psychoapp.iliev.psychoapp.helpers.HttpDataHelper;
import com.psychoapp.iliev.psychoapp.customs.ProportionalImageView;
import com.psychoapp.iliev.psychoapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.Bind;

import static android.app.PendingIntent.getActivity;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String LOGIN_PARAMS = "LOGIN_PARAMS";
    public static final String USER_PREFERENCES = "userPreferences";
    public static final String TOKEN = "token";
    public static final String USERNAME = "username";

    SharedPreferences sharedpreferences;

    public static List<String> responseData = new ArrayList<String>();

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.input_username) EditText _username;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    @Bind(R.id.link_login_google) SignInButton _googleLink;
    @Bind((R.id.tv_or)) TextView _tv_or;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        _username.setTypeface(face);
        _username.setTextSize(20);
        _passwordText.setTypeface(face);
        _passwordText.setTextSize(20);
        _loginButton.setTypeface(face);
        _loginButton.setTextSize(24);
        _signupLink.setTypeface(face);
        _signupLink.setTextSize(20);
        _tv_or.setTypeface(face);
        _tv_or.setTextSize(12);

        _username.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _passwordText.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _loginButton.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _signupLink.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
//        _googleLink.setShadowLayer(10, 0, 0, R.color.themeGreenDark);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        _googleLink.setSize(SignInButton.COLOR_AUTO);
        _googleLink.setScopes(gso.getScopeArray());
        _googleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in);
        _background.startAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String username = _username.getText().toString();
        final String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        HttpDataHelper loginTask = new HttpDataHelper();
                        loginTask.execute(LOGIN_PARAMS, username, password);

                        try {
                            responseData = loginTask.get();

                            sharedpreferences = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(TOKEN, responseData.get(0));
                            editor.putString(USERNAME, responseData.get(1));
                            editor.commit();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivityForResult(intent, REQUEST_SIGNUP);

                        onLoginSuccess();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_SIGNUP) {
        }

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivityForResult(intent, RC_SIGN_IN);
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        this.finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = _username.getText().toString();
        String password = _passwordText.getText().toString();

        if (username.isEmpty() || username.length() < 2 || username.length() > 50) {
            _username.setError("enter a valid username");
            valid = false;
        } else {
            _username.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
