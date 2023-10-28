package theatricalplays;

public class TragedyPlay extends Play {
    public TragedyPlay(String name , String type) {
        super(name);
        if (!ValidType(type)) {
            throw new IllegalArgumentException("Type invalide pour TragedyPlay: " + type);
        }
    }

    @Override
    public double calculateAmountPerformance(Performance perf) {
        int thisAmount = 400;
        if (perf.audience > 30) {
            thisAmount += 10 * (perf.audience - 30);
        }
        return thisAmount;
    }

    @Override
    public int calculateCreditsPerformance(Performance perf) {
        return Math.max(perf.audience - 30, 0);
    }    
}
