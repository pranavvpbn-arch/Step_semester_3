import java.util.Scanner;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee, amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4 || entryFee <= 0)
            throw new IllegalArgumentException("Invalid input");
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) { if (amount > 0) amountPaid += amount; }
    public double getBalanceDue() { return Math.max(0, entryFee - amountPaid); }

    public void announce() {
        System.out.println("Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue());
    }
}

class RunnerEntry extends RaceEntry {
    protected String category;
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
    @Override public void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber +
                " | Category: " + category + " | Balance: " + getBalanceDue());
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;
    public EliteRunnerEntry(String b, double f, String c, double bonus) {
        super(b, f, c); sponsorBonus = bonus;
    }
    @Override public void announce() {
        System.out.println("Elite Runner | Bib: " + bibNumber +
                " | Category: " + category + " | Sponsor Bonus: " +
                sponsorBonus + " | Balance: " + getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String b, double f, int size) {
        super(b, f);
        if (size <= 0) throw new IllegalArgumentException("Invalid team size");
        teamSize = size;
    }
    public int getTeamSize() { return teamSize; }
    @Override public void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize + " | Balance: " + getBalanceDue());
    }
}

public class Week_6_Problem2_ThreeRaceShapes {
    static String classifyGeneration(RaceEntry e) {
        if (e instanceof EliteRunnerEntry) return "Multilevel descendant (3 generations deep)";
        if (e instanceof RelayTeamEntry) return "Hierarchical sibling (independent branch)";
        if (e instanceof RunnerEntry) return "Single-inheritance descendant";
        return "Base RaceEntry";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry e : entries) total += e.getBalanceDue();
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Runner bib: "); String rb = sc.nextLine();
        System.out.print("Runner fee: "); double rf = sc.nextDouble(); sc.nextLine();
        System.out.print("Category: "); String cat = sc.nextLine();
        System.out.print("Elite bib: "); String eb = sc.nextLine();
        System.out.print("Elite fee: "); double ef = sc.nextDouble();
        System.out.print("Sponsor bonus: "); double bonus = sc.nextDouble(); sc.nextLine();
        System.out.print("Relay bib: "); String xb = sc.nextLine();
        System.out.print("Relay fee: "); double xf = sc.nextDouble();
        System.out.print("Team size: "); int size = sc.nextInt();

        RaceEntry[] entries = {
            new RunnerEntry(rb, rf, cat),
            new EliteRunnerEntry(eb, ef, "Elite Full Marathon", bonus),
            new RelayTeamEntry(xb, xf, size)
        };

        for (RaceEntry e : entries) e.announce();
        System.out.println(classifyGeneration(entries[1]));
        System.out.println(classifyGeneration(entries[2]));
        System.out.println("Total balance due: " + getTotalBalanceDue(entries));
        sc.close();
    }
}
