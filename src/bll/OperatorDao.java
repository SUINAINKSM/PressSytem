package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.dbhelper.MyDBHelper;
import model.Operator;

public class OperatorDao {
	public MyDBHelper myDbhelper = new MyDBHelper();
	/**
	 * ���ʵ��
	 * @return
	 */
	public static OperatorDao getInstance() {
		return new OperatorDao();
	}
	
	/**
	 * ��õ�ǰ�û����еĲ�������
	 * @param userID
	 * @return
	 */
	public List<Operator> QueryRecords(int userID) {
		List<Operator> list = new ArrayList<Operator>();
		String sql = "select * from Operator where userID = ?";
		Object[] parameters = {userID};
		ResultSet rs = myDbhelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				Operator item = new Operator();
				item.setID(rs.getInt("ID"));
				item.setUserID(rs.getInt("userID"));
				item.setLogicalSignal(rs.getString("logicalSignal"));
				item.setSegment(rs.getString("segment"));
				item.setSignal(rs.getString("signal"));
				item.setValueData(rs.getString("valueData"));
				list.add(item);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ɾ��ѡ�е�����
	 * @param ID
	 * @return
	 */
	public int Delete(int ID){
		String sql = "delete from Operator where ID = ?";
		Object[] parameters = {ID};
		if( myDbhelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;//�ɹ�
		return 0; //ʧ��
	}
	
	/**
	 * ����µ�where����
	 * @param userID
	 * @param logicalSignal
	 * @param segment
	 * @param signal
	 * @param valueData
	 * @return
	 */
	public int Add(int userID,String logicalSignal,String segment,String signal,String valueData){
		String sql = "insert into Operator values(?,?,?,?,?)";
		Object[] parameters = {userID,logicalSignal,segment,signal,valueData};
		if( myDbhelper.executeNonQuery(sql, parameters) != 0)//ִ��Ӱ��������Ϊ0
			return 1;//�ɹ�
		return 0;//ʧ��
	}
	
	/**
	 * ��ѯ����ID
	 * @param userID
	 * @param logicalSignal
	 * @param segment
	 * @param signal
	 * @param valueData
	 * @return
	 */
	public int QueryID(int userID,String logicalSignal,String segment,String signal,String valueData){
		String sql = "select ID from Operator where userID = ? and logicalSignal = ? and segment = ? and signal = ? and valueData = ?";
		Object[] parameters = {userID,logicalSignal,segment,signal,valueData};
		ResultSet rs = myDbhelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getInt("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
