package com.example.samsung.loginactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsung on 2018-09-08.
 */

public class UserAdapter  extends ArrayAdapter<User> {
    private Context context;
    private List mList;
    private ListView mListView;

    class UserViewHolder{
        public TextView rank_tv;
        public TextView name_tv;
        public TextView pts_tv;
    }
    public UserAdapter(Context context, List<User> list, ListView listview){
        super(context,0,list);

        this.context=context;
        this.mList=list;
        this.mListView=listview;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup parentViewGroup){
        View rowView= convertView;
        UserViewHolder viewHolder;
        if(rowView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.list_item,parentViewGroup,false);

            viewHolder = new UserViewHolder();
            viewHolder.rank_tv = (TextView) rowView.findViewById(R.id.textview_rank);
            viewHolder.name_tv = (TextView) rowView.findViewById(R.id.textview_name);
            viewHolder.pts_tv = (TextView) rowView.findViewById(R.id.textview_pts);
        }
        else{
            viewHolder=(UserViewHolder) rowView.getTag();
        }
        User user = (User) mList.get(position);
        viewHolder.rank_tv.setText(user.getRank());
        viewHolder.name_tv.setText(user.getName());
        viewHolder.pts_tv.setText(user.getPts());

        return rowView;
    }
}
