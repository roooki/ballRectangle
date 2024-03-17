package ballRectangle;

import java.awt.*;

public class Main {

    public static final int WINDOW_H = 500, WINDOW_W = 700;
    public static final int LEFT_TOP_ANGLE_X = 10, LEFT_TOP_ANGLE_Y = 10;
    public static final int RIGHT_BOT_ANGLE_X = WINDOW_W - 10, RIGHT_BOT_ANGLE_Y = WINDOW_H - 10;
    public static final int LINE_TOP_X = WINDOW_W/2, LINE_TOP_Y = WINDOW_H/2;
    public static final int LINE_BOT_X = WINDOW_W/2, LINE_BOT_Y = WINDOW_H - 10;
    public static final int REC_TOP_X = 450, REC_TOP_Y = 130;
    public static final int REC_WIDTH = 150, REC_HEIGHT = 200;

    public static final int DIAMETER = 20;
    public static int ballX = 50, ballY = 50;
    public static int ballVellocityX = 4, ballVellocityY = 3;
    public static final int FPS = 1000 / 165;


    public static void main(String[] args) {
        DrawingPanel dp = new DrawingPanel(WINDOW_W, WINDOW_H);
        Graphics2D g = dp.getGraphics();

        for(;true;) {
            update(g);
            dp.sleep(FPS);
        }
    }

    public static void update(Graphics2D g){
        draw(g);

        ballX += ballVellocityX;
        ballY += ballVellocityY;

        checkCollision();
    }

    public static void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, WINDOW_W, WINDOW_H);

        g.setColor(Color.red);
        g.drawRect(LEFT_TOP_ANGLE_X, LEFT_TOP_ANGLE_Y, WINDOW_W - 20, WINDOW_H - 20);
        //g.drawLine(LINE_TOP_X, LINE_TOP_Y, LINE_BOT_X, LINE_BOT_Y);
        g.drawRect(REC_TOP_X, REC_TOP_Y, REC_WIDTH, REC_HEIGHT);

        g.setColor(Color.blue);
        g.fillOval(ballX, ballY, DIAMETER, DIAMETER);
    }

    public static void checkCollision(){
        if(ballX <= LEFT_TOP_ANGLE_X) {
            ballX = LEFT_TOP_ANGLE_X;
            ballVellocityX *= -1;
        }
        if(ballY <= LEFT_TOP_ANGLE_Y) {
            ballY = LEFT_TOP_ANGLE_Y;
            ballVellocityY *= -1;
        }
        if (ballX + DIAMETER >=  RIGHT_BOT_ANGLE_X){
            ballX = RIGHT_BOT_ANGLE_X - DIAMETER;
            ballVellocityX *= -1;
        }
        if (ballY + DIAMETER >=  RIGHT_BOT_ANGLE_Y){
            ballY = RIGHT_BOT_ANGLE_Y - DIAMETER;
            ballVellocityY *= -1;
        }
//        if(ballY > LINE_TOP_Y + DIAMETER)
//            if (ballX <= LINE_TOP_X && ballX + DIAMETER > LINE_TOP_X){
//                ballVellocityX *= -1;
//            }

        //TOP SIDE
        if (ballY >= REC_TOP_Y - DIAMETER && ballY <= REC_TOP_Y)
            if (ballX > REC_TOP_X && ballX < REC_TOP_X + REC_WIDTH){
                ballY = REC_TOP_Y - DIAMETER;
                ballVellocityY *= -1;
            }

        //BOTTOM SIDE
        if (ballY <= REC_TOP_Y + REC_HEIGHT && ballY >= REC_TOP_Y + REC_HEIGHT - DIAMETER)
            if (ballX >= REC_TOP_X - DIAMETER && ballX <= REC_TOP_X + REC_WIDTH){
                ballY = REC_TOP_Y + REC_HEIGHT;
                ballVellocityY *= -1;
            }

        //LEFT SIDE
        if(ballX >= REC_TOP_X - DIAMETER && ballX <= REC_TOP_X)
            if (ballY >= REC_TOP_Y && ballY <= REC_TOP_Y + REC_HEIGHT){
                ballX = REC_TOP_X - DIAMETER;
                ballVellocityX *= -1;
            }

        //RIGHT SIDE
        if (ballX <= REC_TOP_X + REC_WIDTH && ballX >= REC_TOP_X + REC_WIDTH - DIAMETER)
            if (ballY >= REC_TOP_Y && ballY <= REC_TOP_Y + REC_HEIGHT){
                ballX = REC_TOP_X + REC_WIDTH;
                ballVellocityX *= -1;
            }
    }
}
