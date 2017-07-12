package com.lbs.controller;
import com.jfinal.core.Controller;
public class ResultController  extends Controller{
	public void index(){
		render("");
	}
	public void search() {
		String string = getPara("searchString");
		//调用search方法
		//传到新页面
	}
}
