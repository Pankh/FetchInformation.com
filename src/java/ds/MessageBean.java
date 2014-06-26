package ds;

import java.io.Serializable;

public class MessageBean implements Serializable{

    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
