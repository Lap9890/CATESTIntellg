import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
 public static void main(String[] args) {
     try {
         Socket s = new Socket("127.0.0.1",8070);
         BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
         PrintWriter out = new PrintWriter(s.getOutputStream(),true);
         Scanner scanner = new Scanner(System.in);
         System.out.println("Connected");

        while (true){
            System.out.println("Inserisci qui riga comando;Nome Studente;ticket number;category;note");
            String message = scanner.nextLine();

            out.println(message);

            System.out.println(in.readLine());
            if(message.trim().equalsIgnoreCase("Stop")){
                break;
            }
        }
     } catch (IOException e) {
         System.out.println("Error while connecting to the server: " + e.getMessage());
     }


    }
}
