import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static final int K = 20;
    static int[] trains;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new int[N + 1];
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int x;
            switch (cmd) {
                case 1: // 기차에 태우기
                    x = Integer.parseInt(st.nextToken()) - 1; // 0번 ~ 19번
                    trains[i] |= (1 << x); // x번째 자리의 값을 1로 변경
                    break;
                case 2: // 기차에서 내리기
                    x = Integer.parseInt(st.nextToken()) - 1;
                    trains[i] &= ~(1 << x); // x번째 자리의 값을 0으로 변경
                    break;
                case 3: // 한 칸씩 뒤(←)로 이동
                    trains[i] <<= 1; // 각 자리의 사람 모두 한 칸씩 뒤로
                    trains[i] &= ~(1 << 20); // 20번 자리의 사람이 있다면 하차(0으로 변경)
                    break;
                case 4: // 한 칸씩 앞(→)으로 이동
//                    trains[i] &= ~1; // 0번 자리의 사람 하차(0으로 변경)
                    trains[i] >>= 1; // 각 자리의 사람 모두 한 칸씩 앞으로
                    break;
            }
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            hashSet.add(trains[i]);
        }
        System.out.println(hashSet.size());
    }
}