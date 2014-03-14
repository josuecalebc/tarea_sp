package com.ug.sp1.data;

import android.content.ContentValues;

public class Comment {
	

	private int id;
	private String comment;
	
	public Comment(int id,String comment){
		this.id=id;
		this.setComment(comment);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public ContentValues getValues(){
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.KEY_ID, id);
		values.put(SQLiteHelper.KEY_COMMENT, comment);
		return values;
	}
}
