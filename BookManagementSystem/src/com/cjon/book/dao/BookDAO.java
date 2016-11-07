package com.cjon.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class BookDAO {

	public String select(String keyword) {
		// Database처리가 나와요!
		// 일반적으로 Database처리를 쉽게 하기 위해서
		// Tomcat같은 경우는 DBCP라는걸 제공해 줘요!
		// 추가적으로 간단한 라이브러리를 이용해서 DB처리를 해 볼꺼예요!!
		// 1. Driver Loading ( 설정에 있네.. )
		// 2. Connection 생성
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select bisbn, bimgurl, btitle, bauthor, bprice "
					   + "from book where btitle like ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgurl"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean update(String isbn, String price, String title, String author) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println(isbn);
			System.out.println(price);
			System.out.println(title);
			System.out.println(author);
			String sql = "update book set btitle=?, bauthor=?, bprice=? where bisbn=?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, Integer.parseInt(price));
			pstmt.setString(4, isbn);
			
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean delete(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println(isbn);
		
			String sql = "delete from book where bisbn=?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, isbn);
		
			
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;

}

	public String info(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			System.out.println(isbn);
			
		
			String sql = "select bdate, bpage, bpublisher from book where bisbn=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			JSONObject obj = null;
			if(rs.next()) {
				obj = new JSONObject();				
				obj.put("date", rs.getString("bdate"));
				obj.put("page", rs.getString("bpage"));
				obj.put("publisher", rs.getString("bpublisher"));
			}
			result = obj.toJSONString();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
		

	}

	public boolean insert(String isbn, String title, String author, String price) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			System.out.println(isbn);
			System.out.println(title);
			System.out.println(author);
			System.out.println(price);
			
		
			String sql = "insert into book(bisbn, btitle, bauthor, bprice) values(?,?,?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, author);
			pstmt.setString(4, price);
			
			
			int rs = pstmt.executeUpdate();
			
			if(rs==1){
				result = true;
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	
	
	}


	public boolean enroll(String id, String email, String password) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			System.out.println(id);
			System.out.println(email);
			System.out.println(password);
		
			
		
			String sql = "insert into member(mid, memail, mpassword ) values(?,?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			System.out.println("id:  "+id);
			System.out.println("emaoll: "+email);
			System.out.println("pass : "+password);
			
			int rs = pstmt.executeUpdate();
			
			if(rs==1){
				result = true;
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean login(String id, String password) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			System.out.println(id);
			System.out.println(password);
		
			System.out.println(result);
		
			String sql = "select * from member where mid=? and mpassword=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			//JSONArray arr = new JSONArray();
			if(rs.next()) {
				
				System.out.println("환영합니다");
				result = true;
				
	
				//arr.add(obj);
			}
			//result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean logout(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public String commentinfo(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select bisbn,bimgurl, btitle, bauthor, bprice from book where bisbn=?";
					  
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			JSONObject obj = null;
			if(rs.next()) {
				obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgurl"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));		
			
			}
			
			result = obj.toJSONString();
			
	
			
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean insertcomment(String isbn, String herecomment, String id) {
		System.out.println("-------여여여여여여영여ㅕ여여여여여");
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		
		boolean result = false;
		try {
			
				String sqlcom = "insert into real_comment(cid, bisbn, cdate, ctext) values(?,?,now(),?)";
				PreparedStatement pstmtcom = null;
				pstmt = con.prepareStatement(sqlcom);
				pstmt.setString(1, id);
				pstmt.setString(2, isbn);
				pstmt.setString(3, herecomment);
				System.out.println("-------333333333333");
				int rs1 = pstmt.executeUpdate();
				System.out.println("-------444444444444");
				if(rs1==1){
					//result = true;
					DBTemplate.commit(con);
				} else {
					DBTemplate.rollback(con);
				}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
	
		return result;
		
	}

	public String commentload(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		
		try {
			System.out.println(isbn);
			
		
			JSONObject obj = null;
			
			String sql= "select cid, ctext, cdate from real_comment where bisbn=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			System.out.println("-------55555555555555");			
			while(rs.next()){
				System.out.println("-------666666666666666666666666666666666");	
				obj = new JSONObject();
				obj.put("id", rs.getString("cid"));
				obj.put("date", rs.getString("cdate"));
				obj.put("text", rs.getString("ctext"));
				arr.add(obj);
								
					
			}; 
		
			result = arr.toJSONString();
			
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBTemplate.close(rs);
				DBTemplate.close(pstmt);
				DBTemplate.close(con);
			} 

	return result;

	}

	public String searchComment(String keyword) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select seq, cid, ctext, cdate from real_comment where ctext like ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("seq", rs.getString("seq"));
				obj.put("id", rs.getString("cid"));
				obj.put("text", rs.getString("ctext"));
				obj.put("date", rs.getString("cdate"));
		System.out.println("7777777777777777777777777777777777777777");
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean deleteComment(String seq) {
		System.out.println("888888888888888888888888888888");
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println(seq);
		
			String sql = "delete from real_comment where seq=?";
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, seq);
		
			
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}
}









