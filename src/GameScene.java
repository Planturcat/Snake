import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends  Scene {
    public Rect background,foreground;
    KL keylistener = new KL();
    Snake snake ;
    public Food food;
    public GameScene(KL keylistener){
        background=new Rect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        foreground= new Rect(24,48,Constants.TILE_WIDTH*31,Constants.TILE_WIDTH*22);
        snake =new Snake(2,(int)foreground.width/2.0,foreground.y+60,24,24,foreground);
        this.keylistener=keylistener;
    food= new Food(foreground,snake,12,12,Color.BLACK);
    food.spawn();
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
        food.update(dt);
        if(!food.isSpawned)food.spawn();
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
    food.draw(g2);
    }
}
