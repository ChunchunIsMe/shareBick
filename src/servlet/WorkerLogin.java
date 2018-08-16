package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bean.bike;
import bean.worker;
import dao.workDao;
import dao.bikeDao;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class WorkerLogin
 */
@WebServlet("/WorkerLogin")
public class WorkerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerLogin() {
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
	    worker worker = new worker();
	    workDao workerdao = new workDao();
	    bikeDao bikedao = new bikeDao();
	    String phone=request.getParameter("phone");
	    String password = request.getParameter("password");
	    System.out.println(phone);
	    worker.setPhone(phone);
	    worker.setPassword(password);
	    System.out.println(phone);
	    int check = workerdao.checkPassword(worker);
	    if(check==1){
	    	List<bike> bike=bikedao.selNotLend();
	    	List<bike> bike1=bikedao.selNeedFix();
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
	    	double [][]fixplace = new double[bike.size()][2];
	    	int j=0;
	    	for(bike b:bike1){
	    		String pla = b.getPlace();
	    		String []xy = pla.split(",");
	    		if(xy[0].toString().equals("undefined")){
	    			continue;
	    		}
	    		double x =Double.valueOf(xy[0].toString());
	    		double y =Double.valueOf(xy[1].toString());
	    		fixplace[j][0]=x;
	    		fixplace[j][1]=y;
	    		j++;
	    	}
	    	session.setAttribute("phone", phone);
	    	session.setAttribute("place", place);
	    	session.setAttribute("fixplace", fixplace);
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
