package com.example.healthyus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FoodTracker extends AppCompatActivity {
    String foodItemsEntered;
    double caloriesEntered;
    double idealCaloriesPerDay = 500;
    double caloriesRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);
        final EditText foodItems = (EditText) findViewById(R.id.txtFoodEntries);
        final EditText calories = (EditText) findViewById(R.id.numCalories);
        Button submit = (Button) findViewById(R.id.btnCalorieDisplay);
        submit.setOnClickListener(new View.OnClickListener() {
            final TextView result = (TextView) findViewById(R.id.txtResult);
            @Override
            public void onClick(View v) {
                foodItemsEntered = foodItems.getText().toString();
                caloriesEntered = Double.parseDouble(calories.getText().toString());

                if(caloriesEntered < idealCaloriesPerDay) {
                    caloriesRequired = idealCaloriesPerDay - caloriesEntered;
                    result.setText("You need " + caloriesRequired + " more calories today.");
                }

                else if(caloriesEntered > idealCaloriesPerDay){
                    caloriesRequired = caloriesEntered - idealCaloriesPerDay;
                    result.setText("You exceeded the limit, consumed " + caloriesRequired + " extra calories");
                }

                else {
                    result.setText("You consumed the ideal amount of calories today");
                }

            }
        });
    }
}