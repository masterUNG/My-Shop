package appewtc.masterung.myshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 4/16/16 AD.
 */
public class ProductAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] iconStrings, titleStrings;

    public ProductAdapter(Context context,
                          String[] iconStrings,
                          String[] titleStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.product_listview, viewGroup, false);

        TextView titleTextView = (TextView) view1.findViewById(R.id.textView8);
        titleTextView.setText(titleStrings[i]);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView2);
        Picasso.with(context).load(iconStrings[i]).resize(100,140).into(iconImageView);

        return view1;
    }
}   // Main Class
