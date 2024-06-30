package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;

import com.blazewheeler.statellus.viewmodel.HorizontalBarChartViewModel;
import com.github.mikephil.charting.charts.HorizontalBarChart;


/**
 * Activity class for displaying a Horizontal Bar Chart.
 */
public class HorizontalBarChartActivity extends AppCompatActivity {

    /**
     * Initializes the activity views and sets up the Horizontal Bar Chart.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_bar_chart);

        initializeViews();
        HorizontalBarChart horizontalBarChart = findViewById(R.id.horizontal_bar_chart);
        ImageView noDataIcon = findViewById(R.id.no_data_img);


        horizontalBarChart.setNoDataText("");
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setTouchEnabled(false);
        horizontalBarChart.invalidate();

        EditText inputData = findViewById(R.id.sample_input);
        Button calculateButton = findViewById(R.id.calc_button);
        Button resetButton = findViewById(R.id.reset_button);

        calculateButton.setOnClickListener(view -> {
            HorizontalBarChartViewModel.errorHandling(inputData, horizontalBarChart);
            noDataIcon.setVisibility(inputData.getError() != null ? View.VISIBLE : View.INVISIBLE);
        });

        resetButton.setOnClickListener(v -> {
            HorizontalBarChartViewModel.resetBarChartData(inputData, horizontalBarChart);
            noDataIcon.setVisibility(View.VISIBLE);
        });
    }

    /**
     * Initializes the activity views with appropriate text and gradient styles.
     */
    private void initializeViews() {
        TextView activityTitleTextView = findViewById(R.id.activity_title);
        activityTitleTextView.setText(R.string.data_visualization_title);
        GradientTextUtil.applyGradientText(activityTitleTextView, getString(R.string.data_visualization_title), "#EC3CAB", "#0B40C5");

        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(R.string.horizontal_bar_chart_title);
        GradientTextUtil.applyGradientText(activityDescTextView, getString(R.string.horizontal_bar_chart_title), "#EC3CAB", "#0B40C5");

        TextView enterDataTextView = findViewById(R.id.card_two_title);
        enterDataTextView.setText(R.string.enter_data_title);
        GradientTextUtil.applyGradientText(enterDataTextView, getString(R.string.enter_data_title), "#EC3CAB", "#0B40C5");
    }
}