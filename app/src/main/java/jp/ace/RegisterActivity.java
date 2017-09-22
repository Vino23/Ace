package jp.ace;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JP on 9/22/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    //EditText
    @BindView(R.id.su_name) EditText etUserId;
    @BindView(R.id.su_bday) EditText etBirthday;
    @BindView(R.id.su_email) EditText etEmail;
    @BindView(R.id.su_password) EditText etPassword;

    //Button
    @BindView(R.id.signupBtn)
    Button btnSignUp;

    //Material Dialog
    MaterialDialog registerdialog;

    //Firebase
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //Variables
    private int year,month,day;
    private String timestamp;
    private SimpleDateFormat currentTimeStamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Initialize();
    }

    public void Initialize() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        currentTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp = currentTimeStamp.format(new Date());

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int Cyear,
                                                  int monthOfYear, int dayOfMonth) {
                                etBirthday.setText(/*"Birthday: " + */(monthOfYear + 1) + "/" + (dayOfMonth) + "/" + Cyear);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerdialog = new MaterialDialog.Builder(RegisterActivity.this)
                        .title("Signing Up")
                        .content("Please wait...")
                        .progress(true, 0)
                        .show();

                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final String name = etUserId.getText().toString().trim();
                final String birthday = etBirthday.getText().toString().trim();

                if (TextUtils.isEmpty(etUserId.getText().toString())) {
                    registerdialog.dismiss();
                    etUserId.setError("Required");
                    return;
                }
                if (TextUtils.isEmpty(etBirthday.getText().toString())) {
                    registerdialog.dismiss();
                    etBirthday.setError("Required");
                    return;
                }
                if (!isValidEmail(etEmail.getText().toString().trim())) {
                    registerdialog.dismiss();
                    etEmail.setError("Enter a valid Email Address");
                    return;
                }
                if (etPassword.length() < 6) {
                    registerdialog.dismiss();
                    etPassword.setError("Password must be atleast 6 characters long");
                    return;
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UserClass user = new UserClass(name, birthday, email, password);
                                        mDatabase.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(user);
                                        registerdialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            new MaterialDialog.Builder(RegisterActivity.this)
                                                    .title("Register Failed")
                                                    .content("Email already in use!")
                                                    .positiveText("Close")
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                            Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }



}
