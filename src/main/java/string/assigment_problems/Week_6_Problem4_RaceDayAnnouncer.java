import java.util.Scanner;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee, amountPaid;

    public RaceEntry(String b, double f) {
        if (b == null || b.trim().length() < 4 || f <= 0)
            throw new IllegalArgumentException("Invalid input");
        bibNumber = b; entryFee = f;
    }
    public void pay(double a) { if (a > 0) amountPaid += a; }
    public double getBalanceDue() { return Math.max(0, entryFee - amountPaid); }
    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    private String category;
    public RunnerEntry(String b, double f, String c) { super(b, f); category = c; }
    @Override public String announce() {
        return "Runner Entry | Bib: " + bibNumber + " | Category: " +
                category + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String b, double f, int s) {
        super(b, f);
        if (s <= 0) throw new IllegalArgumentException("Invalid team size");
        teamSize = s;
    }
    public int getTeamSize() { return teamSize; }
    @Override public String announce() {
        return "Relay Team | Bib: " + bibNumber + " | Team Size: " +
                teamSize + " | Balance: " + getBalanceDue();
    }
}

public class Week_6_Problem4_RaceDayAnnouncer {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();
        for (RaceEntry e : entries) {
            report.append(e.announce()).append(" ");
            if (e instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) e;
                report.append("[Team size via downcast: ")
                      .append(relay.getTeamSize()).append("] ");
            }
        }
        return report.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Runner bib: "); String rb = sc.nextLine();
        System.out.print("Runner fee: "); double rf = sc.nextDouble(); sc.nextLine();
        System.out.print("Category: "); String cat = sc.nextLine();
        System.out.print("Relay bib: "); String xb = sc.nextLine();
        System.out.print("Relay fee: "); double xf = sc.nextDouble();
        System.out.print("Team size: "); int size = sc.nextInt();

        RaceEntry[] entries = {
            new RunnerEntry(rb, rf, cat),
            new RelayTeamEntry(xb, xf, size)
        };
        System.out.println(announceAll(entries));
        sc.close();
    }
}
