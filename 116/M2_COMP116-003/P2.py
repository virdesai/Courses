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

import math
import numpy as np

def estimate_e(tolerance):
    num_e=0
    last_term=np.inf
    k=0
    while last_term>tolerance:
        last_term = math.factorial(k)**-1
        num_e += last_term
        k += 1
    return num_e, k

## Do NOT modify the following test code
if __name__ == '__main__':
    tolerance = 1e-15
    e, n = estimate_e(tolerance)
    print 'Problem 2:'
    print 'e =', e
    print 'number of terms =',n
