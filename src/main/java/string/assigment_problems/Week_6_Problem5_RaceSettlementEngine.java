import java.util.Scanner;

class RaceEntry {
    private static int bibCounter = 0;
    private final String entryCode;
    protected double entryFee, amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4 || entryFee <= 0)
            throw new IllegalArgumentException("Invalid input");

        bibCounter++;
        entryCode = "RACE-" + bibCounter;
        this.entryFee = entryFee;
    }

    public void pay(double amount) { if (amount > 0) amountPaid += amount; }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return Math.max(0, entryFee - amountPaid);
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2)) ||
            !Character.isDigit(code.charAt(3))) return false;
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() { return bibCounter; }
    public String getEntryCode() { return entryCode; }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String b, double f, String c) { super(b, f); }
}

class EliteRunnerEntry extends RunnerEntry {
    public EliteRunnerEntry(String b, double f, String c, double bonus) {
        super(b, f, c);
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String b, double f, int size) {
        super(b, f);
        if (size <= 0) throw new IllegalArgumentException("Invalid team size");
        teamSize = size;
    }
}

public class Week_6_Problem5_RaceSettlementEngine {
    static String settleNight(RaceEntry[] entries) {
        int processed = 0, nullSkipped = 0, relay = 0, individual = 0;

        for (RaceEntry e : entries) {
            if (e == null) { nullSkipped++; continue; }
            processed++;
            if (e instanceof RelayTeamEntry) relay++;
            else individual++;
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + relay + " relay | " + individual + " individual";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bib number: "); String bib = sc.nextLine();
        System.out.print("Entry fee: "); double fee = sc.nextDouble(); sc.nextLine();
        RaceEntry entry = new RaceEntry(bib, fee);

        System.out.print("Payment: "); double payment = sc.nextDouble(); sc.nextLine();
        System.out.print("Payment mode: "); String mode = sc.nextLine();
        entry.pay(payment, mode);

        System.out.print("Discount code: "); String code = sc.nextLine();
        System.out.println("Valid discount code: " + RaceEntry.isValidDiscountCode(code));
        System.out.println("Entry code: " + entry.getEntryCode());
        System.out.println("Balance due: " + entry.getBalanceDue());
        System.out.println("Bib counter: " + RaceEntry.getBibCounter());
        sc.close();
    }
}
