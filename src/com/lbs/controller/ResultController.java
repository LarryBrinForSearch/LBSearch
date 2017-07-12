package com.lbs.controller;

import com.jfinal.core.Controller;
import com.lbs.common.Config;

import lsmodel.ResultModel;
import searchFromES.SearchFromES;

public class ResultController  extends Controller{

	private static String searchString=null;
	private static String channel=null;
	private static int page_size = 15; // 每页显示15条搜索结果
	private static int list_size = 10; // 搜索结果可能
	public void index(){
		render("");
	}
	public void setChannel() {
		int c= getParaToInt(0);
		switch(c) {
		case 1:
			channel = "财经";
			break;
		case 2:
			channel = "国际";
			break;
		case 3:
			channel = "股票";
			break;
		case 4:
			channel = "新闻";
			break;
		case 5:
			channel = "体育";
			break;
		case 6:
			channel = "军事";
			break;
		
		}
		setAttr("channel",channel);
		render("/index/index.html");
	}
	public  void search() {
		searchString = getPara("searchString");
		channel = getPara("channel");
		if(!("财经国际股票新闻体育军事".contains(channel))) {
			channel = null;
		}
		ResultModel result = Config.sfs.QueryAtFirst(searchString,channel,page_size);
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
		ResultModel result = Config.sfs.QueryLater(i, page_size, searchString, channel);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", i);
		setAttr("list_size",list_size);
		render("result.html");
	}
}