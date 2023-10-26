package theatricalplays;

public class ComedyPlay extends Play {
    public ComedyPlay(String name) {
        super(name);
    }

    @Override
    public double calculateAmount(Performance perf) {
        int thisAmount = 300;
        if (perf.audience > 20) {
            thisAmount += 100 + 5 * (perf.audience - 20);
        }
        thisAmount += 3 * perf.audience;
        return thisAmount;
    }

    @Override
    public int calculateCredits(Performance perf) {
        int creditComedy = Math.max(perf.audience - 30, 0);
        return creditComedy += Math.floor(perf.audience / 5);
    }
}