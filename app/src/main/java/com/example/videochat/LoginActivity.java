package com.example.videochat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginNextClick(View view) {
        EditText userNameEditText = findViewById(R.id.et_login_user_name);
        String userName = userNameEditText.getText().toString();

        if(userName == null || userName == "") {
            Toast.makeText(this, "user name cannot be empty", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, VideoCallActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        }
    }

    public void onLoginBackClick(View view) {
        finish();
    }
}
