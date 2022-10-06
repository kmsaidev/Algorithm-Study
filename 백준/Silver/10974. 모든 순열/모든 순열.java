import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[] bucket = new int[N];
        solution(N, bucket, N, N);
    }

    static void solution(int n, int[] bucket, int bucketSize, int k){
        if(k == 0){
            for(int i = 0; i < bucketSize; i++)
                System.out.print(bucket[i] + " ");
            System.out.println();
            return;
        }
        int lastIndex = bucketSize - k - 1;
        int smallest = 1;
        boolean chosen;
        for(int item = smallest; item <= n; item++){
            chosen = false;
            for(int i = 0; i <= lastIndex; i++){
                if(item == bucket[i]){
                    chosen = true;
                    break;
                }
            }
            if(!chosen){
                bucket[lastIndex + 1] = item;
                solution(n, bucket, bucketSize, k - 1);
            }
        }

    }
}
