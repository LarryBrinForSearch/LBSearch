package com.lbs.controller;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
public class IndexController extends Controller {
	public void index(){
		render("/index/index.html");
	}
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8889, "/", 5);
	}
}
