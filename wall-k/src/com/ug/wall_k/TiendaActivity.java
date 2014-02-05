package com.ug.wall_k;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TiendaActivity extends Activity implements OnClickListener{
	public static final String TITULO = "tilulo";
	public static final String DATOS = "datos";
	public static final String TELEFONO = "telefono";
	String queryTel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tienda);
		TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		TextView txtDatos = (TextView) findViewById(R.id.txtDatos);
		Button boton = (Button) findViewById(R.id.btnLlamar);
		Intent intent = getIntent();		
		String queryTitulo = intent.getStringExtra(TITULO);
		String queryDatos = intent.getStringExtra(DATOS);
		queryTel = intent.getStringExtra(TELEFONO);
		
		txtTitulo.setText(queryTitulo);
		txtDatos.setText(queryDatos);
		
		Linkify.addLinks(txtDatos, Linkify.ALL);
		boton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tienda, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.btnLlamar){
			Intent intent = new Intent(Intent.ACTION_DIAL);
		   intent.setData(Uri.parse("tel:"+queryTel));		   
		   startActivity(intent);
		
		}
	}

}
