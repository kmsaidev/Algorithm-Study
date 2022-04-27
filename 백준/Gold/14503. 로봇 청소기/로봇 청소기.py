from collections import deque

def bfs(board, x, y, d, n, m):
    cnt = 1
    visited = []
    board[x][y] = -1
    queue = deque()
    queue.append([x, y])
    move_x = [-1, 0, 1, 0]
    move_y = [0, 1, 0, -1]
    
    while(queue):
        node = queue.popleft()
        d = (d - 1) % 4
        n_x = node[0] + move_x[d]
        n_y = node[1] + move_y[d]
        if 0 <= n_x < n and 0 <= n_y < m:
            if board[n_x][n_y] == 0 and [n_x, n_y] not in visited:
                cnt += 1
                queue.append([n_x, n_y])
                board[n_x][n_y] = -1
                visited.clear()
            elif board[n_x][n_y] != 0 and [n_x, n_y] not in visited: 
                queue.append(node)
                visited.append([n_x, n_y])
            elif [n_x, n_y] in visited:
                d = (d + 1) % 4
                back = (d - 2) % 4
                b_x = node[0] + move_x[back]
                b_y = node[1] + move_y[back]
                if 0 <= b_x < n and 0 <= b_y < m and board[b_x][b_y] == 0:
                    cnt += 1
                    queue.append([b_x, b_y])
                    queue[b_x][b_y] = -1
                elif 0 <= b_x < n and 0 <= b_y < m and board[b_x][b_y] == -1:
                    queue.append([b_x, b_y])
                else:
                    break
                visited.clear()
    return cnt
                
n, m = map(int , input().split())
board = []

r, c, d = map(int, input().split())

for i in range(n):
    board.append(list(map(int, input().split())))
print(bfs(board, r, c, d, n, m))    