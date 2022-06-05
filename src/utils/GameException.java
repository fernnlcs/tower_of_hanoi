package utils;

public class GameException extends RuntimeException {
    
    private String message = "";

    public GameException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
