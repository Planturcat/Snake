public class Snake {
    public Rect[] body= new Rect[100];
    public double BodyWidth, BodyHeight;
    public int size;
    public int tail=0;
    public int head=0;

    public Snake(int size,double startx,double starty,double bodyWidth,double bodyHeight){
        this.size=size;
        this.BodyWidth =bodyWidth;
        this.BodyHeight =bodyHeight;

        for (int i = 0; i < size; i++) {
            Rect bodyPiece= new Rect(bodyWidth,bodyHeight,startx+i*bodyWidth,starty);
            body[1]= bodyPiece;
            head--;
        }
    }
}
