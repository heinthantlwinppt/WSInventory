package com.ppt.wsinventory;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.util.Utility;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by User on 31/01/2018.
 */

public class ProductInfoDialog extends DialogFragment {
    TextView productname;
    ImageView productphoto;
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.product_info_dialog,container,false);
       productname = rootView.findViewById(R.id.productName);
       productphoto = rootView.findViewById(R.id.productPhoto);
       Bundle intent = getArguments();
        InventoryAllProducts inventoryAllProducts = new InventoryAllProducts();

        inventoryAllProducts.setProduct_name( intent.getString(InventoryAllProducts.COLUMN_PRODUCT_NAME));
        inventoryAllProducts.setPhoto_type(intent.getString(InventoryAllProducts.COLUMN_PHOTO_TYPE));
        inventoryAllProducts.setPhoto_name(intent.getString(InventoryAllProducts.COLUMN_PHOTO_NAME));

        productname.setText(inventoryAllProducts.getProduct_name());
        File dir = Utility.creatdesignfolder(inventoryAllProducts.getPhoto_type());
        File my_file = new File(dir, inventoryAllProducts.getPhoto_name() + ".png");

        if(my_file.exists()) {
            Picasso.with(mContext).load(my_file).resize(300, 300).into(productphoto);
        }


       return rootView;
    }


}
