package com.feifanuniv.collegeebook.entity;

public class Ebook {
	private int id;
	private String SSId;
	private String DXId;
	private String bookKey;
	private String bookName;
	private String author;
	private String publisher;
	private String bookPath;
	private String coverImgPath;
	private int bookStatus;
	
	public String getSSId() {
		return SSId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDXId() {
		return DXId;
	}
	public void setDXId(String dXId) {
		DXId = dXId;
	}
	public String getBookKey() {
		return bookKey;
	}
	public void setBookKey(String bookKey) {
		this.bookKey = bookKey;
	}
	public void setSSId(String sSId) {
		SSId = sSId;
	}
	public String getBookPath() {
		return bookPath;
	}
	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}
	public String getCoverImgPath() {
		return coverImgPath;
	}
	public void setCoverImgPath(String coverImgPath) {
		this.coverImgPath = coverImgPath;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}
	@Override
	public String toString() {
		return "Ebook [id=" + id + ", SSId=" + SSId + ", DXId=" + DXId + ", bookKey=" + bookKey + ", bookName="
				+ bookName + ", author=" + author + ", publisher=" + publisher + ", bookPath=" + bookPath
				+ ", coverImgPath=" + coverImgPath + ", bookStatus=" + bookStatus + "]";
	}
	
}
