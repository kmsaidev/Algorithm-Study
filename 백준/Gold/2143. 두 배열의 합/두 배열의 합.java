import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static int[] arrA, arrB;
    static ArrayList<Integer> subA, subB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arrA = new int[N + 1];
        subA = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arrA[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        arrB = new int[M + 1];
        subB = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++)
            arrB[i] = Integer.parseInt(st.nextToken());

        getSubArray(arrA, N, subA);
        getSubArray(arrB, M, subB);

        Collections.sort(subA);
        Collections.sort(subB, Collections.reverseOrder());

        System.out.println(getCount());
    }

    static void getSubArray(int[] arr, int arrSize, ArrayList<Integer> sub){
        int low = 0, high = 0, sum = arr[0];
        while(true){
            if(high != arrSize) {
                sub.add(sum);
                sum += arr[++high];
            } else{
                sum = 0;
                sum += arr[++low];
                high = low;
            }
            if(low == arrSize)
                break;
        }
    }

    static long getCount(){
        int p1 = 0, p2 = 0;
        long count = 0;
        long sCountA, sCountB;

        while(true){
            if(p1 == subA.size() || p2 == subB.size())
                break;
            int a = subA.get(p1);
            int b = subB.get(p2);

            if(T == a + b){
                sCountA = 1;
                sCountB = 1;
                while(true){
                    if(p1 + 1 == subA.size()) {
                        p1++;
                        break;
                    }
                    int new_a = subA.get(p1 + 1);
                    if(new_a != a){
                        p1++;
                        break;
                    } else {
                        p1++;
                        sCountA++;
                    }
                }
                while(true){
                    if(p2 + 1 == subB.size()) {
                        p2++;
                        break;
                    }
                    int new_b = subB.get(p2 + 1);
                    if(new_b != b){
                        p2++;
                        break;
                    } else {
                        p2++;
                        sCountB++;
                    }
                }
                count += sCountA * sCountB;
            } else if(T > a + b){
                p1++;
            } else{
                p2++;
            }
        }
        return count;
    }
}
