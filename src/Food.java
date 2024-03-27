import java.awt.*;
class Food {
    public Rect bg;
    public Snake snake;
    public int width;
    public int height;
    public   Color color;

    public Rect rect;
    public  int xPadding;
    public  boolean isSpawned= false;
    public Food(Rect bg, Snake snake,int width,int height,Color color){
    this.bg=bg;
    this.color=color;
    this.snake=snake;
    this.width=width;
    this.height=height;
    this.rect=new Rect(0,0,width,height);
    xPadding=(int)(Constants.TILE_WIDTH-this.width/2.0);

    }
    public void spawn(){
    do{
double randX=(int)(Math.random()*(int)(bg.width/Constants.TILE_WIDTH))*Constants.TILE_WIDTH+bg.x;
double randY=(int)(Math.random()*(int)(bg.height/Constants.TILE_WIDTH))*Constants.TILE_WIDTH+bg.y;
    this.rect.x=randX;
    this.rect.y=randY;
    }while (snake.intersectsrect(this.rect));
    this.isSpawned=true;
    }
   public void update(double dt){
if(snake.intersectsrect(this.rect)){
    snake.grow();
    this.rect.x=-100;
    this.rect.y=-100;
    isSpawned=false;
}
   }
    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.fillRect((int)this.rect.x+xPadding,(int)this.rect.y+xPadding,width,height);
    }
}
