import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, R, C, order = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		findVisitCnt(0, 0, N);
	}

	static void findVisitCnt(int r, int c, int size) {
		if (size == 1) { // size가 1 == (R, C) 지점에 도달한 경우
			System.out.println(order);
			return;
		}

		int newSize = size / 2, nr = r + newSize, nc = c + newSize;
		if (R < nr && C < nc) { // 1구역
			findVisitCnt(r, c, newSize);
		} else if (R < nr && C >= nc) { // 2구역
			order += newSize * newSize;
			findVisitCnt(r, nc, newSize);
		} else if (R >= nr && C < nc) { // 3구역
			order += newSize * newSize * 2;
			findVisitCnt(nr, c, newSize);
		} else if (R >= nr && C >= nc) { // 4구역
			order += newSize * newSize * 3;
			findVisitCnt(nr, nc, newSize);
		}
	}
}