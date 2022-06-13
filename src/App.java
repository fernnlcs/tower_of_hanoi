import game.Level;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Torre de Hanói\n");

        // Configurando nível
        Level level1 = Level.read("C:\\Users\\fernn\\Desktop\\Level 1.txt");
        
        // Jogar!
        level1.play();
    }
}
