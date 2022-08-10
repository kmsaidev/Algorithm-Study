import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, K, C;
    static int[] cut, diff;
    static int resultLen, resultPos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cut = new int[K + 1];
        diff = new int[K + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++)
            cut[i] = Integer.parseInt(st.nextToken());
        cut[K] = L;
        Arrays.sort(cut);
        diff[0] = cut[0];
        for(int i = 1; i <= K; i++)
            diff[i] = cut[i] - cut[i - 1];

        search();
        System.out.println(resultLen + " " + resultPos);
    }

    static void search(){
        int left = 1;
        int right = L;
        while(left <= right){
            int mid = (left + right) / 2;
            int check = isPossible(mid);
            if(check != -1) {
                resultLen = mid;
                resultPos = check;
                right = mid - 1;
            } else left = mid + 1;
        }
    }

    static int isPossible(int len){
        int sum = 0;
        int count = C;
        for(int i = K; i >= 0; i--){
            if(diff[i] > len) return -1;
            sum += diff[i];

            if(sum > len){
                count--;
                sum = diff[i];
            }
            if(count == 0){
                if(cut[i] > len) return -1;
                else return cut[i];
            }
        }
        return cut[0];
    }
}
