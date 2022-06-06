import game.Level;
import game.Tower;
import game.TowerGroup;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Torre de Hanói\n");

        String[] names = {"A", "B", "C"};
    
        Tower origin = new Tower("A");
        origin.add(3);
        origin.add(2);
        origin.add(1);

        TowerGroup group = new TowerGroup(names);

        Level level1 = new Level(group, origin, "C");
        
        level1.play();
    }
}
