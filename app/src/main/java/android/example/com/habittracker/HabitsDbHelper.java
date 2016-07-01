package android.example.com.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class HabitsDbHelper extends SQLiteOpenHelper {

    private static final String INTEGER_PK = " INTEGER PRIMARY KEY";
    private static final String TEXT_TYPE = " TEXT";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_HABITS =
            "CREATE TABLE " + HabitsContract.Habit.TABLE_NAME + " (" +
                    HabitsContract.Habit.COLUMN_NAME_HABIT_ID + INTEGER_PK + COMMA_SEP +
                    HabitsContract.Habit.COLUMN_NAME_HABIT + TEXT_TYPE + COMMA_SEP +
                    HabitsContract.Habit.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    HabitsContract.Habit.COLUMN_NAME_DATE + TIMESTAMP_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HabitsContract.Habit.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Habits.db";

    public HabitsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HABITS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Remove the table (Just for testing purposes)
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Insert the new row, returning the primary key value of the new row
     */
    public long insertHabit(ContentValues values) {

        SQLiteDatabase db = this.getWritableDatabase();
        long newRowId;

        newRowId = db.insert(
                HabitsContract.Habit.TABLE_NAME,
                HabitsContract.Habit.COLUMN_NAME_NULLABLE,
                values);

        return newRowId;
    }

    /**
     * Update specific element from a Database
     */
    public int updateNameById(String name, int rowId) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitsContract.Habit.COLUMN_NAME_HABIT, name);

        String selection = HabitsContract.Habit.COLUMN_NAME_HABIT_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(rowId)};

        int count = db.update(
                HabitsContract.Habit.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    /**
     * Delete specific element from a Database
     */
    public void removeHabit(int rowId) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = HabitsContract.Habit.COLUMN_NAME_HABIT_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(rowId)};
        db.delete(HabitsContract.Habit.TABLE_NAME, selection, selectionArgs);

    }

    /**
     * Fetch all habits stored in database
     */
    public void showAllHabits() {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                HabitsContract.Habit._ID,
                HabitsContract.Habit.COLUMN_NAME_HABIT,
                HabitsContract.Habit.COLUMN_NAME_DESCRIPTION,
        };

        String sortOrder =
                HabitsContract.Habit.COLUMN_NAME_HABIT_ID + " DESC";

        Cursor c = db.query(
                HabitsContract.Habit.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
    }
}


