package com.ug.sp1.fragments;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.ug.sp1.R;
import com.ug.sp1.activities.TiendaActivity;
import com.ug.sp1.data.SQLiteHelper;
import com.ug.sp1.data.Store;


public class TiendaListFragment extends Fragment implements OnItemClickListener {

	
	ListView list;
	Menu menu;
	private List<String> listado = new ArrayList<String>();
	private ArrayList<String> tiendas_nombres = new ArrayList<String>();
	private ArrayList<String> tiendas_detalles = new ArrayList<String>();
	private ArrayList<Store> datos_tienda = new ArrayList<Store>();
	
	


	private int TypeMap = MapaFragment.NORMAL;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		JSONParser jsonstore = new JSONParser();
		AssetManager assetManager = getActivity().getAssets();
		InputStream input;
		try {
			input = assetManager.open("datos.json");
			int sizefile = input.available();
		    byte[] buffer = new byte[sizefile];
		    input.read(buffer);
		    input.close();
		    String text = new String(buffer);
		    JSONArray arraydatos = (JSONArray) jsonstore.parse(text);
		    
		    for(Object obj : arraydatos){
		    	JSONObject objdato = (JSONObject) obj;
		    	if(objdato.get("type").toString().equals("store")){
		    		JSONObject loc = (JSONObject) objdato.get("location");
		    		LatLng location = new LatLng(Double.parseDouble(loc.get("lat").toString()), Double.parseDouble(loc.get("lon").toString()));
			    	Store tienda = new Store( (String) objdato.get("name"),
			    			 				  (String) objdato.get("address"),
			    			 				  (String) objdato.get("phone"),
			    			 				  (String) objdato.get("hoursOfOperaion"),
			    			 				  (String) objdato.get("url"),
			    			 				  (String) objdato.get("email"),
			    			 				  location
			    			                 );
			    	//tienda.setId(datos_tienda.size()+1);
			    	//Toast.makeText(getActivity(), ""+tienda.getId(), Toast.LENGTH_SHORT).show();
			        datos_tienda.add(tienda);
			        
			        
		    	}
		    	
		    }
		    SQLiteHelper dbh = new SQLiteHelper(getActivity(), SQLiteHelper.DB, null,SQLiteHelper.VERSION);
			   
		     SQLiteDatabase db = dbh.getWritableDatabase();
		    Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteHelper.STORE,null);
		     
		    
		    //Toast.makeText(getActivity(), ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
		    if(cursor.getCount()==0){
		    for(int i=0;i<datos_tienda.size();i++){
		    	if(db!=null){
		        	db.insertWithOnConflict(SQLiteHelper.STORE, null, datos_tienda.get(i).getValues(), SQLiteDatabase.CONFLICT_IGNORE);
		        	
		        }
		    }
		    for(int i = 0; i < datos_tienda.size(); i++){
				tiendas_nombres.add(datos_tienda.get(i).getTitulo());				
			}
		    }else{
		    	if(cursor.moveToFirst()){
		    		
		    		do{
		    			tiendas_nombres.add(cursor.getString(cursor.getColumnIndex("name")));
		    		}while(cursor.moveToNext());
		    	}
		    }
		   
		    db.close();	
						
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 		
		
		
		
		
		
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity() , 
												android.R.layout.simple_list_item_1, 
												tiendas_nombres);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_list_tiendas,container,false);
	    list = (ListView) view.findViewById(R.id.listView);
		return view;
	}

	
	
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(getActivity(),TiendaActivity.class);
		
		Store tiendas = datos_tienda.get(pos);				
		intent.putExtra(TiendaActivity.TITULO, tiendas.getTitulo());
		intent.putExtra(TiendaActivity.TELEFONO, tiendas.getTelefono());
		intent.putExtra(TiendaActivity.DATOS,  tiendas.getDireccion() +"\n"+
											   tiendas.getTelefono()  +"\n"+
											   tiendas.getHorarios()+ "\n"+
											   tiendas.getWebsite() + "\n" +
											   tiendas.getEmail() + "\n");
		startActivity(intent);
		
	}


	public int getTypeMap() {
		return TypeMap;
	}


	public void setTypeMap(int typeMap) {
		TypeMap = typeMap;
	}
	
	public ArrayList<Store> getDatos_tienda() {
		return datos_tienda;
	}
}

