import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int[] L;
    static int[] check;
    static int inf = Integer.MAX_VALUE;
    static int len = 0;
    static ArrayList<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nums = new int[N];
        L = new int[N];
        check = new int[N];
        answer = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            L[i] = inf;
        }

        for(int i = 0; i < N; i++) {
            int start = 0;
            int end = L.length - 1;
            while (true) {
                if(start == end){
                    if(L[start] == inf) {
                        len = Math.max(len, start);
                    }
                    L[start] = nums[i];
                    check[i] = start;
                    break;
                }
                int mid = (start + end) / 2;
                if (L[mid] >= nums[i]){
                    end = mid;
                } else{
                    start = mid + 1;
                }
            }
        }

        int target = len;
        len++;
        System.out.println(len);
        for(int i = N - 1; i >= 0; i--){
            if(target == check[i]){
                answer.add(nums[i]);
                target--;
            }
            if(target == -1)
                break;
        }
        for(int i = answer.size() - 1; i >= 0; i--)
            System.out.print(answer.get(i) + " ");
        System.out.println();
    }
}
