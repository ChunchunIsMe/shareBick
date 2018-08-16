package servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.swetake.util.Qrcode;




import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.bike;
import dao.bikeDao;
import com.swetake.util.Qrcode;

/**
 * Servlet implementation class CreateCode
 */
@WebServlet("/CreateCode")
public class CreateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ά��ͼƬ�ĸ߿��
        // API�ĵ��涨����ͼƬ��ߵķ�ʽ ��v�Ǳ��β��Եİ汾��
		response.setContentType("text/html");  
		response.setHeader("Cache-Control", "no-store");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0); 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		bikeDao bikedao = new bikeDao();
		bike bike = new bike();
		bikedao.create(bike);
        int v =6;
        int width = 67 + 12 * (v - 1);
        int height = 67 + 12 * (v - 1);


        Qrcode x = new Qrcode();

        /**
         * ����ȼ���Ϊ
         * level L : ��� 7% �Ĵ����ܹ���������
         * level M : ��� 15% �Ĵ����ܹ���������
         * level Q : ��� 25% �Ĵ����ܹ���������
         * level H : ��� 30% �Ĵ����ܹ���������
         */
        x.setQrcodeErrorCorrect('L');
        x.setQrcodeEncodeMode('B');//ע��汾��Ϣ N�������� ��A���� a-z,A-Z��B���� ����)
        x.setQrcodeVersion(v);//�汾��  1-40

        String qrData = Integer.toString(bike.getBno());//������Ϣ

        byte[] d = qrData.getBytes("utf-8");//����ת��ʽ��Ҫ�׳��쳣

        //������
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //��ͼ
        Graphics2D gs = bufferedImage.createGraphics();

        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);
        //ƫ����
        int pixoff = 2;


        /**
         * ���ײȿӵĵط�
         * 1.ע��forѭ�������i��j��˳��
         *   s[j][i]��ά�����j��i��˳��Ҫ����������е� gs.fillRect(j*3+pixoff,i*3+pixoff, 3, 3);
         *   ˳��ƥ�䣬�������ֽ���ͼƬ��һ������
         * 2.ע����ж�if (d.length > 0 && d.length < 120)
         *   �Ƿ�������ַ������ȴ���120�������ɴ��벻ִ�У���ά��հ�
         *   �����Լ����ַ�����С�����ô�����
         */
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
        //����ͼƬ��ʽ���������·�� 
        ImageIO.write(bufferedImage, "png", new File("C:/Users/Administrator/Desktop/bikeCode/"+bike.getBno()+".png"));
        PrintWriter out= response.getWriter();
        out.print("���г�"+bike.getBno()+"��ά�����ɳɹ�");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
