package com.mau.dalvi.expensesapp;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    private MainController controller;
    private EditText etTitleEx, etPrice;
    private Spinner spinCategoryEx;
    private TextView tvDateEx;
    private Button btnSaveEx, btnBackEx;
    private DatePickerDialog.OnDateSetListener dateListener;


    public ExpensesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        initializeComponents(view);
        return view;
    }

    public void onResume(){
        super.onResume();
        etTitleEx.setText("");
        etPrice.setText("");
    }

    private void initializeComponents(View view) {
        etTitleEx = view.findViewById(R.id.etTitleEx);
        etPrice = view.findViewById(R.id.etPrice);
        spinCategoryEx = view.findViewById(R.id.spinCategoryEx);
        tvDateEx = view.findViewById(R.id.tvDateEx);
        tvDateEx.setOnClickListener(new View.OnClickListener() {
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
                tvDateEx.setText(date);
            }
        };

        btnSaveEx = view.findViewById(R.id.btnSaveEx);
        btnSaveEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitleEx.getText().toString().isEmpty() || etPrice.getText().toString().isEmpty() ||
                        spinCategoryEx.getSelectedItem().toString().isEmpty() || tvDateEx.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all blanks", Toast.LENGTH_LONG).show();
                } else {
                    controller.addExpense(etTitleEx.getText().toString(), etPrice.getText().toString(),
                            spinCategoryEx.getSelectedItem().toString(), tvDateEx.getText().toString());
                }
            }
        });

        btnBackEx = view.findViewById(R.id.btnBackEx);
        btnBackEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.btnBackClicked();
            }
        });
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
