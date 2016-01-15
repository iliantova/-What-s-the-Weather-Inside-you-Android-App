package com.psychoapp.iliev.psychoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private static final String REGISTER_PARAMS = "REGISTER_PARAMS";

    @Bind(R.id.background_image) ProportionalImageView _pim_backgrond_image;
    @Bind(R.id.input_name) EditText _et_input_name;
    @Bind(R.id.input_email) EditText _et_input_email;
    @Bind(R.id.input_password) EditText _et_input_password;
    @Bind(R.id.btn_signup) Button _btn_signup;
    @Bind(R.id.link_login) TextView _tv_link_login;
    @Bind(R.id.link_login_anon) TextView _tv_login_anon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        BackGroundChanger.backgroundRandomizer(_pim_backgrond_image);

        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/simonettaitalic.ttf");
        _et_input_name.setTypeface(face);
        _et_input_name.setTextSize(20);
        _et_input_email.setTypeface(face);
        _et_input_email.setTextSize(20);
        _et_input_password.setTypeface(face);
        _et_input_password.setTextSize(20);
        _btn_signup.setTypeface(face);
        _btn_signup.setTextSize(24);
        _tv_link_login.setTypeface(face);
        _tv_link_login.setTextSize(20);
        _tv_login_anon.setTypeface(face);
        _tv_login_anon.setTextSize(20);

        _btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _tv_link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });

        _tv_login_anon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and load the anonymious usage pageview
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(SignupActivity.this, R.anim.fade_in);
        _pim_backgrond_image.startAnimation(fadeIn);
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

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _btn_signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String username = _et_input_name.getText().toString();
        final String email = _et_input_email.getText().toString();
        final String password = _et_input_password.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        HtppServerResponseTask registerTask = new HtppServerResponseTask();
                        registerTask.execute(REGISTER_PARAMS, username, password, password, email);

                        onSignupSuccess();
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivity(intent);

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _btn_signup.setEnabled(true);
        setResult(RESULT_OK, null);

        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _btn_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _et_input_name.getText().toString();
        String email = _et_input_email.getText().toString();
        String password = _et_input_password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _et_input_name.setError("at least 3 characters");
            valid = false;
        } else {
            _et_input_name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _et_input_email.setError("enter a valid email address");
            valid = false;
        } else {
            _et_input_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _et_input_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _et_input_password.setError(null);
        }

        return valid;
    }

}