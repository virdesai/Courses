#############################
## Homework 2              ##
## Name:Vir Desai          ##
## Collaborators:None      ## 
#############################

import sys, random, time

# entry point for maze solver
def hns(filename):
  friends, trees = openFileHS(filename)
  local(friends,trees)


def local(friends, trees):
  count = 0
  conflicts = 0
  initialFriends = friends[:]
  for friend in friends:#1st iter to get current # of conflicts
    conflicts += currentConflicts(friend,friends[:],trees)
  while(conflicts != 0 and count < 100):
    count += 1
    bestFriend = friends[0][:]
    rowMove = 0
    diff = 0
    for friend in friends:
      td, tr = rowConflicts(friend,friends[:],trees)
      if td >= diff:
        bestFriend = friend
        rowMove = tr
        diff = td
    friends[friends.index(bestFriend)][0]=rowMove
    conflicts = 0
    for friend in friends:
      conflicts += currentConflicts(friend,friends[:],trees)
  if count == 100:
    print("Stuck in local minima on iteration: " + str(count))
    print("Current friends positions with " + str(conflicts) + " conflicts left")
    print("Friend locations initialized as: ")
    for friend in initialFriends:
      print(str(friend[0]+1) + " " + str(friend[1]+1))
  else:
    print("Iterations: " + str(count))
  for friend in friends:
    print(str(friend[0]+1) + " " + str(friend[1]+1))


def rowConflicts(c, friends, trees):
  num = len(friends[:])
  conflicts = currentConflicts(c,friends[:],trees)
  rowtrees = filter(lambda x: x[1] == c[1], trees)
  row = range(0,num)
  for a in rowtrees:
    row.remove(a[0])
  bestRow = 0
  diff = 0
  for i in row:
    temp = currentConflicts([i,c[1]],friends[:],trees)
    if temp <= conflicts:
      diff += conflicts-temp
      conflicts = temp
      bestRow = i
  return (diff, bestRow)


#for returning the amount of current conflicts a position c faces
def currentConflicts(c, friends, trees):
  conflicts = 0
  if c in friends:
    friends.remove(c)
  for f in friends:
    if abs(f[0]-c[0]) == abs(f[1]-c[1]):#diagonal check
      found = False
      for t in trees:
        if (((f[0]>t[0] and f[1]>t[1] and c[0]<t[0] and c[1]<t[1])
            or (f[0]<t[0] and f[1]<t[1] and c[0]>t[0] and c[1]>t[1]))
            and abs(f[0]-t[0]) == abs(f[1]-t[1])):
          found = True
      if found == False:
        conflicts += 1
    if f[0] == c[0]:#row check
      tempTrees = filter(lambda x: x[0] == f[0],trees)
      found = False
      for t in tempTrees:
        if (f[1]>t[1] and c[1]<t[1]) or (f[1]<t[1] and c[1]>t[1]):
          found = True
      if found == False:
        conflicts += 1
  return conflicts


#opening the file and instantiating necessary objects
def openFileHS(fileName):
  num = 0
  trees = []
  friends = []
  with open(fileName) as f:
    count = 0
    for line in f:
      if count == 0:
        num = int(line.split()[0])
      else:
        trees.append([int(i)-1 for i in line.split()])
      count+=1
  f.close()
  for i in range(0,num):
    r = range(0,num)
    temp = filter(lambda x: x[1] == i,trees)
    for a in temp:
      r.remove(a[0])
    rand = random.choice(r)
    friends.append([rand,i])
  return (friends, trees)


#simple moving functions
def up(current):
  if current[0] != 0:
    return [current[0]-1, current[1]]
  return False
def down(current):
  if current[0] != 5:
    return [current[0]+1, current[1]]
  return False
def right(current):
  if current[1] != 5:
    return [current[0], current[1]+1]
  return False
def left(current):
  if current[1] != 0:
    return [current[0], current[1]-1]
  return False

 
def openFileMM(fileName):
  with open(fileName) as f:
    data = []
    owners = []
    for line in f:
      data.append([int(i) for i in line.split()])
      owners.append([0,0,0,0,0,0])
  return (data, owners)


def candy(limit1, limit2):
  files = ['AlmondJoy.txt','Ayds.txt','Bit-O-Honey.txt','Mounds.txt','ReesesPieces.txt']
  for f in files:
    weights, owners = openFileMM(f)
    matchups = [[1,1],[2,2],[2,1],[1,2]]
    global tree, optimal
    tree = []
    optimal = []
    for match in matchups:
      for i in range(max(limit1,limit2)):
        tree.append([])
      board = Board(weights[:], owners[:])
      turns = 0
      l1 = limit1 if match[0] == 1 else limit2
      l2 = limit1 if match[1] == 1 else limit2
      p1 = Player('Blue',match[0],1, l1)
      p2 = Player('Green',match[1],2, l2)
      turn = Turn(p1,p2)
      while(board.turns != 36):
        t1 = time.time()
        turn.get().execute(board,turn.get().limit,turn)
        t2 = time.time()
        turn.get().time += t2-t1
        board.update(optimal[0],True,turn,0)
        turn.get().movesMade.append(optimal[0])
        turn.switch()
      output(p1,p2,board,f,l1,l2,match[0],match[1])
      del tree[:]
      wipe(owners)


