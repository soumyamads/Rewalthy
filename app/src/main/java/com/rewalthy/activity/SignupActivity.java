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
public class SignupActivity extends AppCompatActivity {
    EditText username,phone_no,email_id,password,reenter_password;
    TextView login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        phone_no=(EditText)findViewById(R.id.phone);
        email_id=(EditText)findViewById(R.id.emailid);
        reenter_password=(EditText)findViewById(R.id.retype_password);
        login=(TextView)findViewById(R.id.back_to_login);
        register=(Button)findViewById(R.id.register_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
