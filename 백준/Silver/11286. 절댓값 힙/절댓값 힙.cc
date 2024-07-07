#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int main()
{
    int N;
    priority_queue<int> pqp;
    priority_queue<int> pqm;
    vector<int> v;
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int x;
        cin >> x;
        if (x > 0)
        {
            pqp.push(-1*x);
        }
        else if (x < 0)
        {
            pqm.push(x);
        }
        else
        {
            if (!pqp.empty() && !pqm.empty())
            {
                if (-pqp.top() == -pqm.top())
                {
                    v.push_back(pqm.top());
                    pqm.pop();
                }
                else if (-pqp.top() > -pqm.top())
                {
                    v.push_back(pqm.top());
                    pqm.pop();
                }
                else
                {
                    v.push_back(-pqp.top());
                    pqp.pop();
                }
            }else{
                if(!pqp.empty()&&pqm.empty())
                {
                    v.push_back(-pqp.top());
                    pqp.pop();
                }else if(pqp.empty()&&!pqm.empty())
                {
                    v.push_back(pqm.top());
                    pqm.pop();
                }else{
                    v.push_back(0);
                }

            }
        }
    }
    for (int i = 0; i < v.size(); i++)
    {
        cout << v[i] << '\n';
    }
    return 0;
}