#  *Habit Tracker*

Functionality:

* [x] There exists a contract class that defines name of table and constants and inside the contract class, there is an inner class for each table created.
* [x] There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade() and contains an implemented deleteDatabase().
* [x] There is a single insert method that adds at least two values of two different datatypes (e.g. INTEGER, STRING) into the database using a ContentValues object and the insert() method.
* [x] There is a single read method that returns a Cursor object. It should get the data repository in read and use the query() method to retrieve at least one column of data.
* [x] There is a single delete method that deletes all the entries from the table.
* [x] There is a single update method that updates at least one value in one column in the table.
* [x] No external libraries (e.g. Realm) or the use of Content Providers should be used. All data insertion, reading, deletion, and updating should be done using direct method calls to the SQLite database in the SQLiteOpenHelper class.

Code Readability:

* [x] All variables, methods, and resource IDs are descriptively named such that another developer reading the code can easily understand their function.
* [x] The code is properly formatted i.e. there are no unnecessary blank lines; there are no unused variables or methods; there is no commented out code. The code also has proper indentation when defining variables and methods.
