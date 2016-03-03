package com.steinsvik.fabricmanagaer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steinsvik.fabricmanagaer.R;
import com.steinsvik.fabricmanagaer.data.Information;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gigabyte on 3/2/2016.
 */
public class ListItemDateAdapter extends RecyclerView.Adapter<ListItemDateAdapter.MyHolder> {
    private LayoutInflater layoutInflater;
    private List<Information> data = Collections.emptyList();

    public ListItemDateAdapter(Context context, List<Information> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_row_date, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.txt_No.setText((position + 1) + "");
        holder.txt_Detail.setText(data.get(position).getDetail());
        double price = data.get(position).getPrice();
        DecimalFormat df = new DecimalFormat("###,###.##");
        holder.txt_Price.setText(df.format(price));
        if (position == data.size() - 1) {
            holder.ID_Result.setVisibility(View.VISIBLE);
            double total = 0;
            for (Information inf : data)
                total += inf.getPrice();
            holder.txt_total.setText(df.format(total));
        } else
            holder.ID_Result.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView txt_No;
        TextView txt_Detail;
        TextView txt_Price;
        LinearLayout ID_Result;
        TextView txt_total;


        public MyHolder(View itemView) {
            super(itemView);
            txt_No = (TextView) itemView.findViewById(R.id.stt_ID);
            txt_Detail = (TextView) itemView.findViewById(R.id.detail_ID);
            txt_Price = (TextView) itemView.findViewById(R.id.price_ID);
            ID_Result = (LinearLayout) itemView.findViewById(R.id.ID_Result);
            txt_total = (TextView) itemView.findViewById(R.id.total_ID);
        }
    }
}
