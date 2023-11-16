import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for(int tc = 1; tc <= T; tc++)
		{
			int n = Integer.parseInt(sc.nextLine());
			int[][] map = new int[n][n];
			for (int i=0; i<n; i++) {
				String[] strings = sc.nextLine().split("");
				for (int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(strings[j]); 
				}
			}
			
			int total = 0;			
			int start = n/2, end = n/2, cnt = 0;
			for (int i=0; i<n; i++) {
				cnt++;
				for (int j=start; j<=end; j++) {
					total += map[i][j];
				}
				if (cnt > n/2) {
					start++;
					end--;
				} else {
					start--;
					end++;
				}
			}
			System.out.println("#"+tc+" "+total);
		}
	}
}