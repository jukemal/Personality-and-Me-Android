package com.example.personalityandme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalityandme.api.ApiServiceGenerator;
import com.example.personalityandme.api.AuthService;
import com.example.personalityandme.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Splash
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthService authService = ApiServiceGenerator.createService(AuthService.class);

        Call<User> currentUser=authService.current_user();

        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("jkb",String.valueOf(response.code()));
                if (response.code() == 200) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
