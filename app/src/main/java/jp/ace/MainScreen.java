package jp.ace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JP on 9/22/2017.
 */

public class MainScreen extends AppCompatActivity {

    //Button
    @BindView(R.id.btnPlay)
    ImageButton btnPlay;
    @BindView(R.id.btnLogout)
    ImageButton btnLogout;

    //Variables
    public String USER_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Initialize();
    }

    public void Initialize(){
        final FirebaseClass firebaseClass = new FirebaseClass(MainScreen.this);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseClass.getUserInstance().signOut();
                SharedPreferences.Editor editor = getSharedPreferences(USER_TYPE, MODE_PRIVATE).edit();
                editor.remove("status");
                editor.commit();
                Intent intent = new Intent(MainScreen.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }


}
