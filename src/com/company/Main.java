package com.company;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        ArrayList<Leksema> list = lexAnalyze(s); //Получаю список лексем
        double answer = stackAnalyze(list); //Получаю ответ

        System.out.println(answer);
        sc.close();

    }

    //Функция производит операцию над двумя числами
    public static double calculate(double a, double b, char op) {
        switch(op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    //Функция распределяет числа и операции по стекам, производит операции над числами
    public static double stackAnalyze(ArrayList<Leksema> list){
        Stack<Double> nStack = new Stack<Double>();	//Стек для чисел
        Stack<Character> oStack = new Stack<Character>();	//Стек для операторов

        Map<Character, Integer> priority = new HashMap<Character, Integer>(); // Приоритеты операций
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('(', 0);

        for(int i = 0; i < list.size(); ) {
            switch (list.get(i).type){
                case 'n':
                    nStack.add(list.get(i).value);
                    i++;
                    break;
                case '(':
                    oStack.add(list.get(i).type);
                    i++;
                    break;
                case ')':
                    if(oStack.peek() != '(') {
                        double b = nStack.pop();
                        double a = nStack.pop();
                        nStack.add(calculate(a, b, oStack.pop()));
                    }else {
                        oStack.pop();
                        i++;
                    }
                    break;
                default:
                    if(oStack.empty() || priority.get(oStack.peek()) < priority.get(list.get(i).type)) {
                        oStack.add(list.get(i).type);
                        i++;
                    }else if(oStack.peek() != '('){
                        double b = nStack.pop();
                        double a = nStack.pop();
                        nStack.add(calculate(a, b, oStack.pop()));
                    }
            }
        }
        while(!oStack.empty()) {
            double b = nStack.pop();
            double a = nStack.pop();
            nStack.add(calculate(a, b, oStack.pop()));
        }
        return nStack.peek();
    }

    //Функция разбивает строку на лексемы
    public static ArrayList<Leksema> lexAnalyze(String s){
        char[] arrayChar = s.toCharArray();
        ArrayList<Leksema> list = new ArrayList<>();

        int i = 0;
        while(i < arrayChar.length) {
            char symb = arrayChar[i];
            if(symb == '+' || symb == '-' || symb == '*' || symb == '/' || symb == '(' || symb == ')') {
                list.add(new Leksema(symb, 0));
                i++;
            }else {
                if(arrayChar[i] >= '0' && arrayChar[i] <= '9') {
                    StringBuilder sb = new StringBuilder();
                    do {
                        sb.append(arrayChar[i]);
                        i++;
                        if(i == arrayChar.length) {
                            break;
                        }
                    } while(arrayChar[i] >= '0' && arrayChar[i] <= '9' || arrayChar[i] == '.');
                    String l = sb.toString();
                    list.add(new Leksema('n', Double.parseDouble(l)));
                } else {
                    i++;
                }

            }
        }
        return list;
    }

}

