package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cjon.book.service.BookService;

/**
 * Servlet implementation class CommentInsertServlet
 */
@WebServlet("/CommentInsertServlet")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("인설트 시작");
			String isbn = request.getParameter("isbn");
			String herecomment = request.getParameter("herecomment");
			String callback = request.getParameter("callback");
			
			HttpSession session = request.getSession(true);
			String id = (String)session.getAttribute("id");
			
			// 2. 로직처리
			BookService service = new BookService();
			boolean result = service.commentBook(isbn, herecomment, id);
		
			//boolean resutltitle = service.updateBook(isbn,title);
			
			// 3. 출력처리
			response.setContentType("text/plain; charset=utf8");
			PrintWriter out = response.getWriter();
			out.println(callback + "(" + result + ")");
			//out.println(callback+"("+ resutltitle+")");
			out.flush();
			out.close();
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
