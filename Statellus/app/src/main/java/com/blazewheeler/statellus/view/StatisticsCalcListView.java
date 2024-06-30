package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.StatisticsCalcViewModel;


import adapters.MainAdapter;


/**
 * Activity class for displaying a list of statistics calculations.
 * This class extends {@link AppCompatActivity}.
 * It uses {@link StatisticsCalcViewModel} for handling data and user interactions.
 */
public class StatisticsCalcListView extends AppCompatActivity {

    /**
     * The ViewModel responsible for handling data and interactions for this activity.
     */
    private StatisticsCalcViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_calc_list_view);

        initializeViews();

        viewModel = new StatisticsCalcViewModel();

        ListView statsListView = findViewById(R.id.stats_calc_list_view);
        String[] title = getResources().getStringArray(R.array.statistics_titles);

        MainAdapter adapter = new MainAdapter(StatisticsCalcListView.this, title);
        statsListView.setAdapter(adapter);

        statsListView.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    /**
     * Initializes the views in the activity, setting titles and applying gradient text.
     */
    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.statistics_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }
}