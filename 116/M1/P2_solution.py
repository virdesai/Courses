import numpy as np
import pylab as py

### No.1
# Load the Carolina and Opponent data:
# data separated by commas and
# skip the first row of description
data_Carolina = np.loadtxt('Carolina.csv', delimiter=',',skiprows=1)
data_Opponent = np.loadtxt('Opponent.csv', delimiter=',',skiprows=1)

# Column indices in the data (for your convenience)
Indx2PM=0 # 2-point made
Indx2PA=1 # 2-point attempted
Indx3PM=2 # 3-point made
Indx3PA=3 # 3-point attempted
IndxFTM=4 # free throw made
IndxFTA=5 # free throw attempted
IndxOReb=6 # offensive rebound
IndxDReb=7 # defensive rebound
IndxPF=8 # personal foul
IndxAsst=9 # assist
IndxTO=10 # Turn over
IndxBlk=11 # Block
IndxSteal=12 # Steal

# Compute scores Carolina and Opponents earned for the recent 23 games.
# Your code goes here
CarolinaScore=data_Carolina[:,Indx2PM]*2+data_Carolina[:,Indx3PM]*3+data_Carolina[:,IndxFTM]
OpponentScore=data_Opponent[:,Indx2PM]*2+data_Opponent[:,Indx3PM]*3+data_Opponent[:,IndxFTM]

py.figure()
indx_Game = np.arange(1,data_Carolina.shape[0]+1)
py.plot(indx_Game,CarolinaScore,'b-',label='Carolina')
py.plot(indx_Game,OpponentScore,'g-',label='Opponents')
py.ylabel('Score')
py.xlabel('Game index')
py.legend()
py.savefig('score.png')
py.close()

### No.2
CarolinaVictory = np.zeros_like(CarolinaScore)
CarolinaVictory[CarolinaScore - OpponentScore > 0] = 1
CarolinaVictory[CarolinaScore - OpponentScore < 0] = -1
print 'CarolinaVictory=',CarolinaVictory

### No.3
CarolinaVictoryAdj = CarolinaVictory - np.mean(CarolinaVictory)

# 3a.
twoPointAdj = data_Carolina[:,Indx2PM]-np.mean(data_Carolina[:,Indx2PM])
C_twoPoint = np.dot(CarolinaVictoryAdj,twoPointAdj)/ (np.sqrt(np.sum(CarolinaVictoryAdj**2))*np.sqrt(np.sum(twoPointAdj**2)))

# 3b.
threePointAdj = data_Carolina[:,Indx3PM]-np.mean(data_Carolina[:,Indx3PM])
C_threePoint = np.dot(CarolinaVictoryAdj,threePointAdj)/ (np.sqrt(np.sum(CarolinaVictoryAdj**2))*np.sqrt(np.sum(threePointAdj**2)))

# 3c.
offensivePower = data_Carolina[:,IndxOReb]-data_Opponent[:,IndxDReb]
offensivePowerAdj = offensivePower - np.mean(offensivePower)
C_offensivePower = np.dot(CarolinaVictoryAdj,offensivePowerAdj)/ (np.sqrt(np.sum(CarolinaVictoryAdj**2))*np.sqrt(np.sum(offensivePowerAdj**2)))

# 3d.
defensivePower = data_Carolina[:,IndxDReb]-data_Opponent[:,IndxOReb]
defensivePowerAdj = defensivePower - np.mean(defensivePower)
C_defensivePower = np.dot(CarolinaVictoryAdj,defensivePowerAdj)/ (np.sqrt(np.sum(CarolinaVictoryAdj**2))*np.sqrt(np.sum(defensivePowerAdj**2)))

# 3e.
assist = data_Carolina[:,IndxAsst]
assistAdj = data_Carolina[:,IndxAsst]-np.mean(data_Carolina[:,IndxAsst])
C_assist = np.dot(CarolinaVictoryAdj,assistAdj)/ (np.sqrt(np.sum(CarolinaVictoryAdj**2))*np.sqrt(np.sum(assistAdj**2)))

Corr = np.array([C_twoPoint,C_threePoint,C_offensivePower,C_defensivePower,C_assist])
print Corr
