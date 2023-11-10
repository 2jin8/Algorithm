import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String str = sc.nextLine();
			StringBuilder sb = new StringBuilder();
			char first = str.charAt(0);
			sb.append(first);
			for (int i=1; i<10; i++) {
				char c = str.charAt(i);
				if (first == c) {
					String word = str.substring(i, 2*i);
					if (sb.toString().equals(word)) break;
					else sb.append(c);
				} else {
					sb.append(c);
				}
			}
			System.out.println("#"+test_case+" "+(sb.toString()).length());
		}
	}
}