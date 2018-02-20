package com.ppt.wsinventory;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddNewProductFragment extends Fragment {
    ImageView imageView;
    Spinner design,gold,smith,location,counter,tray;
    ViewGroup rootView;
    DbAccess dbaccess;
    public AddNewProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =(ViewGroup) inflater.inflate(R.layout.fragment_add_new_product, container, false);

        design = (Spinner)rootView.findViewById(R.id.product);
        gold =(Spinner)rootView.findViewById(R.id.gold);
        smith = (Spinner)rootView.findViewById(R.id.smith);
        location =(Spinner)rootView.findViewById(R.id.location);
        counter =(Spinner)rootView.findViewById(R.id.bin);
        tray=(Spinner)rootView.findViewById(R.id.pallet);
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        Button click =(Button)rootView.findViewById(R.id.camera);
        imageView = (ImageView)rootView.findViewById(R.id.image);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(c,0);

            }
        });
        LoadSpinnerData();


        return rootView;
    }

    private void LoadSpinnerData() {

        List<String> designList = dbaccess.getALLProductList();
        List<String> goldlist = dbaccess.getALLGoldtList();
        List<String> smithlist = dbaccess.getALLSmithList();
        List<String> locationList = dbaccess.getALLLocationList();

        ArrayAdapter<String> designListadapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, designList);
        ArrayAdapter<String> goldListadapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, goldlist);
        ArrayAdapter<String> smithListadapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, smithlist);
        ArrayAdapter<String> locationListadapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, locationList);

                designListadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        design.setAdapter(designListadapter);

        design.setAdapter(designListadapter);
        gold.setAdapter(goldListadapter);

//        smith.setAdapter(smithListadapter);
        location.setAdapter(locationListadapter);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"  trur " +location.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

                String locationname = location.getSelectedItem().toString();

                    String location_id = dbaccess.getLocaionId(locationname);
                    Toast.makeText(getContext(),locationname+" * " + location_id,Toast.LENGTH_LONG).show();

                    List<String> containerList = dbaccess.getALLContainerList(location_id);

                    ArrayAdapter<String> counterListadapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, containerList);

                    List<String> trayList = dbaccess.getALLTrayList(location_id);
                Toast.makeText(getContext(),"True aung " + trayList.size(),Toast.LENGTH_LONG).show();

                    ArrayAdapter<String> trayListadapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, trayList);

                    Toast.makeText(getContext(),"True aung " + trayList.size(),Toast.LENGTH_LONG).show();
                    counter.setAdapter(counterListadapter);

                    tray.setAdapter(trayListadapter);

                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap m= (Bitmap)data.getExtras().get("data");

        imageView.setImageBitmap(m);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(getContext());
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout
                        , null));
                ImageView img = (ImageView)settingsDialog.findViewById(R.id.image_view);
                img.setImageBitmap(m);
                settingsDialog.show();
            }
        });
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(m,filePathColumn,null,null,null);
//        int columnIndex =cursor.getColumnIndex(filePathColumn[0]);
//
//        String filepath = cursor/gets

    }
}
