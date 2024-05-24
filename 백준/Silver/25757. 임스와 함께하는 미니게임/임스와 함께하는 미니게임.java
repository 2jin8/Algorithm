import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        char kind = st.nextToken().charAt(0);

        HashSet<String> memberList = new HashSet<>();
        for (int i = 0; i < n; i++) {
            memberList.add(br.readLine());
        }

        int member = memberList.size();
        switch (kind) {
            case 'F':
                member /= 2;
                break;
            case 'O':
                member /= 3;
                break;
        }
        System.out.println(member);
    }
}