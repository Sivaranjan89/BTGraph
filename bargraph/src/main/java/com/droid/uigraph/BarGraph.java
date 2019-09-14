package com.droid.uigraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class BarGraph extends LinearLayout {


    private final Context mContext;
    private boolean singleBar = true;
    private int orientation = 0;
    private float totalWidth = DroidFunctions.dpToPx(100);
    private float totalHeight = DroidFunctions.dpToPx(10);
    private boolean shouldAnimate = false;
    private boolean shouldBounce = false;
    private int animationDuration = 500;
    private float radius = 0;
    private boolean showLegend = false;
    private float legendTextSize = DroidFunctions.dpToPx(10);
    private int legendTextColor = Color.BLACK;
    private int legendTextStyle = 1;
    private float legendIconSize = DroidFunctions.dpToPx(10);
    private boolean showLegendPercentage = true;

    ArrayList<Float> values = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();
    ArrayList<String> legend = new ArrayList<>();

    public BarGraph(Context context) {
        super(context);
        mContext = context;
        invalidateComponent();
    }

    public BarGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        //Retrieve Values
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BarGraph);
        singleBar = ta.getBoolean(R.styleable.BarGraph_singleBar, true);
        orientation = ta.getInt(R.styleable.BarGraph_graphOrientation, 0);
        totalWidth = ta.getDimension(R.styleable.BarGraph_barWidth, DroidFunctions.dpToPx(100));
        totalHeight = ta.getDimension(R.styleable.BarGraph_barHeight, DroidFunctions.dpToPx(10));
        shouldAnimate = ta.getBoolean(R.styleable.BarGraph_shouldAnimate, false);
        shouldBounce = ta.getBoolean(R.styleable.BarGraph_shouldBounceAnimate, false);
        animationDuration = ta.getInt(R.styleable.BarGraph_animationDuration, 500);
        radius = ta.getDimension(R.styleable.BarGraph_barRadius, 0);
        showLegend = ta.getBoolean(R.styleable.BarGraph_showLegend, false);
        legendTextSize = ta.getDimension(R.styleable.BarGraph_legendTextSize, DroidFunctions.dpToPx(10));
        legendTextColor = ta.getColor(R.styleable.BarGraph_legendTextColor, Color.BLACK);
        legendTextStyle = ta.getInt(R.styleable.BarGraph_legendTextStyle, 1);
        legendIconSize = ta.getDimension(R.styleable.BarGraph_legendIconSize, DroidFunctions.dpToPx(10));
        showLegendPercentage = ta.getBoolean(R.styleable.BarGraph_showLegendPercentage, true);

        ta.recycle();

        invalidateComponent();
    }

    private void invalidateComponent() {
        this.removeAllViews();

        if (singleBar && orientation == 0) {
            renderSingleBarHorizontal();
        } else if (!singleBar && orientation == 0) {
            renderMultiBarHorizontal();
        } else if (singleBar && orientation == 1) {
            renderSingleBarVertical();
        } else if (!singleBar && orientation == 1) {
            renderMultiBarVertical();
        }
    }

    private void renderMultiBarVertical() {
        LinearLayout parent = new LinearLayout(mContext);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        LinearLayout main = new LinearLayout(mContext);
        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        float totalValue = 0;
        for (int i = 0; i < values.size(); i++) {
            totalValue = totalValue + values.get(i);
        }

        ArrayList<Float> percentage = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            percentage.add((values.get(i) * 100) / totalValue);
        }

        for (int i = 0; i < values.size(); i++) {
            float viewHeight = (percentage.get(i) * totalHeight) / 100;
            CardView view = new CardView(mContext);
            view.setCardBackgroundColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) totalWidth, (int) viewHeight);
            if (i != 0) {
                params.setMargins((int) DroidFunctions.dpToPx(10), 0, 0, 0);
            }
            view.setLayoutParams(params);
            view.setRadius(radius);
            view.setCardBackgroundColor(colors.get(i));
            main.addView(view);
        }

        main.setRotation(180);

        parent.addView(main);

        if (showLegend) {
            GridView grid = new GridView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) DroidFunctions.dpToPx(10), 0, 0);
            grid.setLayoutParams(params);
            grid.setNumColumns(1);
            grid.setAdapter(new LegendAdapter(mContext, colors, legend, percentage, legendIconSize, legendTextColor, legendTextSize,
                    legendTextStyle, showLegendPercentage));

            parent.addView(grid);
        }

        addView(parent);

        if (shouldAnimate || shouldBounce) {
            ScaleAnimation animation = new ScaleAnimation(1, 1, 0, 1);
            animation.setDuration(animationDuration);
            if (shouldBounce) {
                animation.setInterpolator(new BounceInterpolator());
            } else {
                animation.setInterpolator(new AccelerateInterpolator());
            }
            main.startAnimation(animation);
        }
    }

    private void renderSingleBarVertical() {
        LinearLayout parent = new LinearLayout(mContext);
        parent.setOrientation(LinearLayout.HORIZONTAL);
        parent.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, (int) totalHeight));

        CardView card = new CardView(mContext);
        card.setRadius(radius);
        card.setCardBackgroundColor(Color.TRANSPARENT);
        card.setLayoutParams(new LayoutParams((int) totalWidth, (int) totalHeight));

        LinearLayout main = new LinearLayout(mContext);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setLayoutParams(new LayoutParams((int) totalWidth, (int) totalHeight));

        float totalValue = 0;
        for (int i = 0; i < values.size(); i++) {
            totalValue = totalValue + values.get(i);
        }

        ArrayList<Float> percentage = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            percentage.add((values.get(i) * 100) / totalValue);
        }

        for (int i = 0; i < values.size(); i++) {
            float viewHeight = (percentage.get(i) * totalHeight) / 100;
            View view = new View(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) totalWidth, (int) viewHeight);
            view.setLayoutParams(params);
            view.setBackgroundColor(colors.get(i));
            main.addView(view);
        }

        card.addView(main);

        parent.addView(card);

        if (showLegend) {
            GridView grid = new GridView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins((int) DroidFunctions.dpToPx(10), 0, 0, 0);
            grid.setLayoutParams(params);
            grid.setNumColumns(1);
            grid.setAdapter(new LegendAdapter(mContext, colors, legend, percentage, legendIconSize, legendTextColor, legendTextSize,
                    legendTextStyle, showLegendPercentage));

            parent.addView(grid);
        }

        addView(parent);

        if (shouldAnimate || shouldBounce) {
            ScaleAnimation animation = new ScaleAnimation(1, 1, 0, 1);
            animation.setDuration(animationDuration);
            if (shouldBounce) {
                animation.setInterpolator(new BounceInterpolator());
            } else {
                animation.setInterpolator(new AccelerateInterpolator());
            }
            card.startAnimation(animation);
        }
    }

    private void renderMultiBarHorizontal() {
        LinearLayout parent = new LinearLayout(mContext);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LayoutParams((int) totalWidth, LayoutParams.WRAP_CONTENT));

        LinearLayout main = new LinearLayout(mContext);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setLayoutParams(new LayoutParams((int) totalWidth, LayoutParams.WRAP_CONTENT));

        float totalValue = 0;
        for (int i = 0; i < values.size(); i++) {
            totalValue = totalValue + values.get(i);
        }

        ArrayList<Float> percentage = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            percentage.add((values.get(i) * 100) / totalValue);
        }

        for (int i = 0; i < values.size(); i++) {
            float viewWidth = (percentage.get(i) * totalWidth) / 100;
            CardView view = new CardView(mContext);
            view.setCardBackgroundColor(Color.TRANSPARENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) viewWidth, (int) totalHeight);
            if (i != 0) {
                params.setMargins(0, (int) DroidFunctions.dpToPx(10), 0, 0);
            }
            view.setLayoutParams(params);
            view.setRadius(radius);
            view.setCardBackgroundColor(colors.get(i));
            main.addView(view);
        }

        parent.addView(main);

        if (showLegend) {
            GridView grid = new GridView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) DroidFunctions.dpToPx(10), 0, 0);
            grid.setLayoutParams(params);
            grid.setNumColumns(2);
            grid.setAdapter(new LegendAdapter(mContext, colors, legend, percentage, legendIconSize, legendTextColor, legendTextSize,
                    legendTextStyle, showLegendPercentage));

            parent.addView(grid);
        }

        addView(parent);

        if (shouldAnimate || shouldBounce) {
            ResizeAnimation animation = new ResizeAnimation(main);
            animation.setDuration(animationDuration);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setParams(10, (int) totalWidth);
            main.startAnimation(animation);
        }
    }

    private void renderSingleBarHorizontal() {
        LinearLayout parent = new LinearLayout(mContext);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LayoutParams((int) totalWidth, LayoutParams.WRAP_CONTENT));

        CardView card = new CardView(mContext);
        card.setRadius(radius);
        card.setCardBackgroundColor(Color.TRANSPARENT);
        card.setLayoutParams(new LayoutParams((int) totalWidth, LayoutParams.WRAP_CONTENT));

        LinearLayout main = new LinearLayout(mContext);
        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setLayoutParams(new LayoutParams((int) totalWidth, (int) totalHeight));

        float totalValue = 0;
        for (int i = 0; i < values.size(); i++) {
            totalValue = totalValue + values.get(i);
        }

        ArrayList<Float> percentage = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            percentage.add((values.get(i) * 100) / totalValue);
        }

        for (int i = 0; i < values.size(); i++) {
            float viewWidth = (percentage.get(i) * totalWidth) / 100;
            View view = new View(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) viewWidth, (int) totalHeight);
            view.setLayoutParams(params);
            view.setBackgroundColor(colors.get(i));
            main.addView(view);
        }

        card.addView(main);

        parent.addView(card);

        if (showLegend) {
            GridView grid = new GridView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) totalWidth, LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) DroidFunctions.dpToPx(10), 0, 0);
            grid.setLayoutParams(params);
            grid.setNumColumns(2);
            grid.setAdapter(new LegendAdapter(mContext, colors, legend, percentage, legendIconSize, legendTextColor, legendTextSize,
                    legendTextStyle, showLegendPercentage));

            parent.addView(grid);
        }

        addView(parent);

        if (shouldAnimate || shouldBounce) {
            ResizeAnimation animation = new ResizeAnimation(card);
            animation.setDuration(animationDuration);
            if (shouldBounce) {
                animation.setInterpolator(new BounceInterpolator());
            } else {
                animation.setInterpolator(new AccelerateInterpolator());
            }
            animation.setParams(10, (int) totalWidth);
            card.startAnimation(animation);
        }
    }









    public void setValues(float value, int color, String legendText) {
        values.add(value);
        colors.add(color);
        legend.add(legendText);
    }

    public void clearData() {
        values.clear();
        colors.clear();
        legend.clear();
    }

    public void deleteValueAt(int position) {
        values.remove(position);
        colors.remove(position);
        legend.remove(position);
    }

    public void render() {
        invalidateComponent();
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    public void setSingleBar(boolean singleBar) {
        this.singleBar = singleBar;
    }

    public boolean isSingleBar() {
        return singleBar;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    public int getAnimationDuration() {
        return animationDuration;
    }

    public void setTotalHeight(float totalHeightInDp) {
        totalHeightInDp = DroidFunctions.dpToPx(totalHeightInDp);
        this.totalHeight = totalHeightInDp;
    }

    public float getTotalHeight() {
        return totalHeight;
    }

    public void setTotalWidth(float totalWidthInDp) {
        totalWidthInDp = DroidFunctions.dpToPx(totalWidthInDp);
        this.totalWidth = totalWidthInDp;
    }

    public float getTotalWidth() {
        return totalWidth;
    }

    public void setLegendIconSize(float legendIconSizeInDp) {
        legendIconSizeInDp = DroidFunctions.dpToPx(legendIconSizeInDp);
        this.legendIconSize = legendIconSizeInDp;
    }

    public float getLegendIconSize() {
        return legendIconSize;
    }

    public void setLegendTextColor(int legendTextColor) {
        this.legendTextColor = legendTextColor;
    }

    public int getLegendTextColor() {
        return legendTextColor;
    }

    public void setLegendTextSize(float legendTextSizeInDp) {
        legendTextSizeInDp = DroidFunctions.dpToPx(legendTextSizeInDp);
        this.legendTextSize = legendTextSize;
    }

    public float getLegendTextSize() {
        return legendTextSize;
    }

    public void setShouldAnimate(boolean shouldAnimate) {
        this.shouldAnimate = shouldAnimate;
    }

    public boolean isShouldAnimate() {
        return shouldAnimate;
    }

    public void setShouldBounce(boolean shouldBounce) {
        this.shouldBounce = shouldBounce;
    }

    public boolean isShouldBounce() {
        return shouldBounce;
    }

    public void setRadius(float radiusInDp) {
        radiusInDp = DroidFunctions.dpToPx(radiusInDp);
        this.radius = radiusInDp;
    }

    public float getRadius() {
        return radius;
    }

    public void setLegendTextStyle(int legendTextStyle) {
        this.legendTextStyle = legendTextStyle;
    }

    public int getLegendTextStyle() {
        return legendTextStyle;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegendPercentage(boolean showLegendPercentage) {
        this.showLegendPercentage = showLegendPercentage;
    }

    public boolean isShowLegendPercentage() {
        return showLegendPercentage;
    }
}
