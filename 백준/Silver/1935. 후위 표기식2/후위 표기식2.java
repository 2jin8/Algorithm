import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String calc = br.readLine();
        double[] values = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < calc.length(); i++) {
            char c = calc.charAt(i);
            if (Character.isAlphabetic(c)) {
                stack.push(values[c - 'A']);
                continue;
            }
            double i1 = stack.pop();
            double i2 = stack.pop();
            switch (c) {
                case '+':
                    stack.push(i1 + i2);
                    break;
                case '-':
                    stack.push(i2 - i1);
                    break;
                case '*':
                    stack.push(i1 * i2);
                    break;
                case '/':
                    stack.push(i2 / i1);
                    break;
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}