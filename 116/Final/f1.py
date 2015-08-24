#Palindrome without looping

def palindrome(string):
    s1=string
    s2=[]
    s2.append(s1)
    if s2[0][:]==s2[0][::-1]:
        return True
    else:
        return False

test_string1='yhrarhy'  #palindrome
test_string2='sdfahsdfla'  #not palindrome

print palindrome(test_string1)
print palindrome(test_string2)
