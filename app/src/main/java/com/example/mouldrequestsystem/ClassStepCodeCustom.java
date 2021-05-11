package com.example.mouldrequestsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ClassStepCodeCustom extends BaseAdapter {
    Context context;

    ClassStepCodeDT classStepCodeDT;

    private static LayoutInflater inflater = null;

    public ClassStepCodeCustom(Context context, ClassStepCodeDT classStepCodeDT) {
        this.context = context;

        this.classStepCodeDT = classStepCodeDT;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (classStepCodeDT.getStepCodeList() == null) ? 0 : classStepCodeDT.getStepCodeList().size();
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
        TextView stepcode;
        TextView location;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ClassStepCodeCustom.Holder holder = new ClassStepCodeCustom.Holder();

        View rowView = inflater.inflate(R.layout.activity_stepcode_custom, null);

        holder.stepcode = rowView.findViewById(R.id.t_stepcode);

        holder.stepcode.setText(classStepCodeDT.getStepCodeList().get(i).getT_stepcode());

        holder.location = rowView.findViewById(R.id.t_location);

        holder.location.setText(classStepCodeDT.getStepCodeList().get(i).getT_location());


        rowView.setTag(holder.stepcode.getText().toString());

        return rowView;
    }
}
