package com.ug.sp1.data;

import java.util.ArrayList;

public class Photo {
	
	private String url;
	private String description;
	private ArrayList<Comment> listcomment;
	private long numero_favoritos;
	
	public Photo(String url, String description, ArrayList<Comment> listcomment, long numero_favoritos){
		this.url = url;
		this.description = description;
		this.listcomment = listcomment;
		this.numero_favoritos = numero_favoritos;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Comment> getListcomment() {
		return listcomment;
	}
	public void setListcomment(ArrayList<Comment> listcomment) {
		this.listcomment = listcomment;
	}
	public long getNumero_favoritos() {
		return numero_favoritos;
	}
	public void setNumero_favoritos(long numero_favoritos) {
		this.numero_favoritos = numero_favoritos;
	}
	
	
}
