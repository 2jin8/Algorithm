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
			int m = sc.nextInt();
			int[][] board = new int[n][n];
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			int k = n-m+1;
			int[][] sum = new int[k][n];
			for (int i=0; i<k; i++) {
				for (int j=0; j<n; j++) {
					for (int l=0; l<m; l++) {
						sum[i][j] += board[i+l][j];
					}
				}
			}
			int max = 0;
			for (int i=0; i<k; i++) {
				for (int j=0; j<k; j++) {
					int total = 0;
					for (int l=0; l<m; l++) {
						total += sum[i][j+l];
					}
					max = Math.max(max, total);
				}
			}
			System.out.println("#"+test_case+" "+max);
		}
	}
}