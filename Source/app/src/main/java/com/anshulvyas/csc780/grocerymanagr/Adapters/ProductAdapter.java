package com.anshulvyas.csc780.grocerymanagr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anshulvyas.csc780.grocerymanagr.HomeFragment;
import com.anshulvyas.csc780.grocerymanagr.Model.DBManager;
import com.anshulvyas.csc780.grocerymanagr.Product;
import com.anshulvyas.csc780.grocerymanagr.R;
import com.anshulvyas.csc780.grocerymanagr.Util;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by av7 on 11/12/15.
 */

/**
 * Adapter for the listView of HomeFragment and Timeline Fragment.
 * Reused the code for timeline fragment.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    private List<Product> productList;
    Context myContext;
    int mResource;


    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        Log.i("~!@#PRODUCTADAPTER", "Constructor reached!");
        this.myContext = context;
        this.productList = objects;
        this.mResource = resource;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        Log.i("~!@#PRODUCTADAPTER", "getView reached");
        if(convertView == null) {
            Log.i("~!@#PRODUCTADAPTER", "convert view is null");

            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Log.i("~!@#PRODUCTADAPTER", "convert view is not null");
        Product productObj = productList.get(position);

        if(productObj != null) {
            Log.i("~!@#PRODUCTADAPTER", productObj.toString());
            TextView productName = (TextView) convertView.findViewById(R.id.textView_product_name);
            TextView productCategory = (TextView) convertView.findViewById(R.id.textView_product_category);
            TextView productExpiry = (TextView) convertView.findViewById(R.id.textView_product_expiry);

            productName.setText(productObj.getProductName());
            productCategory.setText(" (" + productObj.getCategory() + ") ");

            if(productObj.isExpired()) {
                productExpiry.setText("Expired");
            } else {
                HomeFragment homeFragment = new HomeFragment();
                productExpiry.setText("expire in " + homeFragment.getLeftDays(productObj) + " days");
            }
        }
        return convertView;
    }

}
