package com.ug.sp1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ug.sp1.R;
import com.ug.sp1.data.ComunidadAdapter;

public class ComunidadFragment extends Fragment implements OnClickListener{
	
	    
	    ListView list;
	    ImageButton takephoto;
	    String[] urls = {
	    		"http://mayaruins.com/tikal_opener.jpg",
	    		"http://mbyers.net/images/498_StonehengeDM3004_468x299.jpg",
	    		"http://www.guate360.com/galeria/data/media/29/torre_reformador.jpg"
	    };
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			ComunidadAdapter adapter = new ComunidadAdapter(getActivity(), new Integer[] {R.drawable.tikal,
				                                                            			  R.drawable.torre,
				                                                            			  R.drawable.stone,
				
			},new String[]{"tikal","torre","stone"});
			//ComunidadAdapter adapter = new ComunidadAdapter(getActivity(), urls ,new String[]{"tikal","torre","stone"});
			list.setAdapter(adapter);
			takephoto.setOnClickListener(this);
			 
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View view = inflater.inflate(R.layout.fragment_comunidad,container,false);
			list = (ListView) view.findViewById(R.id.listViewComunidad);
		    takephoto = (ImageButton) view.findViewById(R.id.btnTakePhoto);
			return view;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
		      
		      startActivity(intent);
		}
	}

