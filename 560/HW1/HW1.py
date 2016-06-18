#############################
## Homework 1              ##
## Name:Vir Desai          ##
## Collaborators:None      ## 
#############################

import sys

# entry point for maze solver
def searchMaze(option, filename):
  current = []
  accessible = []
  explored = []
  goal = []
  #load the maze as list of lists and instantiate the start & goal coords
  maze = openFile(option, filename, current, goal)

  # initialize visited array
  i = 0
  for line in maze:
    accessible.append([])
    for cell in line:
      if cell == '%':
        accessible[i].append(-1)
      else:
        accessible[i].append(0)
    i += 1

  frontier = [current]
  cAccessible = accessible[:]

  # solve maze
  if option == "DFS":
    dfs(frontier, accessible, goal, explored)
  elif option == "BFS":
    bfs(frontier, accessible, goal, explored)
  elif option == "GREEDY":
    greedy(frontier, accessible, goal, explored)
  elif option == "A":
    A_star(frontier, accessible, goal, explored)
  elif option == "CHEESE":
    chars = ACheese(frontier, accessible, goal, explored, maze)
  else:
    print('FAILED')
    sys.exit(1)
  
  #output maze solution
  if option != "CHEESE":
    pathback(maze, current, goal, accessible)
    print('Path Cost = ' + str(accessible[goal[0]][goal[1]]) + '\n')
  else:
    print('Path Cost = ' + str(pathCost(current,maze,chars,cAccessible)) + '\n')
  mazeOutput(filename, maze, option)
  print("Number of nodes = " + str(len(explored)) + '\n')


#depth first search
def dfs(frontier, accessible, goal, explored):
  found = False
  start = frontier[0]
  while len(frontier) > 0 and not found:
    current = frontier.pop()#key to how the frontier is manager LIFO
    cost = updateCost(start,accessible, current, explored)#to make sure cost is handled correctly
    explored.append(current)
    accessible[current[0]][current[1]] = cost + 1
    if current == goal:
      found = True
      break
    #handling of the frontier is below
    if isUpOpen(current,accessible,explored):
      frontier.append(up(current))
    if isLeftOpen(current,accessible,explored):
      frontier.append(left(current))
    if isRightOpen(current,accessible,explored):
      frontier.append(right(current))
    if isDownOpen(current,accessible,explored):
      frontier.append(down(current))


#breadth first search
def bfs(frontier, accessible, goal, explored):
  found = False
  start = frontier[0]
  while len(frontier) > 0 and not found:
    current = frontier[0]# this and the line below integral for frontier
    frontier = frontier[1:]#management for bredth-first. FIFO
    cost = updateCost(start,accessible, current, explored)#to get proper cost associated with the current node
    explored.append(current)
    accessible[current[0]][current[1]] = cost + 1
    if current == goal:
      found = True
      break
    #handling of the frontier is below
    if isUpOpen(current,accessible,explored):
      frontier.append(up(current))
    if isRightOpen(current,accessible,explored):
      frontier.append(right(current))
    if isLeftOpen(current,accessible,explored):
      frontier.append(left(current))
    if isDownOpen(current,accessible,explored):
      frontier.append(down(current))


# greedy best first search (goes to closest node to goal)
def greedy(frontier, accessible, goal, explored):
  found = False
  frontier[0].append(manhattan([frontier[0][0],frontier[0][1]],goal))
  start = frontier[0]
  while len(frontier) > 0 and not found:
    current = frontier[0]
    frontier = frontier[1:]#FIFO
    cost = updateCost(start,accessible,current,explored)
    explored.append(current[:2])
    accessible[current[0]][current[1]] = cost + 1
    if current[:2] == goal:
      found = True
      break
    greedyNeighbors(current, accessible, frontier, explored, goal)#frontier management in this function


