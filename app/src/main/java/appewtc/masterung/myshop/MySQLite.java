package appewtc.masterung.myshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 4/15/16 AD.
 */
public class MySQLite {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_Name = "Name";
    public static final String column_Surname = "Surname";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Address = "Address";
    public static final String column_Phone = "Phone";

    public MySQLite(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
    }   // Constructor

    public long addNewUser(String strName,
                           String strSurname,
                           String strUser,
                           String strPassword,
                           String strAddress,
                           String strPhone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_Surname, strSurname);
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);
        contentValues.put(column_Address, strAddress);
        contentValues.put(column_Phone, strPhone);
        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

}   // Main Class
