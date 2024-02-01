import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] cards = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);
        int total = 0;
        for (int i = 0; i < n - 2; i++) {
            int tmp = cards[i];
            for (int j = i + 1; j < n - 1; j++) {
                tmp += cards[j];
                for (int k = j + 1; k < n; k++) {
                    tmp += cards[k];
                    if (tmp <= m) total = Math.max(tmp, total);
                    tmp -= cards[k];
                }
                tmp -= cards[j];
            }
        }
        System.out.println(total);
    }
}