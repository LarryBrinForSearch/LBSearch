package com.lbs.controller;

import com.jfinal.core.Controller;

public class AdminController extends Controller {
	public void index(){
		render("/admin_page/dataMoniter.html");
	}
	public void moniter(){
		render("/admin_page/dataMoniter.html");
	}
	public void restore(){
		String start = getPara("startInput");
		String end = getPara("endInput");
		System.out.println("com.lbs.conroler.AdminContreller.restore:\n\t"+start+":"+end);
		render("/admin_page/dataRestore.html");
	}

}
