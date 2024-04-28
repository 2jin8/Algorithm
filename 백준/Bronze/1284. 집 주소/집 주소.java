import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String n = br.readLine();
            if (n.equals("0")) {
                System.out.println(sb.toString());
                break;
            }

            int total = 0;
            for (int i = 0; i < n.length(); i++) {
                char c = n.charAt(i);
                total += charToInt(c) + 1;
            }
            sb.append(total + 1).append("\n");
        }
    }
    static int charToInt(char c) {
        if (c == '0') return 4;
        else if (c == '1') return 2;
        else return 3;
    }
}