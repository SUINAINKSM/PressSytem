package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dal.dbhelper.MyDBHelper;

public class ABiz {
	 public static MyDBHelper myDbhelper=new MyDBHelper();
	 public Object[][] getBjsnameList() throws SQLException{
		 ArrayList<String> arr_list=new ArrayList<String>();
		
	     String sql1="Select * from A ORDER BY bjsName";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			arr_list.add(rs1.getString("bjsName"));
			i++;
		}
		 Object[][] obj = new Object[i][1];
		 for (int j=0;j<i;j++){
			 obj[j][0]=arr_list.get(j);
		 }
		 myDbhelper.free(rs1); 
	    	return obj;

    }
	 public int  getBjsnameListNumber() throws SQLException{
	     String sql1="Select * from A";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
	
		 myDbhelper.free(rs1); 
	    	return  i;

    }
	 public int AddbjsName(String bjsname) throws SQLException{//注册
	    	String  sql="select bjsName from A";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bjsName").equals(bjsname)){
	    			return 0;//已有该用户名的用户，不能再次注册
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		String  sql1="Insert INTO A Values(?)";
	    		Object[] obj=new Object[]{bjsname};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	 public void deleteBjsName(String bjsname) throws SQLException{
		 String  sql="select bjsname from A";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bjsName").equals(bjsname)){
	    				 String sql2="Delete from A where bjsName=?";
	    	    		 Object[]   obj1=new Object[] {bjsname};
	    	    		 myDbhelper.executeNonQuery(sql2, obj1);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    } 
	 }
	 public int getBjsID(String bjsname) throws SQLException{//获得用户的ID
	    	String sql="select ID from A where bjsname=?";
	    	Object[] paratemers=new Object[]{bjsname};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	 public String getBjsName(int uid) throws SQLException{
	    	String sql="select bjsname from A where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("bjsname");
	    	}
	    	return ass;
	    }
	 public int updateBjsName(int uid,String bjsname) throws SQLException{//修改用户名
	    	String sql="select bjsname from A";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("bjsname").equals(bjsname)){
	    		return 0;
	    	}
	    	}
	    	
	    		String sql1="update A set bjsname=? where ID=? ";
	    		Object[] obj=new Object[]{bjsname,uid};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1; 		
	    }
}
