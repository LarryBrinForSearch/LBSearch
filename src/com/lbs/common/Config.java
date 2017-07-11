package com.lbs.common;
import com.jfinal.config.*;
import com.jfinal.template.Engine;
import com.lbs.controller.*;
public class Config  extends JFinalConfig  {
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/result",ResultController.class);
    }
    public void configEngine(Engine me) {
    		me.addSharedFunction("/common/_layout.html");
		me.addSharedFunction("/common/_paginate.html");
    }
    public void configPlugin(Plugins me) {}
    public void configInterceptor(Interceptors me) {}
    public void configHandler(Handlers me) {}
    
}
