#include<stdio.h>
#include<stdlib.h>

int count(int n){
    int arr[12]={0,1,2,4,};
    for(int i=4; i<12; i++){
        arr[i]=arr[i-1]+arr[i-2]+arr[i-3];
    }
    return arr[n];
}

int main(){
    int n;
    scanf("%d", &n);
    int a;
    for(int i=0; i<n; i++){
        scanf("%d", &a);
        printf("%d\n", count(a));
    }
}