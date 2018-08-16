package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
	    PrintWriter out= response.getWriter();
	    userBean user = new userBean();
	    userDao userdao = new userDao();
	    bikeDao bikedao = new bikeDao();
	    String phone=request.getParameter("phone");
	    String password = request.getParameter("password");
	    //System.out.println(phone+password);
	    user.setPhone(phone);
	    user.setPassword(password);
	    int check = userdao.checkPassword(user);
	    if(check==1){
	    	List<bike> bike=bikedao.selNotLend();
	    	double [][]place = new double[bike.size()][2];
	    	int i=0;
	    	for(bike b:bike){
	    		if(b.getPlace().equals("")){
	    			continue;
	    		}
	    		String pla = b.getPlace();
	    		String []xy = pla.split(",");
	    		if(xy[0].toString().equals("undefined")){
	    			continue;
	    		}
	    		double x =Double.valueOf(xy[0].toString());

	    		double y =Double.valueOf(xy[1].toString());
	    		place[i][0]=x;
	    		place[i][1]=y;
	    		i++;
	    	}
	    	int bno = userdao.selBno(phone);
	    	session.setAttribute("phone", phone);
	    	session.setAttribute("place", place);
	    	session.setAttribute("bno", bno);
	    	out.print("µ«¬º≥…π¶");

	    }else{
	    	out.print("’À∫≈ªÚ√‹¬Î¥ÌŒÛ!");
	    }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
