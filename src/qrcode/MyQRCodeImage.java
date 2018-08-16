package qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * 实现QRCodeImage接口，
 * 设置解码的图片信息
 * 告诉codeDecoder.decode()将要解析的图片类型
 * Created by BigRoc on 2017/9/9.
 */
public class MyQRCodeImage implements QRCodeImage{


    BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
    }

    //宽
    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    //高
    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    //像素还是颜色
    @Override
    public int getPixel(int i, int j) {
        return bufferedImage.getRGB(i,j);
    }
}
