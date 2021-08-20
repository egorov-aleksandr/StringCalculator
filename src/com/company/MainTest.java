package com.company;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void calculateTestPlus() {
        double actual = Main.calculate(2, 4, '+');
        double expected = 6.0;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateTestMinus() {
        double actual = Main.calculate(12.6, 4.2, '-');
        double expected = 8.4;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateTestMul() {
        double actual = Main.calculate(2.5, 3.5, '*');
        double expected = 8.75;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateTestDiv() {
        double actual = Main.calculate(10, 2.5, '/');
        double expected = 4;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void lexAnalyzeTest() {
        ArrayList<Leksema> expected = new ArrayList<Leksema>();
        expected.add(new Leksema('n', 2));
        expected.add(new Leksema('+', 0));
        expected.add(new Leksema('n', 2));
        expected.add(new Leksema('*', 0));
        expected.add(new Leksema('(', 0));
        expected.add(new Leksema('(', 0));
        expected.add(new Leksema('n', 2.22));
        expected.add(new Leksema('-', 0));
        expected.add(new Leksema('n', 1.01));
        expected.add(new Leksema('/', 0));
        expected.add(new Leksema('n', 35));
        expected.add(new Leksema(')', 0));
        expected.add(new Leksema('*', 0));
        expected.add(new Leksema('n', 223));
        expected.add(new Leksema(')', 0));

        ArrayList<Leksema> actual = Main.lexAnalyze("2 + 2 * ((2.22 - 1.01 / 35) * 223)");
        for(int i =0; i < expected.size(); i++){
        assertEquals(expected.get(i).type, actual.get(i).type);
        assertEquals(expected.get(i).value, actual.get(i).value, 0.01);
        }
    }

    @Test
    public void testStackAnalyze() {
        ArrayList<Leksema> list = new ArrayList<Leksema>();
        list.add(new Leksema('n', 2));
        list.add(new Leksema('+', 0));
        list.add(new Leksema('n', 2));
        list.add(new Leksema('*', 0));
        list.add(new Leksema('(', 0));
        list.add(new Leksema('(', 0));
        list.add(new Leksema('n', 20.22));
        list.add(new Leksema('-', 0));
        list.add(new Leksema('n', 10.2));
        list.add(new Leksema('/', 0));
        list.add(new Leksema('n', 2));
        list.add(new Leksema(')', 0));
        list.add(new Leksema('*', 0));
        list.add(new Leksema('n', 3));
        list.add(new Leksema(')', 0));

        double expected = 92.72;
        double actual = Main.stackAnalyze(list);
        assertEquals(expected, actual, 0.01);
    }
}