import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        len = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, len[i]);
        }

        int start = 0, end = max, mid, result = 0;
        long sum;
        while(true){
            if(start > end)
                break;
            sum = 0;
            mid = (start + end) / 2;

            for(int i = 0; i < N; i++) {
                int diff = len[i] - mid;
                if(diff >= 0)
                    sum += diff;
            }

            if(sum == M) {
                result = mid;
                break;
            } else if(sum > M){
                result = mid;
                start = mid + 1;
            } else
                end = mid - 1;
        }

        System.out.println(result);
    }
}
