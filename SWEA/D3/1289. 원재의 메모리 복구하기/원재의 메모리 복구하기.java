import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	static char[] origin, change;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(sc.nextLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			String str = sc.nextLine();
			int len = str.length();
			origin = new char[len];
			change = new char[len];
			Arrays.fill(origin, '0');
			for (int i=0; i<len; i++) {
				change[i] = str.charAt(i);
			}
			
			int total = 0;
			for (int i=0; i<len; i++) {
				if (origin[i] != change[i]) {
					Arrays.fill(origin, i, len, change[i]);
					total++;
				}
			}
			
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}	
}
