import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int total = 0;
		while (n > 0) {
			if (n % 5 == 0) {
				n -= 5;
				total++;
			}
			else if (n % 3 == 0) {
				n -= 3;
				total++;
			}
			else if (n - 5 >= 0) {
				n -= 5;
				total++;
			}
			else if (n - 3 >= 0) {
				n -= 3;
				total++;
			}
			else {
				System.out.println("-1");
				return;
			}
		}
	
		System.out.println(total);
	}
}