def wipe(array):
  del array[:]
  for i in range(6):
    array.append([])
    for j in range(6):
      array[i].append(0)


def output(p1, p2, board, f, limit1, limit2, alg1, alg2):
  d = {1:'Minimax', 2:'Alphabeta'}
  fileObject = open(f[:-4]+d[alg1]+str(limit1)+d[alg2]+str(limit2)+'Sol.txt','w')
  s = "On File: " + f + " with depth limit: " + str(limit1) + " on algorithm: " + d[alg1] + " for Blue/Player 1 and depth limit: " + str(limit2) + " on algorithm: " + d[alg2] + " for Green/Player2"
  print(s)
  fileObject.write(s + '\n' + '\n')
  d1 = {0:'A', 1:'B', 2:'C', 3:'D', 4:'E', 5:'F'}
  d2 = {1: 'Blue/Player 1', 2: 'Green/Player 2'}
  d3 = {1: 'B', 2:'G'}
  for i in range(18):
    move1 = p1.movesMade[i]
    move2 = p2.movesMade[i]
    if(i != 17):
      s = "Blue: drop " + d1[move1[1]] + str(move1[0]+1) + ", Green: drop " + d1[move2[1]] + str(move2[0]+1) + " then "
      print(s)
      fileObject.write(s + '\n')
    else:
      s = "Blue: drop " + d1[move1[1]] + str(move1[0]+1) + ", Green: drop " + d1[move2[1]] + str(move2[0]+1)
      fileObject.write(s + '\n' + '\n')
  print("Final State:")
  fileObject.write("Final State:\n")
  for i in range(6):
    for j in range(6):
      s = d1[j] + str(i+1) + ": " + d2[board.owners[i][j]]
      print(s)
      fileObject.write(s + '\n')
  fileObject.write('\n')
  for i in range(6):
    for j in range(6):
      fileObject.write(d3[board.owners[i][j]]+ '\t')
    fileObject.write('\n')
  s = "\nPlayer 1 Total Score: " + str(p1.score)
  print(s)
  fileObject.write(s + '\n')
  s = "Player 2 Total Score: " + str(p2.score)
  print(s)
  fileObject.write(s + '\n')
  s = "Total Nodes Expanded by Player 1: " + str(p1.expanded)
  print(s)
  fileObject.write(s + '\n')
  s = "Total Nodes Expanded by Player 2: " + str(p2.expanded)
  print(s)
  fileObject.write(s + '\n')
  s = "Avg Nodes Expanded by Player 1 per move: " + str(float(p1.expanded)/float(len(p1.movesMade)))
  print(s)
  fileObject.write(s + '\n')
  s = "Avg Nodes Expanded by Player 2 per move: " + str(float(p2.expanded)/float(len(p2.movesMade)))
  print(s)
  fileObject.write(s + '\n')
  s = "Avg Time Spent by Player 1 per move: " + str(float(p1.time)/float(len(p1.movesMade)))
  print(s)
  fileObject.write(s + '\n')
  s = "Avg Time Spent by Player 2 per move: " + str(float(p2.time)/float(len(p2.movesMade)))
  print(s)
  fileObject.write(s + '\n')
  fileObject.close()


class Turn(object):

  def __init__(self, p1, p2):
    self.p1 = p1
    self.p2 = p2
    self.turn = True

  def switch(self):
    if self.turn == True:
      self.turn = False
    else:
      self.turn = True

  def get(self):
    if self.turn == True:
      return self.p1
    return self.p2

  def getopp(self):
    if self.turn == True:
      return self.p2
    return self.p1


