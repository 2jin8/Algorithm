import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.equals("0")) break;

            int original = Integer.parseInt(str);
            StringBuilder sb = new StringBuilder(str);
            int reverse = Integer.parseInt(sb.reverse().toString());
            if (original == reverse) result.append("yes\n");
            else result.append("no\n");
        }
        System.out.println(result);
    }
}