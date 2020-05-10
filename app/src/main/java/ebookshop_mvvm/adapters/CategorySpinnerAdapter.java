package ebookshop_mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ListitemsLayoutBinding;

import java.util.List;

import ebookshop_mvvm.models.Category;

public class CategorySpinnerAdapter extends ArrayAdapter<Category> {

    List<Category> list;

    public CategorySpinnerAdapter(Context context, List<Category> list) {
        super(context, R.layout.listitems_layout, R.id.tv_category, list);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView, position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    private View rowview(View convertView, int position) {
        Category category = getItem(position);
        viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {
            ListitemsLayoutBinding listitemsLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(convertView.getContext()), R.layout.listitems_layout, null, false);
            holder = new viewHolder(listitemsLayoutBinding);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }
        holder.listitemsLayoutBinding.setCategory(category);
        return rowview;
    }

    private class viewHolder {
        ListitemsLayoutBinding listitemsLayoutBinding;

        public viewHolder(ListitemsLayoutBinding listitemsLayoutBinding) {
            this.listitemsLayoutBinding = listitemsLayoutBinding;
        }

    }
}
