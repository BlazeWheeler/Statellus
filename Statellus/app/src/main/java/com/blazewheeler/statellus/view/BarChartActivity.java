package com.blazewheeler.statellus.view;

import static com.blazewheeler.statellus.viewmodel.BarChartViewModel.errorHandling;
import static com.blazewheeler.statellus.viewmodel.BarChartViewModel.resetBarChartData;

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
import com.github.mikephil.charting.charts.BarChart;

/**
 * Activity class for displaying a bar chart and handling user interactions.
 */
public class BarChartActivity extends AppCompatActivity {

    /**
     * Initializes the activity views and sets up event listeners.
     *
     * - Sets the gradient text for activity title and description.
     * - Sets up the bar chart with a custom background.
     * - Handles user input for data visualization.
     *
     * @param savedInstanceState The saved instance state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        // Initialize views and set up event listeners
        initializeViews();
        BarChart barChart = findViewById(R.id.bar_chart);
        ImageView noDataIcon = findViewById(R.id.no_data_img);



        barChart.setNoDataText("");
        barChart.getLegend().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.invalidate();

        // Handle user input for data visualization
        EditText inputData = findViewById(R.id.sample_input);
        Button calculateButton = findViewById(R.id.calc_button);
        Button resetButton = findViewById(R.id.reset_button);

        calculateButton.setOnClickListener(view -> {
            errorHandling(inputData, barChart);
            if(inputData.getError() != null )
                noDataIcon.setVisibility(View.VISIBLE);
            else
                noDataIcon.setVisibility(View.INVISIBLE);
        });

        resetButton.setOnClickListener(v ->  {
            resetBarChartData(inputData, barChart);
            // Restore the alpha value of the image drawable
            noDataIcon.setVisibility(View.VISIBLE);
        });
    }

    /**
     * Initializes the views of the activity and sets gradient text for titles.
     */
    private void initializeViews() {
        // Set gradient text for activity title and description
        String activityTitle = getResources().getString(R.string.data_visualization_title);
        TextView activityTitleTextView = findViewById(R.id.activity_title);
        activityTitleTextView.setText(R.string.data_visualization_title);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.bar_chart_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(R.string.bar_chart_title);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");

        String cardTwoLabel = getResources().getString(R.string.enter_data_title);
        TextView enterDataTextView = findViewById(R.id.card_two_title);
        enterDataTextView.setText(R.string.enter_data_title);
        GradientTextUtil.applyGradientText(enterDataTextView, cardTwoLabel, "#EC3CAB", "#0B40C5");
    }
}
