import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int L, K, C;
    static int[] loc;
    static int[] len;
    static int targetSize, targetLoc;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        loc = new int[K + 1];
        len = new int[K + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++)
            loc[i] = Integer.parseInt(st.nextToken());
        loc[K] = L;
        Arrays.sort(loc);


        len[0] = loc[0];
        for(int i = 1; i <= K; i++)
            len[i] = loc[i] - loc[i - 1];
        solution();
        System.out.println(targetSize + " " + targetLoc);
    }

    static void solution(){
        int left = 1, right = L;
        while(left <= right) {
            int mid = (left + right) / 2;
            int answer = query(mid);
            if(answer == -1){
                left = mid + 1;
            }else{
                right = mid - 1;
                targetSize = mid;
                targetLoc = answer;
            }
        }
    }

    static int query(int minSize){
        int count = C;
        int sum = 0;
        for(int i = K; i >= 0; i--){
            if(len[i] > minSize)
                return -1;
            sum += len[i];
            if(sum > minSize){
                count--;
                sum = len[i];
            }
            if(count == 0){
                if (loc[i] > minSize)
                    return -1;
                else
                    return loc[i];
            }
        }
        return loc[0];
    }
}
