package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.dbhelper.MyDBHelper;
import model.SearchBooks;
import model.SearchBooks1;

public class SearchBooksDao {
	public MyDBHelper myDbhelper = new MyDBHelper();
	public static SearchBooksDao getInstance() {
		return new SearchBooksDao();
	}
	
	/**
	 * ��ѯ��Ŀ
	 * @param condition
	 * @return
	 */
	public List<SearchBooks1> QueryAll(String condition){
		List<SearchBooks1> list = new ArrayList<SearchBooks1>();
		String sql = null;
		ResultSet rs = null;
		//û������
		if(condition == null||condition.equals("")) {
			sql = "select * from SearchBooks";
			rs = myDbhelper.executeQuery(sql);
		}
		//������
		else {
			sql = "select * from SearchBooks where"+condition;
			rs = myDbhelper.executeQuery(sql);
		}
		try {
			while(rs.next()) {
				SearchBooks1 item = new SearchBooks1();
				item.setID(rs.getInt("ID"));
				item.setBookName(rs.getString("bookName"));
				item.setAuthorName(rs.getString("authorName"));
				item.setBookNumber(rs.getString("bookNumber"));
				item.setBookType(rs.getString("bookType"));
				item.setSize(rs.getString("size"));
				item.setPrintNumber(rs.getInt("printNumber"));
				item.setPrice(rs.getFloat("price"));
				item.setPublishTime(rs.getDate("publishTime"));
				item.setArticleType(rs.getString("articleType"));
				item.setBjsName(rs.getString("bjsName"));
				item.setEditorName(rs.getString("editorName"));
				item.setMerge(rs.getString("merge"));
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
	 * ����������id
	 * @param name
	 * @return
	 */
	public int QueryIDByBookName(String name) {
		String sql = "select ID from SearchBooks where bookName = ?";
		Object[] parameters = {name};
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
	
	/**
	 * ��ѯ�鱾��Ŀ
	 * @return
	 */
	public int QueryCount(){
		String sql = "select * from SearchBooks";
		return myDbhelper.getCount(sql);
	}
	
	/**
	 *����id��ѯ����
	 * @param ID
	 * @return
	 */
	public String QueryBookNameByID(int ID) {
		String sql = "select bookName from SearchBooks where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = myDbhelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bookName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����id�����
	 * @param ID
	 * @return
	 */
	public String QueryBookNumberByID(int ID) {
		String sql = "select bookNumber from SearchBooks where ID = ?";
		Object[] parameters = {ID};
		ResultSet rs = myDbhelper.executeQuery(sql,parameters);
		try {
			while(rs.next()) {
				return rs.getString("bookNumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �����Ƿ����
	 * @param bookName
	 * @return
	 */
	public boolean bookNameIsExist(String bookName){
		String sql = "select * from SearchBooks where bookName = ?";
		Object[] parameters = {bookName};
		if( myDbhelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
	
	/**
	 * ����Ƿ����
	 * @param bookNumber
	 * @return
	 */
	public boolean bookNumberIsExist(String bookNumber){
		String sql = "select * from SearchBooks where bookNumber = ?";
		Object[] parameters = {bookNumber};
		if( myDbhelper.getCount(sql, parameters) > 0)
			return true;
		return false;
	}
}
