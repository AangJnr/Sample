package io.nothing.sample;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Toast;

import io.nothing.sample.utility.Constants;
import io.nothing.sample.utility.Utils;


public class LoginActivity extends BaseActivity {
;

    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLayout = (TextInputLayout) findViewById(R.id.email_layout);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_layout);



        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if(Utils.checkInternetConnection(LoginActivity.this)) {

                    email = emailLayout.getEditText().getText().toString();
                    password = passwordLayout.getEditText().getText().toString();

                    if (!signInValidate()) {
                        Toast.makeText(LoginActivity.this, "Please provide valid info", Toast.LENGTH_LONG).show();

                    } else {


                        //prefs.edit().putBoolean(Constants.IS_USER_SIGNED_IN, true).apply();

                        startActivity(new Intent(LoginActivity.this, DrawerBottomSheetActivity.class));
                        supportFinishAfterTransition();


                    }
                }else Toast.makeText(LoginActivity.this, "Please ensure you have an Internet Connection and try again", Toast.LENGTH_SHORT).show();




            }
        });
    }





    public boolean signInValidate() {
        boolean valid = true;



        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("enter a valid email address");
            valid = false;
        } else {
            emailLayout.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            passwordLayout.setError("password must be more than 6 characters");
            valid = false;
        } else {
            passwordLayout.setError(null);
        }

        return valid;
    }



}
