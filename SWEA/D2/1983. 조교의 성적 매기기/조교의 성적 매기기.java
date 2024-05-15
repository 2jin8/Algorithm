import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

	static String[] scores = { "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0" };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			PriorityQueue<Student> pq = new PriorityQueue<>();
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int mScore = Integer.parseInt(st.nextToken());
				int fScore = Integer.parseInt(st.nextToken());
				int hScore = Integer.parseInt(st.nextToken());
				double total = mScore * 0.35 + fScore * 0.45 + hScore * 0.2;
				pq.offer(new Student(i, total));
			}

			int cnt = 0, div = n / 10;
			while (!pq.isEmpty()) {
				Student student = pq.poll();
				if (student.idx == k) {
					sb.append("#").append(t).append(" ").append(scores[cnt / div]).append("\n");
					break;
				}
				cnt++;
			}
		}
		System.out.println(sb.toString());
	}

	static class Student implements Comparable<Student> {
		int idx; // 입력 순서
		double total;

		public Student(int idx, double total) {
			this.idx = idx;
			this.total = total;
		}

		@Override
		public int compareTo(Student s) {
			// 점수를 기준으로 내림차순 정렬
			if (this.total > s.total)
				return -1;
			return 1;
		}
	}
}