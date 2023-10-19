import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public void writeToFile(Customer customer, String customerLogFilePath) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(customerLogFilePath, true))) {
            br.write(customer.getName() + " med personnummer: " +
                    customer.getSocialSecNr() + " checkade in." + "\nDatum: " + LocalDate.now() + "\n");
            br.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Läsande av fil misslyckades.");
        }
    }
    public List<Customer> readFile(String membersFilePath) {

        List<Customer> customerList = new ArrayList<>();

        try (
                FileReader fileReader = new FileReader(membersFilePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line;
            // Loopar genom filen och lägger till användare i listan.
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(", ");

                String socialSecurityNr = parts[0].trim();
                String name = parts[1].trim();

                line = bufferedReader.readLine();
                LocalDate lastPurchaseDate = LocalDate.parse(line.trim());

                customerList.add(new Customer(name, socialSecurityNr, lastPurchaseDate));
            }

        } catch (IOException e) {
            throw new RuntimeException("Filen finns inte: " + membersFilePath);
        }
        return customerList;
    }
}
