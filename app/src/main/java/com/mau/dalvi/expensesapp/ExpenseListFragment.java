package com.mau.dalvi.expensesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;


public class  ExpenseListFragment extends Fragment {

    private MainController controller;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    List<ExpensesTable> kostnader;

    public ExpenseListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ExpenseAdapter(getActivity(), kostnader);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setExpenseList(List<ExpensesTable> kostnader){
        this.kostnader = kostnader;
        Log.d("Tag", "set expenses melons list");
    }


    public void setController(MainController controller) {
        this.controller = controller;
    }
}
