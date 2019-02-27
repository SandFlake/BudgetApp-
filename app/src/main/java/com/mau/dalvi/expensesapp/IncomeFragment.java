package com.mau.dalvi.expensesapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {

    private MainController controller;
    private EditText etTitleIn;
    private EditText etAmount;
    private Spinner spinCategory;
    private TextView tvDateIn;
    private Button btnSave, btnBack;
    private DatePickerDialog.OnDateSetListener dateListener;

    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_income, container, false);
        initializeComponents(view);
        return view;
    }

    public void onResume() {
        super.onResume();
        etTitleIn.setText("");
        etAmount.setText("");
    }
    private void initializeComponents(View view) {
        etTitleIn = view.findViewById(R.id.etTitleIn);
        etAmount = view.findViewById(R.id.etAmount);
        spinCategory = view.findViewById(R.id.spinCategory);
        tvDateIn = view.findViewById(R.id.tvDateIn);
        tvDateIn.setOnClickListener(new View.OnClickListener() {
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

                String date = year + "-" + month + "-" + dayOfMonth;
                tvDateIn.setText(date);
            }
        };

        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitleIn.getText().toString().isEmpty() || etAmount.getText().toString().isEmpty() ||
                        spinCategory.getSelectedItem().toString().isEmpty() || tvDateIn.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_LONG).show();
                } else {
                    controller.addIncome(etTitleIn.getText().toString(), etAmount.getText().toString(),
                            spinCategory.getSelectedItem().toString(), tvDateIn.getText().toString());
                }
            }
        }
        );

        btnBack = view.findViewById(R.id.btnBackIn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnBackClicked();
                onDestroy();

            }
        });
    }


    public void setController(MainController controller){
        this.controller = controller;
    }

}


