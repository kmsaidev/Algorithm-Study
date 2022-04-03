global pay_list
pay_list = []
def solution(idx, time, pay, depth, sum):
    if depth <= idx:
        pay_list.append(sum)
        return 
    for i in range(idx, depth):
        if i + time[i] <= depth:
            solution(i + time[i], time, pay, depth, sum + pay[i])
        else:
            solution(i + time[i], time, pay, depth, sum)

days = int(input())

time = []
pay = []
for i in range(days):
    t, p = map(int, input().split())
    time.append(t)
    pay.append(p)
    
solution(0, time, pay, days, 0)
result = 0
for i in range(len(pay_list)):
    result = max(result, pay_list[i])
print(result)
