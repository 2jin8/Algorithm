import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //A: 65, a: 97
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a') {
                sb.append(String.valueOf(c).toUpperCase());
            } else {
                sb.append(String.valueOf(c).toLowerCase());
            }
        }
        System.out.println(sb.toString());
    }
}