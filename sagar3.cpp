
#include <bits/stdc++.h>
#include <algorithm>
#include <set>
#include <math.h>
#include <vector>
#define int long long 
#define cn cout<<"NO\n"
#define cy cout<<"YES\n"
#define cm cout<<"-1\n"
#define vl vector<int>v
#define vi vector<int>
#define sl set<int>v
#define ff first
#define ss second
#define DBL double
#define LDBL long double
#define STR string
#define LTH length
#define sc set<char>v
#define elif else if
#define nl endl
#define pii pair<int,int>
#define MOD 1000000007
#define mode 1000000007
#define mo 10000005
using namespace std;
struct DSU{
    vi parent,sz;
    DSU (int v) {
        parent.clear();
        sz.clear();
        parent.resize(v);
        sz.resize(v);
        for(int i=0;i<v;i++){
            parent[i] = i;
            sz[i] = 1;
        }
    }
    int find_set(int v) {
        if (v == parent[v])
            return v;
        return parent[v] = find_set(parent[v]);
    }
    void union_sets(int a, int b) {
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (sz[a] < sz[b])
                swap(a, b);
            parent[b] = a;
            sz[a] += sz[b];
        }
    }
};
bool isPrime(int n)
{
    // Corner cases
    if (n <= 1)
        return false;
    if (n <= 3)
        return true;

    if (n % 2 == 0 || n % 3 == 0)
        return false;
  
    for (int i = 5; i * i <= n; i = i + 6)
        if (n % i == 0 || n % (i + 2) == 0)
            return false;
  
    return true;
}
int powerLL(int x, int n){
    int result = 1;
    while (n) {
        if (n & 1)
            result = result * x ;
        n = n / 2;
        x = x * x ;
    }
    return result;
}
int computeXOR(int n){
  if (n % 4 == 0)
    return n;
  if (n % 4 == 1)
    return 1;
  if (n % 4 == 2)
    return n + 1;
  return 0;
}
int facto(int n){
    int res = 1, i;
    for (i = 2; i <= n; i++)
        res =  (res*i)%mode;
    return res%mode;
}
bool cmp(pii &a , pii &b){
    return a.second<b.second;
}
int32_t main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int e;
    cin>>e;
    while(e--){
        int n;
        cin>>n;
        int a[n];
        for(int i=0;i<n;i++){
            cin>>a[i];
        }
        int mat[10000][n]={0};
        for(int j=0;j<10000;j++){
            int b[n+1]={0};
            for(int i=0;i<n;i++){
                b[a[i]]++;
            }
            for(int i=0;i<n;i++){
                mat[j][i]=a[i];
            }
            for(int i=0;i<n;i++){
                a[i]=b[a[i]];
            }
        }
        int q;
        cin>>q;
        while(q--){
            int x , y;
            cin>>x>>y;
            x--;
            if(y>10000){
                cout<<mat[10000-1][x]<<endl;
            }
            else
                cout<<mat[y][x]<<endl;
        }
    }
    return 0;
}
