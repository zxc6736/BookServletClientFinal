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
 * Servlet implementation class BookLoginServlet
 */
@WebServlet("/BookLoginServlet")
public class BookLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		String callback = request.getParameter("callback");
		String password = request.getParameter("password");
	
		System.out.println("id:   "+id+"password:  "+password);
		// 2. 로직처리
		BookService service = new BookService();
		boolean result = service.login(id, password);
	
		if(result){
			HttpSession session = request.getSession(true);
			// 만약 기존에 사용하던 세션 객체가 있으면 그거 들고오고
			// 만약 기존에 사용하던 세션 객체가 없으면 새로 만들어서 들고와!!
			session.setAttribute("id", id);
			session.setAttribute("password", password);
			System.out.println("세션세션세션:");
		}else{
			
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
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
