package com.example.app_smart_pan.login;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.MainActivity;
import com.example.app_smart_pan.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        userRegistration = findViewById(R.id.tvRegister);
        forgotPassword = findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this, R.style.LoadingLogin);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        Login.setOnClickListener(view -> validate(Name.getText().toString(), Password.getText().toString()));
        userRegistration.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
        forgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, PasswordActivity.class)));
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();
        if ((userName.isEmpty() || userName == null) && (userPassword.isEmpty() || userPassword == null)){
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_login), Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                    login();
                else
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_cred), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            });
        }
    }

    private void login(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
