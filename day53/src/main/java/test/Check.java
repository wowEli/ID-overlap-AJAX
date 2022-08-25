package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check
 */
@WebServlet("/check.do") 
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Check() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로직(아이디 중복검사)
		System.out.println("로그 doPost(): ["+request.getParameter("")+"]");

			TestDAO dao = new TestDAO();
			TestVO vo = new TestVO();

			vo.setMid(request.getParameter("mid")); // /check.do 할 때 ajax에서 mid를 보내줘야 했음
			
			int result = dao.check(vo); // output이 0 or 1 로 int
			
			// ****요청했던 곳(ajax)으로 result 값을 보낼 예정****
			// 보통 어노테이션을 사용하는데 어려워서 이번 경우만 응답방식으로 이용
			response.setContentType("application/x-json; charset=UTF-8"); // 이거 json파일이다 utf-8로 인코딩해!
			response.getWriter().write(result+""); // 문자열을 더하면서 String 문자열로 변환

			// 컬렉션 프레임워크를 이용해 여러 데이터를 ajax에 보내줄 수 있다
			// 데이터를 N개 , 객체 등을 보낼 수 있다
			// 이때 String 타입으로 보내야만 한다
			// view 에서는 JS가 동적타이핑을 지원하기 때문에
			// List로 바로 사용이 가능하다 ex) result[0] , result[1]
			
	}

}
