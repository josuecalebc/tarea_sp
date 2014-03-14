package com.ug.sp1.fragments;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ug.sp1.R;
import com.ug.sp1.data.Comment;
import com.ug.sp1.data.SQLiteHelper;

public class CommentFragment extends Fragment implements OnClickListener, OnItemLongClickListener {

	ArrayList<String> listComment = new ArrayList<String>();
	ArrayList<Comment> comments = new ArrayList<Comment>();
	ArrayAdapter<String> adapter;
	Button btnAgregar;
	EditText editComment;
	ListView list;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		SQLiteHelper dbh = new SQLiteHelper(getActivity(), SQLiteHelper.DB, null, SQLiteHelper.VERSION);			   
	    SQLiteDatabase db = dbh.getWritableDatabase();
	    Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteHelper.COMMENT,null);
	    if(cursor.moveToFirst())
	    	do{
	    		String comm = cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_COMMENT));
	    		int id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_ID));
	    		listComment.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_COMMENT)));
	    		comments.add(new Comment(id,comm));
	    	}while(cursor.moveToNext());
		adapter = new ArrayAdapter<String>(getActivity() , 
				android.R.layout.simple_list_item_1, 
				listComment);		
		list.setAdapter(adapter);
		list.setOnItemLongClickListener((OnItemLongClickListener) this);
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
				SQLiteHelper dbh = new SQLiteHelper(getActivity(), SQLiteHelper.DB, null, SQLiteHelper.VERSION);			   
			     SQLiteDatabase db = dbh.getWritableDatabase();
			     Comment comentario = new Comment(listComment.size()+1,comment);
			     db.insertWithOnConflict(SQLiteHelper.COMMENT, null, comentario.getValues(), SQLiteDatabase.CONFLICT_IGNORE);
			     comments.add(comentario);
			     db.close();
			}
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterview, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		SQLiteHelper dbh = new SQLiteHelper(getActivity(), SQLiteHelper.DB, null, SQLiteHelper.VERSION);			   
	     SQLiteDatabase db = dbh.getWritableDatabase();
	     if(db.delete(SQLiteHelper.COMMENT, "id="+comments.get(position).getId(), null)>0){
	    	 listComment.remove(position);
	    	 
	     	 adapter.notifyDataSetChanged();
	     }
	     db.close();
		return false;
	}

}
