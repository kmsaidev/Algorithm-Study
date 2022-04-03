def solution(idx, time, pay, depth, list):
    if depth <= idx:
        payList.append(list[:])
        return
    for i in range(idx, depth):
        if i + time[i] <= depth:
            solution(i + time[i], time, pay, depth, list + [pay[i]])
        else:
            solution(i + time[i], time, pay, depth, list)

days = int(input())

global payList
time = []
pay = []
for i in range(days):
    t, p = map(int, input().split())
    time.append(t)
    pay.append(p)
payList = []
solution(0, time, pay, days, [])
result = 0
for i in range(len(payList)):
    sum = 0
    for j in range(len(payList[i])):
        sum += payList[i][j]
    result = max(result, sum)
print(result)