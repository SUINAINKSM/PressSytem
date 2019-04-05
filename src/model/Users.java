package model;

import java.util.Date;

public class Users {//用户类
  private String username;//用户姓名
  private String Password;//用户密码
  private int    fault_time;//连续登陆失败的次数
  private String unlock_time;//自动解锁的时间
  public void set_username(String username){
	  this.username=username;
  }
  public String get_username(){
	return username;
	  
  }
  public void set_password(String password){
     this.Password=password;
  }
  public String get_password(){
	return Password;
	  
  }
  public void set_faulttime(int faulttime){
	  this.fault_time=faulttime;
  }
  public int get_faulttime(){
	return fault_time;
	  
  }
  public void set_unlocktime(String unlocktime){
	  this.unlock_time=unlocktime;
  }
  public String get_unlocktime(){
	return unlock_time;
	  
  }
}
