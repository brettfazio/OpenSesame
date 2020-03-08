package com.example.juleeyahwright.opensesame.Common;

import com.example.juleeyahwright.opensesame.Common.Constant;
import com.example.juleeyahwright.opensesame.R;

public class Theme {

    // this method uses preset ARGB values taken from the ColorPicker library listed above
    // to apply custom themes set up in styles.xml using these colors
    public static void setColorTheme() {
        switch (Constant.color) {
            case (0xffF44336): // red
                Constant.appTheme = R.style.AppTheme_red;
                break;
            case (0xffE91E63): // pink
                Constant.appTheme = R.style.AppTheme_pink;
                break;
            case (0xff9C27B0): // purple
                Constant.appTheme = R.style.AppTheme_purple;
                break;
            case (0xff673AB7): // deep purple
                Constant.appTheme = R.style.AppTheme_deep_purple;
                break;
            case (0xff3F51B5): // indigo
                Constant.appTheme = R.style.AppTheme_indigo;
                break;
            case (0xff2196F3): // blue
                Constant.appTheme = R.style.AppTheme_blue;
                break;
            case (0xff03A9F4): // light blue
                Constant.appTheme = R.style.AppTheme_light_blue;
                break;
            case (0xff00BCD4): // cyan
                Constant.appTheme = R.style.AppTheme_cyan;
                break;
            case (0xff009688): // teal
                Constant.appTheme = R.style.AppTheme_teal;
                break;
            case (0xff4CAF50): // green
                Constant.appTheme = R.style.AppTheme_green;
                break;
            case (0xff8BC34A): // light green
                Constant.appTheme = R.style.AppTheme_light_green;
                break;
            case (0xffCDDC39): // lime
                Constant.appTheme = R.style.AppTheme_lime;
                break;
            case (0xffFFEB3B): // yellow
                Constant.appTheme = R.style.AppTheme_yellow;
                break;
            case (0xffFFC107): // amber
                Constant.appTheme = R.style.AppTheme_amber;
                break;
            case (0xffFF9800): // orange
                Constant.appTheme = R.style.AppTheme_orange;
                break;
            case (0xffFF5722): // deep orange
                Constant.appTheme = R.style.AppTheme_deep_orange;
                break;
            case (0xff795548): // brown
                Constant.appTheme = R.style.AppTheme_brown;
                break;
            case (0xff9E9E9E): // grey
                Constant.appTheme = R.style.AppTheme_gray;
                break;
            case (0xff607D8B): // blue gray
                Constant.appTheme = R.style.AppTheme_blue_gray;
                break;
            case (0xff000000): // black
                Constant.appTheme = R.style.AppTheme_black;
                break;
            case (0xffffffff): // white
                Constant.appTheme = R.style.AppTheme_white;
                break;
        }
    }

    public static int getColorTheme() {
        return Constant.appTheme;
    }

}
