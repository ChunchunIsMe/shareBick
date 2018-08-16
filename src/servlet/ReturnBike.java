package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.bike;
import bean.userBean;
import dao.bikeDao;
import dao.userDao;

/**
 * Servlet implementation class ReturnBike
 */
@WebServlet("/ReturnBike")
public class ReturnBike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType("text/html");  
		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0); 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		bike bike = new bike();
		userBean user = new userBean();
		bikeDao bikedao = new bikeDao();
		userDao userdao = new userDao();
		String place = request.getParameter("place");
		String phone = request.getParameter("phone");
		String lendtime = userdao.selTime(phone);
		int bno = userdao.selBno(phone);
		bike.setBno(bno);
		bike.setplace(place);
		bikedao.updateBikeReturn(bike);
		userdao.updateBikeBuff(user);
		System.out.println(user.getDatetime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		java.util.Date now,date;
		long l=0;
		try {
			now = df.parse(user.getDatetime());
			date=df.parse(lendtime);
			l=now.getTime()-date.getTime();
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		long day=l/(24*60*60*1000);

		long hour=(l/(60*60*1000)-day*24);

		long min=((l/(60*1000))-day*24*60-hour*60);

		long s=(l/1000-day*24*60*60-hour*60*60-min*60);

		
		long minutes=day*1440+hour*60+min;
		System.out.println(minutes+"分");
		int money=(int) (-minutes/60);
		user.setPhone(phone);
		user.setMoney(money);
		userdao.updateMoney(user);
		userdao.updateBikeBuff(user);
		PrintWriter out= response.getWriter();
        out.print("还车成功!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
