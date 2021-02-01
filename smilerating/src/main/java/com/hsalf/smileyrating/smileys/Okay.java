package com.hsalf.smileyrating.smileys;

import android.graphics.Color;

import com.hsalf.smileyrating.helper.Point;
import com.hsalf.smileyrating.smileys.base.Smiley;

public class Okay extends Smiley {

    public Okay() {
        super(-135, 360);
        createStraightSmile(new Point(CENTER_SMILE, MOUTH_CENTER_Y), (CENTER_SMILE * 0.1f), 360f, (CENTER_SMILE * 0.9f));
        setup(
                getClass().getSimpleName(),
                Color.parseColor("#FFFF3F"),
                Color.parseColor("#353431")
        );
    }

}
