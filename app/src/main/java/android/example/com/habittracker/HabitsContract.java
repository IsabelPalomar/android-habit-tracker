package android.example.com.habittracker;

import android.provider.BaseColumns;

public class HabitsContract {
    public HabitsContract() {}

    public static abstract class Habit implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String COLUMN_NAME_HABIT_ID = "habitId";
        public static final String COLUMN_NAME_HABIT = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
        public static String COLUMN_NAME_NULLABLE = null;
    }
}