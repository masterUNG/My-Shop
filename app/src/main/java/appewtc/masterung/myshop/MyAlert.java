package appewtc.masterung.myshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 4/13/16 AD.
 */
public class MyAlert {

    public void myDialog(Context context,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.danger);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}   // Main Class
