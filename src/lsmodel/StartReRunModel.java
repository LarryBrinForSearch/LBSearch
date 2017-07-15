package lsmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StartReRunModel {

	public static boolean reRun(String strStart, String strEnd) {
		boolean flag = true;
		String[] test = new String[] { "java", "-jar", "G:\\workspace\\Web\\ReRun2.0.jar", strStart, strEnd };
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(test);
			// 取得命令结果的输出流
			InputStream fis = process.getInputStream();
			// 用一个读输出流类去读
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			// 逐行读取输出到控制台
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			System.out.println("Error exec AnyQ");
		}
		return flag;
	}

	public static void main(String args[]) {
		if (args.length != 2) {
			System.out.print("参数错误");
			return;
		} else {
			reRun(args[0], args[1]);
		}
	}

}