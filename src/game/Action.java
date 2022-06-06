package game;

public class Action {
    
    private TowerGroup towers;
    private String from;
    private String to;

    public Action(TowerGroup towers, String from, String to) {
        this.towers = towers;
        this.from = from;
        this.to = to;
    }

    public boolean perform() {
        try {
            towers.move(from, to);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean undo() {
        try {
            towers.move(to, from);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
