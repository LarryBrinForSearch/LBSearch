package com.lbs.controller;

import java.util.HashMap;

import com.jfinal.core.Controller;
import com.lbs.common.Config;

import lsmodel.ResultModel;
import searchFromES.SearchFromES;

public class ResultController  extends Controller{
	private static HashMap<String,Long> map=null;//类别及每类的数量
	private static String searchString=null;//搜索字符串
	private static String channel=null;//搜索频道
	private static int page_size = 15; // 每页显示15条搜索结果
	private static int list_size = 10; // 搜索结果可能
	public void index(){
		render("");
	}
	/*
	 * 设置搜索频道，自动更新第一页面
	 */
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
		case 7:
			channel = null;
			break;
		
		}
		setAttr("channel",channel);
		render("/index/index.html");
	}
	/*
	 * 设置搜索频道2，自动更新到第二页面
	 */
	public void setChannel2() {
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
		case 7:
			channel = null;
			break;
		
		}
		setAttr("channel",channel);
		ResultModel result = Config.sfs.multiQuery(searchString,channel,page_size);
		setAttr("map",map);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", 1);
		setAttr("list_size",list_size);
		setAttr("search_string",searchString);
		System.out.println(result.getJsonArr().size());
		render("result.html");
	}
	/*
	 * search方法，主页输入搜索词后调用，返回ES的搜索结果给显示页面
	 */
	public  void search() {
		searchString = getPara("searchString");

		channel = getPara("channel");
		if(!("财经国际股票新闻体育军事".contains(channel))) {
			channel = null;
		}
		ResultModel result = Config.sfs.multiQuery(searchString,channel,page_size);
		map = result.getStaMap();
		setAttr("map",map);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", 1);
		setAttr("list_size",list_size);
		setAttr("search_string",searchString);
		System.out.println(result.getJsonArr().size());
		render("result.html");
	}
	
	/*
	 * 点击页号进行搜索，更新页面
	 */
	public void page() {
		int i = getParaToInt(0);
		System.out.println("page: string is \"" + searchString + "\"\n\t page is \"" + i);;
		ResultModel result = Config.sfs.QueryLater(i, page_size, searchString, channel);
		setAttr("map",map);
		setAttr("result", result);
		setAttr("page_size", page_size);
		setAttr("current_page", i);
		setAttr("list_size",list_size);
		render("result.html");
	}
}