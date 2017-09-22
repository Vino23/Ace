package jp.ace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    //EditText
    @BindView(R.id.login_emailid) EditText etUserId;
    @BindView(R.id.login_password) EditText etPassword;

    //SignUP
    @BindView(R.id.tvSignUp)
    TextView tvSignup;


    //Preference
    private String USER_TYPE;
    private String statushldr;
    //MaterialDialog
    private MaterialDialog logindialog;

    //Firebase
    private FirebaseClass firebasefunctions;
    private DatabaseReference mRootRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Initialize();
    }

    public void Initialize(){

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sign Up
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        firebasefunctions = new FirebaseClass(this);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = firebasefunctions.getUserInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                SharedPreferences prefs = getSharedPreferences(USER_TYPE, MODE_PRIVATE);
                statushldr = prefs.getString("status", "Welcome");
                if(statushldr.equals("loggedin")){
                    //Login
                    Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }
        };
    }

    public void Login(View v) {
        logindialog = new MaterialDialog.Builder(this)
                .title("Logging in")
                .content("Please wait...")
                .progress(true, 0)
                .show();
        if (TextUtils.isEmpty(etUserId.getText().toString())) {
            etUserId.setError("Enter email address");
            logindialog.dismiss();
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Enter password");
            logindialog.dismiss();
        } else if (!isValidEmail(etUserId.getText().toString())) {
            etUserId.setError("Enter a valid email address");
            logindialog.dismiss();
        } else {
            mAuth.signInWithEmailAndPassword(etUserId.getText().toString(), etPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull final Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                logindialog.dismiss();
                                try {
                                    throw task.getException();

                                } catch (FirebaseAuthInvalidUserException e) {
                                    new MaterialDialog.Builder(LoginActivity.this)
                                            .title("Login Failed")
                                            .content("User Not Found")
                                            .positiveText("Close")
                                            .show();
                                    etPassword.setText("");
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    new MaterialDialog.Builder(LoginActivity.this)
                                            .title("Login Failed")
                                            .content("Email or Password did not match. Please try again")
                                            .positiveText("Close")
                                            .show();
                                    etPassword.setText("");
                                } catch (FirebaseNetworkException e) {
                                    new MaterialDialog.Builder(LoginActivity.this)
                                            .title("Login Failed")
                                            .content("Please Check Internet Connection, and try again")
                                            .positiveText("Close")
                                            .show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {
                                //LOGIN SUCCESSFUL
                                SharedPreferences.Editor editor = getSharedPreferences(USER_TYPE, MODE_PRIVATE).edit();
                                editor.putString("status", "loggedin");
                                editor.commit();
                            }
                        }
                    });
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
