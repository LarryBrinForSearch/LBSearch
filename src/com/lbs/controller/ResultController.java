package com.lbs.controller;

import com.jfinal.core.Controller;
import com.lbs.common.Config;

import lsmodel.ResultModel;
import searchFromES.SearchFromES;

public class ResultController  extends Controller{

	private static String searchString=null;
	private static int page_size = 15; // 每页显示15条搜索结果
	private static int list_size = 10; // 搜索结果可能
	public void index(){
		render("");
	}
	public  void search() {
		searchString = getPara("searchString");
		ResultModel result = Config.sfs.QueryAtFirst(searchString,null,page_size);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", 1);
		setAttr("list_size",list_size);
		System.out.println(result.getJsonArr().size());
		render("result.html");
	}
	public void page() {
		int i = getParaToInt(0);
		System.out.println("page: string is \"" + searchString + "\"\n\t page is \"" + i);;
		ResultModel result = Config.sfs.QueryLater(i, page_size, searchString, null);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", i);
		setAttr("list_size",list_size);
		render("result.html");
	}
}