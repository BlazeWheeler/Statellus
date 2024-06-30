/**
 * This package contains utility classes used in the Statellus application.
 */
package com.blazewheeler.statellus.utils

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.renderer.PieChartRenderer
import kotlin.math.cos
import kotlin.math.sin

/**
 * CustomPieChartRenderer extends PieChartRenderer to provide custom rendering for the pie chart slices.
 * It draws lines from the center of the pie chart to the edges of the slices and fills the slices with gradients.
 */
class CustomPieChartRenderer
/**
 * Constructor for CustomPieChartRenderer.
 *
 * @param pieChart The PieChart instance to render.
 */
    (pieChart: PieChart) : PieChartRenderer(pieChart, pieChart.animator, pieChart.viewPortHandler) {
    /**
     * Draws the data set of the pie chart with custom rendering.
     *
     * @param c       The Canvas object to draw on.
     * @param dataSet The data set to be drawn.
     */
    override fun drawDataSet(c: Canvas, dataSet: IPieDataSet) {
        val rotationAngle = mChart.rotationAngle
        val radius = mChart.radius
        val drawAngles = mChart.drawAngles
        var angle = rotationAngle
        for (j in 0 until dataSet.entryCount) {
            val sliceAngle = drawAngles[j]

            // Calculate start and end angles for the slice
            val startAngle = angle
            val endAngle = angle + sliceAngle

            // Calculate the midpoint angle of the slice
            val midAngle = (startAngle + endAngle) / 2

            // Calculate coordinates of the line start point (center of the pie chart)
            val startX = mChart.centerCircleBox.x
            val startY = mChart.centerCircleBox.y

            // Calculate coordinates of the line end point (edge of the pie slice)
            val endX = (startX + radius * cos(Math.toRadians(midAngle.toDouble()))).toFloat()
            val endY = (startY + radius * sin(Math.toRadians(midAngle.toDouble()))).toFloat()

            // Draw the line
            c.drawLine(startX, startY, endX, endY, mRenderPaint)

            // Set the shader for gradient fill
            val gradient = RadialGradient(
                startX, startY, radius,
                dataSet.getColor(j * 2),  // Start color of the gradient
                dataSet.getColor(j * 2 + 1),  // End color of the gradient
                Shader.TileMode.MIRROR
            )
            mRenderPaint.setShader(gradient)

            // Draw the filled pie slice
            val slicePath = Path()
            slicePath.moveTo(startX, startY)
            slicePath.arcTo(
                RectF(startX - radius, startY - radius, startX + radius, startY + radius),
                startAngle, sliceAngle
            )
            slicePath.close()
            c.drawPath(slicePath, mRenderPaint)

            // Reset the shader
            mRenderPaint.setShader(null)

            // Increment angle for the next slice
            angle += sliceAngle
        }
    }
}
