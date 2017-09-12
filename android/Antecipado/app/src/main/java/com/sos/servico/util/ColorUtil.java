package com.sos.servico.util;

import android.graphics.Color;

/**
 * Created by deivison on 6/9/15.
 */
public class ColorUtil {

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}
