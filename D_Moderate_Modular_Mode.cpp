#include<bits/stdc++.h>
#include<string>
#include <algorithm>
#include <set>
#include <cmath>
#include <vector>
#define ll long long
#define repi(i,a,b) for(ll i=a;i<=b;i++)
#define repd(i,a,b) for(ll i=a;i>=b;i--)
#define cy cout<<"YES\n"
#define cn cout<<"NO\n"
#define MOD 1000000007
#define vi vector<int>
#define vl vector<ll>
#define pb push_back
#define nl cout<<"\n"
#define cm cout<<"-1\n"
using namespace std;

int main(){
    ll t;
    cin>>t;
    while(t--){
        ll x,y;
        cin>>x>>y;
        if(x==y) cout<<x<<endl;
        else if(x>y) cout<<x+y<<endl;
        else{
            ll k=y%x;
            ll o=y-(k/2);
            cout<<o; nl;
        }
    }
    return 0;
}