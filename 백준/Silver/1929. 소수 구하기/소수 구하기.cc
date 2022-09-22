#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void sosu(int m, int n){
    int* check=(int*)malloc(sizeof(int)*n+5);
    memset(check, 0, sizeof(int)*n+5);
    for(int i=2; i<=n; i++){
        if(check[i]==0){
            for(int j=i+i; j<=n; j+=i){
                check[j]=1;
            }
        }
    }
    for(int i=m; i<=n; i++){
        if(check[i]==0 && i!=1){
            printf("%d\n", i);
        }
    }
}

int main(){
    int m, n;
    scanf("%d %d", &m, &n);
    sosu(m,n);
}