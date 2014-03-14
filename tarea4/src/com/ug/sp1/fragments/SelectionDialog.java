package com.ug.sp1.fragments;

import com.google.android.gms.maps.GoogleMap;
import com.ug.sp1.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SelectionDialog extends DialogFragment implements DialogInterface.OnClickListener {
	private int item=0;
	private int TypeMap = GoogleMap.MAP_TYPE_NORMAL;
	private GoogleMap map;
    @Override
    
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
     final String[] items = {getString(R.string.mapa_normal), 
    		 				 getString(R.string.mapa_satelite),
    		 				 getString(R.string.mapa_terreno),
    		 			     getString(R.string.mapa_hibrido)};
 
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
 
        builder.setTitle("Selección")
           .setSingleChoiceItems(items, item,this)
           .setPositiveButton("Aceptar", this)
           .setNegativeButton("Cancelar", this);
        
 
        return builder.create();
    }
    
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		
		this.item = item;
	}

	public int getTypeMap() {
		return TypeMap;
	}
	public void setTypeMap(int typeMap) {
		TypeMap = typeMap;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int item) {
		
		
		if(item >=0)
		TypeMap = item+1;
		if(item == -1) 
			map.setMapType(TypeMap);
		
		
	}

	public GoogleMap getMap() {
		return map;
	}

	public void setMap(GoogleMap map) {
		this.map = map;
	}
}
