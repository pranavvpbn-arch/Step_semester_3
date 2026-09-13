import java.util.Arrays;
import java.util.Scanner;

class RaceEntry {
    protected double entryFee, amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4 || entryFee <= 0)
            throw new IllegalArgumentException("Invalid input");
        this.entryFee = entryFee;
    }

    public void pay(double amount) { if (amount > 0) amountPaid += amount; }
    public double getBalanceDue() { return Math.max(0, entryFee - amountPaid); }

    protected void applyLateFee(double amount) {
        entryFee += amount;
        lateFeeHistory[feeCount++] = amount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String b, double f, String category) { super(b, f); }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Week_6_Problem3_LateWithdrawalPenalty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bib number: "); String bib = sc.nextLine();
        System.out.print("Entry fee: "); double fee = sc.nextDouble();
        System.out.print("Payment: "); double payment = sc.nextDouble();
        System.out.print("Late fee: "); double lateFee = sc.nextDouble();

        RunnerEntry r = new RunnerEntry(bib, fee, "Open 10K");
        r.pay(payment);
        r.applyLateFee(lateFee);

        System.out.println("Balance due: " + r.getBalanceDue());
        double[] history = r.getLateFeeHistory();
        System.out.println("History: " + Arrays.toString(history));
        if (history.length > 0) history[0] = 999;
        System.out.println("Actual history: " + Arrays.toString(r.getLateFeeHistory()));
        sc.close();
    }
}
