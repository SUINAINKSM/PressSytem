package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dal.dbhelper.MyDBHelper;
import model.Book;
import model.E;
import ui.TSDJ_UI;

public class BookInformationBiz {
	public MyDBHelper myDbhelper = new MyDBHelper();

	public List<Book> getBookInformation(int sortNum) {
		List<Book> result = new ArrayList<Book>();
		String sql = null;
		if (sortNum == 1)
			sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY books.Num";
		else if (sortNum == 2)
			sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bookName";
		else if (sortNum == 3)
			sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bjsName";
		else if (sortNum == 4)
			sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY publishTime";
		else if (sortNum == 5)
			sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bookTypeName";
		ResultSet rs = myDbhelper.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setAuthorName(rs.getString("authorName"));
				book.setBookNum(rs.getString("Num"));
				book.setBookTypeName(rs.getString("bookTypeName"));
				book.setSizeName(rs.getString("sizeName"));
				book.setPrintNum(rs.getInt("printNumber"));
				book.setPrice(String.valueOf(rs.getFloat("price")));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(rs.getDate("publishTime"));
				// System.out.println(sDate);
				book.setPublishTime(sDate);
				book.setLanguageName(rs.getString("languageName"));
				book.setBjsName(rs.getString("bjsName"));
				book.setEditorName(rs.getString("EditorName"));
				result.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Object[][] getBookList(String searchText, int sortNum) throws SQLException {
		List<Book> result = QueryAll(searchText, sortNum);
		System.out.println(result.size());
		Object[][] obj = new Object[result.size()][11];
		for (int i = 0; i < result.size(); i++) {
			obj[i][0] = result.get(i).getBookNum();
			obj[i][1] = result.get(i).getBookName();
			obj[i][2] = result.get(i).getAuthorName();
			obj[i][3] = result.get(i).getBookTypeName();
			obj[i][4] = result.get(i).getSizeName();
			obj[i][5] = result.get(i).getPrintNum();
			obj[i][6] = result.get(i).getPrice();
			obj[i][7] = result.get(i).getPublishTime();
			obj[i][8] = result.get(i).getLanguageName();
			obj[i][9] = result.get(i).getBjsName();
			obj[i][10] = result.get(i).getEditorName();
		}
		return obj;

	}

	public List<String> getBookTypeNameList() throws SQLException {
		List<String> array = new ArrayList<String>();
		String sql = "select bookTypeName from bookType ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			array.add(rs.getString("bookTypeName"));
		}
		return array;

	}

