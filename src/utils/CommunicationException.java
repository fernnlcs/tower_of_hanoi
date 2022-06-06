package utils;

public class CommunicationException extends RuntimeException {
    
    private String message = "";

    public CommunicationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
