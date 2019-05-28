package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.dbhelper.MyDBHelper;
import model.D;

public class DBiz {
	public MyDBHelper myDbhelper=new MyDBHelper();
	public int getBjsListNumber() throws SQLException{
		 String sql1="Select * from D";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
		 myDbhelper.free(rs1); 
	    	return  i;
		
	}
	 public List<D> getBjsInformation(){
			List<D> result = new ArrayList<D>();
			String sql = "SELECT * from D ORDER BY Num";
			ResultSet rs = myDbhelper.executeQuery(sql);
			try {
				while(rs.next()){
				    D d=new D();
				    d.setID(rs.getInt("ID"));
				    d.setBjsName(rs.getString("bjsName"));
				    d.setNum(rs.getInt("Num"));
				    d.setInUse(rs.getString("inUse"));
				    result.add(d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	 public Object[][] getBjsnameList() throws SQLException{
		 List<D> result=getBjsInformation();
		   Object[][] obj=new Object[result.size()][3]; 
	        for(int i=0;i<result.size();i++){
	            	obj[i][0]=result.get(i).getNum();
	            	obj[i][1]=result.get(i).getBjsName();
	            	obj[i][2]=result.get(i).getInUse();
	        }
	        return obj;

   }
	 public int AddBjsInformation(String bjsname,int num,String inUse) throws SQLException{//注册
	    	String  sql="select * from D";
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
	    		String  sql1="Insert INTO D Values(?,?,?)";
	    		Object[] obj=new Object[]{num,bjsname,inUse};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	 public void deleteBjsName(String bjsname) throws SQLException{
		String sql = "select bjsname from  D";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			try {
				if (rs.getString("bjsName").equals(bjsname)) {
					String sql2 = "Delete from D where bjsName=?";
					Object[] obj1 = new Object[] { bjsname };
					myDbhelper.executeNonQuery(sql2, obj1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 }
	 public int  getBjsnameListNumber() throws SQLException{
	     String sql1="Select * from D";
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
	    	String sql="select ID from D where bjsname=?";
	    	Object[] paratemers=new Object[]{bjsname};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	 public int getBjsNum(int uid) throws SQLException{//获得用户的ID
	    	String sql="select Num from D where ID=?";
	    	Object[] paratemers=new Object[]{uid};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("Num");
			  }
		    return b;
	    	
	    }
	 public String getBjsName(int uid) throws SQLException{
	    	String sql="select bjsname from D where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("bjsname");
	    	}
	    	return ass;
	    }
	 public int updateBjsName(int uid,String bjsname) throws SQLException{//修改用户名
	    	String sql="select bjsname from D";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("bjsname").equals(bjsname)){
	    		return 0;
	    	}
	    	}
	    	String sql1="update D set bjsname=? where ID=? ";
	    	Object[] obj=new Object[]{bjsname,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBjsNum(int uid,int bjsnum) throws SQLException{
	    	String sql="select Num from D";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getInt("Num")==bjsnum){
	    		return 0;
	    	}
	    	}
	    	String sql1="update D set Num=? where ID=? ";
	    	Object[] obj=new Object[]{bjsnum,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBjsinUse(int uid,String inUse) throws SQLException{
	    	String sql1="update D set inUse=? where ID=? ";
	    	Object[] obj=new Object[]{inUse,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
}
