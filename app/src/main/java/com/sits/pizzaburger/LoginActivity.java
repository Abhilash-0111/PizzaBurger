package com.sits.pizzaburger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {


    EditText name , mobile , password , org ;
   static Button signup,otpget ;
    TextView ol ;
    private FirebaseAuth mAuth;
    String mVerificationId ;
    String mobile1, password1;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();

     /*  if (auth.getCurrentUser() != null) {
            startActivity(new Intent(StudIn.this, MainActivity.class));
            finish();
        }
*/
new abcd().execute() ;
        //   name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        mobile = (EditText)findViewById(R.id.mobilenum);
        //       org = (EditText)findViewById(R.id.org);
        signup = (Button)findViewById(R.id.signup) ;
        otpget = (Button)findViewById(R.id.otp) ;
        ol = (TextView) findViewById(R.id.ol) ;



        Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim);
        ol.startAnimation(animBlink);

        otpget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mobile1=mobile.getText().toString() ;




                mAuth = FirebaseAuth.getInstance();
                sendVerificationCode(mobile1);


            }
        });






        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = password.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    password.setError("Enter valid code");
                    password.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });

        ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openol() ;


            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                ColorDrawable[] color = {new ColorDrawable(Color.BLUE), new ColorDrawable(Color.GREEN)};
                TransitionDrawable trans = new TransitionDrawable(color);
                signup.setBackground(trans);
                trans.startTransition(3000); // duration 3 seconds
                handler.postDelayed(this, 3000);

            }
        }, 3000);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {




                ColorDrawable[] color2 = {new ColorDrawable(Color.GREEN), new ColorDrawable(Color.BLUE)};
                TransitionDrawable trans2 = new TransitionDrawable(color2);
                otpget.setBackground(trans2);
                trans2.startTransition(3000); // duration 3 seconds
                //Do something after 20 seconds
                handler.postDelayed(this, 3000);
            }
        }, 3000);




    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                password.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }


                        }
                    }
                });
    }

    void openol(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);



    }

}


class abcd extends AsyncTask<String,String,Integer> {


    @Override
    protected Integer doInBackground(String... params) {
        try {
            ColorDrawable[] color = {new ColorDrawable(Color.RED), new ColorDrawable(Color.GREEN)};
            TransitionDrawable trans = new TransitionDrawable(color);
            LoginActivity.signup.setBackground(trans);
while (true){

            trans.startTransition(3000); // duration 3 seconds
    wait(15000);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

