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
		render("/admin_page/dataRestore.html");
	}
}
