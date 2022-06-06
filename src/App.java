import game.Level;
import game.Tower;
import game.TowerGroup;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Torre de Hanói\n");

        // Configurando Torres
        String[] names = {"A", "B", "C"};
        TowerGroup group = new TowerGroup(names);
    
        // Origem
        Tower origin = new Tower("A");
        origin.add(3);
        origin.add(2);
        origin.add(1);

        // Configurando nível
        Level level1 = new Level(group, origin, "C");
        
        // Jogar!
        level1.play();
    }
}
