#######################################################################################
## Assignment 6                                                                      ##
## Discussion is allowed.                                                            ##
## Copying or sharing code is prohibited.                                            ##
## By typing your name below, you indicate agreement with the UNC Honor Code Pledge, ##
## that you have not given or received unauthorized assistance on this assignment.   ##
## Name:Vir Desai                                                                             ##
## Collaborators:                                                                    ## 
#######################################################################################

import numpy as np
from datetime import date

data=np.loadtxt('krdu-rain-2001-2010.csv', delimiter = ',') #load data
data2=[]
for i in data:
    for n in i:
        data2.append(int(n))
        year=data2[0::4]
        month=data2[1::4]
        day=data2[2::4]
rainfall=data[:,3]

#days of the week
day_of_week=[]
for i in range(np.size(year)):
    dow=date(year[i],month[i],day[i]).weekday()
    day_of_week.append((dow))
#0=Monday, 1=Tuesday, 2=Wednesday, 3=Thursday, 4=Friday, 5= Saturday, 6=Sunday

# midweek and weekend average and diff
def avgdiff(year,month,day,rainfall):
    midweek=[]
    weekend=[]
    for i in range(np.size(year)):
        dow=date(year[i],month[i],day[i]).weekday()
        if dow==1 or dow==2 or dow==3:
            midweek.append(rainfall[i])
        elif dow==5 or dow==6 or dow==0:
            weekend.append(rainfall[i])
    return np.mean(midweek)-np.mean(weekend)
diff=avgdiff(year,month,day,rainfall)

print 'The regular mean difference between midweek and weekend rainfall is',diff,'inches'
print ''

#Calculating p-value from looped function
shuf=[]
n=1
count=0
while n<=1000:
    np.random.shuffle(rainfall)
    shuf.append(avgdiff(year,month,day,rainfall))
    n+=1
    if avgdiff(year,month,day,rainfall)>diff:
        count+=1
p=float(count)/float(n)
print ''
print 'The p-value for this data is',p
print ''

#Bonus below. It's probably wrong.

rainfall=data[:,3]
indx=[]
for i in range(np.shape(day_of_week)[0]):
    if day_of_week[i]>=1 and day_of_week[i]<=3:
        indx.append(i)
tue=indx[0::3]
wed=indx[1::3]
thu=indx[2::3]
mid=[]
for i in range(np.shape(tue)[0]):
    mid.append([tue[i],wed[i],thu[i]])
midrain=rainfall[mid]
mwavg=[]
m=0
for i in midrain:
    if np.mean(i)>0 and m<=200:
        mwavg.append(np.mean(i))
        m+=1
array=np.array(mwavg)
sort=np.sort(array)

def longincrease(array,sort):
    for aindx,a in enumerate(array):
        for sindx,s in enumerate(sort):
            acsi=0
            scsi=0
            if a>path[-1] and a==s and sindx>scsi and aindx>acsi:
                path.append(a)
                scsi=sindx
                acsi=aindx

path=[0]
longincrease(array,sort)

print ''
print 'The longest increasing subsequence of midweek rainfall means is:' , path[1:-1]
print ''
