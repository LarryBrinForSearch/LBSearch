package lsmodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StartReRunModel {

      
      public static boolean reRun(String begin,String end){
    	  boolean flag = false;
    	  //String path ="H:\\jre\\bin\\java.exe -jar  H:\\Elasticsearch_fat.jar";
    	  //String path2="H:\\MPlayer\\mplayer.exe";
    	  String str1=begin;
    	  String str2=end;
    	  String[] test=new String[]{"java","-jar", "G:\\workspace\\Web\\ReRun2.0.jar",  
                  str1,str2};
    	  try {
    		   Runtime runtime = Runtime.getRuntime();
    		   Process process = runtime.exec(test);
    		 //取得命令结果的输出流    
               InputStream fis = process.getInputStream();   
               //用一个读输出流类去读    
               BufferedReader br = new BufferedReader(new InputStreamReader(fis));   
               String line = null;   
               try
               {
                   File fw = new File("result.txt");
                   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(  
                           new FileOutputStream(fw),"UTF-8"));
                   StringBuffer sb = new StringBuffer();
                   //逐行读取输出到控制台    
                   while ((line = br.readLine()) != null) {  
                	   sb.append(line+"\n");
                       System.out.println(line);   
                   }   
//                   while((str = br.readLine()) != null)
//                   {
//                       sb.append(str+"\n");
//                   }
                   String content = sb.toString();
                   br.close();
                   bw.write(content);
                   bw.close();
                   System.out.println("finish");
                   flag=true;
               } catch (IOException e)
               {
                   e.printStackTrace();
               }
              
    		  } catch (IOException e) {
    		   e.printStackTrace();
    		   //System.out.println("Error exec AnyQ");
    		  }
    	  return flag;
    	 }

  
      
      public static void main(String args[]){
    	  if(args.length!=2){
  			System.out.print("参数错误");
  			return;
  		}else{
    	  reRun(args[0],args[1]);
  		}
      }
      
}