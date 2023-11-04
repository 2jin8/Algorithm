import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
        }
        int[] increase = new int[n];
        int[] decrease = new int[n];
        increase[0] = 1; decrease[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                increase[i] = increase[i - 1] + 1;
            } else {
                increase[i] = 1;
            }
            if (nums[i] <= nums[i - 1]) {
                decrease[i] = decrease[i - 1] + 1;
            } else {
                decrease[i] = 1;
            }
        }
        int incMax = 0, decMax = 0;
        for (int i = 0; i < n; i++) {
            incMax = Math.max(incMax, increase[i]);
            decMax = Math.max(decMax, decrease[i]);
        }
        System.out.println(Math.max(incMax, decMax));
    }
}