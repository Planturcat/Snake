import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends  Scene {
    Rect background,foreground;
    KL keylistener = new KL();
    Snake snake ;
    public GameScene(KL keylistener){
        background=new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        foreground= new Rect(24,48,24*31,24*22);
        snake =new Snake(2,48,48+24,24,24);
        this.keylistener=keylistener;

    }
    @Override
    public void update(double dt) {
        if(keylistener.isKeyPressed(KeyEvent.VK_UP)){
            snake.changeDirection(Direction.UP);
        } else if (keylistener.isKeyPressed(KeyEvent.VK_DOWN)) {
            snake.changeDirection(Direction.DOWN);
        } else if (keylistener.isKeyPressed(KeyEvent.VK_LEFT)) {
            snake.changeDirection(Direction.LEFT);
        }else if (keylistener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            snake.changeDirection(Direction.RIGHT);
        }
        snake.update(dt);

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 =(Graphics2D)g;
    g2.setColor(Constants.SCREEN_COLOR_BACKGROUND);
    g2.fill(new Rectangle2D.Double(background.x,background.y, background.width,background.height));
    g2.setColor(Constants.SCREEN_COLOR_FOREGROUND);
    g2.fill(new Rectangle2D.Double(foreground.x,foreground.y, foreground.width,foreground.height));
    snake.draw(g2);
    }
}
