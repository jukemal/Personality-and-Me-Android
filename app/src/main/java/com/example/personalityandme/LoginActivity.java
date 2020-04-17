package com.example.personalityandme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalityandme.api.ApiServiceGenerator;
import com.example.personalityandme.api.AuthService;
import com.example.personalityandme.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Login
public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout textInputLayoutEmail = findViewById(R.id.txtEmailLayout);
        final TextInputEditText textInputEditTextEmail = findViewById(R.id.txtEmail);
        final TextInputLayout textInputLayoutPassword = findViewById(R.id.txtPasswordLayout);
        final TextInputEditText textInputEditTextPassword = findViewById(R.id.txtPassword);

        Button buttonLogin = findViewById(R.id.btnLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEditTextEmail.getText().toString();
                String password = textInputEditTextPassword.getText().toString();

                //Input Validation
                if (email.isEmpty() || password.isEmpty()) {
                    if (email.isEmpty()) {
                        textInputLayoutEmail.setError("Please Enter Your Email.");
                    } else {
                        textInputLayoutEmail.setError(null);
                    }
                    if (password.isEmpty()) {
                        textInputLayoutPassword.setError("Please Enter Your Password.");
                    } else {
                        textInputLayoutPassword.setError(null);
                    }
                } else {
                    AuthService authService= ApiServiceGenerator.createService(AuthService.class);

                    User logUser=new User(email,password);

                    Call<User> call=authService.login(logUser);

                    call.enqueue(new Callback<User>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            if (response.errorBody()!=null){
                                Toast.makeText(getApplicationContext(),"Incorrect email or password.",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("error",call.toString(),t);
                        }
                    });
                }
            }
        });

        Button buttonRegister = findViewById(R.id.btnRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
