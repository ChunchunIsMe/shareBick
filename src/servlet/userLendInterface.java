package servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.userBean;
import dao.bikeDao;
import dao.userDao;
import jp.sourceforge.qrcode.QRCodeDecoder;
import qrcode.MyQRCodeImage;

/**
 * Servlet implementation class userLendInterface
 */
@WebServlet("/userLendInterface")
public class userLendInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userLendInterface() {
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
		bikeDao bikedao = new bikeDao();
		userDao userdao = new userDao();
		userBean user = new userBean();
		//int a[][] = {{1,2},{3,4},{5,6}};
        //ͼƬ·��
	    System.out.println("aa");
        File file = new File("C:/Users/Administrator/Desktop/bikeCode/"+request.getParameter("filename"));
      //��ȡͼƬ��������
        BufferedImage bufferedImage = ImageIO.read(file);
        //QRCode������
        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        /**
         *codeDecoder.decode(new MyQRCodeImage())
         *������Ҫʵ��QRCodeImage�ӿڣ��Ʋ����һ�δ���
         */
        //ͨ��������ά������Ϣ
        String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
        String phone = request.getParameter("phone");
        int bno = Integer.parseInt(result);
        session.setAttribute("bno", bno);
        bikedao.updateBikeLend(bno);
        user.setPhone(phone);
        user.setBno(bno);
        userdao.updateBikeBuff(user);
        PrintWriter out= response.getWriter();
        out.print("�⳵�ɹ�!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
