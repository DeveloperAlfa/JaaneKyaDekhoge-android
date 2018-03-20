package me.developeralfa.jaanekyadekhoge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devalfa on 20/3/18.
 */

public class handloomadapter extends BaseAdapter{
    Context context;
    ArrayList<ImadResult> imadResults;

    public handloomadapter(Context context, ArrayList<ImadResult> imadResults) {
        this.context = context;
        this.imadResults = imadResults;
    }

    @Override
    public int getCount() {
        return imadResults.size();
    }

    @Override
    public ImadResult getItem(int position) {
        return imadResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);

        TextView textView = view.findViewById(R.id.text1);
        TextView textView2 = view.findViewById(R.id.text2);
        textView.setText(imadResults.get(position).title);
        textView2.setText(imadResults.get(position).description);
        return view;
    }

}
