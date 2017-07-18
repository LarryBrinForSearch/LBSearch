package com.lbs.controller;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.jfinal.core.Controller;

import lsmodel.StartReRunModel;
import searchFromES.CountByDate;
import searchFromES.SearchFromES;

public class AdminController extends Controller {
	public void index(){
		moniter();
	}
	public void moniter(){
		line_chart(); // 绘制折线图
		setAttr("data_by_web_site",SearchFromES.aggSearch("website_name"));
		setAttr("data_by_channel",SearchFromES.aggSearch("channel_name"));
		render("/admin_page/dataMoniter.html");
	}
	// 绘制折线图
	private void line_chart(){
		// 折线图数据，用一个Map对象line_chart_data传递，
		// 对象line_chart_data的键和值分别是一个map对象，
		// 对象line_chart_data 值是map对象，一个值包含了一个站点数据采集量
		Map<String,Map<String,Integer>> line_chart_data = 
				new HashMap< String,Map<String, Integer>>();
		String sites[] = {"新浪网","百度网"};
		String [] yKeys = {"a", "b", "c", "d", "e", "f", "g", 
				"h", "i", "j", "k", "l", "m", "n", 
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", };
		for(String site:sites){
			line_chart_data.put(site,CountByDate.getCountResultByDay(7,1,7,17,site));
		}
		

		setAttr("data_line_chart",line_chart_data);
		setAttr("hello","hello");
		setAttr("yKeys",yKeys);
		setAttr("array",new String[26]);
	}
	
	
	public void restore(){

		render("/admin_page/dataRestore.html");
	}
	public void rerun(){
		String start = getPara("startInput");
		String end = getPara("endInput");
		String info = null;
		System.out.println("com.lbs.conroler.AdminContreller.restore:\n\t"+start+":"+end);
		if (StartReRunModel.reRun(start, end)){
			info="数据重跑成功，重跑范围是从"+start+"到" +end;
		}else{
			info="数据重跑失败，详细原因请查看后台日志";
		}
		setAttr("timestamp",(new Date()).toString());
		setAttr("info",info);
		render("/admin_page/rerun_result.html");
		
	}
	

}