# A* search (looks for cheapest and closest node)
def A_star(frontier, accessible, goal, explored):
  found = False
  #attaching the goal cost to each of the nodes
  frontier[0].append(aDist(frontier[0],accessible,[frontier[0][0],frontier[0][1]],goal))
  start = frontier[0]
  while len(frontier) > 0 and not found:
    current = frontier[0]
    frontier = frontier[1:]#FIFO
    cost = updateCost(start,accessible,current,explored)
    explored.append(current[:2])
    accessible[current[0]][current[1]] = cost + 1
    if current[:2] == goal:
      found = True
      break
    aNeighbors(current, accessible, frontier, explored, goal)#frontier management in this function


# A* search for cheese
def ACheese(frontier, accessible, goal, explored, maze):
  frontier[0].append(0)
  current = frontier[0]
  start = frontier[0]
  lettersUsed = []
  name = "123456789abcdefghijklmnopqrstuvwxyz"
  while len(goal) > 0:
    current = frontier[0]
    frontier = frontier[1:]#FIFO
    explored.append(current)
    if current[:2] in goal:
      goal.remove(current[:2])
      if len(name) > 0:
          maze[current[0]][current[1]] = name[0]
          name = name[1:]
      else:
          maze[current[0]][current[1]] = '-'
      lettersUsed.append([current[0],current[1]])#for path cost usage after function execution complete
      if len(goal) == 0:
        return lettersUsed
    cheeseNext(current,accessible,frontier,explored,goal)#frontier management in this function


# ---- SEARCH, INPUT, AND OUTPUT HELP FUNCTIONS BELOW ---- #

#for path cost of cheese mazes
def pathCost(start, maze, chars, accessible):
    current = start[:]
    frontier = [start]
    newAccess = accessible[:]
    cost = 0
    while len(chars) > 0:
        goal = chars[0]
        chars = chars[1:]
        accessible = newAccess[:]
        explored = []
        bfs(frontier,accessible,goal,explored)
        cost += accessible[goal[0]][goal[1]]
        current = goal
        frontier = [current]
    return cost


#find path back to maze
def pathback(maze, start, goal, accessible):
  current = goal
  while(accessible[current[0]][current[1]] != 1):
  #while(current != start):
    if accessible[up(current)[0]][up(current)[1]] == accessible[current[0]][current[1]]-1:
      current = up(current)
    elif accessible[down(current)[0]][down(current)[1]] == accessible[current[0]][current[1]]-1:
      current = down(current)
    elif accessible[left(current)[0]][left(current)[1]] == accessible[current[0]][current[1]]-1:
      current = left(current)
    elif accessible[right(current)[0]][right(current)[1]] == accessible[current[0]][current[1]]-1:
      current = right(current)
    maze[current[0]][current[1]] = '.'
  maze[start[0]][start[1]] = "S"


#previous explored cost to make sure path cost handled correctly
def updateCost(start, accessible, current, explored):
  accessible[start[0]][start[1]] = 1
  if len(explored) > 1:
    neighbors = [up(current),down(current),right(current),left(current)]
    temp = filter(lambda c: c in explored, neighbors)
    low = 1000000
    for item in temp:
      if accessible[item[0]][item[1]]-1 < low:
        low = accessible[item[0]][item[1]]
    if low != 1000000:
        return low
    return 0
  else:
    return 0


#simple moving functions
def up(current):
  return [current[0]-1, current[1]]
def down(current):
  return [current[0]+1, current[1]]
def right(current):
  return [current[0], current[1]+1]
def left(current):
  return [current[0], current[1]-1]


#figure out which neighbors are open for the frontier and add to them and then sort the frontier
def greedyNeighbors(current, accessible, frontier, explored, goal):
  if isLeftOpen(current,accessible,explored):
    frontier.append([current[0],current[1]-1,manhattan([current[0],current[1]-1],goal)])
  if isRightOpen(current,accessible,explored):
    frontier.append([current[0],current[1]+1,manhattan([current[0],current[1]+1],goal)])
  if isUpOpen(current,accessible,explored):
    frontier.append([current[0]-1,current[1],manhattan([current[0]-1,current[1]],goal)])
  if isDownOpen(current,accessible,explored):
    frontier.append([current[0]+1,current[1],manhattan([current[0]+1,current[1]],goal)])
  frontier.sort(key=lambda x:x[2])


