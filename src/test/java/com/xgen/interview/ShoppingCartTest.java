package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        try {
            ListItemStore itemStore =  new ListItemStore();
            Pricer pricer = new Pricer();
            RegularReceiptFormat regularReceiptFormat = new RegularReceiptFormat();
            ShoppingCart sc = new ShoppingCart(itemStore, pricer, regularReceiptFormat);
    
            sc.addItem("apple", 1);
    
    
            final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
    
            sc.printReceipt();
            String[] actuals = myOut.toString().split("\\s-\\s|\\s+");
            System.out.println(actuals);
            String[] expecteds = new String[] {"apple", "1", "€1.00", "Total", "Price:", "€1.00"};
            assertArrayEquals(expecteds, actuals);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canAddMoreThanOneItem() {
        ListItemStore itemStore =  new ListItemStore();
        Pricer pricer = new Pricer();
        RegularReceiptFormat regularReceiptFormat = new RegularReceiptFormat();
        ShoppingCart sc = new ShoppingCart(itemStore, pricer, regularReceiptFormat);

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String[] actuals = myOut.toString().split("\\s-\\s|\\s+");
        String[] expecteds = new String[] {"apple", "2", "€2.00", "Total", "Price:", "€2.00"};
        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void canAddDifferentItems() {
        ListItemStore itemStore =  new ListItemStore();
        Pricer pricer = new Pricer();
        RegularReceiptFormat regularReceiptFormat = new RegularReceiptFormat();
        ShoppingCart sc = new ShoppingCart(itemStore, pricer, regularReceiptFormat);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] actuals = myOut.toString().split("\\s-\\s|\\s+");
        String[] expecteds = new String[] {"apple", "2", "€2.00", "banana", "1", "€2.00",  "Total", "Price:", "€4.00"};
        assertArrayEquals(expecteds, actuals);
    }

        @Test
    public void doesntExplodeOnMysteryItem() {
        ListItemStore itemStore =  new ListItemStore();
        Pricer pricer = new Pricer();
        RegularReceiptFormat regularReceiptFormat = new RegularReceiptFormat();
        ShoppingCart sc = new ShoppingCart(itemStore, pricer, regularReceiptFormat);

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] actuals = myOut.toString().split("\\s-\\s|\\s+");
        String[] expecteds = new String[] {"crisps", "2", "€0.00", "Total", "Price:", "€0.00"};
        assertArrayEquals(expecteds, actuals);
    }
}


