import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene{
    public KL keyListener;
    public  ML mouseListener;
    public BufferedImage title,play,playPressed,exit,ExitPressed;
    Snake snake;
    public Rect titleRect,playRect,ExitRect;
    public Rect background =new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    public Rect food;
    public  BufferedImage playCurrentImage,ExitCurrentImage;
 public MenuScene(KL keyListener,ML mouseListener) {
     this.keyListener = keyListener;
     this.mouseListener=mouseListener;
     try {
         BufferedImage spriteSheet= ImageIO.read( new File("assets/menuSprite.png"));
         title =spriteSheet.getSubimage(0,242,960,240);
         play=spriteSheet.getSubimage(0,121,261,121);
         playPressed=spriteSheet.getSubimage(264,121,261,121);
                 exit=spriteSheet.getSubimage(0,0,233,93);
         ExitPressed=spriteSheet.getSubimage(264,0,233,93);

     } catch (Exception e) {
         System.out.println(" ");
     }
     playCurrentImage=play;
     ExitCurrentImage=exit;
     snake = new Snake(20,50,240,40,30,background);
     //snakeRect = new Rect()
     food = new Rect(40,240,40,40);
     titleRect=new Rect(230, 100, 320, 100);
     playRect = new Rect(310, 280, 150, 70);
     ExitRect = new Rect(318, 355, 130, 55);
 }
    @Override
    public void update(double dt) {
        if (mouseListener.getMouseX() >= playRect.x &&
                mouseListener.getMouseX() <= playRect.width + playRect.x &&
                mouseListener.getMouseY() >= playRect.y  &&
                mouseListener.getMouseY() <= playRect.height / 2 + playRect.y) {
playCurrentImage=playPressed;

            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        } else {
            playCurrentImage=play;
        }
        if (mouseListener.getMouseX() > ExitRect.x &&
                mouseListener.getMouseX() < ExitRect.width + ExitRect.x &&
                mouseListener.getMouseY() > ExitRect.y &&
                mouseListener.getMouseY() < ExitRect.height  + ExitRect.y) {
       ExitCurrentImage=ExitPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().stop();
            }
        }else {
            ExitCurrentImage=exit;
        }

    }

    @Override
    public void draw(Graphics g) {
    g.setColor(Constants.SCREEN_COLOR_MAIN);
    g.fillRect(0,0 ,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    g.drawImage(title,(int)titleRect.x,(int)titleRect.y,(int)titleRect.width,(int)titleRect.height,null);
    snake.draw((Graphics2D)g);
    g.drawImage(playCurrentImage,(int)playRect.x,(int)playRect.y,(int)playRect.width,(int)playRect.height,null);
    g.drawImage(ExitCurrentImage,(int)ExitRect.x,(int)ExitRect.y,(int)ExitRect.width,(int)ExitRect.height,null);
    }
}
