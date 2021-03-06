package com.example.app_smart_pan.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_smart_pan.R;
import com.example.services.beans.user.User;
import com.example.services.repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userFirstName, userPassword, userEmail, userLastName;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    private String email, firstName, lastName, password;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    private Uri imagePath;
    private StorageReference storageReference;
    private UserRepository userRepository;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                userProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        getSupportActionBar().hide();

        userRepository = new UserRepository();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        userProfilePic.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
        });



        regButton.setOnClickListener(view -> {
            if(validate()){
                String user_email = userEmail.getText().toString().trim();
                String user_password = userPassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        sendUserData();
                        firebaseAuth.signOut();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        userLogin.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this, LoginActivity.class)));

    }

    private void setupUIViews(){
        userFirstName = findViewById(R.id.etUserFirstName);
        userLastName = findViewById(R.id.etUserLastName);
        userPassword = findViewById(R.id.etUserPassword);
        userEmail = findViewById(R.id.etUserEmail);
        regButton = findViewById(R.id.btnRegister);
        userLogin = findViewById(R.id.tvUserLogin);
        userProfilePic = findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        lastName = userLastName.getText().toString();
        firstName = userFirstName.getText().toString();

        if(lastName.isEmpty() || password.isEmpty() || email.isEmpty() || firstName.isEmpty() || imagePath == null){
            Toast.makeText(this, getResources().getString(R.string.error_form), Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
               if(task.isSuccessful()){
                   sendUserData();
                   Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                   firebaseAuth.signOut();
                   finish();
                   startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
               }else{
                   Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
               }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(e -> Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        User user = new User(email, password, lastName, firstName);
        myRef.setValue(user);

        Call<String> call = userRepository.create(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(RegistrationActivity.this, String.format("OK"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, String.format("KO"), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
