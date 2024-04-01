import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        HashSet<String> hashSet = new HashSet<>();

        int len = str.length(), cnt = 1;
        while (len > 0) {
            for (int i = 0; i < len; i++) {
                hashSet.add(str.substring(i, i + cnt));
            }
            cnt++;
            len--;
        }
        System.out.println(hashSet.size());
    }
}