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

def countVal( x, minVal, maxVal ):
    cnt = 0
    for i in x:
        if i>=minVal and i<=maxVal and i%5==0:
            cnt+=1
    return cnt

## Do NOT modify the following test code
if __name__ == '__main__':
    data = np.loadtxt('P1.txt')
    myMin = 25
    myMax = 66
    print "Problem 1:"
    print countVal(data, myMin, myMax)

