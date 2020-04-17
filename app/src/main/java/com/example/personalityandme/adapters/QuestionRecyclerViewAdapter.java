package com.example.personalityandme.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalityandme.R;
import com.example.personalityandme.models.Question;
import com.example.personalityandme.models.QuestionHelperModel;

import java.util.List;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.QuestionRecyclerViewHolder> {

    private static final String TAG = "QuestionRecyclerAdapter";

    private Context context;
    private List<QuestionHelperModel> questionHelperModelList;

    public QuestionRecyclerViewAdapter(Context context/*, List<QuestionHelperModel> questionHelperModelList*/) {
        this.context = context;
//        this.questionHelperModelList = questionHelperModelList;
    }

    @NonNull
    @Override
    public QuestionRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_item, parent, false);
        return new QuestionRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRecyclerViewHolder holder, int position) {
//        final QuestionHelperModel questionHelperModel=questionHelperModelList.get(position);
//        final Question question=questionHelperModel.getQuestion();
//
//        holder.textViewId.setText(String.valueOf(question.getId()));
//        holder.radioButtonQ1.setText(question.getQuestion1());
//        holder.radioButtonQ2.setText(question.getQuestion2());

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.answer_1 :
                        Log.e(TAG,"1");
                        break;
                    case R.id.answer_2:
                        Log.e(TAG,"2");
                        break;
                    case R.id.answer_3:
                        Log.e(TAG,"3");
                        break;
                    case R.id.answer_4:
                        Log.e(TAG,"4");
                        break;
                    case R.id.answer_5:
                        Log.e(TAG,"5");
                        break;
                    case R.id.answer_6:
                        Log.e(TAG,"6");
                        break;
                    case R.id.answer_7:
                        Log.e(TAG,"7");
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 25;
    }


    public  class QuestionRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView textViewId;
        RadioButton radioButtonQ1;
        RadioButton radioButtonQ2;
        CardView cardView;
        RadioGroup radioGroup;

        public QuestionRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

//            textViewId=itemView.findViewById(R.id.id);
//            radioButtonQ1=itemView.findViewById(R.id.q1);
//            radioButtonQ2=itemView.findViewById(R.id.q2);
            cardView=itemView.findViewById(R.id.card_view);
            radioGroup=itemView.findViewById(R.id.radio_group);
        }
    }
}
