import java.util.Scanner;

class Main{


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0 ; i < n ; i ++){
            arr[i] = sc.nextInt();
        }

        int start = 0;
        int end = n-1;
        int temp = 0;
        int ans = 0;
        while(start <= end){
            temp = 0;
            // 값 저장
            for(int i = start; i < n; i++){
                temp += arr[i];

                //값이 m이랑 같으면 올리고 패스
                if(temp == m){
                    ans++;
                    break;
                }
                if(temp > m){
                    break;
                }
            }

            start++;
        }

        System.out.println(ans);


    }
}