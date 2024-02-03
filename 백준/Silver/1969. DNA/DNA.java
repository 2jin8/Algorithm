import java.io.*;
import java.util.*;

public class Main {
    static int total = 0;
    static String[] dna;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dna = new String[n];
        for (int i = 0; i < n; i++) {
            dna[i] = br.readLine();
        }
        hammingDistance(n, m);
        System.out.println(sb);
        System.out.println(total);
    }

    public static void hammingDistance(int n, int m) {
        for (int i = 0; i < m; i++) {
            // A, C, G, T
            int[] count = new int[4];
            for (int j = 0; j < n; j++) {
                count[charToInt(dna[j].charAt(i))]++;
            }

            int max = count[0], idx = 0;
            for (int j = 1; j < 4; j++) {
                if (max < count[j]) { // 같으면 사전순 → '=' 포함 X
                    max = count[j];
                    idx = j;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (idx == j) continue;
                total += count[j];
            }
            sb.append(intToString(idx));
        }
    }

    public static int charToInt(char c) {
        if (c == 'A') return 0;
        else if (c == 'C') return 1;
        else if (c == 'G') return 2;
        else return 3;
    }

    public static String intToString(int idx) {
        if (idx == 0) return "A";
        else if (idx == 1) return "C";
        else if (idx == 2) return "G";
        else return "T";
    }
}