import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			int n = sc.nextInt();
			long sum = 0L;
			int[] peoples = new int[n];
			for (int i=0; i<n; i++) {
				peoples[i] = sc.nextInt();
				sum += peoples[i];
			}
			Arrays.sort(peoples); // 탐색 편의를 위해 정렬
			
			double avg = (double) sum / n; // 평균 소득
			int total = 0; // 평균 이하의 소득을 가진 사람의 수
			for (int i=0; i<n; i++) {
				if (avg < peoples[i]) break;
				total++;
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
}