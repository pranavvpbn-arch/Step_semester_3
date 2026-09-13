import java.util.Scanner;

class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException("Invalid bib number");
        if (entryFee <= 0) throw new IllegalArgumentException("Invalid fee");
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) {
        if (amount > 0) amountPaid += amount;
    }

    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0, rejected = 0;
        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

public class Week_6_Problem1_RaceEntryFoundation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bib number: ");
        String bib = sc.nextLine();
        System.out.print("Entry fee: ");
        double fee = sc.nextDouble();
        sc.nextLine();
        System.out.print("Category: ");
        String category = sc.nextLine();

        try {
            RunnerEntry r = new RunnerEntry(bib, fee, category);
            System.out.print("Payment: ");
            r.pay(sc.nextDouble());
            System.out.println("Balance due: " + r.getBalanceDue());
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
        sc.close();
    }
}
