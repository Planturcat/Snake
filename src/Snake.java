import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    public Rect[] body= new Rect[1000];
    public double BodyWidth, BodyHeight;
    public int size;
    public int tail=0;
    public int head=0;
    public double originalWaitBetweenUpdate=0.1f;
    public double waitTimeLeft = originalWaitBetweenUpdate;
public Direction direction;
public Rect bg;

    public Snake(int size,double startx,double starty,double bodyWidth,double bodyHeight,Rect bg){
        this.size=size;
        this.BodyWidth =bodyWidth;
        this.BodyHeight =bodyHeight;
    this.bg=bg;
        for (int i = 0; i < size; i++) {
            Rect bodyPiece= new Rect(startx+i*bodyWidth,starty,bodyWidth,bodyHeight);
            body[i]= bodyPiece;
            head++;
        }
        head--;
        if (intersectWithBOUNDS(body[head])) {
            System.out.println("Initial wall collision!");
            this.size--;
            head--;
            // Handle the collision (e.g., reduce size, change position)
        }
    }
public  void changeDirection(Direction newdirection){

    if(newdirection== Direction.RIGHT && direction!= Direction.LEFT)
direction= newdirection;
    else if (newdirection== Direction.LEFT && direction!= Direction.RIGHT) {
        direction= newdirection;
    } else if (newdirection== Direction.UP && direction!= Direction.DOWN) {
        direction= newdirection;
    } else if (newdirection== Direction.DOWN && direction!= Direction.UP) {
        direction= newdirection;
    }
    if (intersectsWithself(body[head])){
        Window.getWindow().changeState(0);
    }
}

    public  void update (double dt){
                if(waitTimeLeft>0){
                    waitTimeLeft-=dt;
                    return;
                }

                waitTimeLeft=originalWaitBetweenUpdate;
    double newx=0;
    double newy=0;
    if (direction==Direction.LEFT) {
        newx = body[head].x - BodyWidth;
        newy = body[head].y;
    }
    else if(direction==Direction.RIGHT){
        newx=body[head].x+BodyWidth;
        newy=body[head].y;

    }
    else if (direction==Direction.UP) {
        newx=body[head].x;
        newy=body[head].y-BodyHeight;

    } else if (direction==Direction.DOWN) {
        newx=body[head].x;
        newy=body[head].y+BodyHeight;
    }
        body[(head+1)%body.length]=body[tail];
    body[tail]=null;
    head=(head+1)%body.length;
    tail=(tail+1)%body.length;

    body[head].x=newx;
    body[head].y=newy;

    }

    public void grow() {
        System.out.println("we are growing");
        double newx = 0;
        double newy = 0;
        if (direction == Direction.RIGHT) {
            newx = body[tail].x - BodyWidth;
            newy = body[tail].y;

        } else if (direction == Direction.LEFT) {
            newx = body[tail].x + BodyWidth;
            newy = body[tail].y;
        } else if (direction == Direction.UP) {
            newx = body[tail].x;
            newy = body[tail].y + BodyHeight;
        } else if (direction == Direction.DOWN) {
            newx = body[tail].x;
            newy = body[tail].y - BodyHeight;
        }

        Rect newBodyPiece = new Rect(newx, newy, BodyWidth, BodyHeight);
    if(!intersectsWithself(body[head])){
        tail = (tail - 1) % body.length;
        body[tail] = newBodyPiece;}
    else System.out.println("hit myself");
    }
    public boolean intersectsWithself(Rect headR){



        return intersectsrect(headR)|| intersectWithBOUNDS(headR);

    }
    public boolean intersectsrect(Rect rect) {

        for (int i = tail; i != head; i = (i + 1) % body.length) {
            if(intersect(rect,body[i]))return true;
        }
        return false;
    }
public  boolean intersectWithBOUNDS(Rect head){
return( head.x<bg.x||(head.x+head.width)>=bg.x+bg.width||
        head.y<bg.y||(head.y+head.height)>=bg.y+bg.height);
}
public  boolean intersect(Rect a, Rect b){
    return (a.x < b.x + b.width &&
            a.x + a.width > b.x &&
            a.y < b.y + b.height &&
            a.y + a.height > b.y);
}
    public void draw(Graphics2D g2){
        for (int i = tail; i != head; i=(i+1)%body.length) {
            Rect piece = body[i];
            double subwidth=(piece.width-6.0)/2.0;
            double subheight=(piece.height-6.0)/2.0;
            g2.setColor(Constants.SNAKE_COLOR);
            g2.fill(new Rectangle2D.Double(piece.x+2.0,piece.y+2.0,subwidth,subheight));
           g2.fill(new Rectangle2D.Double(piece.x+4.0+subwidth,piece.y+2.0,subwidth,subheight));
            g2.fill(new Rectangle2D.Double(piece.x+2.0,piece.y+4.0+subheight,subwidth,subheight));
            g2.fill(new Rectangle2D.Double(piece.x+4.0+subwidth,piece.y+4.0+subheight,subwidth,subheight));

        }
    }
}
