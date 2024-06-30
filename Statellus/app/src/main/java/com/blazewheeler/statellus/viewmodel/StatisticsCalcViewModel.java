package com.blazewheeler.statellus.viewmodel;

import android.content.Context;
import android.content.Intent;


import com.blazewheeler.statellus.view.MeasureOfCentralTendencyActivity;
import com.blazewheeler.statellus.view.MeasuresOfDataSpreadActivity;

import com.blazewheeler.statellus.view.SampleSizeCalculatorView;
import com.blazewheeler.statellus.view.StatisticsCalcListView;
import com.blazewheeler.statellus.view.SetOperationsActivity;
import com.blazewheeler.statellus.view.ZscoreView;

/**
 * ViewModel class for handling data and user interactions related to the StatisticsCalcListView activity.
 * Extends {@link StatisticsCalcListView}.
 */
public class StatisticsCalcViewModel extends StatisticsCalcListView {

    /**
     * Handles the item click event for the statistics list view.
     *
     * @param context  The context from which the click event originated.
     * @param position The position of the clicked item in the list view.
     */
    public void onListItemClicked(Context context, int position) {
        switch (position) {
            case 0:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, MeasureOfCentralTendencyActivity.class));
                break;
            case 1:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, MeasuresOfDataSpreadActivity.class));
                break;

            case 2:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, ZscoreView.class));
                break;

            case 3:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, SampleSizeCalculatorView.class));
                break;

            case 4:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, SetOperationsActivity.class));
                break;
        }
    }
}
