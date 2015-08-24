##  Assignment 4 - Watershed computation (loops and lists)

## Author: Vir Desai
## Collaborators:

import numpy as np
import pylab
from A4Func import *

# peaks and pits
terrain=np.load('elevation.npy')

#1 Draw the terrain 
pylab.imshow(terrain, cmap=pylab.cm.gray)


#2 Draw pits on the terrain
pits=findPits(terrain)
pits=np.array(pits)
pylab.plot(pits[:,1],pits[:,0], 'go')

#3 Draw peaks on the terrain
peaks=findPits(-terrain)
peaks=np.array(peaks)
pylab.plot(peaks[:,1],peaks[:,0], 'r+')


#4 Print the number of peaks and the number of pits
num_pits = len(pits) # replace with your code here
num_peaks = len(peaks) # replace withyour code here

print 'Number of peaks:',num_peaks
print 'Number of pits:',num_pits

#5 Save this figure as 'peaksNPits.png'
pylab.axis('image')
pylab.savefig("peaksNPits.png")
pylab.close()

#6-9 Trace drop in the terrain
pylab.imshow(terrain,cmap=pylab.cm.gray)
point=np.array(pylab.ginput(1)[0]) 
r=int(np.round(point[1]))
c=int(np.round(point[0]))
tD=traceDrop(terrain,r,c)
pylab.plot(tD[:,1],tD[:,0],'b-')
pylab.axis('image')
pylab.savefig("raindrop.png")
pylab.close()

#10-15 EXTRA CREDIT!: Compute the flow for the terrain
