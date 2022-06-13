package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import structures.DoubleLinkedList;

public class Ranking {

    private File file;
    private DoubleLinkedList<Score> scoreList = new DoubleLinkedList<Score>();

    public Ranking(File file) throws FileNotFoundException {
        this.file = file;
        this.importFromFile(file);
    }

    public Ranking(String filePath) throws IOException {
        File file = new File(filePath);
        this.file = file;

        try {
            this.importFromFile(file);
        } catch (FileNotFoundException e) {
            file.createNewFile();
            this.importFromFile(file);
        }
    }

    public void importFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineItems = line.split(" -> ");
            
            Double number = Double.parseDouble(lineItems[0]);
            String player = lineItems[1];

            Score score = new Score(player, number);
            scoreList.addLast(score);
        }
        
        scanner.close();
    }

    public void addScore(Score score) {
        scoreList.addLast(score);
    }

    public void show() {
        System.out.println(this.toString());
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(file);

        List<Score> list = this.scoreList.toArray();

        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i).toString() + "\n");
        }
        
        writer.close();
    }

    @Override
    public String toString() {
        String result = "";
        List<Score> list = scoreList.toArray();

        for (int i = 0; i < list.size(); i++) {
            result += list.get(i).toString() + "\n";
        }

        return result;
    }
}
