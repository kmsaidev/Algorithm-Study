#include<stdio.h>
#define MAX_SIZE 1001

int n, m, v;
int dfs_check[MAX_SIZE];
int bfs_check[MAX_SIZE];
int arr[MAX_SIZE][MAX_SIZE];
int queue[MAX_SIZE];
int back_pointer=0;
int front_pointer=0;

int size(){
    return back_pointer-front_pointer;
}

void push(int item){
    if(size()==MAX_SIZE){
        return;
    }
    else{
        queue[back_pointer++]=item;
        return;
    }
}

void pop(){
    if(size()==0){
        return;
    }
    else{
        front_pointer++;
        return;
    }
}

int empty(){
    if(size()==0){
        return 1;
    }
    else{
        return 0;
    }
}

int front(){
    if(size()==0){
        return -1;
    }
    else{
        return queue[front_pointer];
    }
}

int back(){
    if(size()==0){
        return -1;
    }
    else{
        return queue[back_pointer-1];
    }
}



void dfs(int start){
    printf("%d ", start);
    dfs_check[start]=1;
    for(int i=1; i<=n; i++){
        if(arr[start][i]==1 && dfs_check[i]!=1){
            dfs(i);
        }
    }
    return;
}

void bfs(int start){
    push(start);
    bfs_check[start]=1;
    while(empty()==0){
        int current=front();
        for(int i=1; i<=n; i++){
            if(arr[current][i]==1 && bfs_check[i]!=1){
                bfs_check[i]=1;
                push(i);
            }
        }
        printf("%d ", current);
        pop();
    }
}

int main(){
    scanf("%d %d %d",&n, &m, &v);
    for(int i=0; i<m; i++){
        int link_a, link_b;
        scanf("%d %d", &link_a, &link_b);
        arr[link_a][link_b]=1;
        arr[link_b][link_a]=1;
    }
    dfs(v);
    printf("\n");
    bfs(v);
}