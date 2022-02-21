
import Model.Office;
import Controller.Controller;
import Model.Client;
import Model.Manager;
import Model.TourGuide;
import Model.Trip;
import java.util.ArrayList;

/**Main class of the program
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class Main {

    public static void main(String args[]) {
        //MainFrame m=new MainFrame();
        //Creating empty arraylists of office's data
        ArrayList<TourGuide> employees = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Trip> trips = new ArrayList<>();
        Manager manager = new Manager("manager");
        //Initializing office object with created lists and manager object
        Office o = new Office(0, employees, clients, trips, manager);
        Controller c = new Controller(o);//Creating controller object
    }
}
