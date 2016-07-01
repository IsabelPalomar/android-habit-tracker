package android.example.com.habittracker;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvUpdates;
    StringBuffer dbUpdates = new StringBuffer();
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUpdates = (TextView) findViewById(R.id.tvUpdates);

        HabitsDbHelper mDbHelper = new HabitsDbHelper(this);
        res = getResources();

        //Insert 3 new elements in DB and show a message with the new ID's
        long newHabitId1 = insertValue(mDbHelper);
        long newHabitId2 = insertValue(mDbHelper);
        long newHabitId3 = insertValue(mDbHelper);
        dbUpdates.append(String.format(res.getString(R.string.app_message_insert), newHabitId1, newHabitId2, newHabitId3));

        //Show all the elements
        String elementsList = getAllElements(mDbHelper);
        dbUpdates.append(res.getString(R.string.app_message_list_header)).append(elementsList);

        //Update the element where the key is 2
        String updatedRows = String.valueOf(updateNameById(mDbHelper, "Run run!", (int)newHabitId2));
        dbUpdates.append(String.format(res.getString(R.string.app_message_update), updatedRows));

        //Show all the elements to see the change
        String elementsListUpdated = getAllElements(mDbHelper);
        dbUpdates.append(res.getString(R.string.app_message_list_header)).append(elementsListUpdated);

        //Remove all elements in DB
        mDbHelper.removeAllHabits();
        dbUpdates.append(res.getString(R.string.app_message_delete));

        //Close HabitsDbHelper
        mDbHelper.close();

        //Finally, display the text in the TextView
        tvUpdates.setText(dbUpdates.toString());

    }

    /**
     * Insert new value
     */
    public long insertValue(HabitsDbHelper mDbHelper){
        long newHabitId1 = 0;

        try{
            newHabitId1 = mDbHelper.insertHabit(generateRandomValue());
        }catch (Exception e){
            e.printStackTrace();
        }

        return newHabitId1;
    }

    /**
     * Update value by Id
     */
    private int updateNameById(HabitsDbHelper mDbHelper, String newName, int i) {

        int rowsUpdated = 0;

        try {
            rowsUpdated = mDbHelper.updateNameById(newName, i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    /**
     * Read all the values an return the String
     */
    private String getAllElements(HabitsDbHelper mDbHelper) {

        StringBuffer elementsList = new StringBuffer();

        try {
            Cursor c = mDbHelper.getAllHabits();
            if (c.moveToFirst()) {
                do {
                    elementsList.append(String.format(res.getString(R.string.app_message_read), c.getString(0), c.getString(1)));
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsList.toString();
    }

    /**
     *  Create a new map of values (testing)
     */
    private ContentValues generateRandomValue() {

        ContentValues values = new ContentValues();
        Random r = new Random();
        int randomNum = r.nextInt(365 - 1 + 1) + 1;

        values.put(HabitsContract.Habit.COLUMN_NAME_HABIT, getString(R.string.app_message_name_default) + randomNum);
        values.put(HabitsContract.Habit.COLUMN_NAME_DESCRIPTION, getString(R.string.app_message_description_default));
        values.put(HabitsContract.Habit.COLUMN_NAME_DATE, getString(R.string.app_message_timestamp_default));

        return values;
    }

}
