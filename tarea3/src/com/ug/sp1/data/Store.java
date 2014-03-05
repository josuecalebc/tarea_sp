package com.ug.sp1.data;

import java.util.ArrayList;

public class Store {
	private String Titulo;	
	private String Direccion;
	private String Telefono;
	private String Horarios;
	private String Website;
	private String Email;
	
	public Store(String Titulo,String Direccion,String Telefono,String Horarios,String Website,String Email){
		this.Titulo = Titulo;
		this.Direccion = Direccion;
		this.Telefono = Telefono;
		this.Horarios = Horarios;
		this.Website = Website;
		this.Email = Email;
		
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
	
}

