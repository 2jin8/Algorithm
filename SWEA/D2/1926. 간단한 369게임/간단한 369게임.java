import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			 String num = String.valueOf(i);
			 if (!num.contains("3") && !num.contains("6") && !num.contains("9")) {
				 sb.append(i + " ");
			 }
			 else {
				 int cnt = 0;
				 for (int j=0; j<num.length(); j++) {
					 char c = num.charAt(j);
					 if (c == '3' || c == '6' || c == '9') cnt++;
				 }
				 for (int j=0; j<cnt; j++) {
					 sb.append("-");
				 }
				 sb.append(" ");
			 }
		}
		System.out.println(sb.toString());
	}
}