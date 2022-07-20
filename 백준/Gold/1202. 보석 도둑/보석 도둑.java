import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, K;
    static ArrayList<Jewelry> jList;
    static ArrayList<Integer> bList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jList = new ArrayList<>();
        bList = new ArrayList<>();
        PriorityQueue<Jewelry> pq = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.price - o1.price;
            }
        });

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            jList.add(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            bList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(jList, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o1.weight - o2.weight;
            }
        });
        Collections.sort(bList);

        int jPoint = 0;
        long result = 0;
        for(int i = 0; i < bList.size(); i++) {
            int bagWeight = bList.get(i);
            while (jPoint < jList.size()) {
                Jewelry j = jList.get(jPoint);
                if(j.weight <= bagWeight) {
                    pq.add(j);
                    jPoint++;
                } else
                    break;
            }
            if(!pq.isEmpty()) {
                Jewelry top = pq.poll();
                result += top.price;
            }
        }

        System.out.println(result);
    }
}

class Jewelry{
    int weight;
    int price;

    public Jewelry(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}
