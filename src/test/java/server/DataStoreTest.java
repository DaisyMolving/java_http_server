package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStoreTest {

    private DataStore dataStore = new DataStore();

    @Test
    public void dataStoreDataCanBeRead() {
        assertEquals("", dataStore.read());
    }

    @Test
    public void dataStoreDataCanBeUpdated() {
        dataStore.update("data=hello");
        assertEquals("data=hello", dataStore.read());
    }

    @Test
    public void dataStoreDataCanBeDeleted() {
        dataStore.update("data=hello");
        assertEquals("data=hello", dataStore.read());
        dataStore.delete();
        assertEquals("", dataStore.read());
    }
}
