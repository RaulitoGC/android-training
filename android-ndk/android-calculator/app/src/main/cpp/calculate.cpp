

#include <iostream>
#include <algorithm>
#include <stack>
#include <map>
#include <sstream>
#include <cctype>
#include <exception>

namespace {
    const std::map<char, int> operator_priority = {
            {'\n', -1},
            {'+',  0},
            {'-',  0},
            {'*',  1},
            {'/',  1},
            {'%',  1},
            {'^',  2},
    };

    int pow2(int x, int y) {
        if (x == 0 && y == 0) {
            throw std::invalid_argument("0 ^ 0 is invalid!");
        }

        int r = 1;
        for (int i = 0; i < y; ++i) {
            r *= x;
        }
        return r;
    }

    void merge_element(std::stack<int> &s, std::stack<char> &sop, char level) {
        char top_op;

        if (!sop.empty()) {
            while (top_op = sop.top(), (operator_priority.at(top_op) >
                                        operator_priority.at(level))) {
                sop.pop();
                int top_num = s.top();
                s.pop();
                switch (top_op) {
                    case '^':
                        top_num = pow2(s.top(), top_num);
                        s.pop();
                        s.push(top_num);
                        break;
                    case '*':
                        top_num = s.top() * top_num;
                        s.pop();
                        s.push(top_num);
                        break;
                    case '/':
                        if (top_num == 0) {
                            throw std::invalid_argument("can not divided by 0!");
                        }
                        top_num = s.top() / top_num;
                        s.pop();
                        s.push(top_num);
                        break;
                    case '%':
                        if (top_num == 0) {
                            throw std::invalid_argument("can not remained by 0!");
                        }
                        top_num = s.top() % top_num;
                        s.pop();
                        s.push(top_num);
                        break;
                    case '+':
                        top_num = s.top() + top_num;
                        s.pop();
                        s.push(top_num);
                        break;
                    case '-':
                        top_num = s.top() - top_num;
                        s.pop();
                        s.push(top_num);
                        break;

                }
            }
        }
    }

    int sub(const std::string &src) {
        std::stringstream ss;
        ss << src;

        int x;
        ss >> x;

        std::stack<int> s;
        s.push(x);
        std::stack<char> sop;
        sop.push('\n');
        int y;

        char op;
        while (ss >> op) {
            if (!(ss >> y)) {
                throw std::invalid_argument("an operator must be followed with a number!");
            }

            if (operator_priority.at(op) < operator_priority.at(sop.top())) {
                merge_element(s, sop, op);
            }
            sop.push(op);
            s.push(y);
        }
        merge_element(s, sop, '\n');
        return s.top();
    }
}

// This method will check the expression
int calculate_expression(std::string s) {
    std::stack<int> st;
    for (int i = 0; i < s.length(); ++i) {
        switch (s[i]) {
            case '(':
                if (s[i + 1] == ')') {
                    throw std::invalid_argument("'(' can not be followed with ')' immediately!");
                }
                if (std::isdigit(s[i - 1])) {
                    throw std::invalid_argument(
                            "a number can not be followed with '(' immediately!");
                }
                st.push(i);
                break;
            case ')':
                if (s[i + 1] == '(') {
                    throw std::invalid_argument("')' can not be followed with '(' immediately!");
                }

                if (st.empty()) {
                    throw std::invalid_argument("extra ')' appears in expression");
                }

                if (std::isdigit(s[i + 1])) {
                    throw std::invalid_argument(
                            "')' can not be followed with a number immediately");
                }

                int left = st.top();
                int right = i;

                std::string subr = std::to_string(
                        sub(std::string(s.begin() + left + 1, s.begin() + right)));
                s.replace(s.begin() + left, s.begin() + right + 1, subr);

                st.pop();
                i = left;
                break;
        }

    }
    if (!st.empty()) {
        throw std::invalid_argument("extra '(' appears in expression");
    }
    return sub(s);
}

