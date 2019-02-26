package anomecon.skelekey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // region Initialize Database values
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManagement.db";
    // endregion

    // region Declare Table names
    private static final String USER_TABLE = "User";
    private static final String CONTENT_TABLE = "Content";
    //endregion

    // region User Table Column names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_EMAIL = "user_email";
    //endregion

    // region Content Table Column names
    private static final String COLUMN_CONTENT_ID = "content_id";
    private static final String COLUMN_CONTENT_USER_ID = "content_user_id";
    private static final String COLUMN_CONTENT_MEDIA = "content_media";
    private static final String COLUMN_CONTENT_USER_NAME = "content_user_name";
    private static final String COLUMN_CONTENT_PASSWORD = "content_password";
    private static final String COLUMN_CONTENT_LAST_EDIT_TIME = "content_lastEditTime";
    private static final String COLUMN_CONTENT_CREATION_TIME = "content_creationTime";
    //endregion

    // region USER_TABLE sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    //endregion USER_TABLE sql query

    //region CONTENT_TABLE sql query
    private String CREATE_CONTENT_TABLE = "CREATE TABLE " + CONTENT_TABLE + "("
            + COLUMN_CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CONTENT_MEDIA + " BLOB," + COLUMN_CONTENT_USER_NAME + " TEXT,"
            + COLUMN_CONTENT_PASSWORD + " TEXT, " + COLUMN_CONTENT_LAST_EDIT_TIME + " INTEGER,"
            + COLUMN_CONTENT_CREATION_TIME + " INTEGER,"
            + COLUMN_CONTENT_USER_ID + " INTEGER, "
            + " FOREIGN KEY ("+COLUMN_CONTENT_USER_ID+") REFERENCES "+USER_TABLE+"("+COLUMN_USER_ID +
            "));";
    //endregion CONTENT_TABLE sql query

    // region table drops
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;
    private String DROP_CONTENT_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;
    //endregion

    //region Constructor
    /**
     * Constructor
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //endregion

    //region onCreate & onUpdate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CONTENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CONTENT_TABLE);

        // Create tables again
        onCreate(db);
    }
    //endregion

    //region User CHUD
    public List<User> getAllUser(){
        // array of columns to fetch
        String[] userColumns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_EMAIL
        };

        // sorting orders
        String sortOrder = COLUMN_USER_NAME + " ASC";

        List<User> userList = new ArrayList<>();

        //open database
        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * We'll use the query function to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(USER_TABLE, //Table to query
                userColumns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(USER_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check if user exists or not
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_name = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(USER_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method is to create a user record
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    /**
     * This method to update user record
     * @param user
     */
    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // update row
        db.update(USER_TABLE, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(USER_TABLE, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    //endregion

    //region Content CHUD
    /**
     * This method is to fetch all content data and return the list of content records
     * @return list
     */
    public List<Content> getAllContent(){
        String[] contentColumns = {
                COLUMN_CONTENT_ID,
                COLUMN_CONTENT_MEDIA,
                COLUMN_CONTENT_USER_NAME,
                COLUMN_CONTENT_PASSWORD
        };

        // sorting orders
        String sortOrder = COLUMN_CONTENT_MEDIA + " ASC";

        List<Content> contentList = new ArrayList<Content>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CONTENT_TABLE,
                contentColumns,
                null,
                null,
                null,
                null,
                sortOrder);

        //Traverse the list
        if (cursor.moveToFirst()){
            do {
                Content content = new Content();
                content.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_ID))));
                content.setContentMedia(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_MEDIA)));
                content.setContentUserName(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_USER_NAME)));
                content.setContentPassword(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_PASSWORD)));
                contentList.add(content);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return contentList;
    }

    /**
     * This method to check content exist or not, will cast contentID as String use
     * @param content_id
     * @return true/false
     */
    public boolean checkContent(String content_id){

        //array of columns to fetch
        String[] columns = {
          COLUMN_CONTENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //criteria
        String selection = COLUMN_CONTENT_ID + " = ?";

        //argument
        String[] selectionArgs = {content_id};

        Cursor cursor = db.query(CONTENT_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method is to create a content record
     * @param content
     */
    public void addContent(Content content){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT_MEDIA, content.getContentMedia());
        values.put(COLUMN_CONTENT_USER_NAME, content.getContentUserName());
        values.put(COLUMN_CONTENT_PASSWORD, content.getContentPassword());

        db.insert(CONTENT_TABLE, null, values);
        db.close();
    }

    /**
     * This method to update content record
     * @param content
     */
    public void updateContent(Content content){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT_MEDIA, content.getContentMedia());
        values.put(COLUMN_CONTENT_USER_NAME, content.getContentUserName());
        values.put(COLUMN_CONTENT_PASSWORD, content.getContentPassword());

        db.update(CONTENT_TABLE, values, COLUMN_CONTENT_ID + " = ?",
                new String[]{String.valueOf(content.getId())});
        db.close();
    }

    /**
     * This method is to delete content record
     *
     * @param content
     */
    public void deleteContent(Content content){
        SQLiteDatabase db = this.getWritableDatabase();

        //delete record
        db.delete(CONTENT_TABLE, COLUMN_CONTENT_ID + " = ?",
                new String[]{String.valueOf(content.getId())});
        db.close();
    }
    //endregion
}