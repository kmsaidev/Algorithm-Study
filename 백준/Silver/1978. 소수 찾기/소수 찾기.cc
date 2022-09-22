#include<stdio.h>


int main(){
    int n;
    int num;
    int count=0;
    scanf("%d",&n);
    int i;
    for(i=0; i<n; i++){
        scanf("%d",&num);
        if(num==1){
            count++;
        }
        for(int j=2; j<num; j++){
          if(num!=j && num%j==0){
              count++;
              break;
          }
      } 
    }
    printf("%d",n-count);
}
   