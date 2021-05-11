package com.example.mouldrequestsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassMouldRequestCustom extends BaseAdapter {

    Context context;

    ClassMouldRequestDT classMouldRequestDT;

    private static LayoutInflater inflater = null;

    public ClassMouldRequestCustom(Context context, ClassMouldRequestDT classMouldRequestDT) {
        this.context = context;

        this.classMouldRequestDT = classMouldRequestDT;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (classMouldRequestDT.getMouldRequestList() == null) ? 0 : classMouldRequestDT.getMouldRequestList().size();
    }

    @Override
    public Object getItem(int i) { return i; }

    @Override
    public long getItemId(int i) { return i; }

    public class Holder {
        TextView t_request_id;
        TextView t_part_code;
        TextView t_step_code;
        TextView t_machine_no;
        TextView t_Location;
        TextView t_date_request;
        TextView t_request_by;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ClassMouldRequestCustom.Holder holder = new ClassMouldRequestCustom.Holder();

        View rowView = inflater.inflate(R.layout.activity_mould_request_custom, null);

        holder.t_request_id = rowView.findViewById(R.id.t_request_id);

        holder.t_request_id.setText(classMouldRequestDT.getMouldRequestList().get(i).getRequestID());

        holder.t_part_code = rowView.findViewById(R.id.t_part_code);

        holder.t_part_code.setText(classMouldRequestDT.getMouldRequestList().get(i).getPartCode());

        holder.t_step_code = rowView.findViewById(R.id.t_step_code);

        holder.t_step_code.setText(classMouldRequestDT.getMouldRequestList().get(i).getStepCode());

        holder.t_machine_no = rowView.findViewById(R.id.t_machine_no);

        holder.t_machine_no.setText(classMouldRequestDT.getMouldRequestList().get(i).getMachineNo());


        holder.t_Location = rowView.findViewById(R.id.t_location);

        holder.t_Location.setText(classMouldRequestDT.getMouldRequestList().get(i).getLocation());

        holder.t_date_request = rowView.findViewById(R.id.t_date_request);

        holder.t_date_request.setText(classMouldRequestDT.getMouldRequestList().get(i).getDateRequest());

        holder.t_request_by = rowView.findViewById(R.id.t_request_by);

        holder.t_request_by.setText(classMouldRequestDT.getMouldRequestList().get(i).getRequestby());

        rowView.setTag(holder.t_request_id.getText().toString());

        return rowView;
    }
}
