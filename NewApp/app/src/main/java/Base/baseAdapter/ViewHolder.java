package Base.baseAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  抽象公共的viewholder
 * ltx
 */

public  class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }
    /**
    获取viewholder
    @param context 上下文
    @param converView converView
    @param parent 依赖的父布局
    @param position 索引
    @param layoutId 布局Id
    @return ViewHolder
            */
    public static ViewHolder get(Context context, View converView, ViewGroup parent, int position, int layoutId) {
        if (converView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) converView.getTag();
            //viewholderr会被复用 及时更新下position
            holder.mPosition = position;
            return holder;
        }
    }
    /**
      取得viewholder存储的的控件
      @param viewId viewId
      @param <T> view
      @return view
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置文字
     * @param viewId viewId
     * @param text 文本内容
     * @return ViewHolder
     */
    public ViewHolder setText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(TextUtils.isEmpty(text)?"":text);
        return this;
    }

    /**
     * 设置图片
     * @param viewId  viewId
     * @param resId 资源Id
     * @return ViewHolder
     */
    public ViewHolder setImageResource(int viewId,int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     *
     * @param object
     * @param resId
     */
    public View setTag(Object object,int resId){
        View view=getView(resId);
        if (null != object) {
            view.setTag(object);
        }
        return view;
    }

}

