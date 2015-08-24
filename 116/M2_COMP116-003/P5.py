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
from PIL import Image

def applyFilter( data, kernel ):
    ret = np.zeros_like(data)
    for i in range(2,data.shape[0]-2):
        for j in range(2,data.shape[1]-2):
            size=data[i-2:i+3,j-2:j+3]
            ret[i,j]=np.sum(np.multiply(size,kernel))
    return ret

## Do NOT modify the following test code
if __name__ == '__main__':
	data = np.asarray(Image.open('P5.png'))
	kernel = np.loadtxt('P5.txt')
	result = applyFilter(data, kernel)
	resultIm = Image.fromarray(result).convert('L')
	resultIm.save('P5_result.png')


