package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.ProbabilityViewModel;

import adapters.ProbabilityAdapter;

public class ProbabilityListView extends AppCompatActivity {

    private ProbabilityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probability_list_view);


        initializeViews();

        viewModel = new ProbabilityViewModel();

        ListView probabilityListView;
        String[] title;

        probabilityListView = findViewById(R.id.probability_list_view);
        title = getResources().getStringArray(R.array.probability_titles);

        ProbabilityAdapter adapter =
                new ProbabilityAdapter(ProbabilityListView.this, title);
        probabilityListView.setAdapter(adapter);

        probabilityListView.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.probability_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }
}