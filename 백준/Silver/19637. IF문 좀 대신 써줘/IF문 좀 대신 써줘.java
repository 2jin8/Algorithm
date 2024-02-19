import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] names;
    static int[] powers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        names = new String[N];
        powers = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            names[i] = st.nextToken();
            powers[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());
            sb.append(biSearch(power)).append("\n");
        }
        System.out.println(sb);
    }

    public static String biSearch(int power) {
        int start = 0, end = N - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (powers[mid] >= power) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return names[start];
    }
}