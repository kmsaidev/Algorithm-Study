import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int result = 0;
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        int low = 0;
        int high = 0;
        int sum = A[0];

        while(true){
            if(sum < M) {
                sum += A[++high];
            }
            else if(sum == M){
                result++;
                sum -= A[low++];
            }
            else sum -= A[low++];
            if(high == N)
                break;
        }
        System.out.println(result);
    }
}
