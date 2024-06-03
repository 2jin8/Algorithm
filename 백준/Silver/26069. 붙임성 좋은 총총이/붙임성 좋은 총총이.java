import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<String> hashSet = new HashSet<>(); // 무지개 댄스를 추는 사람
        hashSet.add("ChongChong");

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String user1 = st.nextToken();
            String user2 = st.nextToken();
            if (hashSet.contains(user1)) {
                hashSet.add(user2);
            } else if (hashSet.contains(user2)) {
                hashSet.add(user1);
            }
        }
        System.out.println(hashSet.size());
    }
}