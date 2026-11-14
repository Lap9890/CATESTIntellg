import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //  System.out.println(t1.getStudentName());
        Manager man = new Manager();
// Test
/*
        try {

            System.out.println(man.open("Alice", "T101", "network", "wifi"));
            System.out.println(man.open("Francesco", "T102", "other", "Lol bsdf"));
            System.out.println(man.open("Alice", "T103", "other", "thstr"));

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(man.list("Alice"));
        System.out.println(man.list("alice"));
        System.out.println(man.list("Bob"));
        System.out.println(man.list("Francesco"));


        try {
            System.out.println(man.close("T101"));
            System.out.println(man.close("t103"));
            System.out.println(man.close("1023"));

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
*/

        try {
            System.out.println(man.gestore("OPEN; T104; -; network; Wi-Fi disconnects repeatedly"));
            System.out.println(man.gestore("list; -; Alice; -; -"));
            System.out.println(man.gestore("close; -; Alice; network; desc"));
            System.out.println(man.gestore("list;T11;Alice;other;test"));

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
// Server socket connection
/*
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Waiting for connection...");
            while (true) {
                Socket s = ss.accept();
                System.out.println("Connection established");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

    }
}


