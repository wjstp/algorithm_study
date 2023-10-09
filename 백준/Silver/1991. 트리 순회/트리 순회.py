import sys
input = sys.stdin.readline

def preorder(node):
    global pre_trv
    if node > 0:
        pre_trv += chr(node+64)
        preorder(ch1[node]-64)
        preorder(ch2[node]-64)
        
def inorder(node):
    global in_trv
    if node > 0:
        inorder(ch1[node]-64)
        in_trv += chr(node+64)
        inorder(ch2[node]-64)
        
def postorder(node):
    global post_trv
    if node > 0:
        postorder(ch1[node]-64)
        postorder(ch2[node]-64)
        post_trv += chr(node+64)

N = int(input().rstrip())
ch1 = [0]*27 # A~Z : 65~90
ch2 = [0]*27
for _ in range(N):
    pnode, lnode, rnode = input().split()
    idx = ord(pnode)-64 # 0번지 안씀
    if lnode != '.':
        ch1[idx] = ord(lnode)
    if rnode != '.':
        ch2[idx] = ord(rnode)

pre_trv =''
in_trv = ''
post_trv = ''
preorder(ord('A')-64)
inorder(ord('A')-64)
postorder(ord('A')-64)

print(pre_trv)
print(in_trv)
print(post_trv)
