package com.blazewheeler.statellus.view;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.utils.GradientTextUtil;
import com.blazewheeler.statellus.viewmodel.CalculusViewModel;

import adapters.CalculusAdapter;

public class CalculusListView extends AppCompatActivity {

    private CalculusViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculus_list_view);

        initializeViews();
        viewModel = new CalculusViewModel();
        ListView calculusListView;
        String[] title;

        calculusListView = findViewById(R.id.calculus_list_view);
        title = getResources().getStringArray(R.array.calculus_titles);

        CalculusAdapter adapter = new CalculusAdapter(CalculusListView.this, title);
        calculusListView.setAdapter(adapter);

        calculusListView.setOnItemClickListener((adapterView, view, i, l) -> viewModel.onListItemClicked(this, i));
    }

    private void initializeViews() {
        String activityTitle = getResources().getString(R.string.app_name);
        TextView activityTitleTextView = findViewById(R.id.app_name);
        activityTitleTextView.setText(activityTitle);
        GradientTextUtil.applyGradientText(activityTitleTextView, activityTitle, "#EC3CAB", "#0B40C5");

        String activityDesc = getResources().getString(R.string.calculus_title);
        TextView activityDescTextView = findViewById(R.id.activity_desc);
        activityDescTextView.setText(activityDesc);
        GradientTextUtil.applyGradientText(activityDescTextView, activityDesc, "#EC3CAB", "#0B40C5");
    }



}