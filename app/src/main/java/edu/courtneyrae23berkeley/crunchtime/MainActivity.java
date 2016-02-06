// Special thanks to www.linux.com for their GridView tutorial including how to extend the adapter
package edu.courtneyrae23berkeley.crunchtime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    boolean exerciseConversion = true;
    String exercise = "jogging";
    double cals;
    Button mButton;
    Button mMode;
    EditText mRepsMins;
    TextView mCalories;
    GridView mGrid;
    TextView mMinsOrReps;
    TextView mEquivalent;
    EditText mWeight;

    static final String[] exercises = new String[] {
                "jogging", "situps", "pushups",
                "squats", "walking", "plank",
                "jumping jacks", "pullups", "cycling",
                "leg-lifts", "swimming", "stair-climbing"};
    static final double[] factors = new double[] {.067, .01, .02, .0096, .036, .026, .089, .006, .045, .027, .071, .046};
    static double[] startReps = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMode = (Button) findViewById(R.id.ToggleMode);
        mButton = (Button)findViewById(R.id.button);
        mRepsMins   = (EditText)findViewById(R.id.editText);
        mCalories = (TextView)findViewById(R.id.editText2);
        mGrid = (GridView) findViewById(R.id.gridView);
        mMinsOrReps = (TextView)findViewById(R.id.textView2);
        mEquivalent = (TextView) findViewById(R.id.equivalent);
        mWeight = (EditText) findViewById(R.id.weight);

        final GridAdapter adapter = new GridAdapter(this);

        mGrid.setAdapter(adapter);


        mGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                v.setBackgroundColor(Color.BLACK);

                exercise = String.valueOf(((TextView) v.findViewById(R.id.title)).getText().toString());
                String[] reps = new String[]{"situps", "pushups", "pullups", "squats"};
                if (Arrays.asList(reps).contains(exercise)) {
                    mMinsOrReps.setText("reps");
                } else {
                    mMinsOrReps.setText("minutes");
                }


            }
        });

        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (exerciseConversion) {
                            int repsMins = Integer.valueOf(mRepsMins.getText().toString());
                            int weight = Integer.valueOf(mWeight.getText().toString());
                            int index = Arrays.asList(exercises).indexOf(exercise);
                            cals = weight * repsMins * factors[index];
                            cals = ((double) ((int) (10 * cals)) / 10);
                            mCalories.setText(String.valueOf(cals) + " calories");

                            for (int i = 0; i < 12; i++) {
                                startReps[i] = (cals / weight) / factors[i];
                            }

                            mGrid.setAdapter(adapter);
                        } else {
                            cals = Integer.valueOf(mRepsMins.getText().toString());
                            for (int i = 0; i < 12; i++) {
                                int weight = Integer.valueOf(mWeight.getText().toString());
                                startReps[i] = (cals / weight) / factors[i];
                            }
                            mGrid.setAdapter(adapter);
                        }

                    }
                }
        );

        mMode.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (exerciseConversion) {
                            mMinsOrReps.setText("calories");
                            mCalories.setText("");
                            exerciseConversion = false;
                            mMode.setText("Exercise Mode");
                        } else {
                            mMinsOrReps.setText("minutes");
                            mCalories.setText("0 calories");
                            exercise = "jogging";
                            exerciseConversion = true;
                            mMode.setText("Calorie Mode");
                        }
                    }
                }
        );



    }

}
