import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.URL;
import java.net.URLConnection;

public class GestoreUrl {
    String url;
    URL u;
    PrintWriter out;
    BufferedReader in2;

    public GestoreUrl(String url,PrintWriter out,BufferedReader in2) {
        this.url = url;
        this.out = out;
        this.in2 = in2;
    }

    public void dowloand() {
        try {
            u = new URL(url);
            URLConnection conn = u.openConnection();
            conn.setConnectTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                out.println("open;"+inputLine);
                System.out.println(in2.readLine());


            }

        } catch (IOException e) {

        }

        System.out.println();


    }
}
