import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene{
    public KL keyListener;
    public  ML mouseListener;
    public BufferedImage title,play,playPressed,exit,ExitPressed;
    public Rect titleRect,playRect,ExitRect;
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
         System.out.println("");
     }
     playCurrentImage=play;
     ExitCurrentImage=exit;
     titleRect=new Rect((double) Constants.SCREEN_WIDTH/2,180,300,100);
     playRect=new Rect(50,310,100,70);
     ExitRect=new Rect(50,400,90,50);
 }
    @Override
    public void update(double dt) {
        if (mouseListener.getMouseX() > playRect.x &&
                mouseListener.getMouseX() < playRect.width + playRect.x &&
                mouseListener.getMouseY() > playRect.y - playRect.height / 2 &&
                mouseListener.getMouseY() < playRect.height / 2 + playRect.y) {
playCurrentImage=playPressed;

            if (mouseListener.isPressed()) {
                Window.changeState(1);
            }
        } else {
            playCurrentImage=play;
        }
        if (mouseListener.getMouseX() > ExitRect.x &&
                mouseListener.getMouseX() < ExitRect.width + ExitRect.x &&
                mouseListener.getMouseY() > ExitRect.y - ExitRect.height / 2 &&
                mouseListener.getMouseY() < ExitRect.height / 2 + ExitRect.y) {
       ExitCurrentImage=ExitPressed;
            if (mouseListener.isPressed()) {
                Window.changeState(1);
            }
        }else {
            ExitCurrentImage=exit;
        }
    }
    public void stop(){
    }
    @Override
    public void draw(Graphics g) {
    g.setColor(Constants.SCREEN_COLOR_MAIN);
    g.fillRect(0,0 ,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    g.drawImage(title,(int)titleRect.x,(int)titleRect.y,(int)titleRect.width,(int)titleRect.height,null);
    g.drawImage(playCurrentImage,(int)playRect.x,(int)playRect.y,(int)playRect.width,(int)playRect.height,null);
    g.drawImage(ExitCurrentImage,(int)ExitRect.x,(int)ExitRect.y,(int)ExitRect.width,(int)ExitRect.height,null);
    }
}
