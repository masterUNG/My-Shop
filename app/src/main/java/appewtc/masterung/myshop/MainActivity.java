package appewtc.masterung.myshop;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText7);
        passwordEditText = (EditText) findViewById(R.id.editText8);

        //Request SQLite
        mySQLite = new MySQLite(this);

        //Synchronize mySQL to SQLite
        synAndDelete();

    }   // onCreate

    private void synAndDelete() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MySQLite.user_table, null, null);

        MySynJSON mySynJSON = new MySynJSON();
        mySynJSON.execute();

    }   // synAndDelete

    //Create Inner Class for Connected JSON
    public class MySynJSON extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            try {
                String strURL = "http://swiftcodingthai.com/shop/php_get_user.php";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("masterUNG", "doInBack ==> " + e.toString());
                return null;
            }
        }   // doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("masterUNG", "strJSON ==> " + s);
            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MySQLite.column_Name);
                    String strSurname = jsonObject.getString(MySQLite.column_Name);
                    String strUser = jsonObject.getString(MySQLite.column_Name);
                    String strPassword = jsonObject.getString(MySQLite.column_Name);
                    String strAddress = jsonObject.getString(MySQLite.column_Name);
                    String strPhone = jsonObject.getString(MySQLite.column_Name);
                    mySQLite.addNewUser(strName, strSurname, strUser, strPassword,
                            strAddress, strPhone);
                }   //for
                Toast.makeText(MainActivity.this, "Synchronize mySQL to SQLite Finish",
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.d("masterUNG", "onPost ==> " + e.toString());
            }

        }   // onPostExecute

    }   // MySynJSON Class



    public void clickSignInMain(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = userEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง",
                    "กรุณากรอกทุกช่อง คะ");
        } else {
            //No Space

        }

    }   // clickSignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,
                SignUpActivity.class));
    }   // Main Method

}  // Main Class
