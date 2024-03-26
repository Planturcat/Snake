public class Main {
    public static void main(String[] args) {
      Window window = Window.getWindow();
      Thread main = new Thread(window);
      main.start();
    }
}