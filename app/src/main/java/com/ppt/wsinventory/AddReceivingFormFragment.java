package com.ppt.wsinventory;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddReceivingFormFragment extends Fragment
{
    private ViewGroup rootView;
    Button save;
    Button add;
    TextView smithname;
    GlobalVariables appContext;

    public AddReceivingFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appContext =(GlobalVariables)getContext().getApplicationContext();

        String smith_name = appContext.getSmith_name();
        appContext.setSmith_name(smith_name);
         rootView= (ViewGroup) inflater.inflate(R.layout.fragment_add_receiving_form, container, false);
         smithname =(TextView)rootView.findViewById(R.id.smith_name);
         smithname.setText(smith_name);
         save =(Button)rootView.findViewById(R.id.save_receive);
         add =(Button)rootView.findViewById(R.id.add_receive);
         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
//                 startActivity(new Intent(getContext(),AddNewProduct.class,Intent.FLAG_ACTIVITY}_NEW_TASK));
                 Intent intent = new Intent(getContext(),AddNewProduct.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                 startActivity(intent);
             }
         });
         return rootView;
    }
}
