package org.techtown.courage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jisub on 2017-09-20.
 */

public class UserItemListAdapter extends BaseAdapter {

    private Fragment parent;
    private Context context;
    private List<UserItem> noticeList;

    public UserItemListAdapter(Context context, List<UserItem> noticeList, Fragment parent) {
        this.context = context;
        this.noticeList = noticeList;
        this.parent = parent;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.user_item,null);

        TextView nameText = (TextView) v.findViewById(R.id.textView);
        TextView PhoneText = (TextView) v.findViewById(R.id.textView2);

        nameText.setText(noticeList.get(i).getName());
        PhoneText.setText(noticeList.get(i).getMobile());

        v.setTag(noticeList.get(i).getName());





        return v;
    }


}
