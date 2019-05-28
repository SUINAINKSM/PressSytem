package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.dbhelper.MyDBHelper;
import model.E;

public class GBiz {
	public MyDBHelper myDbhelper=new MyDBHelper();
	public int getBjsListNumber() throws SQLException{
		 String sql1="Select * from G";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
		 myDbhelper.free(rs1); 
	    	return  i;
		
	}
	 public List<E> getBjsInformation(){
			List<E> result = new ArrayList<E>();
			String sql = "SELECT bjsName,bookType,shum from G,C,D WHERE C.CODE=G.bookTypeID AND D.ID=G.bjsNameID ORDER BY BjsName";
			ResultSet rs = myDbhelper.executeQuery(sql);
			try {
				while(rs.next()){
				    E d=new E();
				    d.setBjsName(rs.getString("bjsName"));
				    d.setBookType(rs.getString("bookType"));
				    d.setShum(rs.getString("shum"));
				    result.add(d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	 public Object[][] getBjsnameList() throws SQLException{
		 List<E> result=getBjsInformation();
		   Object[][] obj=new Object[result.size()][3]; 
	        for(int i=0;i<result.size();i++){
	            	obj[i][0]=result.get(i).getBjsName();
	            	obj[i][1]=result.get(i).getBookType();
	            	obj[i][2]=result.get(i).getShum();
	        }
	        return obj;

   }
	 public int getBjsNameID(String bjsname) throws SQLException{
		 String sql="select ID  from D WHERE D.bjsName=? ";
		 Object[] paratemers=new Object[]{bjsname};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	 }
	 public String getBookTypeID(String bookType) throws SQLException{
		 String sql="select Code from C WHERE C.bookType=? ";
		 Object[] paratemers=new Object[]{bookType};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			String b = null;
			while(rs.next()){
				   b=rs.getString("Code");
			  }
		    return b;
	 }
	 public int AddBjsInformation(String bjsname,String bookType,String shum) throws SQLException{//注册
	    	String  sql="select * from G";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	int bjsID=getBjsNameID(bjsname);
	    	String bookTypeID=getBookTypeID(bookType);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("shum").equals(shum)){
	    			  return 0;
	    		   }    
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		String  sql1="Insert INTO G Values(?,?,?)";
	    		
	    		Object[] obj=new Object[]{bjsID,bookTypeID,shum};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	 public void deleteBookName(String Bookname) throws SQLException{
		 String  sql="select shum from  G";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("shum").equals(Bookname)){
	    				 String sql2="Delete from G where shum=?";
	    	    		 Object[]   obj1=new Object[] {Bookname};
	    	    		 myDbhelper.executeNonQuery(sql2, obj1);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    } 
	 }
	 public int  getBjsnameListNumber() throws SQLException{
	     String sql1="Select * from G";
	     ResultSet rs1=myDbhelper.executeQuery(sql1);	  
		 int i=0;
		 while(rs1.next())
		{    
			i++;
		}
		 myDbhelper.free(rs1); 
	    	return  i;
    }
	 public int getBjsID(String shum) throws SQLException{//获得用户的ID
	    	String sql="select ID from G where shum=?";
	    	Object[] paratemers=new Object[]{shum};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	 public String getBookType(int uid) throws SQLException{//获得用户的ID
	    	String sql="select bookType from G,C where G.ID=? AND C.Code=G.bookTypeID";
	    	Object[] paratemers=new Object[]{uid};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			String  b = null;
			while(rs.next()){
				   b=rs.getString("bookType");
			  }
		    return b;
	    	
	    }
	 public String getBjsName(int uid) throws SQLException{
	    	String sql="select bjsname from G,D where G.ID=? AND D.ID=G.bjsNameID ";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("bjsname");
	    		
	    	}
	    	return ass;
	    }
	 public String getBookName(int uid) throws SQLException{
	    	String sql="select shum from G where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("shum");
	    	}
	    	return ass;
	    }
	 public int updateBjsName(int uid,String bjsName) throws SQLException{//修改用户名
		    int bjsNameID=getBjsNameID(bjsName);
	    	String sql1="update G set bjsnameID=? where ID=? ";
	    	Object[] obj=new Object[]{bjsNameID,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBookType(int uid,String bookType) throws SQLException{//修改用户名
		 String bookTypeID=getBookTypeID(bookType);
	    	String sql1="update G set bookTypeID=? where ID=? ";
	    	Object[] obj=new Object[]{bookTypeID,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	 public int updateBookName(int uid,String shum) throws SQLException{
	    	String sql="select shum from G";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("shum").equals(shum)){
	    		return 0;
	    	}
	    	}
	    	String sql1="update G set shum=? where ID=? ";
	    	Object[] obj=new Object[]{shum,uid};
	    	myDbhelper.executeNonQuery(sql1, obj);
	    	return 1; 		
	    }
	public List<String>  getBjsNameList() throws SQLException{
		List<String> array=new ArrayList<String>();
		String sql="select bjsName from D WHERE inUse=?";
		ResultSet rs=myDbhelper.executeQuery(sql,"是");
		while(rs.next()){
			array.add(rs.getString("bjsName"));
		}
		return array;
		
	}
	public List<String>  getBookTypeNameList() throws SQLException{
		List<String> array=new ArrayList<String>();
		String sql="select bookType from C";
		ResultSet rs=myDbhelper.executeQuery(sql);
		while(rs.next()){
			array.add(rs.getString("bookType"));
		}
		return array;
		
	}
	 public int getID(String shum) throws SQLException{//获得用户的ID
	    	String sql="select ID from G where shum=?";
	    	Object[] paratemers=new Object[]{shum};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
}
