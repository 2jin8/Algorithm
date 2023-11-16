import java.util.Scanner;

class Solution
{
	static int n, m;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++)
		{
			n = sc.nextInt();
			m = sc.nextInt();
			
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			for (int i=0; i<n; i++) {
				arr1[i] = sc.nextInt();
			}
			for (int i=0; i<m; i++) {
				arr2[i] = sc.nextInt();
			}
			
			int min = Math.min(n, m);
			int total = 0;
			if (n == m) {
				for (int i=0; i<n; i++) {
					total += arr1[i] * arr2[i]; 
				}
			} else {
				if (n < m) {
					for (int j=0; j<=m-n; j++) {
						int sum = 0, k = j;
						for (int i=0; i<n; i++) {
							sum += arr1[i] * arr2[k++];
						}
						total = Math.max(total, sum);
					}
				} else {
					for (int j=0; j<=n-m; j++) {
						int sum = 0, k = j;
						for (int i=0; i<m; i++) {
							sum += arr1[k++] * arr2[i];
						}
						total = Math.max(total, sum);
					}
				}
			}
			System.out.println("#"+tc+" "+total);
		}
	}
}