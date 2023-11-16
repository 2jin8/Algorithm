import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Solution
{
	static String[] scoreList = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
	static PriorityQueue<Score> scores;
	static int n, k;
	public static <T> void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = sc.nextInt(); // 학생 수
			k = sc.nextInt() - 1; // 알고싶은 학생의 번호
			scores = new PriorityQueue<>(new Comparator<Score>() {

				@Override
				public int compare(Score o1, Score o2) {
					return o1.total > o2.total? -1 : 1;
				}
			});
			
			for (int i=0; i<n; i++) {
				double mid = sc.nextInt() * 0.35;
				double fin = sc.nextInt() * 0.45;
				double homework = sc.nextInt() * 0.2;
				scores.offer(new Score(i, mid + fin + homework));
			}
			
			System.out.println("#"+test_case+" " +findList(n/10, 0));
		}	
	}
	
	public static String findList(int sNum, int slistNum) {
		while (true) {
			for (int i=0; i<sNum; i++) {
				Score score = scores.poll();
				if (score.idx == k) return scoreList[slistNum]; 
			}
			slistNum++;
		}
	}
}

class Score {
	int idx;
	double total;
	
	public Score(int idx, double total) {
		this.idx = idx;
		this.total = total;
	}
}