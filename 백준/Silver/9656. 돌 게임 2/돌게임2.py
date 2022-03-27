# 돌 게임
N = int(input())

n_3 = N//3
n_1 = N%3

if (n_1 + n_3) % 2 == 0 :
    print('SK')
else :
    print('CY')
