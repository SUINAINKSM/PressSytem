package model;

public class Operator {
	private int ID;               //������id
	private int userID;           //��¼������id
	private String logicalSignal; //�߼������
	private String segment;       //�ֶ���
	private String signal;        //�����
	private String valueData;     //ֵ
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getLogicalSignal() {
		return logicalSignal;
	}
	public void setLogicalSignal(String logicalSignal) {
		this.logicalSignal = logicalSignal;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getValueData() {
		return valueData;
	}
	public void setValueData(String valueData) {
		this.valueData = valueData;
	}
}
