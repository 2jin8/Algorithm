import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
			int[][] dp = new int[10][10];
			
			dp[0][0] = 1; dp[1][0] = 1; dp[1][1] = 1;
			for (int i=2; i<n; i++) {
				for (int j=0; j<=i; j++) {
					if (i == j) dp[i][j] = 1;
					else if (j == 0) dp[i][j] = 1;
					else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<n; i++) {
				for (int j=0; j<=i; j++) {
					sb.append(dp[i][j]+ " ");
				}
				sb.append("\n");
			}
			
			System.out.println("#"+test_case);
			System.out.print(sb.toString());
		}
	}
}