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
    offsetRow = np.zeros_like(terrain)
    offsetCol = np.zeros_like(terrain)
    for i in np.arange(1,terrain.shape[0]-1): # rows
        for j in np.arange(1,terrain.shape[1]-1): # cols
            _min=terrain.max()
            for p in np.arange(-1,2):
                for q in np.arange(-1,2):
                    if terrain[i+p,j+q]<= _min:
                        _min = terrain[i+p,j+q]
                        offsetRow[i,j]=p # [row,col]
                        offsetCol[i,j]=q
            
    return offsetRow, offsetCol



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
    offsetRow, offsetCol = findLowNhbr( terrain )
    offsetRow[0,:]=-1 # ignoring the boundaries
    offsetRow[:,0]=-1
    offsetRow[-1,:]=-1
    offsetRow[:,-1]=-1
    
    indx = np.where(np.logical_and(offsetRow==0,offsetCol==0))
    return np.column_stack((indx[0],indx[1])) #[row col]



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
    offsetRow,offsetCol = findLowNhbr( terrain )
    path = [(row,column)] # [row col]
    cond = True
    while cond:
        if offsetRow[row,column]==0 and offsetCol[row,column]==0:
            cond = False
        else:
            new_point = (row+offsetRow[row,column],column+offsetCol[row,column])
            path.append(new_point)
            row = new_point[0]
            column = new_point[1]
    
    return np.array(path)


## THE findFlow FUNCTION IS EXTRA CREDIT!!!

# Inputs: terrain - a 2D-array representing a terrain elevation map.
# Outputs: A 2D-array (the same shape as terrain) consisting of the total
#       flow through each point in the terrain (except the boundaries.)
# Functionality: Computes the flow at each point on the terrain by accumulating
#       flow from the highest points to the lowest points.
def findFlow( terrain ):
    """Computes the flow array of the terrain"""
    flow = np.ones_like(terrain)
    indx = np.argsort(-1*terrain, axis=None) # from high to low
    indx_row = indx / terrain.shape[1]
    indx_col = indx % terrain.shape[1]
    offsetRow,offsetCol = findLowNhbr( terrain )
    for i in range(indx.size):
        offRow = offsetRow[indx_row[i],indx_col[i]]
        offCol = offsetCol[indx_row[i],indx_col[i]]
        if offRow != 0 or offCol !=0 : # not a pit nor at boundary
            flow[indx_row[i]+offRow,indx_col[i]+offCol]+=flow[indx_row[i],indx_col[i]]

    
    return flow
