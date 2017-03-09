package server;

public class DataStore {

    private String data;

    public DataStore() {
        this.data = "";
    }

    public String read() {
        return data;
    }

    public void update(String newData) {
        data = newData;
    }

    public void delete() {
        data = "";
    }
}
