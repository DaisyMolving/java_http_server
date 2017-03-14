package server;

public class RequestLogStore {

    private String logs;

    public RequestLogStore() {
        this.logs = "";
    }

    public String read() {
        return logs;
    }

    public void add(String newLogEntry) {
        logs = logs.concat(newLogEntry);
    }

}
