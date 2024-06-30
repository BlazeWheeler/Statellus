package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.blazewheeler.statellus.R;
import com.blazewheeler.statellus.view.VisualizeDataListView;

/**
 * Custom adapter for populating a ListView with visualized data.
 * Extends {@link BaseAdapter}.
 */
public class VisualizeDataAdapter extends BaseAdapter {

    private final VisualizeDataListView visualizeDataListView;
    private final String[] title;
    private final LayoutInflater inflater;

    /**
     * Constructor for the VisualizeDataAdapter.
     *
     * @param visualizeDataListView The associated activity or fragment for the adapter.
     * @param title                 Array of titles to be displayed in the ListView.
     */
    public VisualizeDataAdapter(VisualizeDataListView visualizeDataListView, String[] title) {
        this.visualizeDataListView = visualizeDataListView;
        this.title = title;
        this.inflater = (LayoutInflater) visualizeDataListView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Get the number of items in the dataset.
     *
     * @return The number of items in the dataset.
     */
    @Override
    public int getCount() {
        return title.length;
    }

    /**
     * Get the data item at the specified position in the dataset.
     *
     * @param i The position of the item within the dataset.
     * @return The data item at the specified position.
     */
    @Override
    public Object getItem(int i) {
        return i;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param i The position of the item within the dataset.
     * @return The row id associated with the specified position.
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Get a View that displays the data at the specified position in the dataset.
     *
     * @param i           The position of the item within the dataset.
     * @param convertView The recycled view to populate.
     * @param viewGroup   The parent view that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item_layout, viewGroup, false);

            holder = new ViewHolder();
            holder.ll_bg = convertView.findViewById(R.id.ll_bg);
            holder.textView = convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ll_bg.setBackground(ContextCompat.getDrawable(visualizeDataListView, R.drawable.gradient_3));
        holder.textView.setText(title[i]);

        return convertView;
    }

    /**
     * ViewHolder pattern for optimizing view recycling in the adapter.
     */
    static class ViewHolder {
        LinearLayout ll_bg;
        TextView textView;
    }
}
