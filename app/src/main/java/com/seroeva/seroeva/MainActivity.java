package com.seroeva.seroeva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText input;
    Spinner from, to;
    ArrayAdapter<Unit> adapter;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        input = findViewById(R.id.input);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        text = findViewById(R.id.result);

        adapter.addAll(new Unit("Мм", 1000), new Unit("См", 100), new Unit("М", 1), new Unit("Км", 0.001));

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        findViewById(R.id.mm).setOnClickListener(view -> from.setSelection(0));
        findViewById(R.id.sm).setOnClickListener(view -> from.setSelection(1));
        findViewById(R.id.m).setOnClickListener(view -> from.setSelection(2));


        findViewById(R.id.button).setOnClickListener(view -> {
            try {
                double info = Double.parseDouble(input.getText().toString());


                Unit current = (Unit) from.getSelectedItem();
                Unit currentTo = (Unit) to.getSelectedItem();

                double metre = info / current.coeff * currentTo.coeff;

                text.setText(String.valueOf(metre));
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_LONG).show();
            }
        });
    }
}