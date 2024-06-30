package com.blazewheeler.statellus.utils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.widget.TextView;

/**
 * Utility class for applying gradient text to TextView objects.
 */
public class GradientTextUtil {

    /**
     * Applies gradient text to the provided TextView.
     *
     * @param textView   The TextView to which gradient text will be applied.
     * @param text       The text content to be displayed.
     * @param colorStart The starting color of the gradient.
     * @param colorEnd   The ending color of the gradient.
     */
    public static void applyGradientText(TextView textView, String text, String colorStart, String colorEnd) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(text);

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor(colorStart),
                        Color.parseColor(colorEnd),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }
}
