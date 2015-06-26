package cm.commons;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeImageBean {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 25;
    String checkCode = "";
    private BufferedImage image;

    public VerifyCodeImageBean() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        //设置背景
        setBackGround(g);
        //设置边框
        setBorder(g);
        //画干扰线
        drawRandomLine(g);
        //写随机数
        drawRandowNum((Graphics2D) g);
    }

    public BufferedImage getCreateVerifyImage() {
        return image;
    }

    private void drawRandowNum(Graphics2D g) {
        // TODO Auto-generated method stub
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 40));
        //中文区间[\u4e00-\u9fa5]  unicode
        String base = "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsaxcvbnmz1234567890";
        int x = 5;
        for (int i = 0; i < 4; i++) {
            int degree = new Random().nextInt(30) % 30;
            String ch = base.charAt(new Random().nextInt(base.length())) + "";
            checkCode += ch;
            g.rotate(degree * Math.PI / 180, x, 20);//设置旋转角度
            g.drawString(ch, x, 20);
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
    }

    public String getVarifyCode() {
        return checkCode;
    }

    private void drawRandomLine(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.GREEN);

        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);

            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }


    private void setBorder(Graphics g) {
        // TODO Auto-generated method stub

        g.setColor(Color.BLUE);
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }


    private void setBackGround(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
