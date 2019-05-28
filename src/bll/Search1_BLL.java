package bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.dbhelper.MyDBHelper;
import model.Search1_model;

public class Search1_BLL {
	public MyDBHelper mydbhelper=new MyDBHelper();
	public List<Search1_model> getAllBook() throws SQLException
	{
		String sql="select * from searchbooks";
		List<Search1_model> booklist=new ArrayList<Search1_model>();
		ResultSet rs=mydbhelper.executeQuery(sql);
		while(rs.next())
		{
			Search1_model bk=new Search1_model();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	/**
	 * 甯︽棩鏈熻寖鍥寸殑鏌ヨ
	 * @param condition
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */
	public List<Search1_model> QueryWithDate(List<String> condition,String start,String end) throws SQLException
	{
		String sql=null;
		if(condition.isEmpty())
		{
			String sqlhead="select * from searchbooks where publishTime>='"+start
					+"' and publishTime<='"+end+"'";
			sql=sqlhead;
		}
		else
		{
			String sqlhead="select * from searchbooks where publishTime>='"+start
							+"' and publishTime<='"+end+"' and merge like ";
			sql=sqlhead;
			for(int i=0;i<condition.size();i++)
			{
				String s=condition.get(i);
				if(i==condition.size()-1)//���һ�������󲻼�and
				{
					sql=sql+"'%"+s+"%'";
				}
				else
				{
					sql=sql+"'%"+s+"%' and merge like ";
				}
			}
		}
		List<Search1_model> booklist=new ArrayList<Search1_model>();
		ResultSet rs=mydbhelper.executeQuery(sql);
		while(rs.next())
		{
			Search1_model bk=new Search1_model();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	/**
	 * 鏃犳棩鏈熸煡璇�
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Search1_model> QueryNoDate(List<String> condition) throws SQLException
	{
		String sqlhead="select * from searchbooks where [merge] like ";
		String sql=sqlhead;
		for(int i=0;i<condition.size();i++)
		{
			String s=condition.get(i);
			if(i==condition.size()-1)
			{
				sql=sql+"'%"+s+"%'";
			}
			else
			{
				sql=sql+"'%"+s+"%' and [merge] like ";
			}
		}
		List<Search1_model> booklist=new ArrayList<Search1_model>();
		ResultSet rs=mydbhelper.executeQuery(sql);
		while(rs.next())
		{
			Search1_model bk=new Search1_model();
			bk.setID(rs.getInt(1));
			bk.setBookName(rs.getString(2));
			bk.setAuthorName(rs.getString(3));
			bk.setBookNumber(rs.getString(4));
			bk.setBookType(rs.getString(5));
			bk.setSize(rs.getString(6));
			bk.setPrintNumber(rs.getInt(7));
			bk.setPrice(rs.getFloat(8));
			bk.setPublishTime(rs.getDate(9));
			bk.setArticleType(rs.getString(10));
			bk.setBjsName(rs.getString(11));
			bk.setEditorName(rs.getString(12));
			bk.setMerge(rs.getString(13));
			booklist.add(bk);
		}
		return booklist;
	}
	/**
		 * �޼۸���ʱ�䷶Χ��ѯ
		 * @param condition
		 * @return
		 * @throws SQLException
		 */
		public List<Search1_model> QueryWithNothing(List<String> condition) throws SQLException
		{
			String sqlhead="select * from searchbooks where [merge] like ";
			String sql=sqlhead;
			for(int i=0;i<condition.size();i++)
			{
				String s=condition.get(i);
				
				if(i==condition.size()-1)//���һ�������󲻼�and
				{
					sql=sql+"'%"+s+"%'";
				}
				else
				{
					sql=sql+"'%"+s+"%' and [merge] like ";
				}
			}
			List<Search1_model> booklist=new ArrayList<Search1_model>();
			ResultSet rs=mydbhelper.executeQuery(sql);
			while(rs.next())
			{
				Search1_model bk=new Search1_model();
				bk.setID(rs.getInt(1));
				bk.setBookName(rs.getString(2));
				bk.setAuthorName(rs.getString(3));
				bk.setBookNumber(rs.getString(4));
				bk.setBookType(rs.getString(5));
				bk.setSize(rs.getString(6));
				bk.setPrintNumber(rs.getInt(7));
				bk.setPrice(rs.getFloat(8));
				bk.setPublishTime(rs.getDate(9));
				bk.setArticleType(rs.getString(10));
				bk.setBjsName(rs.getString(11));
				bk.setEditorName(rs.getString(12));
				bk.setMerge(rs.getString(13));
				booklist.add(bk);
			}
			return booklist;
		}
		/**
			 * �м۸���ʱ�䷶Χ��ѯ
			 * @param condition
			 * @param minprice
			 * @param maxprice
			 * @return
			 * @throws SQLException
			 */
			public List<Search1_model> QueryWithPrice(List<String> condition,String minprice,String maxprice) throws SQLException
			{
				String sqlhead=null;
				if(minprice.length()<=0&&maxprice.length()>0)//ֻ����߼�
				{
					sqlhead="select * from searchbooks where price<="+Float.valueOf(maxprice);
				}
				else if(maxprice.length()<=0&&minprice.length()>0)//ֻ����ͼ�
				{
					sqlhead="select * from searchbooks where price>="+Float.valueOf(minprice);
				}
				else//��߼ۺ���ͼ۶�����
				{
					sqlhead="select * from searchbooks where price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice);
				}
				String sql=null;
				if(condition.isEmpty())
				{
					sql=sqlhead;
				}
				else
				{
					sql=sqlhead+" and [merge] like";
					for(int i=0;i<condition.size();i++)
					{
						String s=condition.get(i);
						if(i==condition.size()-1)//���һ�������󲻼�and
						{
							sql=sql+"'%"+s+"%'";
						}
						else
						{
							sql=sql+"'%"+s+"%' and [merge] like ";
						}
					}
				}
				List<Search1_model> booklist=new ArrayList<Search1_model>();
				ResultSet rs=mydbhelper.executeQuery(sql);
				while(rs.next())
				{
					Search1_model bk=new Search1_model();
					bk.setID(rs.getInt(1));
					bk.setBookName(rs.getString(2));
					bk.setAuthorName(rs.getString(3));
					bk.setBookNumber(rs.getString(4));
					bk.setBookType(rs.getString(5));
					bk.setSize(rs.getString(6));
					bk.setPrintNumber(rs.getInt(7));
					bk.setPrice(rs.getFloat(8));
					bk.setPublishTime(rs.getDate(9));
					bk.setArticleType(rs.getString(10));
					bk.setBjsName(rs.getString(11));
					bk.setEditorName(rs.getString(12));
					bk.setMerge(rs.getString(13));
					booklist.add(bk);
				}
				return booklist;
			}
			/**
				 * ��ʱ���м۸�Χ�Ĳ�ѯ
				 * @param condition
				 * @param start
				 * @param end
				 * @param minprice
				 * @param maxprice
				 * @return
				 * @throws SQLException
				 */
				public List<Search1_model> QueryDateAndPrice(List<String> condition, String start, String end,
						String minprice, String maxprice) throws SQLException {
					String sqlhead=null;
					String sql=null;
					if(condition.isEmpty())
					{
						sqlhead="select * from searchbooks where publishTime>='"+start
								+"' and publishTime<='"+end+"' ";
						if(minprice.length()<=0&&maxprice.length()>0)//ֻ����߼�
						{
							sqlhead=sqlhead+"and price<="+Float.valueOf(maxprice);
						}
						else if(maxprice.length()<=0&&minprice.length()>0)//ֻ����ͼ�
						{
							sqlhead=sqlhead+"and price>="+Float.valueOf(minprice);
						}
						else//��߼ۺ���ͼ۶�����
						{
							sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice);
						}
						sql=sqlhead;
					}
					else
					{
						sqlhead="select * from searchbooks where publishTime>='"+start
								+"' and publishTime<='"+end+"' ";
						if(minprice.length()<=0&&maxprice.length()>0)//ֻ����߼�
						{
							sqlhead=sqlhead+"and price<="+Float.valueOf(maxprice)+" and [merge] like";
						}
						else if(maxprice.length()<=0&&minprice.length()>0)//ֻ����ͼ�
						{
							sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and [merge] like";
						}
						else//��߼ۺ���ͼ۶�����
						{
							sqlhead=sqlhead+"and price>="+Float.valueOf(minprice)+" and price<="+Float.valueOf(maxprice)+" and [merge] like";
						}
						sql=sqlhead;
						for(int i=0;i<condition.size();i++)
						{
							String s=condition.get(i);
							if(i==condition.size()-1)//���һ�������󲻼�and
							{
								sql=sql+"'%"+s+"%'";
							}
							else
							{
								sql=sql+"'%"+s+"%' and [merge] like ";
							}
						}
					}
					List<Search1_model> booklist=new ArrayList<Search1_model>();
					ResultSet rs=mydbhelper.executeQuery(sql);
					while(rs.next())
					{
						Search1_model bk=new Search1_model();
						bk.setID(rs.getInt(1));
						bk.setBookName(rs.getString(2));
						bk.setAuthorName(rs.getString(3));
						bk.setBookNumber(rs.getString(4));
						bk.setBookType(rs.getString(5));
						bk.setSize(rs.getString(6));
						bk.setPrintNumber(rs.getInt(7));
						bk.setPrice(rs.getFloat(8));
						bk.setPublishTime(rs.getDate(9));
						bk.setArticleType(rs.getString(10));
						bk.setBjsName(rs.getString(11));
						bk.setEditorName(rs.getString(12));
						bk.setMerge(rs.getString(13));
						booklist.add(bk);
					}
					return booklist;
				}


	
}
