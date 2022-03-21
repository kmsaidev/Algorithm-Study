#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 10001
#define INF 9999
#define MAX_ELEMENT 100001
#define TRUE 1
#define FALSE 0

typedef struct {
	int key;	// 간선의 가중치
	int u;		// 정점 1
	int v;		// 정점 2
} element;

typedef struct {
	element heap[MAX_ELEMENT];
	int heap_size;
} HeapType;// 히프의 요소 타입 정의

typedef struct GraphNode {
	int vertex;
	int weight;
	struct GraphNode* link;
} GraphNode;

typedef struct GraphType {
	int n; // 정점의 개수
	GraphNode* adj_list[MAX_VERTICES];
} GraphType;

int parent[MAX_VERTICES];		// 부모 노드의 index를 저장; 집합의 대표 원소에 대해서는 -(그 집합에 속한 원소의 갯수)를 저장한다.
int num[MAX_VERTICES];		// 각 집합의 크기 ==> 필요 없음

// 초기화
void set_init(int n)
{
	int i;
	for (i = 0; i < n; i++) {
		parent[i] = -1;
		//		num[i] = 1;
	}
}

int set_find(int vertex)
{
	int p, s, i = -1;
	for (i = vertex; (p = parent[i]) >= 0; i = p);// 루트 노드까지 반복
	s = i;			// 집합의 대표 원소
	for (i = vertex; (p = parent[i]) >= 0; i = p)
		parent[i] = s;	// 집합의 모든 원소들의 부모를 p로 설정
	return s;
}
// 두개의 원소가 속한 집합을 합친다.
void set_union(int s1, int s2)
{
	if (num[s1] < num[s2]) {
		parent[s1] = s2;
		num[s2] += num[s1];
	}
	else {
		parent[s2] = s1;
		num[s1] += num[s2];
	}
}

// 초기화 함수
void init(HeapType* h)
{
	h->heap_size = 0;
}
//
int is_empty(HeapType* h)
{
	if (h->heap_size == 0)
		return TRUE;
	else
		return FALSE;
}
// 삽입 함수
void insert_min_heap(HeapType* h, element item)
{
	int i;
	i = ++(h->heap_size);

	//  트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (item.key < h->heap[i / 2].key)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = item;     // 새로운 노드를 삽입
}
// 삭제 함수
element delete_min_heap(HeapType* h)
{
	int parent, child;
	element item, temp;

	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;
	while (child <= h->heap_size) {
		// 현재 노드의 자식노드중 더 작은 자식노드를 찾는다.
		if ((child < h->heap_size) &&
			(h->heap[child].key) > h->heap[child + 1].key)
			child++;
		if (temp.key <= h->heap[child].key) break;
		// 한단계 아래로 이동
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}
	h->heap[parent] = temp;
	return item;
}

// 그래프 초기화 
void graph_init(GraphType* g)
{
	int i;
	g->n = 0;
	for (i = 0; i < MAX_VERTICES; i++)
		for (GraphNode* node = g->adj_list[i]; node != NULL; node = node->link)
			node->weight = INF;
}

void insert_vertex(GraphType* g, int v) // 정점 삽입 연산
{
	if (((g->n) + 1) > MAX_VERTICES) {
		fprintf(stderr, "그래프 정점의 개수 초과");
		return;
	}
	g->n++;
}

// 간선 삽입 연산, v 를 u 의 인접 리스트에 삽입한다.
void insert_edge(GraphType* g, int u, int v, int weight)
{
	GraphNode* node;
	if (u >= g->n || v >= g->n) {
		fprintf(stderr, "그래프: 정점 번호 오류");
		return;
	}
	// u 에 v 를 매단다
	node = (GraphNode*)malloc(sizeof(GraphNode));
	node->vertex = v;
	node->link = g->adj_list[u];
	node->weight = weight;
	g->adj_list[u] = node;
	// v 에 u 를 매단다
	node = (GraphNode*)malloc(sizeof(GraphNode));
	node->vertex = u;
	node->link = g->adj_list[v];
	node->weight = weight;
	g->adj_list[v] = node;
}

// 인접 행렬이나 인접 리스트에서 간선들을 읽어서 최소 히프에 삽입 
// 현재는 예제 그래프의 간선들을 삽입한다.
void insert_all_edges(HeapType* h, GraphType* g)
{
	// 구현
	element item;
	int i, j;

	for (i = 0; i < g->n; i++) {
		for (GraphNode* p = g->adj_list[i]; p != NULL; p = p->link) {
			if (p->vertex > i) {
				item.key = p->weight;
				item.u = i;
				item.v = p->vertex;
				insert_min_heap(h, item);
			}
		}
	}
}

// kruskal의 최소 비용 신장 트리 프로그램
int kruskal(GraphType* g)
{
	// 구현
	int edge_accepted = 0; // 현재까지 선택된 간선의 수
	HeapType h; // 최소 히프
	int uset, vset; // 정점 u 와 정점 v 의 집합 번호
	element e; // 히프 요소
	int result = 0;

	init(&h); // 히프 초기화
	insert_all_edges(&h, g); // 히프에 간선들을 삽입
	set_init(g->n); // 집합 초기화

	while (edge_accepted < (g->n - 1)) // 간선의 수 < (n-1)
	{
		e = delete_min_heap(&h); // 최소 히프에서 삭제: 가장 key(weight)가 작은 간선
		uset = set_find(e.u); // 정점 u 의 집합 번호
		vset = set_find(e.v); // 정점 v 의 집합 번호
		if (uset != vset) { // 서로 속한 집합이 다르면, 즉 사이클이 생기지 않으면
			result += e.key;
			edge_accepted++;
			set_union(uset, vset); // 두개의 집합을 합친다.
		}
	}
	return result;
}

GraphType g;

int main()
{
	int n, pairs, v1, v2, weight, i;

	graph_init(&g);

	scanf("%d %d", &n, &pairs);
	g.n = n;
	for (i = 0; i < pairs; i++) {
		scanf("%d %d %d", &v1, &v2, &weight);
		insert_edge(&g, v1 - 1, v2 - 1, weight);
	}

	printf("%d", kruskal(&g));
}