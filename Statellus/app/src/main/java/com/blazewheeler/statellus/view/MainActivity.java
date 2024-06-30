package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.MainActivityViewModel;


/**
 * The main activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    /** The view model for the main activity. */
    public MainActivityViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mainViewModel = new MainActivityViewModel();

        initializeViews();
        setupCardListeners();
    }

    /**
     * Initializes the views of the activity.
     * Sets the title and description texts and applies gradient text effects.
     */
    private void initializeViews() {
        String appTitle = getResources().getString(R.string.app_name);
        TextView appTitleTextView = findViewById(R.id.app_name);
        appTitleTextView.setText(appTitle);



        String appDesc = getResources().getString(R.string.app_desc);
        TextView appDescTextView = findViewById(R.id.app_desc);
        appDescTextView.setText(appDesc);

        GradientTextUtil.applyGradientText(appTitleTextView, appTitle, "#EC3CAB", "#0B40C5");
        GradientTextUtil.applyGradientText(appDescTextView, appDesc, "#EC3CAB", "#0B40C5");
    }

    /**
     * Sets up click listeners for the card views.
     * Each card view has its own click listener handling different actions.
     */
    private void setupCardListeners() {
        CardView statsCalcCard = findViewById(R.id.stats_card);
        CardView calculusCard = findViewById(R.id.calculus_card);
        CardView probabilityCard = findViewById(R.id.probability_card);
        CardView graphCardView = findViewById(R.id.graphing_card);
        CardView visualizeDataCardView = findViewById(R.id.visualize_data_card);
        CardView discreteMathCardView = findViewById(R.id.discrete_math_card);

        statsCalcCard.setOnClickListener(v -> mainViewModel.onStatisticsCardClicked(this));
        probabilityCard.setOnClickListener(v -> mainViewModel.onProbabilityCardClicked(this));
        discreteMathCardView.setOnClickListener(v -> mainViewModel.onDiscreteMathCardClicked(this));
        visualizeDataCardView.setOnClickListener(v -> mainViewModel.onVisualizeDataCardClicked(this));
        calculusCard.setOnClickListener(view -> mainViewModel.onCalculusCardClicked(this));
        graphCardView.setOnClickListener(view -> mainViewModel.onGraphingCardClicked(this));
    }
}
