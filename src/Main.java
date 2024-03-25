public class Main {
    public static void main(String[] args) {
      Window window = new Window(600,800,"Snake");
      Thread main = new Thread(window);
      main.start();
    }
}