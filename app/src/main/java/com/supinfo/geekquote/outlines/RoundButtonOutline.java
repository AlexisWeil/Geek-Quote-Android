package com.supinfo.geekquote.outlines;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.supinfo.geekquote.R;

/**
 * Created by alexis on 02/12/14.
 */
public class RoundButtonOutline extends ViewOutlineProvider {

    int size;

    public RoundButtonOutline(int size) {
        super();
        this.size = size;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        outline.setOval(0, 0, size, size);
        view.setOutlineProvider(this);
    }
}
