'''Assignment 2 Water Data'''

# Author: Vir Desai
# Collaborators: (put anyone you worked with here)

import numpy as np
import pylab as py

# Make sure all the plots are closed
py.close()

# Read the data

# depth: a 276 by 2 array with depth of Jordan and Falls lakes
# for each month from Jan 1985 to Dec 2007, which is 23 years.
# Data that is not available is NaN.
depth = np.loadtxt('depth.txt')

# rain: a 276x2 array with total rainfall in inches for each month
rain = np.loadtxt('rain.txt')

# hawgage: a 365x4 array of daily average river or lake height (ft) at
# Haw River, Bynum, and above & below the Jordan Lake Dam by Moncure.
# (These sites are listed upstream to downstream, but the gauges are
# not in that order.)
hawgage = np.loadtxt('hawgage.txt')

# hawrain: a 365x2 array of daily rainfall (in) measured at two
# rain gauges from 29 Aug 2007 - 28 Aug 2008.
hawrain = np.loadtxt('hawrain.txt')

# 1. Plot a line graph of depths for both lakes.
# (Is there an obvious yearly cycle?)

# create a new figure for this part
py.figure(1)

# put the appropriate call here to plot the depths
x=np.arange(0,276,1)
py.plot(x,depth[:,0], 'ro-',label='Jordan lake',linewidth=2)
py.plot(x,depth[:,1], 'bx-',label='Falls lake',linewidth=2)
py.legend(loc='upper right')
py.axis([np.min(x), np.max(x), np.min(200), np.max(270)])

# these show how to label the figure
py.title('Depth of Jordan and Falls lakes')
py.ylabel('Depth (feet)')
py.xlabel('Months starting with Jan 1985')
#py.savefig('Fig1.png')

# 2. The targets for Jordan and Falls lakes are 216ft and 251.5ft,
# respectively. For how many months was each lake over its target?

# include the code here to compute and print the answer
dJ=depth[:,0]
dF=depth[:,1]
dj=[x for x in dJ if x>216]
df=[x for x in dF if x>251.5]

JordanOverTarget = len(dj)
print 'Months Jordan lake was over its target =', JordanOverTarget

FallsOverTarget = len(df)
print 'Months Falls lake was over its target =', FallsOverTarget

# 3. Plot the rain in August as a line graph over years for both lakes.

# start a new figure for the this part
py.figure(3)

# include code to plot the figure with some nice labels as above
rJ=rain[:,0]
aJ=rJ[7:277:12]
rF=rain[:,1]
aF=rF[7:277:12]
x2=np.arange(0,23,1)
py.plot(x2,aJ, 'ro-',label='Jordan lake Rainfall',linewidth=2)
py.plot(x2,aF, 'bx-',label='Falls lake Rainfall',linewidth=2)
py.legend(loc='upper left')
py.axis([np.min(x2), np.max(x2), np.min(0), np.max(9)])

py.title('Rain in August for Jordan and Falls lakes')
py.ylabel('Rain (inches)')
py.xlabel('Every August from 1985 to 2007')
#py.savefig('Fig2.png')

# 4. Compute the average height that Falls Lake is relative to its target
# for each month over the 23 years from 1985-2007, and display as bar
# chart with a bar for each month. Plot the line for 2007 in red on
# top of this bar chart.


# start a new figure for the this part
py.figure(4)

# put code here to compute FallsByMonth
monthly_target=dF-251.5
fjanuary_depth=np.mean(monthly_target[0:277:12])
ffebruary_depth=np.mean(monthly_target[1:277:12])
fmarch_depth=np.mean(monthly_target[2:277:12])
fapril_depth=np.mean(monthly_target[3:277:12])
fmay_depth=np.mean(monthly_target[4:277:12])
fjune_depth=np.mean(monthly_target[5:277:12])
fjuly_depth=np.mean(monthly_target[6:277:12])
faugust_depth=np.mean(monthly_target[7:277:12])
fseptember_depth=np.mean(monthly_target[8:277:12])
foctober_depth=np.mean(monthly_target[9:277:12])
fnovember_depth=np.mean(monthly_target[10:277:12])
fdecember_depth=np.mean(monthly_target[11:277:12])
FallsByMonth=[fjanuary_depth,ffebruary_depth,fmarch_depth,fapril_depth,fmay_depth,fjune_depth,fjuly_depth,faugust_depth,fseptember_depth,foctober_depth,fnovember_depth,fdecember_depth]

