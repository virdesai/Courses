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

# Load the data from the text file
# Do not change the following line
data = np.loadtxt('data.txt')

# Q1. How many of participants are female in the investigation

# Compute the number of females and name the variable num_female in the following line 
num_female = 0 # replace 0 by your calculation

# Do not change the following line
print 'Number of females:', num_female

# Q2. Plot the histogram (with 10 bins) of height for all female participants in a figure

# Create a new figure
pylab.figure()

# Your plotting code goes here

# Add xlabel, ylabel and title to the figure
# Do not change the following lines
pylab.xlabel('height in meters')
pylab.ylabel('count')
pylab.title('Distribution of female heights')
pylab.savefig('hist.png')


# Q3. For each program plot the average BMI of the 100 participants for each month

# Compute BMI for each participant here

# Compute the average BMI for each program (of 100 participants) here

# Create a new figure
pylab.figure()

# Your plotting code goes here

# Add information to the figure.
# Do not change the following lines.
pylab.xlabel('Month')
pylab.ylabel('Average BMI')
pylab.title('Average BMI for each program')
pylab.legend(['Program 1', 'Program 2', 'Program 3', 'Program 4', 'Program 5'])
pylab.savefig('BMI.png')
