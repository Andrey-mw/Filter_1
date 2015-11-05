package com.example.android.filter_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText txtSearch;
    private ArrayList<Country> list;

    public MainActivity() {
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        initData();

        final MyCutomAdapter adapter = new MyCutomAdapter(this, R.layout.country_info, list);
        listView.setAdapter(adapter);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void initData() {
        list = new ArrayList<Country>();
        Country country = new Country("AFG", "Afghanistan", "Asia", "Southern and Central Asia");
        list.add(country);
        country = new Country("ALB", "Албания", "Europe", "Southern Europe");
        list.add(country);
        country = new Country("DZA", "Алгерия", "Africa", "Northern Africa");
        list.add(country);
        country = new Country("ASM", "Фмерика", "Oceania", "Polynesia");
        list.add(country);
        country = new Country("AND", "Андора", "Europe", "Southern Europe");
        list.add(country);
        country = new Country("AGO", "Ангола", "Africa", "Central Africa");
        list.add(country);
        country = new Country("AIA", "Ангулина", "North America", "Caribbean");
        list.add(country);
    }

}
