package com.ug.wall_k;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private List<String> listado = new ArrayList<String>();
	private ArrayList<String> tiendas_nombres = new ArrayList<String>();
	private ArrayList<String> tiendas_detalles = new ArrayList<String>();
	private ArrayList<Tiendas> datos_tienda = new ArrayList<Tiendas>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listview_listado = (ListView) findViewById(android.R.id.list);
		
		
		listview_listado.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),TiendaActivity.class);
				Tiendas tiendas = datos_tienda.get(pos);				
				intent.putExtra(TiendaActivity.TITULO, tiendas.getTitulo());
				intent.putExtra(TiendaActivity.TELEFONO, tiendas.getTelefono());
				intent.putExtra(TiendaActivity.DATOS,  tiendas.getDireccion() +"\n"+
													   tiendas.getTelefono()  +"\n"+
													   tiendas.getHorarios().get(0)+ "\n"+
													   tiendas.getWebsite() + "\n" +
													   tiendas.getEmail() + "\n");
				startActivity(intent);
				//.makeText(getBaseContext(), ""+arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
				
			}
			
		});
		
		datos_tienda.add(new Tiendas("Tienda de Articulos para el Hogar",
									 "Zona1",
									 "+50255512345",
									 new ArrayList<String>(Arrays.asList(new String[]{"Lunes 8:00 - 18:00",})),
									 "http://hogar.com",
									 "correo@mail.com"
		                            )
		                );
		datos_tienda.add(new Tiendas("Tienda de Muebles",
									 "Zona2",
									 "+50255543216",
									 new ArrayList<String>(Arrays.asList(new String[]{"Lunes 8:00 - 18:00",})),
									 "http://muebles.com",
									 "correo.mueble@mail.com"
			               )
					);
		datos_tienda.add(new Tiendas("Tienda de Jugetes",
									 "Zona2",
									 "+50255511223",
									 new ArrayList<String>(Arrays.asList(new String[]{"Lunes 8:00 - 18:00",})),
									 "http://juguetes.com",
									 "correo.juguete@mail.com"
					      )
					);
		datos_tienda.add(new Tiendas("Tienda de Ropa",
									 "Zona2",
									 "+50255500998",
									 new ArrayList<String>(Arrays.asList(new String[]{"Lunes 8:00 - 18:00",})),
									 "http://muebles.com",
									 "correo@mail.com"
					      )
					);
		datos_tienda.add(new Tiendas("Tienda de Vehiculos",
									 "Zona2",
									 "+50255588321",
									 new ArrayList<String>(Arrays.asList(new String[]{"Lunes 8:00 - 18:00",})),
									 "http://muebles.com",
									 "correo@mail.com"
					      )
					);
		tiendas_nombres.add(datos_tienda.get(0).getTitulo());		
		tiendas_nombres.add(datos_tienda.get(1).getTitulo());
		tiendas_nombres.add(datos_tienda.get(2).getTitulo());		
		tiendas_nombres.add(datos_tienda.get(3).getTitulo());
		tiendas_nombres.add(datos_tienda.get(4).getTitulo());
		
		
		
		ArrayAdapter adapter = new ArrayAdapter(this , 
												android.R.layout.simple_list_item_1, 
												tiendas_nombres);
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(),FotoActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
		
	}

	

	


}
