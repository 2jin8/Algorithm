import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 종이컵의 수
		int X = Integer.parseInt(st.nextToken()); // 간식이 들어있는 종이컵의 위치
		int K = Integer.parseInt(st.nextToken()); // 컵의 위치를 맞바꾸는 횟수

		// 위치를 바꾼 컵의 정보
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// 바꾸는 위치가 간식이 있는 위치인지 확인하기
			if (A == X) {
				X = B;
			} else if (B == X) {
				X = A;
			}
		}
		System.out.println(X);
	}
}