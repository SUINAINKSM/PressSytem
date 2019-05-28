package model;

import java.math.BigDecimal;

public class Book {
	public int id;
	public String bookName;
	public String authorName;
	public String bookNum;
	public String bookTypeName;
	public String sizeName;
	public int printNum;
	public String price;
	public String publishTime;
	private String languageName;
	private String bjsName;
	private String editorName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookType) {
		this.bookTypeName = bookType;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public int getPrintNum() {
		return printNum;
	}
	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getBjsName() {
		return bjsName;
	}
	public void setBjsName(String bjsName) {
		this.bjsName = bjsName;
	}
	public String getEditorName() {
		return editorName;
	}
	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}
	
}
