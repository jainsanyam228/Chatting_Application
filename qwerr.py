def main():
    n=int(input("size of array: "))
    a = list(map(int,input("array : ").strip().split()))[:n]
    x = {}
    max = len(a)
    for i in a:
        if not i in x:
            x[i] = True
        else:
            print("Repeated element: ", i)
    for i in range(1, max + 1):
        if not i in x:
            print("Missing element: ", i)
main()