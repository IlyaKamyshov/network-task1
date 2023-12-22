import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public final static Integer SRVPORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SRVPORT)) {
            System.out.println("Server started");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    String name = in.readLine();
                    System.out.printf("New connection accepted. Client: %s, port:%d%n",
                            name, clientSocket.getPort());

                    out.println(String.format("Hi \"%s\", your port is %d%n", name, clientSocket.getPort()));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
