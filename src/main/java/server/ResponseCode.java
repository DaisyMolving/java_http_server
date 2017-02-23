package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
    public class ResponseCode  {

        public Request currentRequest;
        public List<String> responseContent = new ArrayList<String>();

        public ResponseCode(Request currentRequest) {
            this.currentRequest = currentRequest;
        }
}

