package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.dbhelper.MyDBHelper;

public class CBiz {
	public MyDBHelper myDbhelper=new MyDBHelper();
	public int getBookTypeListNumber() throws SQLException{
		 String sql1="Select * from C";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
		 myDbhelper.free(rs1); 
	    	return  i;
		
	}
	 public List<HashMap<String,String>> getBookTypeInformation(){
			List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
			String sql = "SELECT Code,bookType from C ORDER BY Code";
			ResultSet rs = myDbhelper.executeQuery(sql);
			try {
				while(rs.next()){
					HashMap<String,String> one = new HashMap<>(); //存放用户<权限名，模块名>
					one.put(rs.getString("bookType"), rs.getString("Code"));
					result.add(one);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	 public Object[][] getBookTypenameList() throws SQLException{
		 List<HashMap<String,String>> result=getBookTypeInformation();
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
	 public int AddBookTypeInformation(String bookType,String code) throws SQLException{//注册
	    	String  sql="select bookType,Code from C";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bookType").equals(bookType)){
	    			return 0;
	    		   }
	    			else if(rs.getString("Code").equals(code))
                        return -1;      
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		String  sql1="Insert INTO C Values(?,?)";
	    		Object[] obj=new Object[]{code,bookType};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	 public void deleteBookTypeName(String bookType) throws SQLException{
		 String  sql="select bookType from C";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("bookType").equals(bookType)){
	    				 String sql2="Delete from C where bookType=?";
	    	    		 Object[]   obj1=new Object[] {bookType};
	    	    		 myDbhelper.executeNonQuery(sql2, obj1);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    } 
	 }
	 public int  getBookTypenameListNumber() throws SQLException{
	     String sql1="Select * from C";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
	
		 myDbhelper.free(rs1); 
	    	return  i;
    }
	 public int getBookTypeID(String bookType) throws SQLException{
	    	String sql="select ID from C where bookType=?";
	    	Object[] paratemers=new Object[]{bookType};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	 public String getBjsCode(int uid) throws SQLException{//获得用户的ID
	    	String sql="select Code from C where ID=?";
	    	Object[] paratemers=new Object[]{uid};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			String b = null;
			while(rs.next()){
				   b=rs.getString("Code");
			  }
		    return b;
	    	
	    }
	 public String getBookTypeName(int uid) throws SQLException{
	    	String sql="select bookType from C where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("bookType");
	    	}
	    	return ass;
	    }
	 public int updateBookTypeName(int uid,String bookType) throws SQLException{//修改用户名
	    	String sql="select bookType from C";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	if(rs.getString("bookType").equals(bookType)){
	    		return 0;
	    	}
	    	}
	    	String sql1="update C set bookType=? where ID=? ";
	    	Object[] obj=new Object[]{bookType,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBookTypeCode(int uid,String Code) throws SQLException{
	    	String sql="select Code from C";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("Code").equals(Code)){
	    		return 0;
	    	}
	    	}
	    	String sql1="update C set Code=? where ID=? ";
	    	Object[] obj=new Object[]{Code,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
}
