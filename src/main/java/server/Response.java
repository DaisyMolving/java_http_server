package server;

import java.io.IOException;

public interface Response {

    public byte[] generateContent() throws IOException;

}
