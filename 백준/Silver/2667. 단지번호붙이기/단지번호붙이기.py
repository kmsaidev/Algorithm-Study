import sys
from collections import deque

def bfs(graph, visited, x, y):
    if [x, y] in visited:
        return 0
    visited.append([x, y])
    queue = deque([[x, y]])
    sum = 0
    
    while queue:
        move_x = [0, 0, -1, 1]
        move_y = [-1, 1, 0, 0]
        node = queue.popleft()
        sum += 1
        for i in range(4):
            new_x = node[0] + move_x[i]
            new_y = node[1] + move_y[i]
            if 0 <= new_x < len(graph) and 0 <= new_y < len(graph) and graph[new_x][new_y] == '1':
                if [new_x, new_y] not in visited:
                    queue.append([new_x, new_y])
                    visited.append([new_x, new_y])
    return sum    

read = sys.stdin.readline
n = int(input())
graph = []
visited = []
result = []
sum = 0
for _ in range(n):
    graph.append(list(read().strip()))
for i in range(n):
    for j in range(n):
        if graph[i][j] == '1':
            num = bfs(graph, visited, i, j)
            if num > 0:
                sum += 1
                result.append(num)        
print(sum)
result.sort()
for i in result:
    print(i)