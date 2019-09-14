package com.droid.uigraph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LegendAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Integer> colors = new ArrayList<>();
    ArrayList<String> legend = new ArrayList<>();
    ArrayList<Float> percentage = new ArrayList<>();
    private int legendTextStyle, legendTextColor;
    private float legendIconSize, legendTextSize;
    private boolean showLegendPercentage;


    public LegendAdapter(Context context, ArrayList<Integer> colors, ArrayList<String> legend, ArrayList<Float> percentage, float legendIconSize, int legendTextColor, float legendTextSize, int legendTextStyle, boolean showLegendPercentage) {
        this.mContext = context;
        this.colors = colors;
        this.legend = legend;
        this.percentage = percentage;
        this.legendIconSize = legendIconSize;
        this.legendTextColor = legendTextColor;
        this.legendTextSize = legendTextSize;
        this.legendTextStyle = legendTextStyle;
        this.showLegendPercentage = showLegendPercentage;

    }

    @Override
    public int getCount() {
        return legend.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        try {
            ViewHolder mViewHolder = null;
            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.legend_display, null);
                mViewHolder = new ViewHolder();

                mViewHolder.legendText = convertView.findViewById(R.id.text);
                mViewHolder.legendColor = convertView.findViewById(R.id.color);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            mViewHolder.legendColor.setBackgroundColor(colors.get(pos));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) legendIconSize, (int) legendIconSize);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mViewHolder.legendColor.setLayoutParams(params);

            if (showLegendPercentage) {
                mViewHolder.legendText.setText(legend.get(pos) + " (" +
                        new DecimalFormat("##.#").format(percentage.get(pos)) + "%)");
            } else {
                mViewHolder.legendText.setText(legend.get(pos));
            }
            mViewHolder.legendText.setTextColor(legendTextColor);
            mViewHolder.legendText.setTextSize(DroidFunctions.pxToDp(legendTextSize));
            mViewHolder.legendText.setTypeface(null, legendTextStyle);

            return convertView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    class ViewHolder {
        public TextView legendText;
        public CardView legendColor;
    }
}
