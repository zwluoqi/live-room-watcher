package cool.scx.live_room_watcher;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import cool.scx.live_room_watcher.messageServer.LiveData;

import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ApplicationWeb {

    private HttpServer server;

    public ApplicationWeb() throws Exception {
        server = HttpServer.create(new InetSocketAddress(8600), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
    }

    public void start() {
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
//            String response = "Hello, world!";
            
            String Authorization = t.getRequestHeaders().getFirst("Authorization");
            String LiveId = t.getRequestHeaders().getFirst("LiveId");
            var popMessage = LiveData.popMessages(LiveId,Authorization);

            byte[] responseBytes = popMessage.getBytes(StandardCharsets.UTF_8);
            t.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = t.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
    }

}
