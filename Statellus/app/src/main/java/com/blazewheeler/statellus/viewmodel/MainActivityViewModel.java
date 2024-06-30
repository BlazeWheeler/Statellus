package com.blazewheeler.statellus.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.blazewheeler.statellus.view.DiscreteMathListView;
import com.blazewheeler.statellus.view.GraphingListView;
import com.blazewheeler.statellus.view.ProbabilityListView;
import com.blazewheeler.statellus.view.StatisticsCalcListView;
import com.blazewheeler.statellus.view.VisualizeDataListView;
import com.blazewheeler.statellus.view.CalculusListView;


/**
 * ViewModel for MainActivity responsible for handling click events and navigation.
 */
public class MainActivityViewModel extends ViewModel {

    /**
     * Opens the ProbabilityListView activity when the probability card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onProbabilityCardClicked(Context context) {
        context.startActivity(new Intent(context, ProbabilityListView.class));
    }

    /**
     * Opens the StatisticsCalcListView activity when the statistics card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onStatisticsCardClicked(Context context) {
        context.startActivity(new Intent(context, StatisticsCalcListView.class));
    }

    /**
     * Opens the VisualizeDataListView activity when the visualize data card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onVisualizeDataCardClicked(Context context) {
        context.startActivity(new Intent(context, VisualizeDataListView.class));
    }

    /**
     * Opens the DiscreteMathListView activity when the discrete math card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onDiscreteMathCardClicked(Context context) {
        context.startActivity(new Intent(context, DiscreteMathListView.class));
    }

    /**
     * Opens the CalculusListView activity when the calculus card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onCalculusCardClicked(Context context) {
        context.startActivity(new Intent(context, CalculusListView.class));
    }

    /**
     * Opens the GraphingListView activity when the graphing card is clicked.
     *
     * @param context The context from which the intent will be started.
     */
    public void onGraphingCardClicked(Context context) {
        context.startActivity(new Intent(context, GraphingListView.class));
    }

}




