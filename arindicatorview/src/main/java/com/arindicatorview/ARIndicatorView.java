package com.arindicatorview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.*;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.*;


public class ARIndicatorView extends IndicatorView {


    private RecyclerView recyclerView;
    private ViewPager viewPager;

    private int selectedPosition = 0;

    private boolean isScrubbing = false;

    public ARIndicatorView(Context context) {
        super(context);
    }

    public ARIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ARIndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void attachTo(RecyclerView recyclerView, boolean shouldPage) {
        this.recyclerView = recyclerView;

        addIndicators(recyclerView);

        if (shouldPage) {
            new PagerSnapHelper().attachToRecyclerView(recyclerView);
        }

        this.recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        int position;
                        if (dx > 0) {
                            position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                        } else {
                            position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                        }
                        if (position <= indicators.size() - 1) {
                            if (selectedPosition != position) {
                                selectIndicatorAt(position);
                                if (indicatorAnimation != 0) {
                                    animateIndicator(position);
                                }
                            }
                        }
                    }
                }

            }
        });
    }

    /**
     * Attach ARIndicator to ViewPager to know which is the current position in ViewPager and which indicator to be selected
     *
     * @param viewPager ViewPager to be attached to
     */
    public void attachTo(ViewPager viewPager) {
        this.viewPager = viewPager;

        this.addIndicators(viewPager);

        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                selectIndicatorAt(i);
                if (indicatorAnimation != 0) {
                    animateIndicator(i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private void addIndicators(RecyclerView recyclerView) {
        if (recyclerView != null) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    drawCircle(i);
                }
            } else {
                throw new NullPointerException("RecyclerView Adapter not found or null --> ARIndicatorView");
            }
        } else {
            throw new NullPointerException("RecyclerView is null --> ARIndicatorView");
        }
    }

    private void addIndicators(ViewPager viewPager) {
        if (viewPager != null) {
            PagerAdapter pagerAdapter = viewPager.getAdapter();
            if (pagerAdapter != null) {
                for (int i = 0; i < pagerAdapter.getCount(); i++) {
                    drawCircle(i);
                }
            } else {
                throw new NullPointerException("ViewPager Adapter is null --> ARIndicatorView");
            }
        } else {
            throw new NullPointerException("ViewPager is null --> ARIndicatorView");
        }
    }


    private void unSelectIndicators() {
        for (int i = 0; i < indicators.size(); i++) {
            this.setUnActiveColorTo(indicators.get(i));
        }
    }


    private void invalidateIndicators() {
        this.removeIndicators();
        if (recyclerView != null) {
            addIndicators(recyclerView);
        } else if (viewPager != null) {
            addIndicators(viewPager);
        }
        this.selectIndicatorAt(this.selectedPosition);
    }

    private void selectIndicatorAt(int position) {
        this.selectedPosition = position;
        this.unSelectIndicators();
        this.setActiveColorTo(this.indicators.get(this.selectedPosition));
    }

    /**
     * Sets the selection color of the indicators
     *
     * @param selectionColor Integer
     */
    public void setSelectionColor(@ColorInt int selectionColor) {
        this.selectionColor = selectionColor;
        this.invalidateIndicators();
    }

    /**
     * Current indicator selection color
     *
     * @return Integer
     */
    public int getSelectionColor() {
        return selectionColor;
    }

    /**
     * Change the indicators size.
     *
     * @param indicatorSize Integer
     */
    public void setIndicatorSize(int indicatorSize) {
        this.indicatorSize = indicatorSize;
        this.invalidateIndicators();
    }

    /**
     * Current indicator size
     *
     * @return Integer
     */
    public int getIndicatorSize() {
        return indicatorSize;
    }

    /**
     * The animation to play when an indicator is selected.
     *
     * @param indicatorAnimation AnimationId
     */
    public void setIndicatorAnimation(int indicatorAnimation) {
        this.indicatorAnimation = indicatorAnimation;
    }

    /**
     * Current indicator animation
     *
     * @return Integer
     */
    public int getIndicatorAnimation() {
        return indicatorAnimation;
    }

    /**
     * Sets the indicators shape.
     * <br>
     * You need to pass the drawable id from drawable res.
     *
     * @param indicatorShape DrawableId
     */
    public void setIndicatorShape(@DrawableRes int indicatorShape) {
        this.indicatorShape = indicatorShape;
        this.invalidateIndicators();
    }

    /**
     * Current indicator shape
     *
     * @return Integer
     */
    public int getIndicatorShape() {
        return indicatorShape;
    }

    /**
     * This is used for setting the color on the indicators when they are not selected
     *
     * @param indicatorColor The color to be set to indicators
     */
    public void setIndicatorColor(@ColorInt int indicatorColor) {
        this.indicatorColor = indicatorColor;
        this.invalidateIndicators();
    }

    /**
     * Current indicator color
     *
     * @return Integer
     */
    public int getIndicatorColor() {
        return indicatorColor;
    }

    /**
     * Selects the indicator at the given position.
     *
     * @param position Position to select
     */
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        this.unSelectIndicators();
        this.setActiveColorTo(this.indicators.get(this.selectedPosition));
        this.scrollToPosition(selectedPosition);
    }

    /**
     * Current position of indicator
     *
     * @return Integer
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Returns numberOfIndicators if you set it via the method setNumberOfDots else 0
     *
     * @return Integer
     */
    public int getNumberOfIndicators() {
        return numberOfIndicators;
    }

    /**
     * This is used for adding indicators if you don't want to be attached to RecyclerView or ViewPager
     *
     * @param numberOfIndicators Integer number of indicators to be added
     */
    public void setNumberOfIndicators(int numberOfIndicators) {
        this.numberOfIndicators = numberOfIndicators;
        if (!this.indicators.isEmpty()) {
            this.removeIndicators();
        }
        for (int i = 0; i < this.numberOfIndicators; i++) {
            drawCircle(i);
        }
    }

    /**
     * Removes all indicators
     */
    public void removeIndicators() {
        for (ImageView imageView : indicators) {
            removeView(imageView);
        }
        indicators.clear();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return this.isScrubbingEnabled;
        }
        this.selectIndicatorWhenScrubbing(ev);
        return this.isScrubbingEnabled;
    }

    private void selectIndicatorWhenScrubbing(@NonNull MotionEvent ev) {

        int x = Math.round(ev.getX());
        int y = Math.round(ev.getY());

        for (int i = 0; i < getChildCount(); i++) {
            ImageView child = (ImageView) getChildAt(i);
            if (x > child.getLeft() && x < child.getRight() && y > child.getTop() && y < child.getBottom()) {
                this.isScrubbing = true;
                this.selectIndicatorAt(i);
                this.scrollToPosition(i);
            }
        }
    }

    private void scrollToPosition(int position) {
        if (this.recyclerView != null) {
            this.selectedPosition = position;
            this.recyclerView.smoothScrollToPosition(position);
        } else if (this.viewPager != null) {
            this.selectedPosition = position;
            this.viewPager.setCurrentItem(position, true);
        }
    }

    private void animateIndicator(int position) {
        if (this.isScrubbingEnabled && this.isScrubbing) {
            if (this.shouldAnimateOnScrubbing) {
                indicators.get(position).startAnimation(AnimationUtils.loadAnimation(getContext(), indicatorAnimation));
            }
        } else {
            indicators.get(position).startAnimation(AnimationUtils.loadAnimation(getContext(), indicatorAnimation));
        }
    }
}
