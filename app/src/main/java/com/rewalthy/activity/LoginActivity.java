package com.rewalthy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rewalthy.R;

/**
 * Created by snyxius on 29/2/16.
 */
public class LoginActivity extends AppCompatActivity {
EditText username,password;
    TextView fgtpswd,signup;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        fgtpswd=(TextView)findViewById(R.id.fgtpaswd);
        signup=(TextView)findViewById(R.id.needacnt);
        login=(Button)findViewById(R.id.login_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });



    }
}
