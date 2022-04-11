from collections import deque
def move(board, cloud, visited, move_number, dist):
    move_x = [0, -1, -1, -1, 0, 1, 1, 1]
    move_y = [-1, -1, 0, 1, 1, 1, 0, -1]
    board_size = len(board)
    cloud_size = len(cloud)
    move_n = move_number - 1
    
    for i in range(cloud_size): 
        c = cloud.popleft()
        x = (c[0] + move_x[move_n] * dist) % board_size
        y = (c[1] + move_y[move_n] * dist) % board_size
        board[x][y] += 1
        visited[x][y] = 1
        cloud.append([x, y])        
    
    for i in range(cloud_size):
        cnt = 0
        c = cloud.popleft()
        x, y = c[0], c[1]
        for j in range(1, 8, 2):
            copy_x = x + move_x[j]
            copy_y = y + move_y[j]
            if 0 <= copy_x < board_size and 0 <= copy_y < board_size and board[copy_x][copy_y] > 0:
                cnt += 1
        board[x][y] += cnt
        
n, m = map(int, input().split())
board = []
cloud = deque([[n - 2, 0], [n - 2, 1], [n - 1, 0], [n - 1, 1]])

for i in range(n):
    board.append(list(map(int, input().split())))
for _ in range(m):
    length = len(cloud)
    s, d = map(int, input().split())
    visited = [[0] * n for _ in range(n)]
    move(board, cloud, visited, s, d)
    for i in range(n):
        for j in range(n):
            if board[i][j] >= 2 and visited[i][j] == 0:
                cloud.append([i, j])
                board[i][j] -= 2
result = 0
for i in range(n):
    result += sum(board[i])
print(result)