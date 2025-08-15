package greeting;

public class Message {
    private long id;
    private String message;

    public Message(long id, String content) {
        this.id = id;
        this.message = content;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
