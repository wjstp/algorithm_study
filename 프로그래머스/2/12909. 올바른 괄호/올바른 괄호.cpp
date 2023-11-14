#include <iostream>
#include <vector>
using namespace std;

bool solution(string s)
{
    bool answer = true;
    vector<char> temp;//임시로 문자열 받을 것
    //앞에서부터 하나씩 받고, )가 나올때마다 앞에서 (를 찾아서 pop_back을 시켜줌
    for(int i=0; i<s.size(); i++)
    {
        temp.push_back(s[i]);
        if(temp.back()==')')
        {
            for(int j=temp.size()-2; j>=0; j--)
            {
                if(temp[j]=='(')
                {
                    temp.pop_back();
                    temp.pop_back();
                    break;
                }
            }
        }
    }
    if(temp.empty())
    {
        answer=true;
    }
    else{
        answer=false;
    }

        

    return answer;
}