import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
  후기)
   이분탐색으로 푸려고 한 것은 좋았다
   But, 입력받은 배열에 대해서만 이분탐색하려고 한 것이 문제
   유연하게 생각하는 것이 중요함
   !! 입력받은 배열이 아닌, '1~나무 최대 높이' 배열을 이분탐색하는 것이 포인트 !!

  풀이 방식)
   1. min, max 사이의 중간값(=mid)을 기준으로(min = 1, max = 나무 최대 높이)
     1.1 자른 높이의 합 > M : 오른쪽 배열 탐색(min = mid + 1)
     1.2 자른 높이의 합 < M : 왼쪽 배열 탐색(max = mid - 1)
   2. min > max인 경우 탐색 종료
   3. mid 출력
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] lan = new int[K];
        long min = 1, max = 0;
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lan[i]);
        }

        long mid = (min + max) / 2;
        while (min <= max) {
            long sum = 0;

            for (int l : lan) {
                sum += l / mid;
            }

            if (sum < N) {
                max = mid - 1;
            } else if (sum >= N) {
                min = mid + 1;
            }
            mid = (min + max) / 2;
        }
        System.out.println(mid);
    }
}