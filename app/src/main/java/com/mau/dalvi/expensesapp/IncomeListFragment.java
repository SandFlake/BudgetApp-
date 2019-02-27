package com.mau.dalvi.expensesapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeListFragment extends Fragment {

    private FloatingActionButton fab;
    private MainController controller;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    List<IncomeTable> inkomst;

    public IncomeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_list, container, false);
        Log.d("Tag", "income list inflater hre yo");
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new IncomeAdapter(getActivity(), inkomst);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setInkomstList(List<IncomeTable> inkomst){
        this.inkomst = inkomst;
        Log.d("Tag", "set income list");
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
