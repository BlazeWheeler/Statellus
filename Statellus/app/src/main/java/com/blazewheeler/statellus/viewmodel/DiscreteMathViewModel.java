package com.blazewheeler.statellus.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.blazewheeler.statellus.view.BarChartActivity;
import com.blazewheeler.statellus.view.DiscreteMathListView;
import com.blazewheeler.statellus.view.FibonacciView;
import com.blazewheeler.statellus.view.NumberSequenceView;
import com.blazewheeler.statellus.view.TruthTableGeneratorView;
import com.blazewheeler.statellus.view.ZscoreView;

public class DiscreteMathViewModel extends DiscreteMathListView {
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

            case 3:
                context.startActivity(new Intent(context, FibonacciView.class));
                break;

            case 4:
                context.startActivity(new Intent(context, NumberSequenceView.class));
                break;

            case 5:
                context.startActivity(new Intent(context, ZscoreView.class));
                break;
            case 6:
                context.startActivity(new Intent(context, TruthTableGeneratorView.class));
                break;



            // Add more cases as needed
        }
    }
}
