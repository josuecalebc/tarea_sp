package com.ug.sp1.fragments;







import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ug.sp1.R;
import com.ug.sp1.data.SQLiteHelper;

public class MapaFragment extends SupportMapFragment implements LocationListener, OnMapLongClickListener{
	public final static int SATELITAL = GoogleMap.MAP_TYPE_SATELLITE;
	public final static int NORMAL = GoogleMap.MAP_TYPE_NORMAL;
	public final static int HIBRIDO = GoogleMap.MAP_TYPE_HYBRID;
	public final static int TERRENO = GoogleMap.MAP_TYPE_TERRAIN;
	public static int TYPE_MAP = NORMAL;
	private GoogleMap map;
	private Bundle savedInstanceSaved;
	private double latitud =14.62 ,longitud=-90.56;
	public static final LatLng GUATEMALA = new LatLng(14.62, -90.56);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.savedInstanceSaved = savedInstanceState;
		
		
	}
	


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setupMap();
		
	}
	
	public void addMarker(LatLng location,String title){
		map.addMarker(new MarkerOptions().position(location)).setTitle(title);
	}
	public void ChangeMapType(int type){
		map.setMapType(type);
		MapaFragment.TYPE_MAP = type;
	}
	private void setupMap() {
		if(map == null){
			map = getMap();
			if(map != null){
				if(savedInstanceSaved == null){
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATEMALA, 10));
					
					map.setOnMapLongClickListener(this);
					map.setMyLocationEnabled(true);
					//Crea el objeto que gestiona las localizaciones
				       LocationManager handle = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
				    
				       Criteria c = new Criteria();
				       c.setAccuracy(Criteria.ACCURACY_FINE);
				       //obtiene el mejor proveedor en función del criterio asignado
				       //(la mejor precisión posible)
				       String provider = handle.getBestProvider(c, true);
				       
				       //Se activan las notificaciones de localización con los parámetros: proveedor, tiempo mínimo de actualización, distancia mínima, Locationlistener
				       handle.requestLocationUpdates(provider, 10000, 1, this);
				       //Obtenemos la última posición conocida dada por el proveedor
				       Location loc = handle.getLastKnownLocation(provider);  
					   
					if(loc != null){
						latitud = loc.getLatitude();
						longitud = loc.getLongitude();
						LatLng ubicacion = new LatLng(latitud,longitud);
						CameraPosition camPos = new CameraPosition.Builder()
							.target(ubicacion)
							.zoom(10)
							.tilt(70)
							.build();
						CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
						map.animateCamera(camUpd3);
																
						
						SQLiteHelper dbh = new SQLiteHelper(getActivity(), SQLiteHelper.DB, null, SQLiteHelper.VERSION);						  
					    SQLiteDatabase db = dbh.getWritableDatabase();
					    Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteHelper.STORE,null);
					    if(cursor.moveToFirst()){
					    	do{
					    		double lat = Double.valueOf(cursor.getString(cursor.getColumnIndex("lat")));
					    		double lng = Double.valueOf(cursor.getString(cursor.getColumnIndex("lng")));
					    		String name = cursor.getString(cursor.getColumnIndex("name"));
					    		map.addMarker(new MarkerOptions().position(new LatLng(lat, lng))).setTitle("name");
					    	}while(cursor.moveToNext());
					    }
					    db.close();
					    
						
					}
					
				}
				
				map.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}
	
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_mapa,container,false);
	}*/
	
	

	
	
	
	
	
	@Override
	public void onLocationChanged(Location location) {
		
		latitud = location.getLatitude();
	  	longitud = location.getLongitude();
		
	}
	
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMapLongClick(LatLng latLng)
    {
        SelectionDialog dialog = new SelectionDialog();
        dialog.setMap(map);
        dialog.setItem(map.getMapType()-1);
        
        dialog.show(getFragmentManager(),null);
        
        //map.setMapType(dialog.getTypeMap());
		
		//Toast.makeText(getActivity(), ""+dialog.getTypeMap(), 4).show();
    }
}
