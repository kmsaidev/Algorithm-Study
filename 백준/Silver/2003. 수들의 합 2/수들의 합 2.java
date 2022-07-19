import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        A = new int[N];
        int result = 0;
        for(int i = 0; i < N; i++)
            A[i] = sc.nextInt();

        int low = 0;
        int high = 1;
        int sum;

        while(high <= A.length){
            sum = 0;
            for(int i = low; i < high; i++)
                sum += A[i];
            if(sum < M)
                high++;
            else if(sum == M){
                result++;
                low++;
            }
            else low++;
        }

        System.out.println(result);
    }
}
