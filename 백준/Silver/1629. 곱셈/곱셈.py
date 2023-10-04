import sys
input = sys.stdin.readline

def modular_exponentiation(base, exponent, modulus):
    result = 1
    base %= modulus

    while exponent > 0:
        if exponent % 2 == 1:
            result = (result * base) % modulus
        exponent //= 2
        base = (base * base) % modulus

    return result

A, B, C = map(int, input().split())
print(modular_exponentiation(A, B, C))