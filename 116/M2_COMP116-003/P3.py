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

def remove_duplicates( lis ):
    nodup=[]
    for i in lis:
        if i not in nodup:
            nodup.append(i)
    return nodup

## Do NOT modify the following test code
if __name__ == '__main__':
    L = [(1,2),3,'hello',3,(1),3.14,5,(1,2),'hello',(1,2),3,'python']
    L_unique = remove_duplicates(L)
    print 'Problem 3:'
    print L_unique

