package com.blazewheeler.statellus.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.VisualizeDataViewModel;

import adapters.VisualizeDataAdapter;

/**
 * Activity class for visualizing data in a list view.
 * This class extends {@link AppCompatActivity}.
 * It uses {@link VisualizeDataViewModel} for handling data and user interactions.
 */
public class VisualizeDataListView extends AppCompatActivity {

    /**
     * The ViewModel responsible for handling data and interactions for this activity.
     */
    private VisualizeDataViewModel viewModel;

    /**
     * Called when the activity is first created.
     * Initializes the views, sets up the ViewModel, and configures the list view with data.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in
     *                           {@link #onSaveInstanceState(Bundle)}. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_data_list_view);

        initializeViews();

        viewModel = new VisualizeDataViewModel();

        ListView visualizeDataList = findViewById(R.id.vis_data_list_view);
        String[] title = getResources().getStringArray(R.array.visualize_data_titles);

        VisualizeDataAdapter adapter = new VisualizeDataAdapter(VisualizeDataListView.this, title);
        visualizeDataList.setAdapter(adapter);

        visualizeDataList.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    /**
     * Initializes the views in the activity, setting titles and applying gradient text.
     */
    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.data_visualization_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }
}
