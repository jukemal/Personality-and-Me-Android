package com.example.personalityandme.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalityandme.R;
import com.example.personalityandme.adapters.QuestionRecyclerViewAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeFragment extends Fragment {

    private QuestionRecyclerViewAdapter questionRecyclerViewAdapter;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = root.findViewById(R.id.progress_bar);

        recyclerView = root.findViewById(R.id.recyclerview_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

//        AuthService service = ApiServiceGenerator.createService(AuthService.class);
//
//        Call<List<Question>> listCall=service.getQuestions();

        questionRecyclerViewAdapter=new QuestionRecyclerViewAdapter(getContext());
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(questionRecyclerViewAdapter);

//        listCall.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                if (response.isSuccessful()){
//                    List<Question> questionList=response.body();
//                    Log.e("question_list",questionList.toString());
//
//                    List<QuestionHelperModel> questionHelperModelList=(List<QuestionHelperModel>) CollectionUtils.collect(questionList, new Transformer<Question, QuestionHelperModel>() {
//                        @Override
//                        public QuestionHelperModel transform(Question input) {
//                            return new QuestionHelperModel(input,false);
//                        }
//                    });
//
//                    questionRecyclerViewAdapter=new QuestionRecyclerViewAdapter(getContext(),questionHelperModelList);
//                    progressBar.setVisibility(View.GONE);
//                    recyclerView.setAdapter(questionRecyclerViewAdapter);
//                }else {
//                    Log.e("question_list","error");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//                Toast.makeText(getContext(), "Error Connecting to Server.", Toast.LENGTH_SHORT).show();
//                Log.e("response_object_err", t.toString());
//            }
//        });

        Button buttonSubmit=root.findViewById(R.id.btnSubmit_home);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewPopup = inflater.inflate(R.layout.layout_processing_popup, null);

                MaterialAlertDialogBuilder processing_popup=new MaterialAlertDialogBuilder(getContext());
                processing_popup.setView(viewPopup);
                final AlertDialog alertDialogProcessingPopup=processing_popup.show();

                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View viewPopup = inflater.inflate(R.layout.layout_personality_popup, null);

                        MaterialAlertDialogBuilder processing_popup=new MaterialAlertDialogBuilder(getContext());
                        processing_popup.setView(viewPopup);
                        alertDialogProcessingPopup.dismiss();
                        processing_popup.show();
                    }
                },5000);
            }
        });

        return root;
    }
}
