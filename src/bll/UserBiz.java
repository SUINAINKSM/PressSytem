package bll;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import dal.dbhelper.MyDBHelper;
import model.Users;
public class UserBiz {
	 public static Users myuser=new Users();//当前用户
	 public MyDBHelper db=new MyDBHelper();
	 public static MyDBHelper myDbhelper;
	 public static String keyid;//当前用户的编号
	public Users returnuser(){//返回当前的用户
		return myuser;
	}
	
	  public String showUsernameOntop(){
		  return myuser.get_username();
	  }
	  public int User_LoginCheck(String username,String password ) throws SQLException{//用户登录函数
		  String Sql = "select * from Users Where username=?";
		  Object[] paratemers=new Object[]{username};//数据传入
		  ResultSet rs=MyDBHelper.executeQuery(Sql, paratemers);
		  while(rs.next()){
			  String a=rs.getString("username");
			  String b=rs.getString("Password");
			  int c=Integer.valueOf(rs.getString("fault_time"));
			  String d=rs.getString("unlock_time");
			  System.out.println(d);
			  if(username.equals(a)&&password.equals(b)){
				 myuser.set_username(username);
				 myuser.set_password(b);
				 myuser.set_faulttime(c);
				 myuser.set_unlocktime(d);
				  return 1;
					 }
		  }
		
		  return 0;
		  
	  }
	  public String Get_Currenttime(){
		  Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			month=month+1;
			int date = c.get(Calendar.DATE); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int dayofweek=c.get(Calendar.DAY_OF_WEEK);//表示当前是星期几
			//System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"   "+dayofweek);
		    /*if(dayofweek==0)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期天");
			else if(dayofweek==1)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期一");
			else if(dayofweek==2)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期二");
			else if(dayofweek==3)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期三");
			else if(dayofweek==4)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期四");
			else if(dayofweek==5)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期五");
			else if(dayofweek==6)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期六");
				*/
			System.out.println(dayofweek);
			String DayofWeek = null;
			if(dayofweek==1)
				DayofWeek="星期天";
			else if(dayofweek==2)
				DayofWeek="星期一";
			else if(dayofweek==3)
				DayofWeek="星期二";
			else if(dayofweek==4)
				DayofWeek="星期三";
			else if(dayofweek==5)
				DayofWeek="星期四";
			else if(dayofweek==6)
				DayofWeek="星期五";
			else if(dayofweek==7)
				DayofWeek="星期六";
			String Current_Time;
			Current_Time="*出版社信息管理系统*    当前用户:  "+myuser.get_username()+"  "+year + "/" + month + "/" + date + " " +hour + ":" +minute +"  "+DayofWeek;
			return Current_Time;
	  }
	  public String Get_Currenttime1(){
		  Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			month=month+1;
			int date = c.get(Calendar.DATE); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second=c.get(Calendar.SECOND);
			int dayofweek=c.get(Calendar.DAY_OF_WEEK);//表示当前是星期几
			//System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"   "+dayofweek);
		    /*if(dayofweek==0)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期天");
			else if(dayofweek==1)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期一");
			else if(dayofweek==2)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期二");
			else if(dayofweek==3)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期三");
			else if(dayofweek==4)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期四");
			else if(dayofweek==5)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期五");
			else if(dayofweek==6)
				this.setTitle("*出版社信息管理系统*    当前用户:     "+year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second+"  "+"星期六");
				*/
			System.out.println(dayofweek);
			String DayofWeek = null;
			if(dayofweek==1)
				DayofWeek="星期天";
			else if(dayofweek==2)
				DayofWeek="星期一";
			else if(dayofweek==3)
				DayofWeek="星期二";
			else if(dayofweek==4)
				DayofWeek="星期三";
			else if(dayofweek==5)
				DayofWeek="星期四";
			else if(dayofweek==6)
				DayofWeek="星期五";
			else if(dayofweek==7)
				DayofWeek="星期六";
			String Current_Time;
			Current_Time=year + "-" + month +"-"+ date+" " +hour +":"+minute+":"+second;
			return Current_Time;
	  }
	  public Users finduser(String username) throws SQLException {//查询用户信息并返回该对象
		   Users ass=new Users();
		   String sql="select* from Users where username=?";
		   Object[] obj=new Object[]{username};
		   ResultSet rs=MyDBHelper.executeQuery(sql, obj);
		   while(rs.next()){
			   if(rs.getString("username").equals(username)){
				   String a=rs.getString("username");
				   String b=rs.getString("Password");
				   int c=Integer.valueOf(rs.getString("fault_time"));
				   String d=rs.getString("unlock_time");
				   ass.set_username(username);
				   ass.set_password(b);
				   ass.set_faulttime(c);
				   ass.set_unlocktime(d);
		           return ass;
			   }
		   }
		   return null;  
		  
		   }
	  public int addFaulttime(String username) throws SQLException{//修改用户的信息
			 Users b=finduser(username);
			 if(b==null){//没有找到对应的用户，修改失败
					return 0;
			 }
			 else{//找到对应的用户进行修改操作
				 String sql2="Update Users Set  fault_time=? where username=? ";
				 Object[]   obj1=new Object[] {b.get_faulttime()+1,username};
				 MyDBHelper.executeNonQuery(sql2, obj1);
				 return 1;
			 } 
		}
	  public int modifyUnlocktime(String username,java.util.Date date1) throws SQLException{//修改用户的信息
			 Users b=finduser(username);
			 if(b==null){//没有找到对应的用户，修改失败
					return 0;
			 }
			 else{//找到对应的用户进行修改操作
				 String sql2="Update Users Set unlock_time =? where username=? ";
				 Object[]   obj1=new Object[] {date1,username};
				 MyDBHelper.executeNonQuery(sql2, obj1);
				 return 1;
			 } 
		}
	  public int resetFaulttime(String username) throws SQLException{//修改用户的信息
			 Users b=finduser(username);
			 if(b==null){//没有找到对应的用户，修改失败
					return 0;
			 }
			 else{//找到对应的用户进行修改操作
				 String sql2="Update Users Set  fault_time=? where username=? ";
				 Object[]   obj1=new Object[] {0,username};
				 MyDBHelper.executeNonQuery(sql2, obj1);
				 return 1;
			 } 
		}
	  