class Player(object):

  def __init__(self, name, algo, playerNum, limit):
    self.name = name
    self.num = playerNum
    self.algo = algo #1 is for minimax, 2 is for alphabeta
    self.limit = limit
    self.expanded = 0
    self.score = 0
    self.moved = 0
    self.movesMade = []
    self.time = 0

  def execute(self, board, depth, turn):
    if self.algo == 1:
      self.minimax(board,depth,True,turn.get().score,turn.getopp().score,turn)
    else:
      self.alphabeta(board,depth,True,turn.get().score,turn.getopp().score,turn,-1000000,1000000)

  def minimax(self, board, depth, maxPlayer, st1, st2, turn):
    if depth == 0 or board.turns == 36:
      if self.limit %2 == 0:
        turn.get().expanded += 1
      else:
        turn.getopp().expanded += 1
      return turn.get().score - turn.getopp().score
    best = -1000000 if maxPlayer == True else 1000000
    for i in range(6):
      for j in range(6):
        if board.owners[i][j] == 0:
          board.update([i,j],False,turn,depth)
          new1 = turn.get().score+board.add if maxPlayer == True else turn.get().score-board.sub
          new2 = turn.getopp().score+board.add if maxPlayer == False else turn.getopp().score-board.sub
          turn.switch()
          value = self.minimax(board,depth-1,not maxPlayer,new1,new2,turn)
          turn.switch()
          best = max(best,value) if maxPlayer == True else min(best,value)
          if depth == self.limit and best == value:
            del optimal[:]
            optimal.append([i,j])
          board.clearupdate([i,j],turn,depth)
    return best

  def alphabeta(self, board, depth, maxPlayer, st1, st2, turn, alpha, beta):
    if depth == 0 or board.turns == 36:
      if self.limit %2 == 0:
        turn.get().expanded += 1
      else:
        turn.getopp().expanded += 1
      return turn.get().score - turn.getopp().score
    if maxPlayer == True:
      for i in range(6):
        for j in range(6):
          if board.owners[i][j] == 0:
            board.update([i,j],False,turn,depth)
            new1 = turn.get().score+board.add if maxPlayer == True else turn.get().score-board.sub
            new2 = turn.getopp().score+board.add if maxPlayer == False else turn.getopp().score-board.sub
            turn.switch()
            value = self.alphabeta(board,depth-1,not maxPlayer,new1,new2,turn,alpha,beta)
            turn.switch()
            if value > alpha:
              alpha = value
              if depth == self.limit:
                del optimal[:]
                optimal.append([i,j])
            board.clearupdate([i,j],turn,depth)
            if beta <= alpha:
              break
        if beta <= alpha:
          break
      return alpha
    else:
      for i in range(6):
        for j in range(6):
          if board.owners[i][j] == 0:
            board.update([i,j],False,turn,depth)
            new1 = turn.get().score+board.add if maxPlayer == True else turn.get().score-board.sub
            new2 = turn.getopp().score+board.add if maxPlayer == False else turn.getopp().score-board.sub
            turn.switch()
            value = self.alphabeta(board,depth-1,not maxPlayer,new1,new2,turn,alpha,beta)
            turn.switch()
            if value < beta:
              beta = value
              if depth == self.limit:
                del optimal[:]
                optimal.append([i,j])
            board.clearupdate([i,j],turn,depth)
            if beta <= alpha:
              break
        if beta <= alpha:
          break
      return beta


class Board(object):

  def __init__(self, weights, owners):
    self.weights = weights
    self.reset = owners
    self.owners = owners
    self.turns = 0
    self.add = 0
    self.sub = 0

  def owner(self, current):
    return self.owners[current[0]][current[1]]

  def weight(self, current):
    return self.weights[current[0]][current[1]]

  def setOwner(self, current, player):
    if type(player) is int:
      self.owners[current[0]][current[1]] = 0
    else:
      self.owners[current[0]][current[1]] = player.num

  def neighbors(self, current, player):
    if up(current) != False:
      if self.owners[current[0]-1][current[1]] == player.num:
        return True
    if down(current) != False:
      if self.owners[current[0]+1][current[1]] == player.num:
        return True
    if left(current) != False:
      if self.owners[current[0]][current[1]-1] == player.num:
        return True
    if right(current) != False:
      if self.owners[current[0]][current[1]+1] == player.num:
        return True
    return False

  def takeNeighbors(self, current, turn, depth):
    self.take(up(current),turn,depth)
    self.take(down(current),turn,depth)
    self.take(left(current),turn,depth)
    self.take(right(current),turn,depth)

  def take(self, current, turn, depth):
    if current == False or self.owner(current) != turn.getopp().num:
      return
    else:
      if self.owner(current) == turn.getopp().num:
        self.sub += self.weight(current)
      self.add += self.weight(current)
      self.setOwner(current, turn.get())
      if type(depth) is int:
        tree[depth].append(current)

  def update(self, current, actual, turn, depth):
    self.add = 0
    self.sub = 0
    if self.turns == 36 or self.owner(current) != 0:
      return False
    self.turns += 1
    self.add += self.weight(current)
    self.setOwner(current,turn.get())
    if type(depth) is int:
      del tree[depth-1][:]
    if self.neighbors(current,turn.get()) == True:
      self.takeNeighbors(current,turn,depth-1)
    if actual == True:
      turn.get().score += self.add
      turn.getopp().score -= self.sub
    return True

  def clearupdate(self, current, turn, depth):
    self.turns -= 1
    self.setOwner(current, 0)
    for i in range(len(tree[depth-1])):
      self.setOwner(tree[depth-1][i],turn.getopp())
    self.add = 0
    self.sub = 0

  def reset(self):
    self.owners = self.reset[:]
    self.turns = 0
    self.add = 0
    self.sub = 0


#Main Function
if __name__== '__main__':
  print("Local Search for Friends in Forest Hide & Seek Game With Standard Input Given")
  for i in range(5):
    hns('input.txt')
    print("")
  print("")
  print("Part 3 Game Executions")
  for i in range(2,4):
    for j in range(2,6):
      print("")
      candy(i,j)
      print("")
