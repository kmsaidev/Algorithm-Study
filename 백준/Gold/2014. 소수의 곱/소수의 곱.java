import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K, N;
    static long[] nums;
    static HashSet<Long> set;
    static long max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>(){
            public int compare(Long o1, Long o2){
                return Long.compare(o1, o2);
            }
        });
        int count = 1;

        set = new HashSet<>();

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        nums = new long[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            pq.add(nums[i]);
            set.add(nums[i]);
        }
        max = nums[K - 1];

        while(count != N){
            long temp = pq.poll();
            for(int i = 0; i < K; i++){
                long next = temp * nums[i];
                if(next >= max && pq.size() >= N - count)
                    continue;
                if (!set.contains(next)){
                    pq.add(next);
                    set.add(next);
                    max = Math.max(max, next);
                }
            }
            count++;
        }
        System.out.println(pq.peek());
    }
}
