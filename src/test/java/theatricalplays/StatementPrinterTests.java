package theatricalplays;

import static org.approvaltests.Approvals.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
            "hamlet",  new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));


        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);
        
        verify(result);
    }
    @Test
    void htmlStatement() {
        Map<String, Play> plays = Map.of(
            "hamlet",  new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));


        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var htmlresult = statementPrinter.toHTML(invoice, plays);
        
        Assertions.assertEquals("<html>\n"+
        "<head><title>Invoice</title></head>\n"+
        "<body>\n"+
        "<h1>Statement for BigCo</h1>\n"+
        "<table border=1 >\n"+
        "<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n"+
        "<tr><td>Hamlet</td><td>55</td><td>$650.00</td></tr>\n"+
        "<tr><td>As You Like It</td><td>35</td><td>$580.00</td></tr>\n"+
        "<tr><td>Othello</td><td>40</td><td>$500.00</td></tr>\n"+
        "<tr><td  colspan =2 >TOTAL </td><td>$1,730.00</td><tr>\n"+
        "</table>\n"+
        "<p>You earned 47 credits</p>\n"+
        "<p>You must pay within 15 days otherwise your reservation will be cancelled.</p>\n"+
        "</body>\n"+
        "</html>\n",htmlresult);
        
    }

    @Test
    void statementWithNewPlayTypes() {
        Map<String, Play> plays = Map.of(
            "hamlet",  new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));


        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));


        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertDoesNotThrow(() -> {
            statementPrinter.print(invoice, plays);
        });
    }
}
