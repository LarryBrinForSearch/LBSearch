package searchFromES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountByDate {
static Connection con=null;
	
	public static boolean connectDB(){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://117.78.37.224:3306/lbsearch?characterEncoding=utf-8";//preparedStatement��������
		String user="root";
		String pwd="Mysql1995*";
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			if(!con.isClosed())
				System.out.println("succeed in connecting DB");
			
		}catch(ClassNotFoundException e){
			System.out.println("Not Find Driver");
			return false;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void cutDownCon(){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	public static int countTime(String start,String end) throws SQLException{
		String sql=" select count(*) from website where unix_timestamp(crawler_time)>unix_timestamp(?) and unix_timestamp(crawler_time)<unix_timestamp(?);";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, start);
		pst.setString(2, end);
		ResultSet rst=pst.executeQuery();
		rst.next();
		int result=rst.getInt(1);
		return result;
	}
	
	public Map<String,Integer> getCountResult(int startM,int startD,int endM,int endD){
		int month = startM;
		int day	=startD;
		int h = 0;//默认从0点开始
		ArrayList<String> times=new ArrayList<String>();//生成日期串
		while(true){
			h++;
			if(h==24){
				h=0;
				day++;
				if((month==5 && day ==32)||(month == 6 && day == 31)){
					day=1;
					month++;
				}
			}
			String tm = String.format("2017-%02d-%02d %02d:00:00", month,day,h);
			if(month==endM && day == endD && h == 23 ){
				break;
			}
			times.add(tm);
		}//end while
		CountByDate.connectDB();	//连接数据库
		Map<String,Integer> rst=new HashMap<String,Integer>();
		
		for(int i=0;i<times.size()-1;i++){
			String start=times.get(i);
			String end=times.get(i+1);
			try {
				int ct=CountByDate.countTime(start,end);
				rst.put(start, ct);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CountByDate.cutDownCon();
		return rst;
	}
}
