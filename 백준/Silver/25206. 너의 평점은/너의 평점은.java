import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static HashMap<String, Double> grades = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		grades.put("A+", 4.5);
		grades.put("A0", 4.0);
		grades.put("B+", 3.5);
		grades.put("B0", 3.0);
		grades.put("C+", 2.5);
		grades.put("C0", 2.0);
		grades.put("D+", 1.5);
		grades.put("D0", 1.0);
		grades.put("F", 0.0);

		double sum = 0.0, totalScore = 0.0;
		for (int i = 0; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String subject = st.nextToken();
			double score = Double.parseDouble(st.nextToken());
			String grade = st.nextToken();
			if (!grade.equals("P")) {
				totalScore += score;
				sum += score * grades.get(grade);
			}
		}
		System.out.println(String.format("%.6f", sum / totalScore));
	}
}