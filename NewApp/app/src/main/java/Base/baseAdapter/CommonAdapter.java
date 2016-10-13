package Base.baseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用适配器
 * ltx
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected int layout;
    protected Context context;
    protected List<T> dataList;
    public CommonAdapter(Context context, int layout, List<T> dataList) {
        this.context = context;
        this.layout = layout;
        this.dataList = dataList;
        this.mInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public View  getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(context, convertView, parent, position, layout);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }
    protected abstract void convert(ViewHolder holder,T item);

}

