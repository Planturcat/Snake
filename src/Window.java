import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class Window extends JFrame implements Runnable {
    public static  Window window = null;
    public  boolean isRunning ;
    public  int currentState;
    public  Scene currentScene;
    public  KL keyListener= new KL();
    public  ML mouseListener = new ML();
    public Window(int width ,int height, String title){
        setSize(width,height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        isRunning=true;
        changeState(0);
    }
    public static Window getWindow(){
        if (Window.window== null){
            Window.window= new Window(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,Constants.TITLE);
        }
        return Window.window;
    }
    public  void changeState(int newState){
        currentState= newState;
        switch (currentState){
            case 0:
                currentScene= new MenuScene(keyListener,mouseListener );
                break;
            case 1:
                currentScene=new GameScene();

                break;
             default :
                System.out.println(" Unknown scene");
                currentScene=null;
                break;
        }
    }
    public void update(double dt){
        Image dbimage= createImage(getWidth(),getHeight());
        Graphics dbg = dbimage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbimage,0,0,this);

        currentScene.update(dt);
    }
    public void draw(Graphics g){
    Graphics2D g2 =(Graphics2D) g;
    currentScene.draw(g);
    }
    public void stop(){
        isRunning = false;
    }
@Override
    public void run(){
        double lastFrameTime =0.0;
       try{ while(isRunning){
    double time =Time.getTime();
    double deltaTime = time-lastFrameTime;
    lastFrameTime=time;
    update(deltaTime);
        }}catch (Exception e){
           e.printStackTrace();
       }
       this.dispose();
    }

}
