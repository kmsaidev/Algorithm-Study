#include<stdio.h>
#include<stdlib.h>

struct Tree{
    char data;
    struct Tree *left;
    struct Tree * right;
}typedef tree;

tree* createtree(char data);

tree* searchtree(tree *head, char data);

tree* inserttree(tree *head, char data, char l, char r);

void preorder(tree *head);

void inorder(tree *head);

void postorder(tree *head);

int main(){
    int num;
    scanf("%d", &num);
    char a,b,c;
    tree* head= NULL;
    for(int i=0; i<num; i++){
        getchar();
        scanf("%c %c %c", &a, &b, &c);
        head= inserttree(head, a, b, c);
    }
        preorder(head);
        printf("\n");
    inorder(head);
    printf("\n");
    postorder(head);
}


tree *createtree(char data){
    tree* new_tree=(tree*)malloc(sizeof(tree));
    new_tree->data=data;
    new_tree->left=NULL;
    new_tree->right=NULL;
    return new_tree;
}

tree *searchtree(tree* head, char data){
    tree* current = NULL;
    if(head == NULL){
        return NULL;
    }
    else if(head->data==data){
        return head;
    }
    else{
        current=searchtree(head->left, data);
        if(current!=NULL){
            return current;
        }
        else{
            return searchtree(head->right, data);
        }
    }
}

tree *inserttree(tree* head, char data, char l, char r){
    if(head==NULL){
        tree* new_tree= createtree(data);
        head=new_tree;
    }

    tree *current=searchtree(head, data);
    if(l!='.'){
        tree* leftnew_tree= createtree(l);
        current->left=leftnew_tree;
    }
    if(r!='.'){
        tree* rightnew_tree= createtree(r);
        current->right= rightnew_tree;
    }
    return head;
}

void preorder(tree* head){
    if(head!=NULL){
    printf("%c", head->data);
    preorder(head->left);
    preorder(head->right);
    }
}

void inorder(tree* head){
    if(head!=NULL){
        inorder(head->left);
        printf("%c", head->data);
        inorder(head->right);
    }
}

void postorder(tree* head){
    if(head!=NULL){
        postorder(head->left);
        postorder(head->right);
        printf("%c", head->data);
    }
}