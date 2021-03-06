package com.aysimasavas.instaclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    EditText emailText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser!=null)
        {
            Intent intent=new Intent(MainActivity.this,feedActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void signInClicked(View view)
    {
        if(emailText.getText().toString().matches("")||passwordText.getText().toString().matches(""))
        {
            Toast.makeText(MainActivity.this,"bos alan bırakmayınız",Toast.LENGTH_LONG).show();
        }
        else
        {
            String email=emailText.getText().toString();
            String password=passwordText.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent=new Intent(MainActivity.this,feedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });

        }

    }
    public void signUpClicked(View view)
    {

        if(emailText.getText().toString().matches("")||passwordText.getText().toString().matches(""))
        {
            Toast.makeText(MainActivity.this,"bos alan bırakmayınız",Toast.LENGTH_LONG).show();
        }else
        {
            String email=emailText.getText().toString();
            String password=passwordText.getText().toString();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(MainActivity.this,"user created",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(MainActivity.this,feedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();

                }
            });
        }


    }
}