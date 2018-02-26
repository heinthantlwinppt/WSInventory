package com.ppt.wsinventory.common;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppt.wsinventory.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryTraysFragment extends Fragment {

    public InventoryTraysFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory_trays, container, false);
    }
}
