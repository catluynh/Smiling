package com.example.smiling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FaceSmilngActivity extends AppCompatActivity {
    ImageView imgHappy,imgNormal,imgUnhappy;
    Animation rotate;
    Button btnFinish;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_smilng);
        getSupportActionBar().hide();
        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_face);
        imgHappy = findViewById(R.id.imgHappy);
        imgNormal=findViewById(R.id.imgNormal);
        imgUnhappy = findViewById(R.id.imgUnhappy);
        btnFinish = findViewById(R.id.btnFinish);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("user");
        String key = bundle.getString("key");
        imgHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                int quantityHappy = user.getHappy();
                int q = quantityHappy + 1;
                user.setHappy(q);
                HashMap hashMap = new HashMap();
                hashMap.put("happy",q);
                databaseReference.child(key).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        imgHappy.startAnimation(rotate);
                    }
                });
            }
        });
        imgNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                int quantityNormal = user.getNormal();
                int q = quantityNormal + 1;
                user.setNormal(q);
                HashMap hashMap = new HashMap();
                hashMap.put("normal",q);
                databaseReference.child(key).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        imgNormal.startAnimation(rotate);
                    }
                });
            }
        });
        imgUnhappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                int quantityUnhappy = user.getUnhappy();
                int q = quantityUnhappy + 1;
                user.setUnhappy(q);
                HashMap hashMap = new HashMap();
                hashMap.put("unhappy",q);
                databaseReference.child(key).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        imgUnhappy.startAnimation(rotate);
                    }
                });
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FaceSmilngActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}