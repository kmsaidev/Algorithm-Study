from collections import deque

def bfs(graph, startNode) : 
    visited = []
    que = deque()

    que.append(startNode) 
    
    while(que) :
        node = que.popleft()
        if node not in visited :
            visited.append(node)
            for i in graph[node] :
                que.append(i)
    
    return visited

T = int(input())
answer = []
for i in range(T) :
    M, N, K = map(int, input().split())
    graph = dict()
    
    for j in range(K) :
        X, Y = map(int, input().split())
        graph[(X,Y)] = []
    
    for m in graph :
        for n in graph :
            if m == n :
                continue
            else :
                if (m[0] == n[0] and abs(m[1] - n[1]) == 1) or (m[1] == n[1] and abs(m[0] - n[0]) == 1) : # 상하좌우 인접
                    graph[m].append(n)

    nodes = []
    
    for k in graph :
        nodes.append(k)
    result = 0
    for k in graph :
        if k not in nodes :
            continue
        
        visits = bfs(graph, k)
        
        for m in visits :
            if m not in nodes :
                continue
            else :
                nodes.remove(m)
        result += 1
    
    answer.append(result)
    
for i in answer :
    print(i)


    
