package com.blazewheeler.statellus.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.renderer.LineChartRenderer


/**
 * class extends LineChartRenderer to customize the appearance of the line chart.
 *
 * @param chart The LineChart instance to which this renderer belongs.
 * @param animator The animator responsible for animating chart transitions.
 * @param viewPortHandler The ViewPortHandler that handles chart's viewport operations.
 */

class CustomLineChartRenderer(
    chart: LineChart,
    animator: com.github.mikephil.charting.animation.ChartAnimator,
    viewPortHandler: com.github.mikephil.charting.utils.ViewPortHandler
) : LineChartRenderer(chart, animator, viewPortHandler) {

    /**
     * Override the drawLinear method to customize the drawing of lines with gradient colors.
     *
     * @param canvas The Canvas object on which to draw.
     * @param dataSet The line data set to be drawn.
     */
    override fun drawLinear(canvas: Canvas, dataSet: com.github.mikephil.charting.interfaces.datasets.ILineDataSet) {

        // Create a gradient color for the line
        val colors = intArrayOf(Color.parseColor("#EC3CAB"), Color.parseColor("#0B40C5")) // Set gradient colors
        val shader = LinearGradient(
            0f, 0f, 0f, mViewPortHandler.chartHeight,
            colors, null, Shader.TileMode.CLAMP
        )
        mRenderPaint.shader = shader // Apply gradient color to the line

        super.drawLinear(canvas, dataSet)
    }
}
