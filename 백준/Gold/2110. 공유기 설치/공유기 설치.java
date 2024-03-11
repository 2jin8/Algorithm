import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] home;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);
        System.out.println(biSearch());
    }

    public static int biSearch() {
        int left = 1, right = home[N - 1] - home[0];
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = getCount(mid);
            if (cnt >= C) { // 설치한 공유기가 C보다 많거나 같은 경우
                ans = mid;
                left = mid + 1; // 공유기 사이의 거리를 넓혀서 설치하기(왼쪽으로 범위 좁히기)
            } else { // 설치한 공유기가 C보다 적은 경우
                right = mid - 1; // 공유기 사이의 거리를 좁혀서 설치하기(오른쪽으로 범위 좁히기)
            }
        }
        return ans;
    }

    public static int getCount(int diff) {
        int prev = home[0]; // 제일 첫 번째 집에 설치
        int cnt = 1; // 설치한 공유기의 개수

        for (int i = 1; i < N; i++) {
            int now = home[i];
            if (now - prev >= diff) { // 두 집 사이의 거리가 diff보다 크다면 공유기 설치 가능
                prev = now;
                cnt++;
            }
        }
        return cnt;
    }
}