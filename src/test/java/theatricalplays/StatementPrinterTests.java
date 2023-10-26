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
            "hamlet",  new TragedyPlay("Hamlet"),
            "as-like", new ComedyPlay("As You Like It"),
            "othello", new TragedyPlay("Othello"));
        
        Customer customer = new Customer("Lala", "C02", 47);
    
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 55),
                new Performance("othello", 100)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Map<String, Play> plays = Map.of(
                "henry-v",  new TragedyPlay("Henry V"),
                "as-like", new ComedyPlay("As You Like It"),
                "othello", new TragedyPlay("Othello"));
                
        
        Customer customer = new Customer("Lala", "C02", 47);
    
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("henry-v", 155),
                new Performance("as-like", 135),
                new Performance("othello", 140)));
        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertDoesNotThrow(() -> {
            statementPrinter.print(invoice, plays);
        });
    }

    @Test
    void testHtmlStatementGeneration() {
        Map<String, Play> plays = Map.of(
            "hamlet",  new TragedyPlay("Hamlet"),
            "as-like", new ComedyPlay("As You Like It"),
            "othello", new TragedyPlay("Othello"));

        Customer customer = new Customer("Lala", "C02", 47);
    
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 55),
                new Performance("othello", 100)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.toHTML(invoice, plays);


        Assertions.assertEquals("<html>\n<head><title>Invoice</title></head>\n<body>\n" +
        "<h1>Statement for Lala</h1>\n" +
        "<table border=1 >\n" +
        "<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>\n" +
        "<tr><td>Hamlet</td><td>55</td><td>$650.00</td></tr>\n" +
        "<tr><td>As You Like It</td><td>55</td><td>$740.00</td></tr>\n" +
        "<tr><td>Othello</td><td>100</td><td>$1,100.00</td></tr>\n" +
        "<tr><td  colspan =2 >Total </td><td>$2,490.00</td><tr>\n" +
        "<tr><td  colspan =2 >Discount </td><td> - $15.00</td><tr>\n" + 
        "<tr><td  colspan =2 >TOTAL </td><td>$2,475.00</td><tr>\n" + 

        "</table>\n" +
        "<p>Your loyality points</p>\n" +
        "<table border=1 >\n" +
        "<tr><th>Credits</th><th>Amount</th></tr>\n" +
        "<tr><td  >Owed</td><td>178</td></tr>\n" + 
        "<tr><td  >Actual</td><td>28</td></tr>\n" + 
        "</table>\n" +
        "<p>You must pay within 15 days otherwise your reservation will be cancelled.</p>\n" +
        "</body>\n</html>", result);

        
        
    }
}






