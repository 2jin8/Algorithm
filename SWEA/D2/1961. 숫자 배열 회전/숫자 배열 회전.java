import java.util.Scanner;

class Solution
{
	static int n;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++)
		{
			n = sc.nextInt();
			int[][] original = new int[n][n]; 
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					original[i][j] = sc.nextInt();
				}
			}
			
			int[][] rotate90 = new int[n][n]; 
			rotate(original, rotate90);
			
			int[][] rotate180 = new int[n][n]; 
			rotate(rotate90, rotate180);

			int[][] rotate270 = new int[n][n];
			rotate(rotate180, rotate270);
			
			StringBuilder sBuilder = new StringBuilder("#"+tc+"\n");
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					sBuilder.append(rotate90[i][j]);
				}
				sBuilder.append(" ");
				for (int j=0; j<n; j++) {
					sBuilder.append(rotate180[i][j]);
				}
				sBuilder.append(" ");
				for (int j=0; j<n; j++) {
					sBuilder.append(rotate270[i][j]);
				}
				sBuilder.append("\n");
			}
			System.out.print(sBuilder.toString());
		}
	}
	
	public static void rotate(int[][] original, int[][] newAry) {
		int k = n - 1;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				newAry[j][k] = original[i][j];
			}
			k--;
		}
	}
}