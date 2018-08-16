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

import dao.bikeDao;
import jp.sourceforge.qrcode.QRCodeDecoder;
import qrcode.MyQRCodeImage;

/**
 * Servlet implementation class ReadQRCode
 */
@WebServlet("/ReadQRCode")
public class ReadQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadQRCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0); 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		bikeDao bikedao = new bikeDao();
		//int a[][] = {{1,2},{3,4},{5,6}};
        //图片路径
		
        File file = new File("C:/Users/Administrator/Desktop/bikeCode/"+request.getParameter("filename"));
        //读取图片到缓冲区
        BufferedImage bufferedImage = ImageIO.read(file);
        //QRCode解码器
        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        /**
         *codeDecoder.decode(new MyQRCodeImage())
         *这里需要实现QRCodeImage接口，移步最后一段代码
         */
        //通过解析二维码获得信息
        String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
        int bno = Integer.parseInt(result);
        bikedao.updateFixFinish(bno);
        PrintWriter out= response.getWriter();
        out.print("自行车"+bno+"修理成功!");
       // System.out.println(result);
        //System.out.println(a.length);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
