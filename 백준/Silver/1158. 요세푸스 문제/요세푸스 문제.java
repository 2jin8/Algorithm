import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        if (n == 1) {
            System.out.println("<1>");
            return;
        }

        StringBuilder sb = new StringBuilder("<");
        int idx = k - 1;
        do {
            sb.append(list.remove(idx) + ", ");
            idx = (idx + k - 1) % list.size();
        } while (list.size() != 1);
        sb.append(list.get(0) + ">");
        System.out.println(sb);
    }

}