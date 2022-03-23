from collections import deque
def dfs(graph, s, visited):
    visited.append(s)
    print(s, end=' ')
    
    for node in graph[s]:
        if node not in visited:
            dfs(graph, node, visited)

def bfs(graph, s, visited):
    queue = deque([s])
    visited[s] = True
    
    while queue:
        node = queue.popleft()
        print(node, end=' ')
        
        for i in graph[node]:
            if not visited[i]:
                visited[i] = True
                queue.append(i)
                
            
n, m, v = map(int, input().split())

graph = [[] for _ in range(n + 1)]
for i in range(m):
    n1, n2 = map(int, input().split())
    graph[n1].append(n2)
    graph[n2].append(n1)
visited = [False] * (n + 1)
for i in range(len(graph)):
    graph[i].sort()
        
dfs(graph, v, [])
print()
bfs(graph, v, visited)