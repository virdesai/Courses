##  Assignment 4 - Watershed computation (loops and lists)

## Author:
## Collaborators:

import numpy as np
import pylab
from A4Func_Solution import *

# peaks and pits
terrain = np.load( 'elevation.npy' )

#1 Draw the terrain 
pylab.imshow( terrain, cmap=pylab.cm.gray )
pylab.savefig( "terrian.png" ) 


#2 Draw pits on the terrain
pits = findPits(terrain) # pits = [row col]
pylab.plot(pits[:,1],pits[:,0],'r.')

#3 Draw peaks on the terrain
peaks = findPits(-1*terrain) # peaks = [row col]
pylab.plot(peaks[:,1],peaks[:,0],'g+')
pylab.axis('image')


#4 Print the number of peaks and the number of pits
num_pits = np.shape(pits)[0]
num_peaks = np.shape(peaks)[0]

print 'Number of peaks:',num_peaks
print 'Number of pits:',num_pits

#5 Save this figure as 'peaksNPits.png' 
pylab.savefig("peaksNPits.png")
pylab.close()

#6-9 Trace drop in the terrain
pylab.imshow( terrain, cmap=pylab.cm.gray ) 
point = np.array( pylab.ginput(1)[0] ) 
r = int( np.round( point[1] ) ) 	# an integer row index 
c = int( np.round( point[0] ) ) # an integer column index
print 'Clicked point at (row=',r,',col=',c,')...'

path = traceDrop( terrain, r, c) # path = [row col]
pylab.plot(path[:,1],path[:,0],'b-')
pylab.axis('image')
pylab.savefig("raindrop.png")
pylab.show()


#10-15 EXTRA CREDIT!: Compute the flow for the terrain
terrainFlow = findFlow( terrain )

streams = terrainFlow > 200  
pylab.imshow( streams , cmap=pylab.cm.gray )
pylab.axis('image')
pylab.savefig("streams.png")
pylab.close()

rivers = terrainFlow > 1000
pylab.imshow( rivers , cmap=pylab.cm.gray )
pylab.axis('image')
pylab.savefig("rivers.png")
pylab.close()








