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

            while (true) {
                System.out.println("Please insert the command: Choose between open/close/list/import or stop to terminate");
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("import")) {
                    System.out.println("Please insert the Url");
                    String url = scanner.nextLine();
                    System.out.println(url);
                    GestoreUrl geo = new GestoreUrl(url,out,in);
                    geo.dowloand();
                    continue;


                }
                String studentName = "-",ticketNumber = "-",category = "-",note = "-";
                switch (command.toLowerCase().trim()){
                    case("open"):{
                        System.out.println("Please insert the Student name");
                        studentName = scanner.nextLine();
                        System.out.println("Please insert ticket number");
                        ticketNumber = scanner.nextLine();
                        System.out.println("Please insert category (choose between network/account/software/other)");
                        category = scanner.nextLine();
                        System.out.println("Please add a note for the ticket ");
                        note = scanner.nextLine();
                        break;
                    }
                    case("close"):{
                        System.out.println("Please insert ticket number");
                        ticketNumber = scanner.nextLine();
                        break;
                    }
                    case("list"):{
                        System.out.println("Please insert the Student name");
                        studentName = scanner.nextLine();
                        break;
                    }


                }

                StringBuffer sb = new StringBuffer();
                sb.append(command);
                sb.append(";");
                sb.append(ticketNumber);
                sb.append(";");
                sb.append(studentName);
                sb.append(";");
                sb.append(category);
                sb.append(";");
                sb.append(note);

             String   message = sb.toString();

                if (command.trim().equalsIgnoreCase("Stop")) {
                    out.println(command);
                    System.out.println(in.readLine());
                    break;
                }

                out.println(message);
                System.out.println(in.readLine());



            }
        } catch (IOException e) {
            System.out.println("Error while connecting to the server: " + e.getMessage());
        }


    }


}
