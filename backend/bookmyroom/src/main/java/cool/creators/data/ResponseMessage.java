package cool.creators.data;

/**
 * Created by kalyandechiraju on 22/05/16.
 */
public class ResponseMessage {
    private String message;

    private ResponseMessage() {}

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
