package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Response {

    public byte[] generateContent() throws IOException;

}
