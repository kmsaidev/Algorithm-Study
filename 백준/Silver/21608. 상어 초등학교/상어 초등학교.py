from collections import deque

move_x = [0, 0, -1, 1]
move_y = [-1, 1, 0, 0]
def set_student_seat(graph, student): 
    cnt_max_friend = 0
    cnt_max_empty = 0
    max_friend = [-1, -1, -1]
    max_empty = [-1, -1, -1]  
    for x in range(1, len(graph)):
        for y in range(1, len(graph)):
            if graph[x][y] == 0:
                friend = 0
                empty = 0
                for i in range(4):
                    new_x = x + move_x[i]
                    new_y = y + move_y[i]
                    if 1 <= new_x < len(graph) and 1 <= new_y < len(graph):
                        if graph[new_x][new_y] in favorite[student]:
                            friend += 1
                        if graph[new_x][new_y] == 0:
                            empty += 1
                if friend > max_friend[0]:
                    max_friend = [friend, x, y]
                    cnt_max_friend = 1
                    max_empty = [empty, x, y]
                    if empty > max_empty[0]:
                        cnt_max_empty = 1
                    elif empty == max_empty[0]:
                        cnt_max_empty += 1
                elif friend == max_friend[0]:
                    cnt_max_friend += 1
                    if empty > max_empty[0]:
                        max_empty = [empty, x, y]
                        cnt_max_empty = 1
                    elif empty == max_empty[0]:
                        cnt_max_empty += 1
                        
    if cnt_max_friend == 1:
        graph[max_friend[1]][max_friend[2]] = student
    else:
        graph[max_empty[1]][max_empty[2]] = student
        
def get_score(graph, favorite):
    totalScore = 0
    for x in range(1, len(graph)):
        for y in range(1, len(graph)):
            friend = 0
            for i in range(4):
                new_x = x + move_x[i]
                new_y = y + move_y[i]
                if 1 <= new_x < len(graph) and 1 <= new_y < len(graph) and graph[new_x][new_y] in favorite[graph[x][y]]:
                    friend += 1                
            if friend == 4:
                totalScore += 1000
            elif friend == 3:
                totalScore += 100
            elif friend == 2:
                totalScore += 10
            elif friend == 1:
                totalScore += 1   
    return totalScore 
    

n = int(input())
graph = [([0] * (n + 1)) for _ in range(n + 1)]
favorite = [[] for _ in range(n * n + 1)]

for _ in range(n * n):
    student, f1, f2, f3, f4 = map(int, input().split())
    favorite[student].append(f1)
    favorite[student].append(f2)
    favorite[student].append(f3)
    favorite[student].append(f4)
    set_student_seat(graph, student)
print(get_score(graph, favorite))