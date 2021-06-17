package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname, lastname, email, password, confpassword, contactno;
    Button btnRegister;
    TextInputLayout firstNameWrapper, lastNameWrapper, emailWrapper, passwordWrapper, confPasswordWrapper, contactNoWrapper;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        firstname = findViewById(R.id.userFirstName);
        lastname = findViewById(R.id.userLastName);
        email = findViewById(R.id.userEmailAddress);
        password = findViewById(R.id.userPassword);
        confpassword = findViewById(R.id.confirmPassword);
        contactno = findViewById(R.id.contactNo);

        firstNameWrapper = findViewById(R.id.userFirstNameWrapper);
        lastNameWrapper = findViewById(R.id.userLastNameWrapper);
        emailWrapper = findViewById(R.id.userEmailAddressWrapper);
        passwordWrapper = findViewById(R.id.passwordWrapper);
        confPasswordWrapper = findViewById(R.id.confirmPasswordWrapper);
        contactNoWrapper = findViewById(R.id.contactNoWrapper);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null){
                    //Success register
                }
                else {
                    String firstnameString = firstname.getText().toString().trim();
                    String lastnameString = lastname.getText().toString().trim();
                    String emailString = email.getText().toString().trim();
                    String passwordString = password.getText().toString().trim();
                    String confPasswordString = confpassword.getText().toString().trim();
                    String contactNoString = contactno.getText().toString().trim();
                    if (firstnameString.isEmpty()) {
                        firstNameWrapper.setError("Enter First Name");
                        firstNameWrapper.requestFocus();
                        return;
                    }

                    if (lastnameString.isEmpty()) {
                        lastNameWrapper.setError("Enter Last Name");
                        lastNameWrapper.requestFocus();
                        return;
                    }

                    if (emailString.isEmpty()) {
                        emailWrapper.setError("Enter Email");
                        emailWrapper.requestFocus();
                        return;
                    }

                    if (passwordString.isEmpty()) {
                        passwordWrapper.setError("Enter Password");
                        passwordWrapper.requestFocus();
                        return;
                    }

                    if (confPasswordString.isEmpty()) {
                        confPasswordWrapper.setError("Enter Confirm Password");
                        confPasswordWrapper.requestFocus();
                        return;
                    }

                    if (password.equals(confpassword)) {
                        confPasswordWrapper.setError("Password did not match");
                        confPasswordWrapper.requestFocus();
                        return;
                    }

                    if (contactNoString.isEmpty()) {
                        contactNoWrapper.setError("Enter Contact Number");
                        contactNoWrapper.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(firstnameString, lastnameString, emailString, contactNoString);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task){
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(RegisterActivity.this, "User Created successfully.", Toast.LENGTH_SHORT).show();
                                                    }
                                                    else{
                                                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                });
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}