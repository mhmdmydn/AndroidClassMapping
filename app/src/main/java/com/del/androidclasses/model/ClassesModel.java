package com.del.androidclasses.model;

public class ClassesModel {
    
	private String text;
	private String text2;
	public ClassesModel(){
		
	}
	
	public ClassesModel(String text, String text2){
		this.text2 = text2;
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText2(){
		return this.text2;
	}

	public void setText2(String text2){
		this.text2 = text2;
	}

	@Override
	public String toString() {
		return text + text2;
	}
}
