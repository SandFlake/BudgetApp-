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
public class TotalFragment extends Fragment {

    private TextView tvInTitle, tvExTitle, tvTotalTitle, tvInTotal, tvExTotal, tvTotal, tvPersonName, tvChooseFromDate, tvChooseToDate;
    private Button btnHome, btnUpdate;
    private MainController controller;
    private DBAccess dbAccess;
    private DatePickerDialog.OnDateSetListener dateListener, dateListener2;
    private LoginFragment fragLogin;


    public TotalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, container, false);
        initializeComponents(view);
        setWelcome();
        return view;
    }

    public void setController(MainController controller){
        this.controller = controller;
    }

    private void initializeComponents(View view) {
        tvInTitle = view.findViewById(R.id.tvInTitle);
        tvExTitle = view.findViewById(R.id.tvExTitle);
        tvTotalTitle = view.findViewById(R.id.tvTotalTitle);
        tvInTotal = view.findViewById(R.id.tvInTotal);
        tvExTotal = view.findViewById(R.id.tvExTotal);
        tvTotal = view.findViewById(R.id.tvTotal);
        tvPersonName = view.findViewById(R.id.tvPersonName);

        tvChooseFromDate = view.findViewById(R.id.tvChooseFromDate);
        tvChooseFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_DeviceDefault,
                        dateListener,
                        year, month, day);

                dialog.show();
            }

        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date1 = year + "-" + month + "-" + dayOfMonth;
                tvChooseFromDate.setText(date1);
            }
        };

        tvChooseToDate = view.findViewById(R.id.tvChooseToDate);
        tvChooseToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_DeviceDefault,
                        dateListener2,
                        year, month, day);

                dialog.show();
            }

        });

        dateListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date2 = year + "-" + month + "-" + dayOfMonth;
                tvChooseToDate.setText(date2);
            }

        };


        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnUpdateClicked();
            }
        });
        btnHome = view.findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnBackClicked();
            }
        });

    }

    public void setWelcome(){
        tvPersonName.setText(fragLogin.getFirstName() + " " + fragLogin.getLastName());
    }

    public void setIncomeView(double difference){
        tvInTotal.setText("" + difference);
    }

    public void setExpenseView(double difference){
        tvExTotal.setText("" + difference);
    }

    public String getFromDate(){
        return tvChooseFromDate.getText().toString();
    }

    public String getToDate(){
        return tvChooseToDate.getText().toString();
    }

    public void setAccountTotal(double difference){
        tvTotal.setText("" + difference);
    }
}
