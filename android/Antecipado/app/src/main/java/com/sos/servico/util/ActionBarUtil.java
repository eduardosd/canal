package com.sos.servico.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sos.servico.R;

/**
 * Created by deivison on 6/9/15.
 */
public class ActionBarUtil {

    public static  void createActionBar(AppCompatActivity activity) {
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ImageView statusBar = (ImageView) activity.findViewById(R.id.statusBar);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(activity.getString(R.string.servicos));
        Resources.Theme theme = activity.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(it.neokree.googlenavigationdrawer.R.attr.colorPrimary, typedValue, true);
        int primaryColor = typedValue.data;
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
            statusBar.setImageDrawable(new ColorDrawable(ColorUtil.darkenColor(primaryColor)));

        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }
}
