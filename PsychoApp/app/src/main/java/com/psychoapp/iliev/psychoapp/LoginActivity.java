package com.psychoapp.iliev.psychoapp;

import android.app.ProgressDialog;
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

import com.psychoapp.iliev.psychoapp.dummy.BackGroundChanger;
import com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers.HtppServerResponseTask;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String LOGIN_PARAMS = "LOGIN_PARAMS";

    @Bind(R.id.background_image) ProportionalImageView _background;
    @Bind(R.id.input_username) EditText _username;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    @Bind(R.id.link_login_google) TextView _googleLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_background);

        // use this for custom font importing to selected UI elements
        // fonts are situated in assets/fonts
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        _username.setTypeface(face);
        _username.setTextSize(20);
        _passwordText.setTypeface(face);
        _passwordText.setTextSize(20);
        _loginButton.setTypeface(face);
        _loginButton.setTextSize(24);
        _signupLink.setTypeface(face);
        _signupLink.setTextSize(20);
        _googleLink.setTypeface(face);
        _googleLink.setTextSize(20);

        _username.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _passwordText.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _loginButton.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _signupLink.setShadowLayer(10, 0, 0, R.color.themeGreenDark);
        _googleLink.setShadowLayer(10, 0, 0, R.color.themeGreenDark);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        _googleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO - attach the google login activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
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

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String username = _username.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        HtppServerResponseTask loginTask = new HtppServerResponseTask();
                        loginTask.execute(LOGIN_PARAMS, username, password);

                        String status = loginTask.getStatus().toString();
                        Toast.makeText(getBaseContext(), status, Toast.LENGTH_LONG).show();
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivityForResult(intent, REQUEST_SIGNUP);
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
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
}
