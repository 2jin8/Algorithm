import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class Solution
{
	static int[] dp;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			int n = sc.nextInt();
			int[] ary = new int[n];
			for (int i=0; i<n; i++) {
				ary[i] = sc.nextInt();
			}
			
			dp = new int[n]; // dp 테이블
			Arrays.fill(dp, 1); // 자기 자신의 길이는 1
			for (int i=1; i<n; i++) {
				for (int j=0; j<i; j++) {
					if (ary[i] > ary[j]) {
						dp[i] = Math.max(dp[i], dp[j]+1);
					}
				}
			}
			int max = IntStream.of(dp).max().getAsInt();
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}	
}
