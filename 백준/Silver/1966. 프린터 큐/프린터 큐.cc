#define _CRT_SECURE_NO_WARNINGS
#define MAX_QUEUE_SIZE 110
#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int data;
	int flag;
} element;

typedef struct {
	element queue[MAX_QUEUE_SIZE];
	int front, rear;
}QueueType;

void error(char* message) {
	fprintf(stderr, "%s\n", message);
	exit(1);
}

void init(QueueType* q) {
	q->front = q->rear = 0;
}

int is_empty(QueueType* q) {
	return q->front == q->rear;
}

int is_full(QueueType* q) {
	return q->front == (q->rear + 1) % MAX_QUEUE_SIZE;
}

void enqueue(QueueType* q, element item) {
	if (is_full(q))
		error("큐가 포화상태입니다");
	q->rear = (q->rear + 1) % MAX_QUEUE_SIZE;
	q->queue[q->rear] = item;
}

element dequeue(QueueType* q) {
	if (is_empty(q))
		error("큐가 공백상태입니다");
	q->front = (q->front + 1) % MAX_QUEUE_SIZE;
	return q->queue[q->front];
}

element peek(QueueType* q) {
	if (is_empty(q))
		error("큐가 공백상태입니다");
	return q->queue[(q->front + 1) % MAX_QUEUE_SIZE];
}

int get_count(QueueType* q) {
	return (q->rear - q->front + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
}

int prec(QueueType* q) {
	int i, turn = (q->front + 1), first = peek(q).data;

	for (i = 0; i < get_count(q); i++) {
		if (turn >= MAX_QUEUE_SIZE)
			turn %= MAX_QUEUE_SIZE;
		if (q->queue[turn++].data > first)
			return 0;
	}
	return 1;
}

int main()
{
	QueueType q;
	element newItem;
	int n, i, j;
	int num, index,importance, flag;

	init(&q);
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%d %d", &num, &index);
		for (j = 0; j < num; j++) {
			if (j == index)
				flag = 1;
			else
				flag = 0;
			scanf("%d", &importance);
			newItem.data = importance;
			newItem.flag = flag;
			enqueue(&q, newItem);
		}
		while (1) {
			if (prec(&q) && peek(&q).flag) {
				dequeue(&q);
				printf("%d\n", num - get_count(&q));
				break;
			}
			else if (prec(&q))
				dequeue(&q);
			else 
				enqueue(&q, dequeue(&q));
		}
		while (!is_empty(&q))
			dequeue(&q);
	}
}