package com.example.personalityandme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalityandme.api.ApiServiceGenerator;
import com.example.personalityandme.api.AuthService;
import com.example.personalityandme.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Register
public class RegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextInputLayout textInputLayoutEmail = findViewById(R.id.txtEmailLayout);
        final TextInputEditText textInputEditTextEmail = findViewById(R.id.txtEmail);
        final TextInputLayout textInputLayoutName = findViewById(R.id.txtNameLayout);
        final TextInputEditText textInputEditTextName = findViewById(R.id.txtName);
        final TextInputLayout textInputLayoutPassword = findViewById(R.id.txtPasswordLayout);
        final TextInputEditText textInputEditTextPassword = findViewById(R.id.txtPassword);

        Button buttonRegister = findViewById(R.id.btnRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Register Activity";

            @Override
            public void onClick(View v) {
                String email = textInputEditTextEmail.getText().toString();
                final String name = textInputEditTextName.getText().toString();
                String password = textInputEditTextPassword.getText().toString();

                if (email.isEmpty() || name.isEmpty() ||password.isEmpty()) {
                    if (email.isEmpty()) {
                        textInputLayoutEmail.setError("Enter Your Email Address.");
                    } else {
                        textInputLayoutEmail.setError(null);
                    }
                    if (name.isEmpty()) {
                        textInputLayoutName.setError("Enter Your Name.");
                    } else {
                        textInputLayoutName.setError(null);
                    }
                    if (password.isEmpty()) {
                        textInputLayoutPassword.setError("Enter a Valid Password.");
                    } else {
                        textInputLayoutPassword.setError(null);
                    }
                } else {

                    User user=new User(email,name,password);

                    AuthService authService= ApiServiceGenerator.createService(AuthService.class);

                    Call<User> registeredUser=authService.register(user);

                    registeredUser.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code()==201){
                                Toast.makeText(getApplicationContext(), "Registration Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Registration Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
