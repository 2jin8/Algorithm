import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = br.readLine();
        }

        int k = 1;
        while (true) {
            HashSet<String> hashSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String str = strs[i];
                hashSet.add(str.substring(str.length() - k, str.length()));
            }
            if (hashSet.size() == n) {
                System.out.println(k);
                break;
            }
            k++;
        }
    }
}