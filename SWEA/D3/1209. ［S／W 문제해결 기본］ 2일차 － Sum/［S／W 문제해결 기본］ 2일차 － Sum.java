import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		for(int tc = 1; tc <= 10; tc++)
		{
			int n = sc.nextInt();
			int[][] ary = new int[100][100];
			int rowSum = 0;
			for (int i=0; i<100; i++) {
				int sum = 0;
				for (int j=0; j<100; j++) {
					ary[i][j] = sc.nextInt(); 
					sum += ary[i][j];
				}
				rowSum = Math.max(rowSum, sum);
			}
			
			int colSum = 0;
			for (int i=0; i<100; i++) {
				int sum = 0;
				for (int j=0; j<100; j++) {
					sum += ary[j][i];
				}
				colSum = Math.max(colSum, sum);
			}
			
			int leftCrossSum = 0, rightCrossSum = 0;
			for (int i=0; i<100; i++) {
				leftCrossSum += ary[i][i];
				rightCrossSum += ary[i][99-i]; 
			}
			
			int max = Math.max(Math.max(rowSum, colSum), Math.max(leftCrossSum, rightCrossSum));
			System.out.println("#"+n+" "+max);
		}
	}
}