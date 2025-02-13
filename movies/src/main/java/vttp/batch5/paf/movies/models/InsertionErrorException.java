package vttp.batch5.paf.movies.models;

import java.util.List;

public class InsertionErrorException extends RuntimeException{

    private List<String> ids;

    public InsertionErrorException(){

    }

    public InsertionErrorException(String message, List<String> ids){
        super(message);
        this.ids = ids;
    }

    public InsertionErrorException(String message, Throwable throwable){
        super(message, throwable);
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
    
}
