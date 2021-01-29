package com.example.smilyrating;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.hsalf.smileyrating.smileys.Bad;
import com.hsalf.smileyrating.smileys.Good;
import com.hsalf.smileyrating.smileys.Great;
import com.hsalf.smileyrating.smileys.Okay;
import com.hsalf.smileyrating.smileys.Terrible;
import com.hsalf.smileyrating.smileys.base.Smiley;

public class SmileyView extends View {

    private final ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    private final Path mPath = new Path();

    private final Smiley mBad = new Bad();
    private final Smiley mGood = new Good();
    private final Smiley mOkay = new Okay();
    private final Smiley mGreat = new Great();
    private final Smiley mTerrible = new Terrible();

    private int mCurrentSmileyIndex = 0;

    private final Smiley[] mSmileys = new Smiley[]{
            mGreat, mGood, mOkay, mBad, mTerrible
    };

    private final Paint mPathPaint = new Paint();
    private final Paint mFacePaint = new Paint();

    private final ValueAnimator mAnimator = new ValueAnimator();

    public SmileyView(Context context) {
        super(context);
        init();
    }

    public SmileyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmileyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mAnimator.setFloatValues(0f, 1f);
        mAnimator.setDuration(250);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawSmiley(animation.getAnimatedFraction());
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                to = null;
                from = null;
            }
        });

        mPathPaint.setAntiAlias(true);
        mPathPaint.setStyle(Paint.Style.FILL);

        mFacePaint.setAntiAlias(true);
        mFacePaint.setStyle(Paint.Style.FILL);

        drawSmiley(0, mSmileys[0], mSmileys[1]);
    }

    final OnSwipeTouchListener mSwipeListener = new OnSwipeTouchListener(getContext()) {
        @Override
        public void onSwipeLeft() {
            super.onSwipeLeft();
            setSmiley(mCurrentSmileyIndex, mCurrentSmileyIndex + 1);
        }

        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
            setSmiley(mCurrentSmileyIndex, mCurrentSmileyIndex - 1);
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mSwipeListener.onTouch(this, event);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (widthMeasureSpec < heightMeasureSpec) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        } else {
            setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
        }
        drawSmiley(0, mSmileys[0], mSmileys[1]);
        setScaleForSmiley();
    }

    private void setScaleForSmiley() {
        for (Smiley smiley : mSmileys) {
            smiley.scale(getMeasuredWidth());
        }
    }

    private Smiley to = null;
    private Smiley from = null;

    private void setSmiley(int from, int to) {
        if (to < 0 || to >= mSmileys.length) {
            return;
        }
        if (mAnimator.isRunning()) {
            return;
        }
        this.to = mSmileys[to];
        this.from = mSmileys[from];
        mCurrentSmileyIndex = to;
        mAnimator.start();

    }

    private void drawSmiley(float fraction) {
        drawSmiley(fraction, from, to);
    }

    private void drawSmiley(float fraction, Smiley from, Smiley to) {
        from.drawFace(to, mPath, fraction);

        mPathPaint.setColor(from.getDrawingColor());

        mFacePaint.setColor((Integer) mArgbEvaluator.evaluate(fraction,
                from.getFaceColor(), to.getFaceColor()));

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f,
                getWidth() / 2f, mFacePaint);
        canvas.drawPath(mPath, mPathPaint);
    }
}