	  public ArrayList<String> getUsernameList() throws SQLException{
	    	ArrayList<String> arr_list=new ArrayList<String>();
	    	 String sql="Select * from Users";
			  ResultSet rs=myDbhelper.executeQuery(sql);
			  
			  int i=0;
		      while(rs.next())
				{    
					
					arr_list.add(rs.getString("username"));
				}
		      myDbhelper.free(rs); 
	    	return arr_list;
	    }
	    public String getUserpassword(String username) throws SQLException{
	    	String Sql = "select * from Users Where username=?";
			Object[] paratemers=new Object[]{username};//数据传入
			ResultSet rs=myDbhelper.executeQuery(Sql, paratemers);
			String b = null;
			while(rs.next()){
				   b=rs.getString("Password");
			  }
		    return b;
			  
	    }
	    public void updateUserPassword(String username,String password) throws SQLException{//更新用户的密码
	    	String  sql="select username from Users";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("username").equals(username)){
	    				String  sql1="Update Users Set password=? where username=?";
	    	    		Object[] obj=new Object[]{password,username};
	    	    		myDbhelper.executeNonQuery(sql1, obj);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		
	    }
	    public int getUserID(String username) throws SQLException{//获得用户的ID
	    	String sql="select ID from Users where username=?";
	    	Object[] paratemers=new Object[]{username};//数据传入
	    	ResultSet rs=myDbhelper.executeQuery(sql, paratemers);
			int b = 0;
			while(rs.next()){
				   b=rs.getInt("ID");
			  }
		    return b;
	    	
	    }
	    public int updateUserName(int uid,String username) throws SQLException{//修改用户名
	    	String sql="select username from Users";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	
	    	if(rs.getString("username").equals(username)){
	    		return 0;
	    	}
	    	}
	    	
