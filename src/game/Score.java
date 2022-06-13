package game;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Score {
    private String player;
    private double number;

    public Score(String player, double score) {
        this.player = player;
        this.setNumber(score);
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        BigDecimal value = new BigDecimal(number);
        value.setScale(2, RoundingMode.HALF_EVEN);
        this.number = value.doubleValue();
    }

    @Override
    public String toString() {
        return this.number + " -> " + this.player;
    }
}
