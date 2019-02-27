package com.mau.dalvi.expensesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    List<IncomeTable> inkomst = new ArrayList<>();
    Context context;

    public IncomeAdapter(Context context, List<IncomeTable> incomeList) {
        this.context = context;
        inkomst.addAll(incomeList);
    }

    @NonNull
    @Override
    public IncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IncomeAdapter.ViewHolder viewHolder, final int i) {
        Log.d("binding bitches", "list to viewholder? " + inkomst.size());


        viewHolder.itemName.setText(inkomst.get(i).getTitle());
        viewHolder.itemAmount.setText(Double.toString(inkomst.get(i).getAmount()));
        viewHolder.itemCategory.setText(inkomst.get(i).getCategory());

        viewHolder.item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Date: " + inkomst.get(i).getDate(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return inkomst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemAmount;
        public TextView itemCategory;
        LinearLayout item_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.listTextName);
            itemAmount = itemView.findViewById(R.id.listTextAmount);
            itemCategory = itemView.findViewById(R.id.listTextCategory);
            item_parent = itemView.findViewById(R.id.item_parent);
        }
    }
}
