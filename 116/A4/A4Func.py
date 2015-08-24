import numpy as np

# Inputs: terrain - a 2D-array representing a terrain elevation map.
# Outputs: Two 2D-arrays. One represents the offset in rows toward the
#       direction of greatest downward change. The other represents
#       the offset in columns toward the direction of greatest downward change.
# Functionality: Water flows in the direction where the terrain is 
#       the steepest. In our terrain model, we need to determine, at each point,
#       which direction the terrain drops the most.
def findLowNhbr( terrain ):
    """Creates two 2D-arrays the shape of terrain consisting
    of the offsets (row and column) to the neighbor with the minimum eleveation"""
    rowoffset=np.zeros_like(terrain)
    coloffset=np.zeros_like(terrain)
    for r in range(1,terrain.shape[0]-1):
        for c in range(1,terrain.shape[1]-1):
            curr_height=np.inf
            for p in [-1,0,1]:
                for q in [-1,0,1]:
                    if terrain[r+p,c+q]<curr_height:
                        curr_height=terrain[r+p,c+q]
                        rowoffset[r,c]=p
                        coloffset[r,c]=q
    return rowoffset, coloffset


# Inputs: terrain - a 2D-array representing a terrain elevation map.
# Outputs: An NX2 array of integer pairs representing the indices (row, col)
#       of all of the "pits" in the terrain.
# Functionality: A "pit" in the terrain is, mathematically speaking, a
#       local minimum. It is the point in terrain that is lower than all
#       of its neighbors. This function find all of the points in the terrain
#       map (ignoring the boundary points) and returns the row, column index values of each pit.
def findPits( terrain ):
    """Creates an Nx2 array of the array index values (row, column) of the
    local minima (aka pits) of the given terrain"""
    offsets=findLowNhbr(terrain)
    rowoffset=offsets[0]
    coloffset=offsets[1]
    offsetsX=([])
    offsetsY=([])
    offsetsXY=([])
    a=np.where(rowoffset==0)
    X=np.column_stack((a[0],a[1]))
    for [c,d] in X:
        if c>0 and c<299 and d>0 and d<299:
            offsetsX.append([c,d])
    b=np.where(coloffset==0)
    Y=np.column_stack((b[0],b[1]))
    for [i,j] in Y:
        if i>0 and i<299 and j>0 and j<299:
            offsetsY.append([i,j])
    XY=[[x,y] for [x,y] in offsetsX if [x,y] in offsetsY]
    for [x,y] in XY:
        offsetsXY.append([x,y])
    return offsetsXY



# Inputs: terrain - a 2D-array representing a terrain elevation map.
#       row - the row index of the starting point of the drop.
#       column - the column index of the starting point of the drop.
# Outputs: An NX2 array of integer pairs representing the path a raindrop
#       would follow down the terrain from the initial point (row, column).
#       The columns are the row and column indices of all the points in
#       the terrain that make up the path.
# Functionality: This function traces the path of a drop of water down the
#       terrain.
def traceDrop( terrain, row, column ):
    """Creates a path (an Nx2 array of row, column indices) of the path that a
    raindrop would travel starting at the position (row, column)"""
    offsets=findLowNhbr(terrain)
    rowoffset=offsets[0]
    coloffset=offsets[1]
    pits=findPits(terrain)
    trace=[]
    cp=([row,column])
    trace.append((cp[0],cp[1]))
    x=rowoffset[cp[0]][cp[1]]
    y=coloffset[cp[0]][cp[1]]
    cond=True
    while cond==True:
        cp[0]+=x
        cp[1]+=y
        x=rowoffset[cp[0]][cp[1]]
        y=coloffset[cp[0]][cp[1]]
        trace.append((cp[0],cp[1]))
        for i in pits:
            if cp==i:
                cond=False
        if cp[0]==0 or cp[0]==299 or cp[1]==0 or cp[1]==299:
            cond=False
    return np.array(trace)


## THE findFlow FUNCTION IS EXTRA CREDIT!!!

# Inputs: terrain - a 2D-array representing a terrain elevation map.
# Outputs: A 2D-array (the same shape as terrain) consisting of the total
#       flow through each point in the terrain (except the boundaries.)
# Functionality: Computes the flow at each point on the terrain by accumulating
#       flow from the highest points to the lowest points.
def findFlow( terrain ):
    """Computes the flow array of the terrain"""
    rowoffset, coloffset=findLowNhbr(terrain)
    flow=np.ones_like(terrain)
    indx=np.argsort(-1*terrain,axis==None)
    row_indx=indx/terrain.shape[1]
    col_indx=indx%terrain.shape[1]
    for i in range(terrain.size):
        row_lowNbr=row_indx[i]+rowoffset[row_indx[i],col_indx[i]]
        col_lowNhr=col_indx[i]+coloffset[row_indx[i],col_indx[i]]
        if rowoffset[row_indx[i],col_indx[i]]!=0 or coloffset[row_indx[i],col_indx[i]]!=0:
            flow[row_lowNbr,col_lowNbr]+=flow[row_indx[i],col_indx[i]]
        
        
    return flow
