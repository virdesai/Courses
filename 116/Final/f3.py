#Pangram

def pangram(string):
    s1=string
    s1=''.join(s1.split()).lower()
    finalstring=''
    alpha=dict()
    for i in range(ord('a'),ord('z')+1):
        alpha[i]=(chr(i))
    for i in s1:
        if ord(i) in alpha.keys():
            del alpha[ord(i)]
    for i in alpha.keys():
        finalstring=finalstring+alpha[i]
    return finalstring

test_string1='The quick brown fox jumps over the lazy dog'  #has all alphabet characters
test_string2='This is not a working pangram it is just a random statement'  #doesn't have all alphabet characters

print pangram(test_string1)
print pangram(test_string2)

    
