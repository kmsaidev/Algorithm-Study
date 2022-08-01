import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int num;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            if(pq1.size() == 0)
                pq1.add(num);
            else {
                if(pq1.peek() < num)
                    pq2.add(num);
                else pq1.add(num);
            }
            if(pq1.size() < pq2.size())
                pq1.add(pq2.poll());
            else if(pq1.size() - pq2.size() == 2)
                pq2.add(pq1.poll());

            if(pq2.size() == 0)
                sb.append(pq1.peek() + "\n");
            else{
                int num1 = pq1.peek();
                int num2 = pq2.peek();
                int mid = num1 < num2 ? num1 : num2;
                sb.append(mid + "\n");
            }
        }
        System.out.print(sb);
    }
}
