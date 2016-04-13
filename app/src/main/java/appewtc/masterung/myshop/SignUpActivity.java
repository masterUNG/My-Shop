package appewtc.masterung.myshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surnameEditText,
            userEditText, passwordEditText,
            addressEditText, phoneEditText;
    private String nameString, surnameString,
            userString, passwordString,
            addressString, phoneString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

    }   // Main Method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        addressString = addressEditText.getText().toString().trim();
        phoneString = phoneEditText.getText().toString().trim();

        //Check Space
        if (checkSpace()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง",
                    "กรุณากรอกทุกช่อง คะ");
            //Have Space
        } else {
            //No Space
            updateValueToServer();
        }

    }   // clickSign

    private void updateValueToServer() {

        String strURL = "http://swiftcodingthai.com/shop/php_add_user.php";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("Surname", surnameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("Address", addressString)
                .add("Phone", phoneString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(strURL).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    finish();
                } catch (Exception e) {
                    Log.d("MyShop", "error " + e.toString());
                }
            }
        });

    }   // update

    private boolean checkSpace() {
        return nameString.equals("") ||
                surnameString.equals("") ||
                userString.equals("") ||
                passwordString.equals("") ||
                addressString.equals("") ||
                phoneString.equals("");
    }

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        addressEditText = (EditText) findViewById(R.id.editText5);
        phoneEditText = (EditText) findViewById(R.id.editText6);
    }

}   // Main Class
