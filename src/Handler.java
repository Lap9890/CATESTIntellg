import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {
     private final Socket s;
    private final Manager m;

    public Handler(Socket s, Manager m) {
        this.s = s;
        this.m = m;
    }

    @Override
    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String inputLine;
            while((inputLine= in.readLine()) != null){
                if(inputLine.trim().equalsIgnoreCase( "STOP")){
                    out.println("TERMINATE");
                    break;
                }
                try {
                    out.println(m.logic(inputLine));
                }catch (InvalidCommandException e ){
                    out.println("InvalidCommandException: " + e.getMessage());
                }


            }



        } catch (IOException e ) {
            System.out.println("Error:" + e.getMessage());
        }finally {

            try {
                s.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
