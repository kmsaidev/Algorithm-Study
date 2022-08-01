import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static ArrayList<Integer> list1, list2;
    static int size1, size2;
    static int min = Integer.MAX_VALUE, count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            if(i % 2 == 0)
                list1.add(Integer.parseInt(st.nextToken()));
            else
                list2.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list1, Collections.reverseOrder());
        Collections.sort(list2);

        for(int i = 1; i <= H; i++){
            int start = 0;
            int end = N / 2 - 1;
            size1 = 0;
            while(true){
                if(start > end)
                    break;
                if(end > N / 2 - 1 || end < 0){
                    size1 = start;
                    break;
                }
                int mid = (start + end) / 2;
                if(list1.get(mid) >= i){
                    size1 = mid + 1;
                    start = mid + 1;
                } else{
                    end = mid - 1;
                }
            }
            start = 0;
            end = N / 2 - 1;
            size2 = 0;
            while(true){
                if(start > end)
                    break;
                if(end > N / 2 - 1 || end < 0){
                    size2 = list2.size() - start;
                    break;
                }
                int mid = (start + end) / 2;
                if(H - list2.get(mid) + 1 <= i){
                    size2 = list2.size() - mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if(min > size1 + size2){
                count = 1;
                min = size1 + size2;
            } else if(min == size1 + size2){
                count++;
            }
        }
        System.out.println(min + " " + count);
    }
}
