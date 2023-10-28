package theatricalplays;

public class ComedyPlay extends Play {
    public ComedyPlay(String name, String type) {
        super(name);
        if (!ValidType(type)) {
            throw new IllegalArgumentException("Type invalide pour ComedyPlay: " + type);
        }
    }

    @Override
    public double calculateAmountPerformance (Performance perf) {
        int thisAmount = 300;
        if (perf.audience > 20) {
            thisAmount += 100 + 5 * (perf.audience - 20);
        }
        thisAmount += 3 * perf.audience;
        return thisAmount;
    }

    @Override
    public int calculateCreditsPerformance(Performance perf) {
        int creditComedy = Math.max(perf.audience - 30, 0);
        return creditComedy += Math.floor(perf.audience / 5);
    }
}