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
import com.example.services.beans.user.User;
import com.example.services.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private SessionManager sessionManager;
    private UserRepository userRepository;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        sessionManager = new SessionManager(this);
        userRepository = new UserRepository();
        Email = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        userRegistration = findViewById(R.id.tvRegister);
        forgotPassword = findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this, R.style.LoadingLogin);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            getUserAndSetSession(user.getEmail());
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        Login.setOnClickListener(view -> validate(Email.getText().toString(), Password.getText().toString()));
        userRegistration.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
        forgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, PasswordActivity.class)));
    }

    private void getUserAndSetSession(String email){
        Call<User> call = userRepository.getUser(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(getApplicationContext(), String.format("OK"), Toast.LENGTH_SHORT).show();
                idUser = response.body().getId();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), String.format("KO"), Toast.LENGTH_SHORT).show();
            }
        });
        String id = Integer.toString(idUser);
        sessionManager.createSession(email, id);
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();
        if ((userName.isEmpty() || userName == null) && (userPassword.isEmpty() || userPassword == null)){
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.error_login), Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    getUserAndSetSession(Email.getText().toString());
                    login();
                }
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
