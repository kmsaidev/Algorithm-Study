def permutation(idx, list, depth, op_list, num_list):
    if idx == depth:
        result = num_list[0]
        for i in range(len(list)):
            if list[i] == '+':
                result += num_list[i + 1]
            elif list[i] == '-':
                result -= num_list[i + 1]
            elif list[i] == '*':
                result *= num_list[i + 1]
            elif list[i] == '/':
                if result > 0:
                    result //= num_list[i + 1]
                else:
                    result = -result // num_list[i + 1]
                    result = -result
        result_list.append(result)
        return
    for i in range(len(op_list)):
        permutation(idx + 1, list + [op_list[i]], depth, op_list[:i]+ op_list[i + 1:], num_list)
                

global result_list
n = int(input())
num_list = list(map(int, input().split()))
add, sub, mul, div = map(int, input().split())

op_list = ['+'] * add + ['-'] * sub + ['*'] * mul + ['/'] * div
result_list = []
permutation(0, [], len(op_list), op_list, num_list)
print(max(result_list))
print(min(result_list))