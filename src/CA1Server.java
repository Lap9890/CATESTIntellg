import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CA1Server {
    public static void main(String[] args) {


        //  System.out.println(t1.getStudentName());
        Manager man = new Manager();
        ServerSocket s;
        System.out.println("Server ready");
        try {

            s = new ServerSocket(8069);
            while (true) {
                Socket ss = s.accept();
                System.out.println("Server connected");

                Thread td = new Thread(new Handler(ss,man));
                td.start();

            }

        } catch (IOException e) {
           System.out.println(e.getMessage());
        }finally {

        }


    }
}


