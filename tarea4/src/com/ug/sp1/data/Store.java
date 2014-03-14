package com.ug.sp1.data;

import android.content.ContentValues;

import com.google.android.gms.maps.model.LatLng;


public class Store {
	private int Id;
	private String Titulo;	
	private String Direccion;
	private String Telefono;
	private String Horarios;
	private String Website;
	private String Email;
	private LatLng Location;
	
	public Store(String Titulo,String Direccion,String Telefono,String Horarios,String Website,String Email,LatLng Location){
		this.Titulo = Titulo;
		this.Direccion = Direccion;
		this.Telefono = Telefono;
		this.Horarios = Horarios;
		this.Website = Website;
		this.Email = Email;
		this.setLocation(Location);
		
	}
	
	public ContentValues getValues(){
		ContentValues values = new ContentValues();
		//values.put(SQLiteHelper.KEY_ID, Titulo);
		values.put(SQLiteHelper.KEY_NAME, Titulo);
		values.put(SQLiteHelper.KEY_ADDRESS, Direccion);
		values.put(SQLiteHelper.KEY_PHONE, Telefono);
		values.put(SQLiteHelper.KEY_HOURS, Horarios);
		values.put(SQLiteHelper.KEY_WEB, Website);
		values.put(SQLiteHelper.KEY_EMAIL, Email);
		values.put(SQLiteHelper.KEY_LAT, Location.latitude);
		values.put(SQLiteHelper.KEY_LNG, Location.longitude);
		return values;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getHorarios() {
		return Horarios;
	}
	public void setHorarios(String horarios) {
		Horarios = horarios;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public LatLng getLocation() {
		return Location;
	}
	public void setLocation(LatLng location) {
		this.Location = location;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
}

