package translator;

public class Translator{
    private final long id;
    private final String message;

    public Translator(long id, String content) {
        this.id = id;
        this.message = content;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
