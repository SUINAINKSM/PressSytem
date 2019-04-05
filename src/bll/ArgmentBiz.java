package bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import dal.dbhelper.MyDBHelper;

public class ArgmentBiz {
 
	public int get_Days() throws SQLException{
    String sql="select* from Argments ";
    ResultSet rs=MyDBHelper.executeQuery(sql);
    while(rs.next()){
		
		           return rs.getInt("days");
			   }
	return 0; 
		  
	}
	public int get_Num() throws SQLException{
	    String sql="select* from Argments ";
	    ResultSet rs=MyDBHelper.executeQuery(sql);
	    while(rs.next()){
			
			           return rs.getInt("num");
				   }
		return 0; 
			  
		}
	public int get_Lockdays() throws SQLException{
	    String sql="select* from Argments ";
	    ResultSet rs=MyDBHelper.executeQuery(sql);
	    while(rs.next()){
			      
			           return rs.getInt("lock_days");
				   }
		return 0; 
			  
		}
}
