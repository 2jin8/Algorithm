import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값
        // 돈을 인출하는데 걸리는 시간을 기준으로 오름차순 정렬
        // 앞 사람부터 인출
        int n = Integer.parseInt(br.readLine());
        int[] lines = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lines[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines);

        int before = lines[0], sum = lines[0];
        for (int i = 1; i < n; i++) {
            int time = before + lines[i];
            before = time;
            sum += time;
        }
        System.out.println(sum);
    }
}
