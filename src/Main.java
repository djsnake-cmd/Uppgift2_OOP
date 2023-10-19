import java.util.List;
import java.util.Scanner;


//Endast G-nivå
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Customer customer = new Customer();
        FileHandler fileHandler = new FileHandler();
        String membersFilePath = "src/Members.txt";

        while (true) {
            System.out.println("Sök på en kund: ");
            String searchCustomer = scan.nextLine();
            if (searchCustomer.isBlank()) {
                break;
            }
            List<Customer> customerList = fileHandler.readFile(membersFilePath);
            customer.searchUser(searchCustomer, customerList);
        }
    }
}