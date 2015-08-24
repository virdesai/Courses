#######################################################################################
## This test is open-book. You may use your scripts, textbook, notes, assignments,   ##
## Python. You may not use anything else including search engines, on-line chatting, ##
## phones, etc. You may not discuss questions with anyone.                           ##
##                                                                                   ##
## By typing your name below, you indicate agreement with the UNC Honor Code Pledge, ##
## that you have not given or received unauthorized assistance on this exam.         ##
## Name:                                                                             ## 
#######################################################################################

# This script is runnable, but it doesn't produce the desired results.
# Your mission is to modify it to get the outputs as requested.

import numpy as np
import pylab

# Load the Carolina and Opponent data
# Data separated by commas and the first row of description is skipped
# Do not change the following two lines
data_Carolina = np.loadtxt('Carolina.csv', delimiter=',',skiprows=1)
data_Opponent = np.loadtxt('Opponent.csv', delimiter=',',skiprows=1)

# Column indices in the data (for your convenience)
Indx2PM=0 # 2-point made
Indx2PA=1 # 2-point attempted
Indx3PM=2 # 3-point made
Indx3PA=3 # 3-point attempted
IndxFTM=4 # free throw made
IndxFTA=5 # free throw attempted
IndxOReb=6 # offensive rebound
IndxDReb=7 # defensive rebound
IndxPF=8 # personal foul
IndxAsst=9 # assist
IndxTO=10 # Turn over
IndxBlk=11 # Block
IndxSteal=12 # Steal

# Q1. Compute scores Carolina and Opponents earned for the recent 23 games.
# Your code goes here
CarolinaScore = np.zeros(23) # replace with your calculation here
OpponentScore = np.zeros(23) # replace with your calculation here

# Create a new figure
pylab.figure()

# Your plotting code goes here

# Add information to the figure
# Do not change the figure
pylab.ylabel('Score')
pylab.xlabel('Game index')
pylab.legend(['Carolina','Opponent'])
pylab.savefig('score.png')

# Q2. Create a 1-D array named CarolinaVictory and 
# store value 1 to represent Carolina victory and value -1 for Carolina defeat. 

# Your code goes here
CarolinaVictory = np.zeros(23) # replace with your calculation here

print 'CarolinaVictory =',CarolinaVictory

# Q3. You can compute the correlation coefficient directly by
# the formula shown in the instruction, or alternatively,
# you can use NumPy function corrcoef to calculate the
# correlation coefficients. 
# The output of corrcoef function is a 2 by 2 symmetric array. 
# Use the value at the first row and the second column of the 2-D array
# as the final correlation coefficient. 

# 3(0).
C_twoPoint = 0 # replace 0 with your calculation here

# 3(1).
C_threePoint = 0 # replace 0 with your calculation here

# 3(2).
C_offensivePower = 0 # replace 0 with your calculation here

# 3(3)
C_defensivePower = 0 # replace 0 with your calculation here

# 3(4).
C_assist = 0 # replace 0 with your calculation here

# Do not change the following two lines
Corr = np.array([C_twoPoint,C_threePoint,C_offensivePower,C_defensivePower,C_assist])
print 'Correlation Coefficients =',Corr

# Determine which feature is most important
# (highest correlation coefficient) to Carolina victory.  
# Use feature index 0,1,2,3,4 for the print out.
print 'The most important feature is ', 0 #replace 0 by your calculation


