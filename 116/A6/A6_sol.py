import numpy as np
from datetime import date

def average_rain( data ):
    ''' This function returns midweek rainfall average,
    weekend rainfall average, and their difference '''
    
    week = np.zeros(len(data))

    for i in xrange(len(data)):
        week[i] = date(int(data[i,0]),int(data[i,1]),int(data[i,2])).weekday()

    weekday = np.logical_and(week >= 1, week <= 3)
    weekend = np.logical_or(week == 5, week == 6)
    weekend = np.logical_or(weekend, week == 0)

    rain_weekday = np.mean( data[weekday, 3] )
    rain_weekend = np.mean( data[weekend, 3] )
    
    return rain_weekday, rain_weekend, rain_weekday-rain_weekend

def getFirst200MidweekAverages( data ):
    ''' This function returns midweek rainfall average,
    weekend rainfall average, and their difference '''
    
    week = np.zeros(len(data))

    for i in xrange(len(data)):
        week[i] = date(int(data[i,0]),int(data[i,1]),int(data[i,2])).weekday()

    weekday = np.logical_and(week >= 1, week <= 3)

    midweekRainfalls = data[weekday, 3]
    midweekRainfalls.shape=(-1,3)
    avgMidweekRainfalls = np.mean(midweekRainfalls,axis=1)

    # Get first 200 nonzero averages
    ret = avgMidweekRainfalls[avgMidweekRainfalls!=0]
    ret = ret[:200]
    return ret

#-------------
#*** Main ***
#-------------

#----------
# Load data
#----------
data = np.loadtxt('krdu-rain-2001-2010.csv', delimiter=',')

#------------------
# Compute averages
#------------------
rain = average_rain(data)
delta = rain[2]
print 'average rain for midweek =',rain[0]
print 'average rain for weekend =',rain[1]
print 'the difference =',delta

#----------
# P-test
#----------
cnt = 0
N = 1000
for i in xrange(N):
    np.random.shuffle(data[:,:3])
    rain = average_rain(data)
    if rain[2] > delta:
        cnt = cnt + 1
p = float(cnt)/N
print 'p value =',p

#----------------------------------------
# Get first 200 nonzero midweek averages
#----------------------------------------
data = np.loadtxt('krdu-rain-2001-2010.csv', delimiter=',')
data200 = getFirst200MidweekAverages( data )
#sort the data
data200_sorted = np.sort(data200)
#-------------
# Finding LIS
#-------------
from lcs import *
# call LCS to fill out 1)length of LCS:lenLCS and 2)direction of LCS: dirLCS
lenLCS = np.ones((len(data200),len(data200_sorted)))*-1
dirLCS = np.ones((len(data200),len(data200_sorted)))*-1
LCS_backward_DP(len(data200)-1,len(data200_sorted)-1,data200,data200_sorted,lenLCS,dirLCS)
# Trace back to get the LCS
LIS=[]
traceBackLCS(data200,data200_sorted,dirLCS,LIS)
print LIS


