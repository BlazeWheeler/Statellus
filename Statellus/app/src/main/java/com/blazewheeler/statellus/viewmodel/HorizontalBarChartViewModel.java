package com.blazewheeler.statellus.viewmodel;


import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.blazewheeler.statellus.view.HorizontalBarChartActivity;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.HorizontalBarChart;

/**
 * ViewModel class for handling data processing and UI updates for a Horizontal Bar Chart.
 */
public class HorizontalBarChartViewModel extends HorizontalBarChartActivity {

    /**
     * Handles input validation and processing for the provided data, updating UI elements accordingly.
     *
     * @param inputData The EditText for user input data.
     * @param barChart  The HorizontalBarChart instance to display the data.
     */
    public static void errorHandling(EditText inputData, HorizontalBarChart barChart) {
        String dataString = inputData.getText().toString();
        ArrayList<Float> userInputData = new ArrayList<>();

        try {
            if (TextUtils.isEmpty(dataString)) {
                inputData.setError("Enter Values");
            } else {
                ArrayList<String> separatedValues = new ArrayList<>(Arrays.asList(dataString.split(",")));

                for (String value : separatedValues) {
                    userInputData.add(Float.valueOf(value));
                }

                if (userInputData.isEmpty()) {
                    inputData.setError("Enter at Least 1 value.");
                } else if (userInputData.size() > 30) {
                    inputData.setError("Enter values <= 30.");
                } else {
                    Collections.sort(userInputData);
                    showHorizontalBarChart(userInputData, barChart);
                }
            }
        } catch (NumberFormatException e) {
            inputData.setError("Enter only comma separated numeric values ");
        }
    }

    /**
     * Resets the data and UI elements associated with the Horizontal Bar Chart.
     *
     * @param inputData The EditText for user input data.
     * @param barChart  The HorizontalBarChart instance to reset.
     */
    public static void resetBarChartData(EditText inputData, HorizontalBarChart barChart) {
        inputData.getText().clear();
        barChart.clear();
        barChart.invalidate();
    }

    /**
     * Displays the Horizontal Bar Chart with the provided data.
     *
     * @param data      The list of data entries.
     * @param barChart  The HorizontalBarChart instance to display the data.
     */
    public static void showHorizontalBarChart(ArrayList<Float> data, HorizontalBarChart barChart) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new BarEntry(i, data.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Data");
        dataSet.setValueTextSize(12f); // Set label text size
        dataSet.setValueTextColor(Color.BLACK); // Set label text color

        // Define start and end colors for the gradient
        int startColor = Color.parseColor("#0B40C5");
        int endColor = Color.parseColor("#EC3CAB");

        // Create a LinearGradient with the specified start and end colors
        Shader shader = new LinearGradient(0, 0, 0, barChart.getHeight(), startColor, endColor, Shader.TileMode.CLAMP);

        // Apply the shader to the paint render of the bar chart's renderer
        barChart.getRenderer().getPaintRender().setShader(shader);

        BarData barData = new BarData(dataSet);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawBorders(false);
        barChart.setPadding(0, 100, 0, 32); // Add 32 pixels padding from the top and bottom

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setEnabled(false);

        barChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART); // Position the labels outside the chart
        barChart.getAxisLeft().setTextColor(Color.BLACK); // Set label text color
        barChart.getAxisLeft().setTextSize(15f); // Set label text size
        barChart.getAxisLeft().setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);

        barChart.animateY(1000);
        barChart.invalidate();
    }
}