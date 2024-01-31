import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] heights = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> diff = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            diff.add(heights[i] - heights[i - 1]);
        }
        Collections.sort(diff);

        int result = 0;
        for (int i = 0; i < n - k; i++) {
            result += diff.get(i);
        }
        System.out.println(result);
    }
}