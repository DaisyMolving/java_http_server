package server;

import java.util.ArrayList;
import java.util.List;

public interface Response {
    public static final String protocolVersion = new String();
    public static final List<String> responseContent = new ArrayList<String>();

    public List<String> generateContent();

}
