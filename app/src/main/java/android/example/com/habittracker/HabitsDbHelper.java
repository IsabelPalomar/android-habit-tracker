package android.example.com.habittracker;

import android.content.ContentValues;
import android.content.Context;
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
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // Insert the new row, returning the primary key value of the new row
    public long insertHabit(ContentValues values) {

        SQLiteDatabase db = this.getWritableDatabase();
        long newRowId;

        newRowId = db.insert(
                HabitsContract.Habit.TABLE_NAME,
                HabitsContract.Habit.COLUMN_NAME_NULLABLE,
                values);

        return newRowId;
    }

    //Delete specific element from a Database
    public void removeElement(int rowId) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = HabitsContract.Habit.COLUMN_NAME_HABIT_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };
        db.delete(HabitsContract.Habit.TABLE_NAME, selection, selectionArgs);

    }

    public int updateByName(String name, int rowId) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitsContract.Habit.COLUMN_NAME_HABIT, name);

        String selection = HabitsContract.Habit.COLUMN_NAME_HABIT_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(rowId) };

        int count = db.update(
                HabitsContract.Habit.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;

    }
}


