package com.hsalf.smileyrating.smileys;

import android.animation.FloatEvaluator;
import android.graphics.Color;

import com.hsalf.smileyrating.helper.Point;
import com.hsalf.smileyrating.smileys.base.Smiley;

public class Better extends Smiley {

    public Better() {
        super(-135, 360);

        // Create MOUTH
        float div = 0.10f;
        FloatEvaluator f = new FloatEvaluator();
        createMirrorSmile(new Point(CENTER_SMILE, 0.7f),
                new Point(f.evaluate(div, CENTER_SMILE * 0.295, CENTER_SMILE), f.evaluate(div, MOUTH_CENTER_Y - (CENTER_SMILE * 0.23), MOUTH_CENTER_Y)),  // Top control
                new Point(f.evaluate(div, CENTER_SMILE * 0.295, CENTER_SMILE), f.evaluate(div, MOUTH_CENTER_Y - (CENTER_SMILE * 0.088), MOUTH_CENTER_Y)),  // Bottom control
                new Point(f.evaluate(div, CENTER_SMILE * 0.591, CENTER_SMILE), f.evaluate(div, MOUTH_CENTER_Y - (CENTER_SMILE * 0.23), MOUTH_CENTER_Y)), // Top Point
                new Point(f.evaluate(div, CENTER_SMILE * 0.591, CENTER_SMILE), f.evaluate(div, MOUTH_CENTER_Y + (CENTER_SMILE * 0.118), MOUTH_CENTER_Y)) // Bottom point
        );
        setup(
                getClass().getSimpleName(),
                Color.parseColor("#f2dd68"),
                Color.parseColor("#353431")
        );

    }


}
