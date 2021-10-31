    /*     *******************************************************************************************************************************
          *    __________   _________    _________    ________                       ____________                       ___________     *
          *    |         |  |        |       |        |                  /\          |                      /\          |          |    *
          *    |         |  |        |       |        |                 /  \         |                     /  \         |          |    *
          *    |_________|  |________|       |        |_______         /    \        |                    /    \        |__________|    *
          *    |            |                |               |        /______\       |      _______      /______\       |       \       *
          *    |            |                |               |       /        \      |      |     |     /        \      |        \      *
          *    |            |                |               |      /          \     |      |     |    /          \     |         \     *
          *    |            |                |        _______|     /            \    |______|     |   /            \    |          \    *
          *                                                                                                                             *
          *******************************************************************************************************************************
    */

    #include<stdlib.h>
    #include<bits/stdc++.h>
    #include<math.h>
    #include<vector>
    #include<algorithm>
    #include<set>
    #include<math.h>
    #include<queue>
    #include<stack>
    #include <iostream>
    #include<string>
    #include<unordered_set>
    #define cn cout<<"NO\n"
    #define cy cout<<"YES\n"
    #define elif else if
    #define ll long long
    #define MOD 1000000007
    #define FASTIO(); ios::sync_with_stdio(0); cin.tie(0);
    #define pb push_back
    #define srt(V) sort(all(V))
    #define all(V) (V).begin(),(V).end()
    #define nl cout<<"\n"
    const long double PI=3.141592653589793238;


    /*
        sort(v.begin(), v.end());
        To insert at particular position in vector:
        auto it=vector_name(position,size,value);
        0 0 0 3 0 0 3 3 0 3 0 0 1 0 0 0 2 0 0
        ios_base::sync_with_stdio(false);(to turn off the sync between the iostream and stdio stream whuch takes the time )
        cin.tie(NULL)(for not flushing the output before input )
        for odd calculation a%2==a&1
        static array<int,4>;
    */
    
    using namespace std;

    /*    ll power (ll a, ll b)
        {
          ll result =1;
          while(b)
          {
            if(b&1)result=(result*a)%mode;
            a=(a*a)%mode;
            b/=2;
          }
          return result;
        }
    */
    /*            HASHING
        unordered_map<int,int>hash;
        hash[0];
        auto it=hash.begin();
        cout << it->first <<" " <<  it->second <<  endl;
    */

    /*
              TRIAL DIVISION -->Wheel Factorisation -->precomputed Factorisation;
    vector<ll>trial_division1(ll n){
      vector<ll>factorisation;
      for(ll d=2; d*d<=n; d++){
        while(n%d==0)
        {
          factorisation.push_back(d);
          n/=d;
        }
      }
      if(n>1){
        factorisation.push_back(n);
      }
      return factorisation;
    }*/
    /*unsigned long long fact(ll n)
    {
        if (n == 0)
            return 1;
        return (n * fact(n - 1))%mode;
    }*/
    bool isPrime(ll z)
    {
      if(z==1)return false;
      if(z==2 || z==3)return true;
      if(z%2==0 || z%3==0)return false;
      for(ll i=5; i*i<=z; i++)
        if(z%i==0 || z%(i+2)==0)
          return false;
      return true;
    }

    /* String to Int Transfromation OR Vice-Versa
        atoi,stoi;
        to_string();
    */
    /*

    ll powerexpo(ll n,ll temp)
    {
      ll z=1;
      while(temp)
      {
        z=(z*n)%MOD;
        temp--;
      }
      return z;
    }*/

    /*
    vector<ll>parent;
    vector<ll>ranki;

    void initialize(ll n)
    {
      for(ll i=1; i<=n; i++)
      {
        parent[i]=i;
        ranki[i]=1;
      }
      return;
    }

    ll finds(ll x)
    {
      if(parent[x]==x)
        return x;
      parent[x]=finds(parent[x]);
      return parent[x];
    }
    void unions(ll x, ll y)
    {
      ll x_rep=finds(x);
      ll y_rep = finds(y);
      if(x_rep==y_rep)return;
      if(ranki[x_rep]<ranki[y_rep]){
        parent[x_rep]=y_rep;
      }
      else if(ranki[x_rep]>ranki[y_rep]){
        parent[y_rep]=x_rep;
      }
      else 
      {
        parent[y_rep]=x_rep;
        ranki[x_rep]=ranki[x_rep]+ranki[y_rep];
      }
      return;
    }*/

    /*
    struct  myCmp{
      bool operator()(pair<ll,ll> const& a, pair<ll,ll> const& b){
        return a.second<b.second;}
    };*/
    /*
    void computeLPSArray(char* pat, int M, int* lps);
      
    // Prints occurrences of txt[] in pat[]
    vector<ll> KMPSearch(char* pat, char* txt)
    {
        vector<ll>v;
        int M = strlen(pat);
        int N = strlen(txt);
      
        // create lps[] that will hold the longest prefix suffix
        // values for pattern
        int lps[M];
      
        // Preprocess the pattern (calculate lps[] array)
        computeLPSArray(pat, M, lps);
      
        int i = 0; // index for txt[]
        int j = 0; // index for pat[]
        while (i < N) {
            if (pat[j] == txt[i]) {
                j++;
                i++;
            }
      
            if (j == M) {
                v.push_back(i-j);
                j = lps[j - 1];
            }
      
            // mismatch after j matches
            else if (i < N && pat[j] != txt[i]) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return v;
    }
      
    // Fills lps[] for given patttern pat[0..M-1]
    void computeLPSArray(char* pat, int M, int* lps)
    {
        // length of the previous longest prefix suffix
        int len = 0;
      
        lps[0] = 0; // lps[0] is always 0
      
        // the loop calculates lps[i] for i = 1 to M-1
        int i = 1;
        while (i < M) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
      
                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
    */ 
  /*
    int main()
    {
      // ios::sync_with_stdio(false);

      // cin.tie(NULL);
      ll t;
      cin >> t;
      while(t--)
      {
        ll k;
        cin >> k;
        ll temp=sqrt(k);

      }
      return 0;
    
ll computeXOR(ll n)
{
   
  // If n is a multiple of 4
  if (n % 4 == 0)
    return n;
 
  // If n%4 gives remainder 1
  if (n % 4 == 1)
    return 1;
 
  // If n%4 gives remainder 2
  if (n % 4 == 2)
    return n + 1;
 
  // If n%4 gives remainder 3
  return 0;
}
  */
  int main(){
      ll t=1;
      // cin >> t;
      while(t--){
       ll t = 1;
      cin >> t;
    while (t--)
    {
        
        ll k;
       cin>>k;
       ll z=8*k -3;
       ll a =sqrt(z);
       if(a%2==0) cout<<a<<endl;
       else cout<<a+1<<endl;
    }
      }
      return 0;
  }
