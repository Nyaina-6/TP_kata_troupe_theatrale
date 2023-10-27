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
            "hamlet",  new TragedyPlay("Hamlet","tragedy"),
            "as-like", new ComedyPlay("As You Like It" ,"comedy"),
            "othello", new TragedyPlay("Othello","tragedy"));
        
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
            "hamlet",  new TragedyPlay("Hamlet","tragedy"),
            "as-like", new ComedyPlay("As You Like It" ,"comedy"),
            "othello", new TragedyPlay("Othello","tragedy"));
                
        
        Customer customer = new Customer("Lala", "C02", 47);
    
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 155),
                new Performance("as-like", 135),
                new Performance("othello", 140)));
        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertDoesNotThrow(() -> {
            statementPrinter.print(invoice, plays);
        });
    }

    @Test
    void testHtmlStatement() {
        Map<String, Play> plays = Map.of(
            "hamlet",  new TragedyPlay("Hamlet","tragedy"),
            "as-like", new ComedyPlay("As You Like It" ,"comedy"),
            "othello", new TragedyPlay("Othello","tragedy"));

        Customer customer = new Customer("Lala", "C02", 47);
    
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 55),
                new Performance("othello", 100)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var htmlresult = statementPrinter.toHTML(invoice, plays);

        verify(htmlresult);
    }
}






