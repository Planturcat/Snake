public class Main {
    public static void main(String[] args) {
      Window window = new Window(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT,Constants.TITLE);
      Thread main = new Thread(window);
      main.start();
    }
}