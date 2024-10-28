import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static String check = "gori";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		System.out.println(str.contains(check) ? "YES" : "NO");
	}
}