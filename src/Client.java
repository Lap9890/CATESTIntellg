import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8070);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected");

            System.out.println("Welcome! Please note that EVERY line of command will have the following structure :  ");
            System.out.println("command;Ticket Number;Student Name;category;note");
            System.out.println("The avaiable category are: network/software/account/other");
            System.out.println("The avaiable command are: open/list/close/import");
            System.out.println("open will require : Ticket Number;Student Name;category;note ");
            System.out.println("list will require : Student Name");
            System.out.println("close will require : Ticket number");
            System.out.println("when an argument in not required kindly insert -");
            System.out.println("Example: list;-;Alice;-;-");
            System.out.println("Enjoy");






            while (true) {
                System.out.println("Please insert: command;Ticket Number;Student Name;category;note or stop to exit  ");
                String message = scanner.nextLine();
                if (message.trim().toLowerCase().startsWith("import")) {
                    String[] url = message.split(";", 2);
                    if (url.length < 2) {
                        System.out.println("Url is missing");
                        continue;
                    }
                    String address = url[1].trim();
                    if (address.isEmpty()) {
                        System.out.println("Url is missing");
                        continue;
                    }
                    GestoreUrl geo = new GestoreUrl(address, out, in);
                    geo.dowloand();
                    continue;
                }
                out.println(message);

                System.out.println(in.readLine());
                if (message.trim().equalsIgnoreCase("Stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while connecting to the server: " + e.getMessage());
        }
    }
    }



