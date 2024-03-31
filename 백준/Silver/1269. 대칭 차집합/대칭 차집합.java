import java.util.*;
import java.io.*;

public class Main {
    static HashSet<String> hashSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        HashSet<Integer> setA = new HashSet<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < A; i++) {
            setA.add(Integer.parseInt(st.nextToken()));
        }

        HashSet<Integer> setB = new HashSet<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < B; i++) {
            setB.add(Integer.parseInt(st.nextToken()));
        }

        int total = 0;
        // A - B
        for (int a : setA) {
            if (!setB.contains(a)) { // 집합 A의 원소가 집합 B의 원소에 포함되지 않는 경우
                total++;
            }
        }

        // B - A
        for (int b : setB) {
            if (!setA.contains(b)) { // 집합 B의 원소가 집합 A의 원소에 포함되지 않는 경우
                total++;
            }
        }
        System.out.println(total);
    }
}