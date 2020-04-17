package com.example.personalityandme.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.personalityandme.R;
import com.example.personalityandme.SplashScreen;
import com.example.personalityandme.api.ApiServiceGenerator;
import com.example.personalityandme.api.AuthService;
import com.example.personalityandme.models.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        AuthService authService= ApiServiceGenerator.createService(AuthService.class);

        Call<User> currentUser=authService.current_user();

        currentUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user=response.body();

                    TextView email=root.findViewById(R.id.email_profile);
                    email.setText(user.getEmail());

                    TextView name=root.findViewById(R.id.name_profile);
                    name.setText(user.getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Button buttonLogout=root.findViewById(R.id.btnLogOut);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());

                builder.setMessage("Do you want to Log Off?")
                        .setTitle("Log Off")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Call<Object> call=authService.logout();

                                call.enqueue(new Callback<Object>() {
                                    @Override
                                    public void onResponse(Call<Object> call, Response<Object> response) {
                                        if (response.isSuccessful()){
                                            Log.e("logout",response.body().toString());

                                            Intent intent = new Intent(getContext(), SplashScreen.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Object> call, Throwable t) {

                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });

        return root;
    }
}
