package com.example.app_smart_pan.profile.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_smart_pan.login.LoginActivity;
import com.example.app_smart_pan.login.UpdateProfile;
import com.example.app_smart_pan.R;
import com.example.services.beans.user.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private ImageView profileUpdate, profilePic;;
    private Button logout;
    private TextView profileFirstName, profileLastName, profileEmail;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        logout = root.findViewById(R.id.logout);
        profilePic = root.findViewById(R.id.profilePic);
        profileLastName = root.findViewById(R.id.tvProfileLastName);
        profileFirstName = root.findViewById(R.id.tvProfileFirstName);
        profileEmail = root.findViewById(R.id.tvProfileEmail);
        profileUpdate = root.findViewById(R.id.btnProfileUpdate);

        profileUpdate.setOnClickListener(view -> updateProfile());
        logout.setOnClickListener(view -> Logout());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                profileFirstName.setText("" + user.getFirstname());
                profileLastName.setText("" + user.getLastname());
                profileEmail.setText("" + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getActivity(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    private void updateProfile(){
        startActivity(new Intent(getContext(), UpdateProfile.class));
    }

    private void Logout(){
        firebaseAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
