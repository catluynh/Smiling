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

public class RegisterActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView signIn;
    EditText editYourname,editEmail,editPassword,editPasswordAgain;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");
        signIn = findViewById(R.id.signIn_register);
        editYourname = findViewById(R.id.editYourName_Register);
        editEmail = findViewById(R.id.editEmail_Register);
        editPassword = findViewById(R.id.editPassword_Register);
        editPasswordAgain = findViewById(R.id.editPasswordAgain_Register);
        btnRegister = findViewById(R.id.btnRegister_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourName = editYourname.getText().toString();
                String email = editEmail.getText().toString();
                String password= editPassword.getText().toString();
                String passwordAgain = editPasswordAgain.getText().toString();
                if(!yourName.matches("^[a-zA-Z]{1,}")){
                    Toast.makeText(RegisterActivity.this, "Your name don't contain number and characters special", Toast.LENGTH_SHORT).show();
                    editYourname.setText("");
                    editYourname.requestFocus();
                }else if(!email.matches("^.{1,}@gmail.com")){
                    Toast.makeText(RegisterActivity.this, "Email incorrect", Toast.LENGTH_SHORT).show();
                    editEmail.setText("");
                    editEmail.setText("");
                    editEmail.requestFocus();
                }else if(!password.matches("[a-zA-Z0-9]{1,}")){
                    Toast.makeText(RegisterActivity.this, "Password don't contain characters special", Toast.LENGTH_SHORT).show();
                    editPassword.setText("");
                    editPassword.requestFocus();
                }else if(!password.equals(passwordAgain)){
                    Toast.makeText(RegisterActivity.this, "Password must be same Password Again", Toast.LENGTH_SHORT).show();
                    editPasswordAgain.setText("");
                    editPasswordAgain.requestFocus();
                }else{
                    User user = new User(yourName,email,password);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean flag = true;
                            for (DataSnapshot ds : snapshot.getChildren()){
                                if(ds.child("email").getValue().equals(email)){
                                    flag = false;
                                    Toast.makeText(RegisterActivity.this, "Email had in system", Toast.LENGTH_SHORT).show();
                                }
                            }
                            if(flag) {
                                databaseReference.push().setValue(user);
                                Toast.makeText(RegisterActivity.this, "OK", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}