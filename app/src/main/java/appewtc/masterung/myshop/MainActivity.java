package appewtc.masterung.myshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;

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

    }   // onCreate

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

                }   //for

            } catch (Exception e) {
                Log.d("masterUNG", "onPost ==> " + e.toString());
            }

        }   // onPostExecute

    }   // MySynJSON Class

    private void checkPassword(String strName, String strSurname, String strUser, String strPassword) {
        
    }

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
            MySynJSON mySynJSON = new MySynJSON();
            mySynJSON.execute();
        }

    }   // clickSignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this,
                SignUpActivity.class));
    }   // Main Method

}  // Main Class
