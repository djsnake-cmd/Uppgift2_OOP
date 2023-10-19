import java.time.LocalDate;
import java.time.Period;
import java.util.List;
public class Customer {

    private String name;
    FileHandler fileHandler = new FileHandler();
    private String socialSecNr;
    private LocalDate lastPurchaseDate;
    private boolean isCustomer = false;
    private MemberType memberType;

    public Customer() {
    }

    public Customer(String name, String socialSecNr, LocalDate lastPurchaseDate) {
        this.name = name;
        this.socialSecNr = socialSecNr;
        this.lastPurchaseDate = lastPurchaseDate;
        this.memberType = determineMemberType(lastPurchaseDate);

    }

    public MemberType determineMemberType(LocalDate lastPurchaseDate) {
        Period period = Period.between(lastPurchaseDate, LocalDate.now());
        MemberType memberType;

        if (period.getYears() < 1) {
            memberType = MemberType.CURRENTMEMBER;
        } else if (period.getYears() > 1) {
            memberType = MemberType.FORMERMEMBER;
        } else {
            memberType = MemberType.NONMEMBER;
        }
        return memberType;

    }

    public String getName() {
        return name;
    }

    public String getSocialSecNr() {
        return socialSecNr;
    }

    public LocalDate getLastPurchaseDate() {
        return lastPurchaseDate;
    }



    public void searchUser(String searchCustomer, List<Customer> customerList) {

        //Loopar genom kundlistan efter match på namn eller personnummer.
        for (Customer customer : customerList) {
            if (customer.getName().equalsIgnoreCase(searchCustomer) || customer.getSocialSecNr().equalsIgnoreCase(searchCustomer)) {
                System.out.println(customer);
                isCustomer = true;
                if (customer.determineMemberType(customer.getLastPurchaseDate()) == MemberType.CURRENTMEMBER) {
                    fileHandler.writeToFile(customer, "src/customerLog.txt");

                }
                break;
            }
        }

        //Ifall namn eller personnummer inte finns.
        if (!isCustomer) {
            System.out.println("Kunden finns inte." + "\n");
        }
    }

    @Override
    public String toString() {
        return "Namn: " + name + "\nPersonnummer: " +
                socialSecNr + "\nDatum för senast betalning: " + lastPurchaseDate +
                "\nMedlemskap: " + memberType.getMemberType() + "\n";
    }

}