import game.Level;
import game.Ranking;
import game.Score;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Torre de Hanói\n");

        // Carregando Ranking
        Ranking ranking = new Ranking("C:\\Users\\fernn\\Desktop\\Ranking.txt");

        // Configurando nível
        Level level1 = Level.read("C:\\Users\\fernn\\Desktop\\Level 1.txt");
        
        // Jogar!
        Score score = level1.play("Fernando");

        ranking.addScore(score);
        ranking.save();
        ranking.show();
    }
}
