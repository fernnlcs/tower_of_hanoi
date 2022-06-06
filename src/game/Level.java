package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.naming.CommunicationException;

import structures.Stack;
import utils.GameException;

public class Level {

    private TowerGroup towers;
    private Tower origin;
    private String destiny;
    private Stack<Action> history = new Stack<Action>();
    private Stack<Action> undone = new Stack<Action>();
    private int moves = 0;
    private int undoneMoves = 0;

    public Level(TowerGroup towers, Tower origin, String destiny) {
        this.towers = towers;
        this.origin = origin;
        this.destiny = destiny;

        try {
            towers.searchByName(destiny);
            towers.replace(origin.name, origin.clone());
        } catch (GameException e) {
            throw e;
        }
    }

    public boolean checkAnswer() {
        Tower destinyTower = towers.searchByName(this.destiny);
        return destinyTower.matches(this.origin);
    }

    public void show() {
        this.towers.show();
    }

    public void move(String command) throws GameException, CommunicationException {
        command = command.toUpperCase();
        String[] parts = command.split(" ");

        if (parts.length < 2) {
            throw new CommunicationException("Não entendi o que você digitou.");
        }

        String from = parts[0];
        String to = parts[1];

        // towers.move(from, to);
        this.perform(from, to);
    }

    public void perform(String from, String to) {
        Action action = new Action(this.towers, from, to);

        if (action.perform()) {
            this.history.push(action);
            this.moves += 1;
        } else {
            throw new GameException("Não foi possível completar a jogada.");
        }
    }

    public void undo() {
        if (this.history.isEmpty()) {
            throw new GameException("Não há nada para desfazer.");
        }

        Action action = this.history.pop();

        if (action.undo()) {
            this.undone.push(action);
            this.moves -= 1;
            this.undoneMoves += 1;
        } else {
            this.history.push(action);
            throw new GameException("Não foi possível desfazer.");
        }
    }

    public void redo() {
        if (this.undone.isEmpty()) {
            throw new GameException("Não há nada para refazer.");
        }

        Action action = this.undone.pop();

        if (action.perform()) {
            this.history.push(action);
            this.moves += 1;
        } else {
            this.undone.push(action);
            throw new GameException("Não foi possível refazer.");
        }
    }

    public void play() {
        System.out.println("Começamos assim:");
        this.towers.show();

        Scanner entry = new Scanner(System.in);

        while (!this.checkAnswer()) {
            System.out.print("\n" + this.moves + " movimentos até agora");
            if (this.undoneMoves > 0) {
                System.out.print(" (desfez " + this.undoneMoves + "x)");
            }
            System.out.println();
            System.out.println("Qual seu próximo movimento?");
            System.out.print("\tOutras opções: ");
            System.out.print("[z] Desfazer | ");
            System.out.print("[y] Refazer | ");
            System.out.println("[q] Desistir");

            try {
                String command = entry.nextLine();

                switch (command) {
                    case "q":
                        entry.close();
                        return;

                    case "z":
                        this.undo();
                        break;

                    case "y":
                        this.redo();
                        break;

                    default:
                        this.move(command);
                        break;
                }

                System.out.println("\nOk, estamos assim:");
                this.towers.show();
            } catch (GameException e) {
                System.out.print("\nJogada inválida: ");
                System.out.println(e.getMessage() + "\n");
                this.towers.show();
                System.out.println();
            } catch (CommunicationException e) {
                System.out.print("\nComando inválido: ");
                System.out.println(e.getMessage() + "\n");
            } catch (NoSuchElementException e) {

            }
        }

        entry.close();

        if (this.checkAnswer()) {
            System.out.println("------------------------");
            System.out.println("- É isso! Você venceu! -");
            System.out.println("------------------------");
        }

    }

}
