#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define MAX_VERTICES 200
#define TRUE 1;
#define FALSE 0;

int visited[MAX_VERTICES]; // 전역변수는 0 으로 자동 초기화

typedef struct GraphType {
	int n;
	int adj_mat[MAX_VERTICES][MAX_VERTICES];
} GraphType;

void graph_init(GraphType* g) // 그래프 초기화
{
	int r, c;
	g->n = 0;
	for (r = 0; r < MAX_VERTICES; r++)
		for (c = 0; c < MAX_VERTICES; c++)
			g->adj_mat[r][c] = 0;
}

void insert_edge(GraphType* g, int start, int end) // 간선 삽입 연산
{
	if (start >= g->n || end >= g->n) {
		fprintf(stderr, "그래프: 정점 번호 오류");
		return;
	}
	g->adj_mat[start][end] = 1;
	g->adj_mat[end][start] = 1;
}

void insert_vertex(GraphType* g, int v) // 정점 삽입 연산
{
	if (((g->n) + 1) > MAX_VERTICES) {
		fprintf(stderr, "그래프 정점의 개수 초과");
		return;
	}
	g->n++;
}

void delete_edge(GraphType* g, int start, int end)
{
	if (start >= g->n || end >= g->n) {
		fprintf(stderr, "그래프: 정점 번호 오류");
		return;
	}

	if (g->adj_mat[start][end]) {
		g->adj_mat[start][end] = 0;
		g->adj_mat[end][start] = 0;
	}
}

int dfs_mat(GraphType* g, int v) // 깊이 우선 탐색
{
	int u;
	int cnt = 0;
	visited[v] = 1;
	for (u = 0; u < g->n; u++)
		if ((g->adj_mat[v][u] == 1) && (visited[u] == 0)) { // w 인접 정점이고 w 가 아직 방문되지
			cnt += 1 + dfs_mat(g, u); // 재귀 호출
		}
	return cnt;
}

int main()
{
	GraphType g;
	int n, pairs, i;
	int v1, v2;
	graph_init(&g);

	scanf("%d", &n);
	g.n = n;

	scanf("%d", &pairs);
	for (i = 0; i < pairs; i++) {
		scanf("%d %d", &v1, &v2);
		insert_edge(&g, v1 - 1, v2 - 1);
	}
	printf("%d", dfs_mat(&g, 0));
}