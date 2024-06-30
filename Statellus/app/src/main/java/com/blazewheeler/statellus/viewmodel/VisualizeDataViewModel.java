package com.blazewheeler.statellus.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.blazewheeler.statellus.view.BarChartActivity;
import com.blazewheeler.statellus.view.BoxPlotActivity;
import com.blazewheeler.statellus.view.HorizontalBarChartActivity;
import com.blazewheeler.statellus.view.OnionChartView;
import com.blazewheeler.statellus.view.PieChartActivity;
import com.blazewheeler.statellus.view.SimpleLineChartActivity;
import com.blazewheeler.statellus.view.StemAndLeafActivity;
import com.blazewheeler.statellus.view.VennDiagramActivity;
import com.blazewheeler.statellus.view.VisualizeDataListView;

/**
 * ViewModel class for handling data and user interactions related to visualizing data.
 * Extends {@link VisualizeDataListView}.
 */
public class VisualizeDataViewModel extends VisualizeDataListView {

    /**
     * Handles the click event for items in the list view.
     *
     * @param context  The context from which the click event originated.
     * @param position The position of the clicked item in the list view.
     */
    public void onListItemClicked(Context context, int position) {
        switch (position) {
            case 0:
                /* Handle item click for position 0 */
                context.startActivity(new Intent(context, BarChartActivity.class));
                break;
            case 1:
                /* Handle item click for position 1 */
                context.startActivity(new Intent(context, BoxPlotActivity.class));
                break;
            case 2:
                /* Handle item click for position 2 */
                context.startActivity(new Intent(context, HorizontalBarChartActivity.class));
                break;
            case 3:
                /* Handle item click for position 3 */
                context.startActivity(new Intent(context, StemAndLeafActivity.class));
                break;
            case 4:
                /* Handle item click for position 4 */
                context.startActivity(new Intent(context, PieChartActivity.class));
                break;
            case 5:
                /* Handle item click for position 5 */
                context.startActivity(new Intent(context, OnionChartView.class));
                break;
            case 6:
                /* Handle item click for position 6 */
                context.startActivity(new Intent(context, VennDiagramActivity.class));
                break;
            case 7:
                /* Handle item click for position 7 */
                context.startActivity(new Intent(context, SimpleLineChartActivity.class));
                break;
        }
    }
}
