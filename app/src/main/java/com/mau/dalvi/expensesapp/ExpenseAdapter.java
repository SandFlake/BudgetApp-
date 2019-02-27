package com.mau.dalvi.expensesapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    List<ExpensesTable> kostnader = new ArrayList<>();
    Context context;

    public ExpenseAdapter(Context context, List<ExpensesTable> expenseList) {
        this.context = context;
        kostnader.addAll(expenseList);
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder viewHolder, final int i) {
        Log.d("Tag", "expenes adapter here you" + i);

        viewHolder.itemName.setText(kostnader.get(i).getEx_title());
        viewHolder.itemAmount.setText("" + kostnader.get(i).getEx_price());
        String category = kostnader.get(i).getEx_category();
        viewHolder.itemCategory.setImageDrawable(getImage(category));
        //viewHolder.itemCategory.setImageDrawable(R.drawable.resor);

        viewHolder.item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Date: " + kostnader.get(i).getEx_date(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private Drawable getImage(String category) {
        Drawable image = null;
        Log.d("getimage", "melons" + category);

        switch (category) {
            case "Travel":
                image = context.getDrawable(R.drawable.resor);
                break;
            case "Leisure":
                image = context.getDrawable(R.drawable.fritid);
                break;
            case "Rent":
                image = context.getDrawable(R.drawable.boende);
                break;
            case "Groceries":
                image = context.getDrawable(R.drawable.livsmedel);
                break;
            case "Other":
                image = context.getDrawable(R.drawable.other);
                break;
        }

        return image;
    }

    @Override
    public int getItemCount() {
        return kostnader.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemAmount;
        public ImageView itemCategory;
        LinearLayout item_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.listTextName);
            itemAmount = itemView.findViewById(R.id.listTextAmount);
            itemCategory = itemView.findViewById(R.id.listImage);
            item_parent = itemView.findViewById(R.id.item_parent);
        }
    }
}


