package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.R;
import com.bean.TextTimeBean;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * StickyList 适配器
 */
public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context mContext;
    private LayoutInflater inflater;

    private List<TextTimeBean> mTextTimeBeens=new ArrayList<>();//数据源
    public StickyListAdapter(Context mContext,List<TextTimeBean> mTextTimeBeans){
        this.mContext=mContext;
        this.mTextTimeBeens=mTextTimeBeans;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.headText = (TextView) convertView.findViewById(R.id.tv_head);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = mTextTimeBeens.get(position).mTime;//时间
        holder.headText.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return position/5;
    }

    @Override
    public int getCount() {
        return mTextTimeBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return mTextTimeBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.test_list_item_layout, parent, false);
            holder.contentText = (TextView) convertView.findViewById(R.id.tv_content_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.contentText.setText(mTextTimeBeens.get(position).mContent);

        return convertView;
    }


    class HeaderViewHolder{
         TextView headText;//头部TextView
     }
    class ViewHolder {
        TextView contentText;
    }
}