# then you can create a bar chart of it like this:
py.bar(np.arange(1,13), FallsByMonth, align='center')

# then plot a line in red on top of that with a call to plot like this:
py.plot(np.arange(1,13), FallsByMonth, 'ro-')

py.title('Average Falls lake depth 85-07, and line for 2007')
py.ylabel('Height above target(ft)')
py.xlabel('Month')
#py.savefig('Fig3.png')

# 5. Determine how many days had more than 1 in of precipitation at
# both two sites in hawrain, and how many days had less than 1/4 in.

hr1=hawrain[:,0]
hr2=hawrain[:,1]
# your code goes here
mt1=[x for x in hr1 if x>1.0]
mt2=[x for x in hr2 if x>1.0]
mt3=len(mt1)+len(mt2)
lt1=[x for x in hr1 if x<0.25]
lt2=[x for x in hr2 if x<0.25]
lt3=len(lt1)+len(lt2)
      
print 'Number of days both lakes had more than one inch =', mt3
print 'Number of days both lakes had less than 1/4 inch =', lt3

# 6. Plot line graphs showing the cumulative amount of rain over the
# past year at both sites.  Which of the two locations (1 or 2)
# received the most rain?

# You'll find the numpy function "cumsum" useful for this one
# put your code here
cumhr1=np.cumsum(hr1, dtype=float)
cumhr2=np.cumsum(hr2, dtype=float)
x3=np.arange(0,365,1)
py.plot(x3,cumhr1,'ro-',label='Rain Gauge 1',linewidth=2)
py.plot(x3,cumhr2,'bx-',label='Rain Gauge 2',linewidth=2)
py.legend(loc='upper left')

# start a new figure for the this part

py.title('Cumulative Rainfall')
py.xlabel('Days since 28Aug07')
py.ylabel('Cumulative rainfall (in)')
#py.savefig('Fig4.png')

# determine which site had the most total rain -- the np.argmax function will help
# this print statement should print 0 or 1

print 'The site with more total rain is', np.argmax(np.array([np.max(cumhr1),np.max(cumhr2)]), axis=0) 

# 7. Determine the lowest height for each gauge, and create an array
# of adjusted heights by subtracting the corresponding lowest heights.
# Plot these adjust heights as a line graph.

# compute the adjusted heights
g1=hawgage[:,0]-np.min(hawgage[:,0])
g2=hawgage[:,1]-np.min(hawgage[:,1])
g3=hawgage[:,2]-np.min(hawgage[:,2])
g4=hawgage[:,3]-np.min(hawgage[:,3])

# start a new figure for the this part
py.figure(7)

# and then plot them using commands similar to above
x4=np.arange(0,365,1)
py.plot(x4,g1,'ro-',label='Rain Gauge 1',linewidth=2)
py.plot(x4,g2,'bx-',label='Rain Gauge 1',linewidth=2)
py.plot(x4,g3,'y^-',label='Rain Gauge 1',linewidth=2)
py.plot(x4,g4,'gd',label='Rain Gauge 1',linewidth=2)
py.legend(loc='upper right')

py.title('Adjusted gauge heights')
py.xlabel('Days since 28Aug07')
py.ylabel('Height above min (ft)')

#py.savefig('Fig5.png')

# 8. Determine the maximum increase and maximum decrease in height
# from one day to the next for each of the four gauges in hawgage.

# your code goes here
mg1=np.diff(hawgage[:,0],axis=0)
mg2=np.diff(hawgage[:,1],axis=0)
mg3=np.diff(hawgage[:,2],axis=0)
mg4=np.diff(hawgage[:,3],axis=0)
np.max(np.array([mg1,mg2,mg3,mg4]))

print 'Maximum one-day change in height =', np.max(np.array([mg1,mg2,mg3,mg4]))
print 'Minimum one-day change in height =', np.min(np.array([mg1,mg2,mg3,mg4]))

py.show()
