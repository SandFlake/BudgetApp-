package com.mau.dalvi.expensesapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView tvLoginTitle;
    EditText etLoginFirstName, etLoginLastName;
    private Button btnLoginSave;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private MainController controller;

    public static String fName;
    public static String lName;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        initializeComponents(view);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mPreferences.edit();
        checkSharedPreferences();

        btnLoginSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (etLoginFirstName.getText().toString().isEmpty() || etLoginLastName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Can't start without your name", Toast.LENGTH_LONG).show();
                } else {
                    String name = etLoginFirstName.getText().toString();
                    mEditor.putString(getString(R.string.first_name), name);
                    mEditor.commit();

                    String surname = etLoginLastName.getText().toString();
                    mEditor.putString(getString(R.string.last_name), surname);
                    mEditor.commit();

                    fName = name;
                    lName = surname;
                    etLoginFirstName.setText(fName);
                    etLoginLastName.setText(lName);

                    controller.btnLoginClicked();
                }
            }
    });

        return view;
    }

    private void checkSharedPreferences() {
        String firstName = mPreferences.getString(getString(R.string.first_name), "");
        String lastName = mPreferences.getString(getString(R.string.last_name), "");

        etLoginFirstName.setText(firstName);
        etLoginLastName.setText(lastName);

        fName = firstName;
        lName = lastName;
    }

    private void initializeComponents(View view) {
        tvLoginTitle = view.findViewById(R.id.tvLoginTitle);
        etLoginFirstName = view.findViewById(R.id.etLoginName);
        etLoginLastName = view.findViewById(R.id.etLoginLastName);
        btnLoginSave = view.findViewById(R.id.btnLoginSave);
    }

    public void setController(MainController controller){
        this.controller = controller;
    }

    public static String getFirstName(){
        return fName;
    }

    public static String getLastName(){
        return lName;
    }

}
