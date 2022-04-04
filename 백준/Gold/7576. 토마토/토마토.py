import sys
from collections import deque
def bfs(graph, col):
    queue = deque()
    n = len(graph)
    m = col
    dist = [[0] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1:
                queue.append([i, j])
                dist[i][j] = 0
    day_cnt = 0
    
    while queue:
        move_x = [0, 0, -1, 1]
        move_y = [-1, 1, 0, 0]
        node = queue.popleft()
        for i in range(4):
            new_x = node[0] + move_x[i]
            new_y = node[1] + move_y[i]
            if 0 <= new_x < n and 0 <= new_y < m and graph[new_x][new_y] == 0:
                queue.append([new_x, new_y])
                graph[new_x][new_y] = 1
                dist[new_x][new_y] = dist[node[0]][node[1]] + 1
                day_cnt = max(day_cnt, dist[new_x][new_y])
    for i in range(n):
        if 0 in graph[i]:
            day_cnt = -1
            break
    return day_cnt

day_cnt = 0
n, m = map(int, sys.stdin.readline().split())
box = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]
day_cnt = bfs(box, n)
print(day_cnt)