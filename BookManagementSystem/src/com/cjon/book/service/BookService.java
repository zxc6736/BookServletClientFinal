package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;

public class BookService {

	// 리스트를 가져오는 일을 하는 method
	public String getList(String keyword) {
		// 일반적인 로직처리 나와요!!
		
		// 추가적으로 DB처리가 나올 수 있어요!
		// DB처리는 Service에서 처리는하는게 아니라..다른 객체를 이용해서
		// Database처리를 하게 되죠!!
		BookDAO dao = new BookDAO();
		String result = dao.select(keyword);	
		
		return result;
	}

	public boolean updateBook(String isbn, String price, String title, String author) {
	
		BookDAO dao = new BookDAO();
		boolean result = dao.update(isbn,price,title,author);	
		return result;
	}

	public boolean deleteBook(String isbn) {
		
		BookDAO dao = new BookDAO();
		boolean result = dao.delete(isbn);
		return result;
	}

	public String infoBook(String isbn) {
		
		BookDAO dao = new BookDAO();
		String result = dao.info(isbn);
		return result;
	}

	public boolean insertBook(String isbn, String title, String author, String price) {
		
		
		BookDAO dao = new BookDAO();
		boolean result = dao.insert(isbn, title, author, price);
		return result;
	}

	

	public boolean enrollmember(String id, String password, String email) {
		BookDAO dao = new BookDAO();
		boolean result = dao.enroll(id, email, password);
		return result;
	}

	public boolean login(String id, String password) {
		BookDAO dao = new BookDAO();
		boolean result = dao.login(id,password);
		return result;
	}

	public boolean logout(String id, String password) {
		BookDAO dao = new BookDAO();
		boolean result = dao.logout(id,password);
		return result;
	}


	public String CommentInfo(String isbn) {
		BookDAO dao = new BookDAO();
		String result = dao.commentinfo(isbn);
		return result;
	}

	public boolean commentBook(String isbn, String herecomment, String id) {
		System.out.println("여기는 오니오니오니?");
		BookDAO dao = new BookDAO();
		boolean result = dao.insertcomment(isbn, herecomment, id);
		return result;
	}

	public String commentLoad(String isbn) {
		System.out.println("코멘트를 불러와용?");
		BookDAO dao = new BookDAO();
		String result = dao.commentload(isbn);
		return result;
	}

	public String getComment(String keyword) {
		
				BookDAO dao = new BookDAO();
				String result = dao.searchComment(keyword);	
				
				return result;
	}

	public boolean deleteComment(String seq) {
		BookDAO dao = new BookDAO();
		boolean result = dao.deleteComment(seq);	
		
		return result;
	}
}











