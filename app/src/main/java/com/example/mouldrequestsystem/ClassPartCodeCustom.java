package com.example.mouldrequestsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassPartCodeCustom extends BaseAdapter {

    Context context;

    ClassPartCodeDT classPartCodeDT;

    private static LayoutInflater inflater = null;

    public ClassPartCodeCustom(Context context, ClassPartCodeDT classPartCodeDT) {
        this.context = context;

        this.classPartCodeDT = classPartCodeDT;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (classPartCodeDT.getPartCodeList() == null) ? 0 : classPartCodeDT.getPartCodeList().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder {
        TextView partcode;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ClassPartCodeCustom.Holder holder = new ClassPartCodeCustom.Holder();

        View rowView = inflater.inflate(R.layout.activity_partcode_custom, null);

        holder.partcode = rowView.findViewById(R.id.t_partcode);

        holder.partcode.setText(classPartCodeDT.getPartCodeList().get(i).getT_partcode());


        rowView.setTag(holder.partcode.getText().toString());

        return rowView;
    }
}
