package com.lbs.controller;

import java.util.Date;

import com.jfinal.core.Controller;

import lsmodel.StartReRunModel;

public class AdminController extends Controller {
	public void index(){
		render("/admin_page/dataMoniter.html");
	}
	public void moniter(){
		render("/admin_page/dataMoniter.html");
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
