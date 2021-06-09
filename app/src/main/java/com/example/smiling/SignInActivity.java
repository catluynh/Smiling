package com.example.smiling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText editEmail,editPassword;
    Button btnSignIn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        btnSignIn = findViewById(R.id.btnSignIn_SignIn);
        editEmail = findViewById(R.id.editEmail_SignIn);
        editPassword = findViewById(R.id.editPassword_SignIn);
        register = findViewById(R.id.register_SignIn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("user");
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                int pos = 0;
                for (int i = 0; i < email.length(); i++) {
                    if (email.charAt(i) == '@') {
                        pos = i;
                        break;
                    }
                }
                String resultEmail = email.substring(0, pos);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean flag = true;
                       for(DataSnapshot ds : snapshot.getChildren()){
                           if(ds.child("email").getValue().equals(email)){
                               String passwordFromDB = ds.child("password").getValue().toString();
                               if(password.equals(passwordFromDB)){
                                   flag = false;
                                   User user = ds.getValue(User.class);
                                   String childEmail = ds.getKey();
                                   Intent intent = new Intent(SignInActivity.this,FaceSmilngActivity.class);
                                   Bundle bundle = new Bundle();
                                   bundle.putSerializable("user",user);
                                   bundle.putString("key",childEmail);
                                   intent.putExtras(bundle);
                                   startActivity(intent);
                                   finish();
                               }
                           }
                       }
                       if (flag)
                           Toast.makeText(SignInActivity.this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}