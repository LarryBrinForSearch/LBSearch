package com.lbs.controller;
import com.jfinal.core.Controller;
import searchFromES.SearchFromES;;
public class ResultController  extends Controller{
	private static SearchFromES sfs = new SearchFromES();
	public void index(){
		render("");
	}
	public void search() {
		String string = getPara("searchString");
		
		renderText(string);
	}
}
