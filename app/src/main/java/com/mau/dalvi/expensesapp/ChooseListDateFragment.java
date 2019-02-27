package com.mau.dalvi.expensesapp;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseListDateFragment extends Fragment {

    private TextView tvFromDate, tvToDate;
    private Button btnIncome, btnExpense;
    private MainController controller;
    private DatePickerDialog.OnDateSetListener listener1, listener2;


    public ChooseListDateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_list_date, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {

        tvFromDate = view.findViewById(R.id.tvFromDate);
        tvToDate  = view.findViewById(R.id.tvToDate);
        btnIncome  = view.findViewById(R.id.btnIncome);
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnIncomeListClicked();
            }
        });
        btnExpense  = view.findViewById(R.id.btnExpense);
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnExpenseListClicked();
            }
        });

        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_DeviceDefault,
                        listener1,
                        year, month, day);

                dialog.show();
            }

        });

        listener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date1 = year + "-" + month + "-" + dayOfMonth;
                tvFromDate.setText(date1);
            }
        };


        tvToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_DeviceDefault,
                        listener2,
                        year, month, day);

                dialog.show();
            }

        });

        listener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date2 = year + "-" + month + "-" + dayOfMonth;
                tvToDate.setText(date2);
            }
        };


    }

    public void setController(MainController controller){
        this.controller = controller;
    }
    public String getFromDate(){
        return tvFromDate.getText().toString(); }

    public String getToDate(){
        return tvToDate.getText().toString();
    }
}
