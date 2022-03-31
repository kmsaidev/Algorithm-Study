def comb(idx, list, depth, n):
    if len(list) == depth:
        track_list.append(list[:])
        return
    for i in range(idx, n):
        comb(i + 1, list + [i], depth, n)

def solution(arr):
    global track_list
    n = len(arr)
    min_dif = 10000000
    power = []
    index = []
                
    for i in range(0, n):
        for j in range(0, n):
            if i < j:
                power.append(arr[i][j] + arr[j][i])
                index.append([i, j])
    track_list = []
    comb(0, [], n / 2, n)
    for i in range(len(track_list) // 2):
        start = 0
        link = 0
        for j in range(len(index)):
            reverse = (len(track_list) - 1) - i
            if index[j][0] in track_list[i] and index[j][1] in track_list[i]:
                start += power[j]
            elif index[j][0] in track_list[reverse] and index[j][1] in track_list[reverse]:
                link += power[j]
        min_dif = min(min_dif, abs(start - link))
    
    return min_dif
                                                 
n = int(input())
arr = []

for i in range(0, n):
    arr.append(list(map(int, input().split())))
    
print(solution(arr))