package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.GraphingViewModel;

import adapters.GraphingAdapter;

public class GraphingListView extends AppCompatActivity {

    private GraphingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphing_list_view);

        initializeViews();
        viewModel = new GraphingViewModel();
        ListView discreteMathListView;
        String[] title;

        discreteMathListView= findViewById(R.id.graphing_list_view);
        title = getResources().getStringArray(R.array.graphing_titles);

        GraphingAdapter adapter = new GraphingAdapter(GraphingListView.this, title);
        discreteMathListView.setAdapter(adapter);

        discreteMathListView.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.graphing_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
       GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }



}