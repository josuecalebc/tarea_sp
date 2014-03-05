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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import com.ug.sp1.R;
import com.ug.sp1.activities.TiendaActivity;
import com.ug.sp1.data.Store;


public class TiendaListFragment extends Fragment implements OnItemClickListener {

	
	ListView list;
	private List<String> listado = new ArrayList<String>();
	private ArrayList<String> tiendas_nombres = new ArrayList<String>();
	private ArrayList<String> tiendas_detalles = new ArrayList<String>();
	private ArrayList<Store> datos_tienda = new ArrayList<Store>();
	

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
		    	Store tienda = new Store( (String) objdato.get("name"),
		    			 				  (String) objdato.get("address"),
		    			 				  (String) objdato.get("phone"),
		    			 				  (String) objdato.get("hoursOfOperaion"),
		    			 				  (String) objdato.get("url"),
		    			 				  (String) objdato.get("email")
		    			                 );
		        datos_tienda.add(tienda);
		    	}
		    	
		    }
			
			Log.d(null, text)	;		
						
			
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
		
		
		datos_tienda.add(new Store("Tienda de Articulos para el Hogar",
									 "Zona1",
									 "+50255512345",
									 "Lunes 8:00 - 18:00",
									 "http://hogar.com",
									 "correo@mail.com"
		                            )
		                );
		datos_tienda.add(new Store("Tienda de Muebles",
									 "Zona2",
									 "+50255543216",
									 "Lunes 8:00 - 18:00",
									 "http://muebles.com",
									 "correo.mueble@mail.com"
			               )
					);
		datos_tienda.add(new Store("Tienda de Jugetes",
									 "Zona2",
									 "+50255511223",
									 "Lunes 8:00 - 18:00",
									 "http://juguetes.com",
									 "correo.juguete@mail.com"
					      )
					);
		datos_tienda.add(new Store("Tienda de Ropa",
									 "Zona2",
									 "+50255500998",
									 "Lunes 8:00 - 18:00",
									 "http://muebles.com",
									 "correo@mail.com"
					      )
					);
		datos_tienda.add(new Store("Tienda de Vehiculos",
									 "Zona2",
									 "+50255588321",
									 "Lunes 8:00 - 18:00",
									 "http://muebles.com",
									 "correo@mail.com"
					      )
					);
		for(int i = 0; i < datos_tienda.size(); i++){
		tiendas_nombres.add(datos_tienda.get(i).getTitulo());				
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
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getActivity().getMenuInflater().inflate(R.menu.main, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		return super.onOptionsItemSelected(item);
		
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

}

