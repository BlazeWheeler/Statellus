package com.blazewheeler.statellus.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.blazewheeler.statellus.view.BarChartActivity;
import com.blazewheeler.statellus.view.BoxPlotActivity;
import com.blazewheeler.statellus.view.GraphingListView;
import com.blazewheeler.statellus.view.SimplePlotActivity;

public class GraphingViewModel extends GraphingListView {
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
                context.startActivity(new Intent(context, BoxPlotActivity.class));
                break;
            // Add more cases as needed
        }
    }
}
