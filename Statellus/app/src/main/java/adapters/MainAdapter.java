package adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.view.StatisticsCalcListView;


public class MainAdapter extends BaseAdapter {

    StatisticsCalcListView statisticsCalcListView;
    String[] title;

    public MainAdapter(StatisticsCalcListView statisticsCalcListView, String[] title) {
        this.statisticsCalcListView =statisticsCalcListView;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(statisticsCalcListView).inflate(R.layout.list_view_item_layout,viewGroup,false);

        TextView textView;
        LinearLayout ll_bg;
        ll_bg = view.findViewById(R.id.ll_bg);
        textView = view.findViewById(R.id.textView);

        ll_bg.setBackground(ContextCompat.getDrawable(statisticsCalcListView, R.drawable.gradient_3));
        textView.setText(title[i]);
        return view;
    }
}
