import java.util.Scanner;
import java.util.StringTokenizer;

class Solution
{
	static Doc[] docs;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());


		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = Integer.parseInt(sc.nextLine());
			docs = new Doc[n];
			for (int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				String a = st.nextToken();
				int c = Integer.parseInt(st.nextToken());
				docs[i] = new Doc(a, c); 
			}
			
			StringBuilder sb = new StringBuilder();
			int width = 0;
			for (int i=0; i<n; i++) {
				String alphabet = docs[i].alphabet;
				int count = docs[i].count;
				for (int j=0; j<count; j++) {
					width++;
					sb.append(alphabet);
					if (width == 10) {
						sb.append("\n");
						width = 0;
					}
				}
			}
			System.out.println("#"+test_case);
			System.out.println(sb.toString());
		}
	}
}

class Doc {
	String alphabet;
	int count;
	
	public Doc(String alphabet, int count) {
		this.alphabet = alphabet;
		this.count = count;
	}
}