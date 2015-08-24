#Palindrome without slicing

def palindrome(string):
    s1=string
    s2=[]
    s3=[]
    for i in range(len(s1)):
        s2.append(s1[i])
    for i in range(len(s1)-1,-1,-1):
        s3.append(s1[i])
    if s2==s3:
        return True
    else:
        return False

test_string1='yhrarhy'  #palindrome
test_string2='sjdfhaskdhf'  #not palindrome

print palindrome(test_string1)
print palindrome(test_string2)