#basically the same as the above function but for A* instead of greedy
def aNeighbors(current, accessible, frontier, explored, goal):
  if isLeftOpen(current,accessible,explored):
    frontier.append([current[0],current[1]-1,aDist(current,accessible,[current[0],current[1]-1],goal)])
  if isRightOpen(current,accessible,explored):
    frontier.append([current[0],current[1]+1,aDist(current,accessible,[current[0],current[1]+1],goal)])
  if isUpOpen(current,accessible,explored):
    frontier.append([current[0]-1,current[1],aDist(current,accessible,[current[0]-1,current[1]],goal)])
  if isDownOpen(current,accessible,explored):
    frontier.append([current[0]+1,current[1],aDist(current,accessible,[current[0]+1,current[1]],goal)])
  frontier.sort(key=lambda x:x[2])


#the cheese version of the A* frontier management function
def cheeseNext(current, accessible, frontier, explored, goal):
  if accessible[current[0]+1][current[1]] != -1:
    d = down(current)
    c = cheeseCost(current,d,goal)
    d.append(c)
    if d not in frontier and d not in explored:
      frontier.append(d)
  if accessible[current[0]-1][current[1]] != -1:
    u = up(current)
    c = cheeseCost(current,u,goal)
    u.append(c)
    if u not in frontier and u not in explored:
      frontier.append(u)
  if accessible[current[0]][current[1]+1] != -1:
    r = right(current)
    c = cheeseCost(current,r,goal)
    r.append(c)
    if r not in frontier and r not in explored:
      frontier.append(r)
  if accessible[current[0]][current[1]-1] != -1:
    l = left(current)
    c = cheeseCost(current,l,goal)
    l.append(c)
    if l not in frontier and l not in explored:
      frontier.append(l)
  frontier.sort(key=lambda x:x[2])



#checks if left cell is open for basic usage
def isLeftOpen(current, accessible, explored):
  if accessible[current[0]][current[1]-1] != -1 and (len(explored)==1 or left(current) not in explored or (accessible[current[0]][current[1]]-1 != accessible[left(current)[0]][left(current)[1]] and left(current) in explored)):
    return True
  return False


#checks if right cell is open for basic usage
def isRightOpen(current, accessible, explored):
  if accessible[current[0]][current[1]+1] != -1 and (len(explored)==1 or right(current) not in explored or (accessible[current[0]][current[1]]-1 != accessible[right(current)[0]][right(current)[1]] and right(current) in explored)):
    return True
  return False


# checks is up cell is open for basic usage
def isUpOpen(current, accessible, explored):
  if accessible[current[0]-1][current[1]] != -1 and (len(explored)==1 or up(current) not in explored or (accessible[current[0]][current[1]]-1 != accessible[up(current)[0]][up(current)[1]] and up(current) in explored)):
    return True
  return False


#checks if down cell is open for basic usage
def isDownOpen(current, accessible, explored):
  if accessible[current[0]+1][current[1]] != -1 and (len(explored)==1 or down(current) not in explored or (accessible[current[0]][current[1]]-1 != accessible[down(current)[0]][down(current)[1]] and down(current) in explored)):
    return True
  return False


#to create and output maze to a solution file in proper format
def mazeOutput(fileName, maze, option):
  fileObject = open(fileName[:-4]+option+'Sol.txt','w')
  for line in maze:
    for char in line:
      fileObject.write(str(char))
    fileObject.write('\n')
  fileObject.close()


