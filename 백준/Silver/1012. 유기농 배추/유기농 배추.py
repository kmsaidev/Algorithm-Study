from collections import deque
def bfs(graph, visited, x, y, col):
    if [x, y] in visited:
        return 0
    visited.append([x, y])
    queue = deque()
    queue.append([x, y])
    
    while queue:
        move_x = [0, 0, -1, 1]
        move_y = [-1, 1, 0, 0]
        node = queue.popleft()
        for i in range(4):
            new_x = node[0] + move_x[i]
            new_y = node[1] + move_y[i]
            if 0 <= new_x < len(graph) and 0 <= new_y < col and graph[new_x][new_y]:
                if [new_x, new_y] not in visited:
                    queue.append([new_x, new_y])
                    visited.append([new_x, new_y])
    return 1     
                    
n = int(input())
for i in range(n):
    c, r, k = map(int, input().split())
    graph = [([0] * c) for _ in range(r)]
    visited = list()
    sum = 0

    for _ in range(k):
        x, y = map(int, input().split())
        graph[y][x] = 1

    for i in range(len(graph)):
        for j in range(len(graph[i])):
            if graph[i][j]:
                sum += bfs(graph, visited, i, j, c)
    print(sum)