#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <limits.h>
#define TRUE 1
#define FALSE 0
#define MAX_VERTICES 101 /* 정점의수 */
#define INF 100000000 /* 무한대 (연결이 없는 경우) */

/* 네트워크의 인접행렬 */
typedef struct GraphType {
	int n;
	int weight[MAX_VERTICES][MAX_VERTICES];
} GraphType;

int A[MAX_VERTICES][MAX_VERTICES];

void insert_edge(GraphType* g, int start, int end, int weight) // 간선 삽입 연산
{
	if (start >= g->n || end >= g->n) {
		fprintf(stderr, "그래프: 정점 번호 오류");
		return;
	}
	if(g->weight[start][end] > weight)
		g->weight[start][end] = weight;
}

void graph_init(GraphType* g) // 그래프 초기화
{
	int r, c;
	g->n = 0;
	for (r = 0; r < MAX_VERTICES; r++) {
		for (c = 0; c < MAX_VERTICES; c++) {
			if (r == c)
				g->weight[r][c] = 0;
			else
				g->weight[r][c] = INF;
		}
	}
}

void floyd(GraphType* g, int n)
{
	int i, j, k;

	for (i = 0; i < n; i++)
		for (j = 0; j < n; j++)
			A[i][j] = g->weight[i][j];

	for (k = 0; k < n; k++)
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				if (A[i][k] + A[k][j] < A[i][j])
					A[i][j] = A[i][k] + A[k][j];
}

GraphType g;

int main()
{
	int n, m, v1, v2, weight, i, j;

	graph_init(&g);

	scanf("%d", &n);
	g.n = n;
	scanf("%d", &m);
	for (i = 0; i < m; i++) {
		scanf("%d %d %d", &v1, &v2, &weight);
		insert_edge(&g, v1 - 1, v2 - 1, weight);
	}
	floyd(&g, n);

	for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++) {
			if (A[i][j] == INF)
				printf("0 ");
			else
				printf("%d ", A[i][j]);
		}
		printf("\n");
	}
}