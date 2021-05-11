package com.example.mouldrequestsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassOperatorCustom extends BaseAdapter{

    Context context;

    ClassOperatorDT classOperatorDT;

    private static LayoutInflater inflater = null;

    public ClassOperatorCustom(Context context, ClassOperatorDT classOperatorDT) {
        this.context = context;

        this.classOperatorDT = classOperatorDT;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (classOperatorDT.getOperatorList() == null) ? 0 : classOperatorDT.getOperatorList().size();
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
        TextView emp_id;
        TextView full_name;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ClassOperatorCustom.Holder holder = new ClassOperatorCustom.Holder();

        View rowView = inflater.inflate(R.layout.activity_operator_custom, null);

        holder.emp_id = rowView.findViewById(R.id.t_empid);

        holder.emp_id.setText(classOperatorDT.getOperatorList().get(i).getEmpID());

        holder.full_name = rowView.findViewById(R.id.t_fullname);

        holder.full_name.setText(classOperatorDT.getOperatorList().get(i).getEmpName());

        rowView.setTag(holder.emp_id.getText().toString());

        return rowView;
    }
}
