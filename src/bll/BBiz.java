package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.dbhelper.MyDBHelper;

public class BBiz {
	public MyDBHelper myDbhelper=new MyDBHelper();
	public int getBjsListNumber() throws SQLException{
		 String sql1="Select * from B";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
		 myDbhelper.free(rs1); 
	    	return  i;
		
	}
	 public List<HashMap<String,String>> getBjsInformation(){
			List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
			String sql = "SELECT Num,bjsName from B ORDER BY Num";
			ResultSet rs = myDbhelper.executeQuery(sql);
			try {
				while(rs.next()){
					HashMap<String,String> one = new HashMap<>(); //存放用户<权限名，模块名>
					one.put(rs.getString("bjsName"), rs.getString("Num"));
					result.add(one);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	 public Object[][] getBjsnameList() throws SQLException{
		 List<HashMap<String,String>> result=getBjsInformation();
		   Object[][] obj=new Object[result.size()][2]; 
	        for(int i=0;i<result.size();i++){
	            for(int j=0;j<2;j++){
	            	for(Map.Entry<String, String> arg:result.get(i).entrySet()){
	            		obj[i][0]=arg.getValue();
	            		obj[i][1]=arg.getKey();
	            	}
	            }
	        }
	        return obj;

   }
	 public int AddBjsInformation(String bjsname,int num) throws SQLException{//注册
	    	String  sql="select bjsName,Num from B";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bjsName").equals(bjsname)){
	    			return 0;
	    		   }
	    			else if(rs.getInt("Num")==num)
                        return -1;      
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		String  sql1="Insert INTO B Values(?,?)";
	    		Object[] obj=new Object[]{num,bjsname};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	 public void deleteBjsName(String bjsname) throws SQLException{
		 String  sql="select bjsname from B";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bjsName").equals(bjsname)){
	    				 String sql2="Delete from B where bjsName=?";
	    	    		 Object[]   obj1=new Object[] {bjsname};
	    	    		 myDbhelper.executeNonQuery(sql2, obj1);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    } 
	 }
	 public int  getBjsnameListNumber() throws SQLException{
	     String sql1="Select * from B";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
	
		 myDbhelper.free(rs1); 
	    	return  i;
    }
	 public int getBjsID(String bjsname) throws SQLException{//获得用户的ID
	    	String sql="select ID from B where bjsname=?";
	    	Object[] paratemers=new Object[]{bjsname};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	 public int getBjsNum(int uid) throws SQLException{//获得用户的ID
	    	String sql="select Num from B where ID=?";
	    	Object[] paratemers=new Object[]{uid};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("Num");
			  }
		    return b;
	    	
	    }
	 public String getBjsName(int uid) throws SQLException{
	    	String sql="select bjsname from B where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("bjsname");
	    	}
	    	return ass;
	    }
	 public int updateBjsName(int uid,String bjsname) throws SQLException{//修改用户名
	    	String sql="select bjsname from B";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("bjsname").equals(bjsname)){
	    		return 0;
	    	}
	    	}
	    	String sql1="update B set bjsname=? where ID=? ";
	    	Object[] obj=new Object[]{bjsname,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBjsNum(int uid,int bjsnum) throws SQLException{
	    	String sql="select Num from B";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getInt("Num")==bjsnum){
	    		return 0;
	    	}
	    	}
	    	String sql1="update B set Num=? where ID=? ";
	    	Object[] obj=new Object[]{bjsnum,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
}
