from collections import deque

n, m, v = map(int, input().split())
graph = [[]for _ in range(n+1)]

for i in range(m) :
    node1, node2 = map(int, input().split())
    graph[node1].append(node2)
    graph[node2].append(node1)

def dfs(graph, startNode) :
    visited = list()
    stack = deque()
    
    stack.append(startNode)
    
    while(stack) :
        node = stack.pop()
        if node not in visited :
            visited.append(node)
            
            tmp_lst = graph[node] # 숫자가 큰 순서대로 넣어야함.
            tmp_lst = sorted(tmp_lst, reverse = True)
            for i in tmp_lst :
                stack.append(i)
    return visited

def bfs(graph, startNode) :
    visited = list()
    que = deque()
    
    que.append(startNode)

    while(que) :
        node = que.popleft()
        if node not in visited :
            visited.append(node)

            tmp_lst = graph[node] # 숫자가 작은 순서대로 넣어야함
            tmp_lst = sorted(tmp_lst)
            for i in tmp_lst :
                que.append(i)
    return visited

def print_answer(lst) :
    for i in lst :
        if i == lst[-1] :
            print(i)
        else :
            print(i, end=' ')

print_answer(dfs(graph, v))
print_answer(bfs(graph, v))





