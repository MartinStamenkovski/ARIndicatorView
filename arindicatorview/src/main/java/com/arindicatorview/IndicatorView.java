package com.arindicatorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class IndicatorView extends LinearLayout {

    protected int indicatorColor;
    protected int selectionColor;
    protected int numberOfIndicators;
    protected int indicatorSize;
    protected int indicatorAnimation;
    protected int indicatorShape;

    protected boolean shouldAnimateOnScrubbing;
    protected boolean isScrubbingEnabled;


    protected List<ImageView> indicators = new ArrayList<>();

    public IndicatorView(Context context) {
        super(context);
        init(null, 0);

    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);

    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);

    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.ARIndicatorView, defStyle, 0);

        this.indicatorColor = styledAttributes.getColor(R.styleable.ARIndicatorView_indicator_color, Color.LTGRAY);
        this.selectionColor = styledAttributes.getColor(R.styleable.ARIndicatorView_selected_color, Color.BLACK);
        this.numberOfIndicators = styledAttributes.getInteger(R.styleable.ARIndicatorView_number_of_indicators, 0);
        this.indicatorSize = styledAttributes.getInteger(R.styleable.ARIndicatorView_indicator_size, 10);
        this.indicatorAnimation = styledAttributes.getResourceId(R.styleable.ARIndicatorView_indicator_animation, 0);
        this.indicatorShape = styledAttributes.getResourceId(R.styleable.ARIndicatorView_indicator_shape, R.drawable.circle);
        this.isScrubbingEnabled = styledAttributes.getBoolean(R.styleable.ARIndicatorView_indicator_scrubbing, false);
        this.shouldAnimateOnScrubbing = styledAttributes.getBoolean(R.styleable.ARIndicatorView_animate_indicator_scrubbing, false);

        styledAttributes.recycle();

        if (isInEditMode()) {
            for (int i = 0; i < numberOfIndicators; i++) {
                drawCircle(i);
            }
        }

    }

    protected void drawCircle(int position) {

        ImageView imageView = new ImageView(getContext());
        imageView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        if (indicatorShape == 0) {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), indicatorShape));
        }
        this.setupColors(imageView, position);

        layoutParams.setMargins(10, 10, 10, 10);
        layoutParams.width = indicatorSize;
        layoutParams.height = indicatorSize;
        addView(imageView, layoutParams);
        indicators.add(imageView);
    }


    private void setupColors(ImageView imageView, int position) {
        if (position == 0) {
            this.setActiveColorTo(imageView);
        } else {
            this.setUnActiveColorTo(imageView);
        }
    }


    protected void setActiveColorTo(ImageView imageView) {
        imageView.setColorFilter(selectionColor);
        imageView.requestLayout();

    }

    protected void setUnActiveColorTo(ImageView imageView) {
        imageView.setColorFilter(indicatorColor);
        imageView.invalidate();
    }

    public boolean isShouldAnimateOnScrubbing() {
        return shouldAnimateOnScrubbing;
    }

    public void setShouldAnimateOnScrubbing(boolean shouldAnimateOnScrubbing) {
        this.shouldAnimateOnScrubbing = shouldAnimateOnScrubbing;
    }

    public boolean isScrubbingEnabled() {
        return isScrubbingEnabled;
    }

    public void setScrubbingEnabled(boolean scrubbingEnabled) {
        isScrubbingEnabled = scrubbingEnabled;
    }
}
