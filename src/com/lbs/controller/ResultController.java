package com.lbs.controller;
import com.jfinal.core.Controller;

import lsmodel.ResultModel;
import searchFromES.SearchFromES;;
public class ResultController  extends Controller{
	private static SearchFromES sfs = new SearchFromES();
	public void index(){
		render("");
	}
	public void search() {
		String string = getPara("searchString");
		ResultModel result = sfs.QueryAtFirst(string,null);
		setAttr("result", result);
		System.out.println(result.getJsonArr().size());
		render("result.html");
	}
}
