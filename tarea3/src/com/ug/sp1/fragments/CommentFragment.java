package com.ug.sp1.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ug.sp1.R;

public class CommentFragment extends Fragment implements OnClickListener {

	ArrayList<String> listComment = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Button btnAgregar;
	EditText editComment;
	ListView list;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		adapter = new ArrayAdapter<String>(getActivity() , 
				android.R.layout.simple_list_item_1, 
				listComment);		
		list.setAdapter(adapter);
		
		btnAgregar.setOnClickListener(this);
	    
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_comment,container,false);
		btnAgregar = (Button) view.findViewById(R.id.btnAgregarComment);
		list = (ListView) view.findViewById(R.id.listViewComment);
		editComment = (EditText) view.findViewById(R.id.editcomment);
		return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btnAgregarComment:
			//Toast.makeText(getActivity(), "hola", Toast.LENGTH_SHORT).show();
			String comment = editComment.getText().toString().trim();
			if(!comment.equals("")){
				listComment.add(comment);
				adapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
		
	}

}
