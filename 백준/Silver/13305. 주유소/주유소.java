import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Key point: 제일 가격이 싼 지점에서 필요한 오일 다 구매
 * 주의사항: 다음 도시의 가격이 현재 도시 가격보다 비싸면 현재 도시에서 미리 구매
 * 그 전까지는 딱 필요한 오일만 구매
 * 다음 작은 도시까지 가격 계산
 *
 */

public class Main {
//    1km마다 1리터
//    원 안의 숫자: 도시에 있는 주유소의 리터당 가격
//    도로 위의 숫자: 도로의 길이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] road = new int[N - 1];
        int[] city = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < road.length; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < city.length; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }

        int minIdx = 0, idx = 1;
        long money = city[minIdx] * (long)road[minIdx];
        while (idx < N - 1) {
            if (city[idx] >= city[minIdx]) {
                money += city[minIdx] * (long)road[idx++];
            } else {
                minIdx = idx;
                money += city[minIdx] * (long)road[idx++];
            }
        }
        System.out.println(money);
    }
}