#######################################################################################
## This test is open-book. You may use your scripts, textbook, notes, assignments,   ##
## Python. You may not use anything else including search engines, on-line chatting, ##
## phones, etc. You may not discuss questions with anyone.                           ##
##                                                                                   ##
## By typing your name below, you indicate agreement with the UNC Honor Code Pledge, ##
## that you have not given or received unauthorized assistance on this exam.         ##
## Name:Vir Desai                                                                             ## 
#######################################################################################

## This script will run as is but, of course, it doesn't yet produce
## the desired results. Your mission is to modify it to get the outputs
## requested.
import numpy as np

def decode_char(char, shift):
    if char.islower():
        return chr(ord(char)-shift) # replace by your code
    elif char.isupper():
        return chr(ord(char)-shift) # replace by your code
    else:
        return char
		
def decode_string(s, shift):
    ret = ''
    for l in s:
        ret+=decode_char(l,shift)
    # Your code goes here
    return ret

## Do NOT modify the following test code
decode_char('h',3)
if __name__ == '__main__':
    s = 'Plvvloh 777 wr eh odxqfkhg dw ilyh HGW rq Pdufk 27!'
    shift = 3
    s_decoded = decode_string(s, shift)

    print 'Problem 4:'
    print s_decoded
	


