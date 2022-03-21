#define _CRT_SECURE_NO_WARNINGS 
#include <stdio.h>
#include <stdlib.h>
#define MAX_ELEMENT 100000
#define TRUE 1
#define FALSE 0

typedef struct {
	int key;
} element;

typedef struct {
	element heap[MAX_ELEMENT];
	int heap_size;
} HeapType;

// 초기화 함수
void init(HeapType* h)
{
	h->heap_size = 0;
}

// 삽입 함수: 현재 요소의 개수가 heap_size 인 히프 h 에 item 을 삽입한다.
void insert_min_heap(HeapType* h, element item)
{
	int i;
	i = ++(h->heap_size);

	// 트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (abs(item.key) <= abs(h->heap[i / 2].key))) {
		if (abs(item.key) == abs(h->heap[i / 2].key) && item.key > h->heap[i / 2].key)
			break;
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = item; // 새로운 노드를 삽입
}

// 삭제 함수
element delete_min_heap(HeapType* h)
{
	int parent, child;
	element item, temp;

	if (!h->heap_size) {
		item.key = 0;
		return item;
	}
	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;
	while (child <= h->heap_size) {
		// 현재 노드의 자식노드중 더 작은 자식노드를 찾는다.
		if ((child < h->heap_size) &&
			abs(h->heap[child].key) > abs(h->heap[child + 1].key))
			child++;
		else if ((child < h->heap_size) && abs(h->heap[child].key) == abs(h->heap[child + 1].key) &&
			(h->heap[child].key) > h->heap[child + 1].key)
			child++;
		if (abs(temp.key) < abs(h->heap[child].key)) break;
		else if (abs(temp.key) == abs(h->heap[child].key) && 
			temp.key < h->heap[child].key)
			break;
		// 한단계 아래로 이동
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}
	h->heap[parent] = temp;
	return item;
}

int main()
{
	HeapType heap; // 히프 생성
	element e;
	int n, i;
	int input;

	init(&heap); // 초기화
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%d", &input);
		if (input > 0 || input < 0) {
			e.key = input;
			insert_min_heap(&heap, e);
		}
		else
			printf("%d\n", delete_min_heap(&heap).key);
	}
}