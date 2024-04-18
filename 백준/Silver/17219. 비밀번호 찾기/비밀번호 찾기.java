import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, String> password = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String site = st.nextToken();
            String pw = st.nextToken();
            password.put(site, pw);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            sb.append(password.get(site)).append("\n");
        }
        System.out.println(sb.toString());
    }
}