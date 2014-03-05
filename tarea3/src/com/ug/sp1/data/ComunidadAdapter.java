package com.ug.sp1.data;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ug.sp1.R;

public class ComunidadAdapter extends BaseAdapter {
	Activity actividad;
	private Integer[] imagenes;
	private String[] comments;
	private Bitmap[] bitmapImagenes;
	URL imageUrl = null;
	HttpURLConnection conn = null;
	public ComunidadAdapter(Activity actividad, Integer[] drawable, String[] comments){
		this.actividad = actividad;
		imagenes = drawable;
		this.comments = comments;
		
	}
	public ComunidadAdapter(Activity actividad, String[] drawable, String[] comments){
		this.actividad = actividad;
		bitmapImagenes = new Bitmap[drawable.length];
		int i=0;
		/*for(String url: drawable){
			
			try {
			imageUrl = new URL(url);
			 conn = (HttpURLConnection) imageUrl.openConnection();
			 conn.connect();
			 Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream());
			 bitmapImagenes[i]=imagen;
			 i++;
			} catch (IOException e) {
				 
				 e.printStackTrace();
				 
			}
		}*/
		this.comments = comments;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagenes.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub	    
		View view = actividad.getLayoutInflater().inflate(R.layout.element_list_comunidad, null);		
		TextView comment = (TextView) view.findViewById(R.id.textCommentElement);
		ImageView image = (ImageView) view.findViewById(R.id.imageViewElement);		
		comment.setText(comments[position]);
		image.setImageResource(imagenes[position]);
		//image.setImageBitmap(bitmapImagenes[position]);
		
		return view;
	}

}
