package com.example.app_smart_pan.login;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.MainActivity;
import com.example.app_smart_pan.R;
import com.example.services.beans.User.User;
import com.example.services.beans.User.UserJSON;
import com.example.services.beans.User.UserResponse;
import com.example.services.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); //hide the title bar

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        userRepository = new UserRepository();

        // firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //  FirebaseUser user = firebaseAuth.getCurrentUser();

        /*if (user != null) {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }*/

        Login.setOnClickListener(view -> validate(Name.getText().toString(), Password.getText().toString()));

        userRegistration.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));

        forgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, PasswordActivity.class)));
    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        UserJSON userJSON = new UserJSON(userName, userPassword);
        Call<UserResponse> call = userRepository.checkLogin(userJSON);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String message = response.body().getMessage();
                if (message.equals(UserResponse.LOGIN_SUCCESSFULL)) {
                    User userResponse = response.body().getUser();
                    Log.d("USER", userResponse.getEmail());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Log.d(UserResponse.FAILED_CONNECTION, message);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("fail request", t.getMessage());
            }
        });
      /*  firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                progressDialog.dismiss();
                //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                checkEmailVerification();
            }else{
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(LoginActivity.this, MainActivity.class));

//        if(emailflag){
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }else{
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }

}
