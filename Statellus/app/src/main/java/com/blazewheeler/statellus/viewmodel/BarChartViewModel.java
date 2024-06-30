package com.blazewheeler.statellus.viewmodel;



import android.graphics.Color;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

public class BarChartViewModel {

    /**
     * Handles input validation and processing for the provided data, updating UI elements accordingly.
     *
     * @param inputData The EditText for user input data.

     * @param barChart  The BarChart instance to display the data.
     */
    public static void errorHandling(EditText inputData, BarChart barChart) {
        /*
          Retrieves the input data as a string and initializes an ArrayList for user input.
         */
        String dataString = inputData.getText().toString();
        ArrayList<Float> userInputData = new ArrayList<>();

        try {
            /*
              Validates if the input data is empty, displaying an error message if necessary.
             */
            if (TextUtils.isEmpty(inputData.getText().toString())) {
                inputData.setError("Enter Values");
            } else {
                /*
                  Splits the input data into separate values and populates the userInputData ArrayList.
                 */
                ArrayList<String> separatedValues = new ArrayList<>(Arrays.asList(dataString.split(",")));

                for (int i = 0; i < separatedValues.size(); i++) {
                    userInputData.add(Float.valueOf(separatedValues.get(i)));
                }

                /*
                  Validates the size of userInputData, displaying an error message if necessary.
                 */
                if (userInputData.size() < 1) {
                    inputData.setError("Enter at Least 1 value.");
                    userInputData.clear();
                } else if (userInputData.size() > 30) {
                    inputData.setError("Enter values <= 30.");
                } else {
                    /*
                      Processes the data, calculates statistics, and updates UI elements.
                     */

                    Collections.sort(userInputData);

                    //inputData.getText().clear();
                    showBarChart(userInputData, barChart);

                    userInputData.clear();
                }
            }
        } catch (NumberFormatException e) {
            // Handles NumberFormatException, displaying an error message if non-numeric values are entered.
            inputData.setError("Enter only comma separated numeric values ");
            userInputData.clear();
        }
    }

    /**
     * Resets the data and UI elements associated with the BarChart.
     *
     * @param inputData The EditText for user input data.
     * @param barChart  The BarChart instance to reset.
     */
    public static void resetBarChartData(EditText inputData,  BarChart barChart) {

        /*
          Clears the input data EditText, BarChart, and invalidates the chart.
         */
        inputData.getText().clear();
        barChart.clear();
        barChart.invalidate();
    }

    /**
     * Displays a bar chart with the provided data on the given BarChart.
     *
     * @param data      The ArrayList of Float values representing the data for the bars.
     * @param barChart  The BarChart instance to display the data.
     */
    public static void showBarChart(ArrayList<Float> data, BarChart barChart) {
        /*
          Creates BarEntries from the provided data and adds them to the chart dataset.
         */
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            entries.add(new BarEntry(i, data.get(i)));
        }



        /*
          Creates a BarDataSet with the entries and sets the color for the bars.
         */
        BarDataSet dataSet = new BarDataSet(entries, "Data");

        dataSet.setGradientColor(Color.parseColor("#EC3CAB"), Color.parseColor("#0B40C5")); // Set color for the bars

        /*
          Creates a BarData object with the dataset.
         */
        BarData barData = new BarData(dataSet);

        // Sets the provided BarData to the BarChart and customizes its appearance.
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false); // Hide description

        barChart.setDrawBorders(false); // Hide chart borders

        /*
          Customizes the X-axis of the BarChart.
         */
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // Place X-axis at the bottom
        barChart.getXAxis().setDrawGridLines(false); // Hide vertical grid lines
        barChart.getXAxis().setEnabled(false);

        /*
          Customizes the left Y-axis of the BarChart.
         */
        barChart.getAxisLeft().setDrawGridLines(false); // Hide horizontal grid lines

        /*
          Disables the right Y-axis of the BarChart.
         */
        barChart.getAxisRight().setEnabled(false);

        barChart.animateY(1000); // Add animation
        barChart.invalidate(); // Refresh chart
    }





}
