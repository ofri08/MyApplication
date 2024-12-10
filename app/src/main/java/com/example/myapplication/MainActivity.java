package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDate, btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnDate = findViewById(R.id.btnDate);
        btnSelect = findViewById((R.id.btnSelect));

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Items for multiple-choice dialog
                final String[] options = {"Option 1", "Option 2", "Option 3"};
                final boolean[] selectedOptions = {false, false, false};

                // Create AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose Options");
                builder.setMultiChoiceItems(options, selectedOptions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // Update the selection state
                        selectedOptions[which] = isChecked;
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Collect and display selected items
                        StringBuilder selectedItems = new StringBuilder("Selected: ");
                        for (int i = 0; i < options.length; i++) {
                            if (selectedOptions[i]) {
                                selectedItems.append(options[i]).append(", ");
                            }
                        }

                        if (selectedItems.toString().endsWith(", ")) {
                            selectedItems = new StringBuilder(selectedItems.substring(0, selectedItems.length() - 2));
                        }

                        Toast.makeText(MainActivity.this, selectedItems.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", null);

                // Show the dialog
                builder.create().show();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;

                // Get the current date
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);


                // Create and show DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                                // Handle selected date
                                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                                Toast.makeText(MainActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                            }
                        },
                        mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });


    }
}