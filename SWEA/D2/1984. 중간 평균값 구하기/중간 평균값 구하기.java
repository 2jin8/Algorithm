import java.util.Arrays;
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
			int[] scores = new int[10];
			for (int i=0; i<10; i++) {
				scores[i] = sc.nextInt(); 
			}
			Arrays.sort(scores);
			int sum = 0;
			for (int i=1; i<9; i++) {
				sum += scores[i];
			}
			System.out.println("#" + test_case+ " " + (int) Math.round(sum / 8.0));
		}
	}
}