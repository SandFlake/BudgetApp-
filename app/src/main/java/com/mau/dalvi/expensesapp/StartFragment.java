package com.mau.dalvi.expensesapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {
    private Button btnAddIncome, btnAddExpense, btnOverview, btnList;
    private TextView tvWelcome;

    private MainController controller;
    private LoginFragment fragLogin;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        initializeComponents(view);
        setWelcome();
        registerListeners();
        return view;
    }

    public void initializeComponents(View view){
        btnAddIncome = view.findViewById(R.id.btnAddIn);
        btnAddExpense = view.findViewById(R.id.btnAddEx);
        btnOverview = view.findViewById(R.id.btnSeeAll);
        btnList = view.findViewById(R.id.btnListView);
        tvWelcome = view.findViewById(R.id.tvWelcome);
    }

    public void setWelcome(){
        tvWelcome.setText(fragLogin.getFirstName() + " " + fragLogin.getLastName());
    }
    public void registerListeners(){
        ButtonListener listener = new ButtonListener();

        btnAddIncome.setOnClickListener(listener);
        btnAddExpense.setOnClickListener(listener);
        btnOverview.setOnClickListener(listener);
        btnList.setOnClickListener(listener);
    }

    public void setMainController(MainController controller){
        this.controller = controller;
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view == btnAddIncome) {
                controller.btnAddIncomeClicked();
            } else if(view == btnAddExpense){
                controller.btnAddExpenseClicked();
            } else if (view == btnOverview){
                controller.btnOverviewClicked();
            } else if (view == btnList){
                controller.btnChooseListClicked();
            }

        }
    }

    public void onSaveInstanceState(Bundle outState) {

    }
}
