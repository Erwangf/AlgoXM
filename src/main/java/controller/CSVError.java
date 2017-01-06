package controller;

/**
 * Created by Erwan on 06/01/2017.
 */
public class CSVError {
    private String message;
    private String line;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public CSVError(String message, String line) {
        this.message = message;
        this.line = line;
    }
}