#opening the file as 2D array and removing the newline char
#coordinates in rox x col format ex: array[row][col]
#and instantiating the necessary variables with proper values
def openFile(option, fileName, start ,goal):
  with open(fileName) as f:
    data = []
    i=0
    for line in f:
      data.append([])
      j=0
      for char in line:
        if char != '\n':
          if char == 'S':
            start.append(i)
            start.append(j)
          if char == 'G':
            goal.append(i)
            goal.append(j)
          if option == "CHEESE" and char == '.':
            goal.append([i,j])
          data[i].append(char)
        j+=1
      i+=1
  f.close()
  return data


#finding the manhattan distance for the current position
def manhattan(current, goal):
  return abs(current[0] - goal[0]) + abs(current[1] - goal[1])


#for cost associated with regular A* search
def aDist(current, accessible, nextNode, goal):
  cost = accessible[nextNode[0]][nextNode[1]]
  if cost == 0:
    cost = accessible[current[0]][current[1]]+1
  return cost + manhattan(nextNode,goal)


#the hellish function I created for finding the shortest path between all of
#the goal nodes and returning them to the bigger cost function
def findClosest(current, options):
    if len(options) == 1:
        return manhattan(current,options[0])
    elif len(options) > 1:
        start = current[:]
        goals = options[:]
        nextNode = start[:]
        temp = 100000
        for node in goals:
            c = manhattan(start,node)
            if c < temp:
                temp = c
                nextNode = node[:]
        goals.remove(nextNode)
        temp += findClosest(nextNode,goals)
        return temp
    else:
        return 0


#for cost associated with cheese A* search to find the lowest cost path
#that the nextNode has if current takes that path
def cheeseCost(current, nextNode, goal):
  temp = goal[:]
  if len(goal) > 1:
    cost = 100000
    if nextNode[:2] in temp:
      temp.remove(nextNode[:2])
    for node in temp:
      tempCost = manhattan(nextNode[:2], node) + len(temp)
      temp2 = temp[:]
      temp2.remove(node)
      tempCost += findClosest(node,temp2)
      if tempCost < cost:
        cost = tempCost
    return cost
  elif len(goal) == 0:
    return 0
  else:
    return manhattan(nextNode,goal[0])


#Main Function
if __name__== '__main__':
  print("Running DFS small maze")
  searchMaze("DFS","smallMaze.txt")
  print("Running BFS small maze")
  searchMaze("BFS","smallMaze.txt")
  print("Running GREEDY small maze")
  searchMaze("GREEDY","smallMaze.txt")
  print("Running A_Star small maze")
  searchMaze("A","smallMaze.txt")
  print("Running DFS medium maze")
  searchMaze("DFS","mediumMaze.txt")
  print("Running BFS medium maze")
  searchMaze("BFS","mediumMaze.txt")
  print("Running GREEDY medium maze")
  searchMaze("GREEDY","mediumMaze.txt")
  print("Running A_Star medium maze")
  searchMaze("A","mediumMaze.txt")
  print("Running DFS big maze")
  searchMaze("DFS","bigMaze.txt")
  print("Running BFS big maze")
  searchMaze("BFS","bigMaze.txt")
  print("Running GREEDY big maze")
  searchMaze("GREEDY","bigMaze.txt")
  print("Running A_Star big maze")
  searchMaze("A","bigMaze.txt")

  print("Running hard A_Star maze")
  searchMaze("A","ahardMaze.txt")
  print("Running easy GREEDY maze")
  searchMaze("GREEDY","ahardMaze.txt")
  print("Running easy A_Star maze")
  searchMaze("A","ghardMaze.txt")
  print("Running hard GREEDY maze")
  searchMaze("GREEDY","ghardMaze.txt")

  print("Running small cheese")
  searchMaze("CHEESE","smallcheese.txt")
  print("Running tricky cheese")
  searchMaze("CHEESE","trickycheese.txt")
  print("Running medium cheese")
  searchMaze("CHEESE","mediumcheese.txt")
