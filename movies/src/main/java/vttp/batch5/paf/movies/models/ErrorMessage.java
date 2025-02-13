package vttp.batch5.paf.movies.models;

import java.util.Date;
import java.util.List;

public class ErrorMessage {
    private List<String> ids;
    private String message;
    private Date timestamp;

  

    public ErrorMessage(List<String> ids, String message, Date timestamp) {
        this.ids = ids;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}