	public List<String> getSizeNameList() throws SQLException {
		List<String> array = new ArrayList<String>();
		String sql = "select sizeName from size ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			array.add(rs.getString("sizeName"));
		}
		return array;

	}

	public List<String> getLanguageNameList() throws SQLException {
		List<String> array = new ArrayList<String>();
		String sql = "select languageName from language ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			array.add(rs.getString("languageName"));
		}
		return array;

	}

	public List<String> getBjsNameList() throws SQLException {
		List<String> array = new ArrayList<String>();
		String sql = "select bjsName from Bjs ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			array.add(rs.getString("bjsName"));
		}
		return array;

	}

	public List<String> getBjsEitorList(String bjsName) throws SQLException {
		List<String> array = new ArrayList<String>();
		String sql = "select editorName from editors,bjs where bjs.ID=editors.editorBjsID and bjs.bjsName=? ";
		Object[] obj = new Object[] { bjsName };
		ResultSet rs = myDbhelper.executeQuery(sql, obj);
		while (rs.next()) {
			array.add(rs.getString("editorName"));
		}
		return array;

	}

	public int getbookTypeID(String bookTypeName) throws SQLException {
		String sql = "select ID  from bookType WHERE bookTypeName=? ";
		Object[] paratemers = new Object[] { bookTypeName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;
	}

	public int getSizeID(String sizeName) throws SQLException {
		String sql = "select ID  from size WHERE sizeName=? ";
		Object[] paratemers = new Object[] { sizeName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;
	}

	public int getLanguageID(String languageName) throws SQLException {
		String sql = "select ID  from language WHERE languageName=? ";
		Object[] paratemers = new Object[] { languageName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;
	}

	public int getBjsID(String bjsName) throws SQLException {
		String sql = "select ID  from bjs WHERE bjsName=? ";
		Object[] paratemers = new Object[] { bjsName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;
	}

	public int getEditorID(String editorName) throws SQLException {
		String sql = "select ID  from editors WHERE editorName=? ";
		Object[] paratemers = new Object[] { editorName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;
	}

	public int AddBookInformation(String bookName, String authorName, String bookNum, String bookTypeName,
			String sizeName, int printNum, double price, String publishTime, String languageName, String bjsName,
			String editorName) throws SQLException {// 注册
		String sql = "select * from books";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			try {
				if (rs.getString("bookName").equals(bookName)) {
					return 0;
				}
				if (rs.getString("Num").equals(bookNum)) {
					return -1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date date = new Date();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			System.out.println(publishTime);

			date = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(publishTime);
			// 格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = sdf.format(date);
			// System.out.println(sDate);
			// date=sDateFormat.parse(publishTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获得当前用户自动解锁的时间
		String sql1 = "Insert INTO books Values(?,?,?,?,?,?,?,?,?,?,?)";
		int sizeID = this.getSizeID(sizeName);
		int languageID = this.getLanguageID(languageName);
		int bjsID = this.getBjsID(bjsName);
		int editorID = this.getEditorID(editorName);
		Object[] obj = new Object[] { bookName, authorName, bookNum, bookTypeName, sizeID, printNum, price, date,
				languageID, bjsID, editorID };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;

	}

	public int getID(String bookName) throws SQLException {
		String sql = "select ID from books where bookName=?";
		Object[] paratemers = new Object[] { bookName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		int b = 0;
		while (rs.next()) {
			b = rs.getInt("ID");
		}
		return b;

	}

	public String getbookName(int bid) throws SQLException {// 获得图书的名称
		String sql = "select bookName from books where ID=?";
		Object[] paratemers = new Object[] { bid };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		String b = null;
		while (rs.next()) {
			b = rs.getString("bookName");
		}
		return b;

	}

	public String getbookNum(int bid) throws SQLException {// 获得图书的名称
		String sql = "select Num from books where ID=?";
		Object[] paratemers = new Object[] { bid };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		String b = null;
		while (rs.next()) {
			b = rs.getString("Num");
		}
		return b;

	}

	public String getprintNum(int bid) throws SQLException {// 获得图书的名称
		String sql = "select printNum from books where ID=?";
		Object[] paratemers = new Object[] { bid };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		String b = null;
		int bss = 0;
		while (rs.next()) {
			bss = rs.getInt("printNum");
		}
		b = String.valueOf(bss);
		return b;

	}

	public String getAuthorName(int bid) throws SQLException {// 获得图书的名称
		String sql = "select authorName from books where ID=?";
		Object[] paratemers = new Object[] { bid };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		String b = null;
		while (rs.next()) {
			b = rs.getString("authorName");
		}
		return b;

	}

	public Date getPublishTime(String bookName) throws SQLException {
		String sql = "select publishTime from books where bookName=?";
		Object[] paratemers = new Object[] { bookName };// 数据传入
		ResultSet rs = myDbhelper.executeQuery(sql, paratemers);
		Date date = new Date();
		while (rs.next()) {
			date = rs.getDate("publishTime");
		}
		return date;

	}

	public void deleteBook(String bookName) throws SQLException {
		String sql = "select bookName from books ";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {
			try {
				if (rs.getString("bookName").equals(bookName)) {
					String sql2 = "Delete from books where bookName=?";
					Object[] obj1 = new Object[] { bookName };
					myDbhelper.executeNonQuery(sql2, obj1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int updateBookName(int bid, String bookName) throws SQLException {
		String sql = "select bookName from books";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {

			if (rs.getString("bookName").equals(bookName)) {
				return 0;
			}
		}
		String sql1 = "update books set bookName=? where ID=? ";
		Object[] obj = new Object[] { bookName, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updateBookNum(int bid, String bookNum) throws SQLException {
		String sql = "select Num from books";
		ResultSet rs = myDbhelper.executeQuery(sql);
		while (rs.next()) {

			if (rs.getString("Num").equals(bookNum)) {
				return 0;
			}
		}
		String sql1 = "update books set Num=? where ID=? ";
		Object[] obj = new Object[] { bookNum, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updateAuthorName(int bid, String authorName) throws SQLException {
		String sql1 = "update books set authorName=? where ID=? ";
		Object[] obj = new Object[] { authorName, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updateBookTypeName(int bid, String bookTypeName) throws SQLException {
		String sql1 = "update books set bookTypeName=? where ID=? ";
		Object[] obj = new Object[] { bookTypeName, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updateSizeID(int bid, String sizeName) throws SQLException {
		String sql = "select sizeName,ID from size";
		ResultSet rs = myDbhelper.executeQuery(sql);
		int sid = 0;
		while (rs.next()) {

			if (rs.getString("sizeName").equals(sizeName)) {
				sid = rs.getInt("ID");
			}
		}
		String sql1 = "update books set SizeID=? where ID=? ";
		Object[] obj1 = new Object[] { sid, bid };
		myDbhelper.executeNonQuery(sql1, obj1);
		return 1;
	}

	public int updatePrintNum(int bid, int printNum) throws SQLException {
		String sql1 = "update books set printNumber=? where ID=? ";
		Object[] obj = new Object[] { printNum, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updatePrice(int bid, double price) throws SQLException {
		String sql1 = "update books set price=? where ID=? ";
		Object[] obj = new Object[] { price, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updatePublishTime(int bid, Date publishTime) throws SQLException {
		String sql1 = "update books set publishTime=? where ID=? ";
		Object[] obj = new Object[] { publishTime, bid };
		myDbhelper.executeNonQuery(sql1, obj);
		return 1;
	}

	public int updateLanguageID(int bid, String languageName) throws SQLException {
		String sql = "select languageName,ID from language";
		ResultSet rs = myDbhelper.executeQuery(sql);
		int sid = 0;
		while (rs.next()) {

			if (rs.getString("languageName").equals(languageName)) {
				sid = rs.getInt("ID");
			}
		}
		String sql1 = "update books set languageID=? where ID=? ";
		Object[] obj1 = new Object[] { sid, bid };
		myDbhelper.executeNonQuery(sql1, obj1);
		return 1;
	}

	public int updateBjsID(int bid, String bjsName) throws SQLException {
		String sql = "select bjsName,ID from bjs";
		ResultSet rs = myDbhelper.executeQuery(sql);
		int sid = 0;
		while (rs.next()) {

			if (rs.getString("bjsName").equals(bjsName)) {
				sid = rs.getInt("ID");
			}
		}
		String sql1 = "update books set bjsNameID=? where ID=? ";
		Object[] obj1 = new Object[] { sid, bid };
		myDbhelper.executeNonQuery(sql1, obj1);
		return 1;
	}

	public int updateEditorNameID(int bid, String editorName) throws SQLException {
		String sql = "select editorName,ID from editors";
		ResultSet rs = myDbhelper.executeQuery(sql);
		int sid = 0;
		while (rs.next()) {

			if (rs.getString("editorName").equals(editorName)) {
				sid = rs.getInt("ID");
			}
		}
		String sql1 = "update books set bjsEditorNameID=? where ID=? ";
		Object[] obj1 = new Object[] { sid, bid };
		myDbhelper.executeNonQuery(sql1, obj1);
		return 1;
	}

	public int getBookTableNumber() throws SQLException {
		String sql1 = "Select * from books";
		ResultSet rs1 = myDbhelper.executeQuery(sql1);
		int i = 0;
		while (rs1.next()) {
			i++;
		}
		myDbhelper.free(rs1);
		return i;

	}

	public List<Book> QueryAll(String searchText, int sortNum) {
		// Book类不同于Books之处为Books对应数据库原始数据，而Book对应表格展示数据
		List<Book> result = new ArrayList<Book>();
		String sql = null;
		ResultSet rs = null;
		if (searchText == null || searchText.equals("")) {
			if (sortNum == 1)
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY books.Num";
			else if (sortNum == 2)
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bookName";
			else if (sortNum == 3)
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bjsName";
			else if (sortNum == 4)
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY publishTime";
			else if (sortNum == 5)
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   ORDER BY bookTypeName";
			rs = myDbhelper.executeQuery(sql);
		}

		else {
			if (sortNum == 1) {
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   "
						+ " And (books.Num like '%" // 增加搜索
						+ searchText + "%' or" + " bookName like '%" + searchText + "%' or" + " authorName like '%"
						+ searchText + "%' or" + " books.bookTypeName like '%" + searchText + "%' or"
						+ " sizeName like '%" + searchText + "%' or" + " publishTime like '%" + searchText + "%' or"
						+ " languageName like '%" + searchText + "%' or" + " price like '%" + searchText + "%' or"
						+ " printNumber like '%" + searchText + "%' or" + " bjsName like '%" + searchText + "%' or"
						+ " editorName like '%" + searchText + "%') ORDER BY books.Num";
			} else if (sortNum == 2) {
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   "
						+ " And (books.Num like '%" // 增加搜索
						+ searchText + "%' or" + " bookName like '%" + searchText + "%' or" + " authorName like '%"
						+ searchText + "%' or" + " books.bookTypeName like '%" + searchText + "%' or"
						+ " sizeName like '%" + searchText + "%' or" + " publishTime like '%" + searchText + "%' or"
						+ " languageName like '%" + searchText + "%' or" + " price like '%" + searchText + "%' or"
						+ " printNumber like '%" + searchText + "%' or" + " bjsName like '%" + searchText + "%' or"
						+ " editorName like '%" + searchText + "%') ORDER BY bookName";
			} else if (sortNum == 3) {
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   "
						+ " And (books.Num like '%" // 增加搜索
						+ searchText + "%' or" + " bookName like '%" + searchText + "%' or" + " authorName like '%"
						+ searchText + "%' or" + " books.bookTypeName like '%" + searchText + "%' or"
						+ " sizeName like '%" + searchText + "%' or" + " publishTime like '%" + searchText + "%' or"
						+ " languageName like '%" + searchText + "%' or" + " price like '%" + searchText + "%' or"
						+ " printNumber like '%" + searchText + "%' or" + " bjsName like '%" + searchText + "%' or"
						+ " editorName like '%" + searchText + "%') ORDER BY bjsName";
			} else if (sortNum == 4) {
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   "
						+ " And (books.Num like '%" // 增加搜索
						+ searchText + "%' or" + " bookName like '%" + searchText + "%' or" + " authorName like '%"
						+ searchText + "%' or" + " books.bookTypeName like '%" + searchText + "%' or"
						+ " sizeName like '%" + searchText + "%' or" + " publishTime like '%" + searchText + "%' or"
						+ " languageName like '%" + searchText + "%' or" + " price like '%" + searchText + "%' or"
						+ " printNumber like '%" + searchText + "%' or" + " bjsName like '%" + searchText + "%' or"
						+ " editorName like '%" + searchText + "%') ORDER BY publishTime";
			} else {
				sql = "SELECT books.Num,bookName,authorName,books.bookTypeName,sizeName,printNumber,price,publishTime,languageName,bjsName,editorName from books,bookType,size,language,bjs,editors WHERE books.bookTypeName=bookType.bookTypeName and books.sizeID=size.ID AND books.languageID=language.ID AND books.bjsNameID=bjs.ID AND editors.ID=books.bjsEditorNameID   "
						+ " And (books.Num like '%" // 增加搜索
						+ searchText + "%' or" + " bookName like '%" + searchText + "%' or" + " authorName like '%"
						+ searchText + "%' or" + " books.bookTypeName like '%" + searchText + "%' or"
						+ " sizeName like '%" + searchText + "%' or" + " publishTime like '%" + searchText + "%' or"
						+ " languageName like '%" + searchText + "%' or" + " price like '%" + searchText + "%' or"
						+ " printNumber like '%" + searchText + "%' or" + " bjsName like '%" + searchText + "%' or"
						+ " editorName like '%" + searchText + "%') ORDER BY bookTypeName";
			}
            System.out.println(sql);
			rs = myDbhelper.executeQuery(sql);
		}
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setAuthorName(rs.getString("authorName"));
				book.setBookNum(rs.getString("Num"));
				book.setBookTypeName(rs.getString("bookTypeName"));
				book.setSizeName(rs.getString("sizeName"));
				book.setPrintNum(rs.getInt("printNumber"));
				book.setPrice(String.valueOf(rs.getFloat("price")));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sDate = sdf.format(rs.getDate("publishTime"));
				// System.out.println(sDate);
				book.setPublishTime(sDate);
				book.setLanguageName(rs.getString("languageName"));
				book.setBjsName(rs.getString("bjsName"));
				book.setEditorName(rs.getString("EditorName"));
				result.add(book);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
