package model;

import java.util.Date;

public class Users {//�û���
  private String username;//�û�����
  private String Password;//�û�����
  private int    fault_time;//������½ʧ�ܵĴ���
  private String unlock_time;//�Զ�������ʱ��
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
