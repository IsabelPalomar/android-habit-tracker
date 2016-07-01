package android.example.com.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvUpdates;
    StringBuffer dbUpdates = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUpdates = (TextView) findViewById(R.id.tvUpdates);
        dbUpdates.append(tvUpdates.getText().toString());

        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);

        //Inserts a new element in DB
        ContentValues newHabitValues = generateRandomValue();
        long newHabitId = mDbHelper.insertHabit(newHabitValues);
        dbUpdates.append("\nNew element created with ID: " + String.valueOf(newHabitId));

        //Remove element in DB
        mDbHelper.removeElement(2);

        //Update habits where name like "Run"
        int updatedRows = mDbHelper.updateByName("Run run!", 4);
        dbUpdates.append("\nNew element updated: " + String.valueOf(updatedRows));

        tvUpdates.setText(dbUpdates.toString());

    }

    // Create a new map of values, where column names are the keys
    private ContentValues generateRandomValue() {

        ContentValues values = new ContentValues();
        Random r = new Random();
        int randomNum = r.nextInt(365 - 1 + 1) + 1;

        values.put(HabitsContract.Habit.COLUMN_NAME_HABIT, "Run day " + randomNum );
        values.put(HabitsContract.Habit.COLUMN_NAME_DESCRIPTION, "Run 5k");
        values.put(HabitsContract.Habit.COLUMN_NAME_DATE, "time('now')");

        return values;
    }

}
