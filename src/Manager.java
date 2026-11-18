import java.util.ArrayList;
import java.util.Arrays;



public class Manager {
    private ArrayList<Ticket> Tickets;
    private static final String [] allowedCategories = {"account","network","software","other"};

    public Manager() {
        Tickets = new ArrayList<>();


    }


    public synchronized String open(String studentName, String ticketNumber, String category, String note)
            throws InvalidCommandException {

        if (!categoryCheck(category)) {
            throw new InvalidCommandException("Category is wrong, category must be account, network, software or other");
        }

        for (Ticket existingTicket : Tickets) {
            if (existingTicket.getTicketNumber().equalsIgnoreCase(ticketNumber)) {
                throw new InvalidCommandException("ticket ID " + ticketNumber + " is already open");
            }
        }

        Tickets.add(new Ticket(studentName, ticketNumber, category, note));
        return counter(studentName);
    }

    public synchronized String close(String ticketNumber) throws InvalidCommandException {
        StringBuffer sb = new StringBuffer();
        String studentname = "";
        for (Ticket T : Tickets) {
            if (T.getTicketNumber().equalsIgnoreCase(ticketNumber)) {
                Tickets.remove(T);
                System.out.println("Ticket removed");
                studentname = T.getStudentName();
                sb.append("Closed ");
                sb.append(ticketNumber);
                sb.append(". ");
                sb.append(counter(studentname));
                return sb.toString();
            }

        }
        throw new InvalidCommandException("Ticket not found");





    }


    public synchronized String list(String studentName) {
        return counter(studentName);
    }

    //Check if the category is the correct one
    private boolean categoryCheck (String category){
        return  Arrays.asList(allowedCategories).contains(category);



    }

    //Count the number of open ticket of the select  student and will return the n + the tickets
    private String counter(String studentName){
        StringBuffer sb = new StringBuffer();
        int counter = 0;
        for(Ticket T: Tickets){
            if (T.getStudentName().equalsIgnoreCase(studentName)){
                counter++;
            }

        }
        sb.append(studentName);
        sb.append(" has ");
        sb.append(counter);
        sb.append(" open");
        sb.append(counter >1? " tickets:": " ticket:");

        for(Ticket T: Tickets) {
            if (T.getStudentName().equalsIgnoreCase(studentName)) {
                sb.append(" ");
                sb.append(T.toString());
                sb.append(";");
            }
        }

       return  counter == 0? studentName + " has no open tickets" :  sb.toString();
    }

    //This the logic of the Manager
    public String logic (String testo) throws  InvalidCommandException{
        String parts [] = testo.split(";",-1);
        for(int i = 0; i< parts.length;i++){
            parts[i] = parts[i].trim();
        }

        if (parts.length != 5){
            throw new InvalidCommandException("command must have exactly 5 fields separated by semicolons");
        }

         switch (parts[0].toLowerCase()){
             case "open":
                 if(!parts[1].equalsIgnoreCase("-") && !parts[2].equalsIgnoreCase("-") && !parts[3].equalsIgnoreCase("-") && !parts[4].equalsIgnoreCase("-")) {
                     return this.open(parts[2],parts[1],parts[3],parts[4] );
                 }
                 else {

                     throw new InvalidCommandException("Missing argument  ");
                 }

             case "close":
                 if(!parts[1].equalsIgnoreCase("-")) {
                     return this.close(parts[1]);
                 }
                 else {
                     throw new InvalidCommandException("Missing ticket Number");
                 }
             case "list":
                 if(!parts[2].equalsIgnoreCase("-")) {
                     return this.list(parts[2]);
                 }
                 else {
                     throw new InvalidCommandException("Missing ticket Number");
                 }

             default:
                 throw new InvalidCommandException("Action not valid ");
         }

    }
}
