package com.lbs.controller;
import com.jfinal.core.Controller;
import lsmodel.ResultModel;
import searchFromES.SearchFromES;;
public class ResultController  extends Controller{
	public void index(){
		render("");
	}
	public void search() {
		String string = getPara("searchString");
		//调用search方法
		//传到新页面
		ResultModel result = sfs.QueryAtFirst(string,null);
		setAttr("result", result);
		System.out.println(result.getJsonArr().size());
		render("result.html");
	}
}
