package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.DiscreteMathViewModel;

import adapters.DiscreteMathAdapter;

public class DiscreteMathListView extends AppCompatActivity {

    private DiscreteMathViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discrete_math_list_view);

        initializeViews();
        viewModel = new DiscreteMathViewModel();
        ListView discreteMathListView;
        String[] title;

        discreteMathListView= findViewById(R.id.discrete_math_list_view);
        title = getResources().getStringArray(R.array.discrete_math_titles);

        DiscreteMathAdapter adapter = new DiscreteMathAdapter(DiscreteMathListView.this, title);
        discreteMathListView.setAdapter(adapter);

        discreteMathListView.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.discrete_math_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }



}