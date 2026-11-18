import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class URLManager {
    private final String url;
    private final PrintWriter out;
    private final BufferedReader serverIn;

    public URLManager(String url, PrintWriter out, BufferedReader serverIn) {
        this.url = url;
        this.out = out;
        this.serverIn = serverIn;
    }

    public void download() {
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setConnectTimeout(5000);

            try (BufferedReader fileIn = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String student = null;

                String line;

                while ((line = fileIn.readLine()) != null) {
                    if (line.toLowerCase().startsWith("student=")) {
                        student = line.substring(line.indexOf('=') + 1).trim();
                        continue;
                    }






                    String[] p = line.split(";", 3);


                    String command = "open;" + p[0].trim() + ";" + student + ";" + p[1].trim() + ";" + p[2].trim();
                    System.out.println(command);
                    out.println(command);
                    System.out.println(serverIn.readLine());
                }
            }
        } catch (IOException e) {
            System.out.println("Import error: " + e.getMessage());
        }
        System.out.println();
    }
}