	    		String sql1="update Users set username=? where ID=? ";
	    		Object[] obj=new Object[]{username,uid};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1; 		
	 
	   
	    }
	    public String getUserName(int uid) throws SQLException{
	    	String sql="select username from Users where ID=?";
	    	Object[] obj=new Object[]{uid};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String ass=null;
	    	while(rs.next()){
	    		ass=rs.getString("username");
	    	}
	    	return ass;
	    }
	    public List<HashMap<String,String>> AllocatedByUserID(int id){
			List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
			String sql = "select rightID,rightName,Module from UsersRights,Rights where UsersRights.userID = ? and UsersRights.rightID = Rights.id order by rightID";
			Object[] parameters = {id};
			ResultSet rs = myDbhelper.executeQuery(sql, parameters);
			try {
				while(rs.next()){
					HashMap<String,String> allocatedRights = new HashMap<>(); //存放用户<权限名，模块名>
					allocatedRights.put(rs.getString("rightName"), rs.getString("Module"));
					result.add(allocatedRights);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * 按照用户id查询未分配的权限名以及所属模块
		 * @param id
		 * @return
		 */
		public List<HashMap<String,String>> UnallocatedByUserID(int id){
			List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
			String sql = "select UsersUNRights.rightName,UsersUNRights.Module from UsersUNRights,Rights where UsersUNRights.id1 = ? and UsersUNRights.id2 = Rights.id";
			Object[] parameters = {id};
			ResultSet rs = myDbhelper.executeQuery(sql, parameters);
			try {
				while(rs.next()){
					HashMap<String,String> allocatedRights = new HashMap<>(); //存放用户<权限名，模块名>
					allocatedRights.put(rs.getString("rightName"), rs.getString("Module"));
					result.add(allocatedRights);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
     
	    public int setUserName(int uid,String username) throws SQLException{
	    	String sql="select username from Users";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	     	   try {
	     			if(rs.getString("username").equals(username)){
	     			return 0;//已有该用户名的用户，不能再次修改该用户名
	     		   }
	     			} catch (SQLException e) {
	     				// TODO Auto-generated catch block
	     				e.printStackTrace();
	     			}
	     	    }
	    	String  sql1="Update Users Set username=? where ID=?";
			Object[] obj=new Object[]{username,uid};
			myDbhelper.executeNonQuery(sql1, obj);
			return 1;  	  
	    }
	    public int Register(String username,String password) throws SQLException{//注册
	    	String  sql="select username from Users";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("username").equals(username)){
	    			return 0;//已有该用户名的用户，不能再次注册
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    }
	    		String  sql1="Insert  Users(username,Password,fault_time) Values(?,?,?)";
	    		Object[] obj=new Object[]{username,password,0};
	    		myDbhelper.executeNonQuery(sql1, obj);
	    		return 1;
	    	
	    }
	    public void deleteUsers(String username) throws SQLException{//删除用户
	    	String  sql="select username from Users";
	    	ResultSet rs=myDbhelper.executeQuery(sql);
	    	while(rs.next()){
	    	   try {
	    			if(rs.getString("username").equals(username)){
	    				 String sql2="Delete from Users where Username=?";
	    	    		 Object[]   obj1=new Object[] {username};
	    	    		 myDbhelper.executeNonQuery(sql2, obj1);
	    		   }
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	    } 
	    	
	    }
	    public int showUserRightsNumber(String username) throws SQLException{//返回用户已分配的权限
	     	 String sql2="select* from UsersRights,Rights,Users Where userID=users.ID AND rightID=Rights.id AND username=? ";
	    	 Object[] obj2=new Object[]{username};
	    	 ResultSet rs2=myDbhelper.executeQuery(sql2,obj2);
	    	  //ArrayList<String> arr_list=new ArrayList<Integer>();
	    	  /*while(rs2.next()){
	    		  QXGL_UI._list1.add(rs2.getString("Module"));
	    		  QXGL_UI._list2.add(rs2.getString("rightName"));
	    		  
	    	  }
	     	  */
	  	  int i=0;
	  	  while(rs2.next()){
	  		 i++;
	  				
	  	  }
	  	  return i;
	    }  
 public int showUserUNRightsNumber(String username) throws SQLException{//展示用户的已分配的权限
	        String sql2="select* from UsersUNRights Where username=? ";
	     	  Object[] obj2=new Object[]{username};
	     	  ResultSet rs2=myDbhelper.executeQuery(sql2,obj2);
	     	  //ArrayList<String> arr_list=new ArrayList<Integer>();
	     	  /*while(rs2.next()){
	     		  QXGL_UI._list1.add(rs2.getString("Module"));
	     		  QXGL_UI._list2.add(rs2.getString("rightName"));
	     		  
	     	  }
	      	  */
	   	  int i=0;
	   	  while(rs2.next()){
	   		
	   				i++;
	   				
	   	  }
	   	  return i;  
	    }
	    public void ArrangeRights(String username,String RightName) throws SQLException{//为用户分配权限
	    	String  sql="select ID from Users where username=?";
	    	 Object[] obj=new Object[]{username};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	rs.next();
	    	int uid=rs.getInt("ID");
	    	String  sql1="select id from Rights where RightName=?";
	   	 Object[] obj1=new Object[]{RightName};
	   	ResultSet rs1=myDbhelper.executeQuery(sql1,obj1);
	   	rs1.next();
	   	int rid=rs1.getInt("id");
	   	String sql2="INSERT INTO UsersRights VALUES(?,?)";
	   	Object[] obj2=new Object[]{uid,rid};
	   	myDbhelper.executeNonQuery(sql2, obj2);
	    	
	    }
	    public void UnArrangeRights(String username,String RightName) throws SQLException{//为用户取消权限
	    	String  sql="select ID from Users where username=?";
	    	 Object[] obj=new Object[]{username};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	rs.next();
	    	int uid=rs.getInt("ID");
	    	String  sql1="select id from Rights where RightName=?";
	   	 Object[] obj1=new Object[]{RightName};
	   	ResultSet rs1=myDbhelper.executeQuery(sql1,obj1);
	   	rs1.next();
	   	int rid=rs1.getInt("id");
	   	String sql2="DELETE FROM UsersRights where userID=? AND RightID=?";
	   	Object[] obj2=new Object[]{uid,rid};
	   	myDbhelper.executeNonQuery(sql2, obj2);
	    	
	    }
	    public ArrayList <String> getUserModule(String username) throws SQLException{//用户的所有模块名
	    	ArrayList<String> arr_list=new ArrayList<String>();
	    	 String sql="Select DISTINCT Rights.Module from Users,Rights,UsersRights where UsersRights.userID=Users.ID AND UsersRights.RightID=Rights.id AND Users.username=? ";
	    	 Object[] obj=new Object[]{username};
			  ResultSet rs=myDbhelper.executeQuery(sql,obj);
			  while(rs.next()){
				  arr_list.add(rs.getString("Module"));
			  }
			  return arr_list;
	    }
	    public String getRightWindowName(String rightname) throws SQLException{
	    	String sql="SELECT W_name from Rights WHERE rightName=?";
	    	Object[] obj=new Object[]{rightname};
	    	ResultSet rs=myDbhelper.executeQuery(sql,obj);
	    	String w_name = null;
	    	while(rs.next()){
				  w_name=rs.getString("W_name");
			  }
	    	return w_name;
	    }
}
