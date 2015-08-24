#######################################################################################
## Assignment 5                                                                      ##
## Discussion is allowed.                                                            ##
## Copying or sharing code is prohibited.                                            ##
## By typing your name below, you indicate agreement with the UNC Honor Code Pledge, ##
## that you have not given or received unauthorized assistance on this assignment.   ##
## Name:Vir Desai                                                                    ##
## Collaborators:                                                                    ## 
#######################################################################################

import numpy as np
import pylab
from mpl_toolkits.mplot3d import Axes3D

def readPDBfile(filename):
        '''read a PDB file, extract the ATOM lines,
	and return atom number, atom name, residue
	number, and coords for each'''
        anum = []
        aname = []
        resno = []
        coords = []
        with open(filename,'r') as f:
                for line in f:
                        if line[0:6]=='ATOM  ':
                                sn=int(line[6:11])
                                anum.append(sn)
                                atom=line[12:16].upper()
                                aname.append(atom)
                                rsn=int(line[22:26])
                                resno.append(rsn)
                                coords.append([float(line[30:38]),float(line[38:46]),float(line[46:54])])
        anum = np.array(anum)
        aname = np.array(aname)
        coords = np.array(coords)
        resno = np.array(resno)
        f.close()
        return anum, aname, resno, coords

def drawCA(aname, coords):
        '''Plot the Calpha backbone of an atom'''
        fig = pylab.figure()
        ax = Axes3D(fig)
        indx=np.where(aname==' CA ')
        cord=coords[indx]
        x=[]
        y=[]
        z=[]
        for i in cord[:,0]:
                x.append(i)
        for i in cord[:,1]:
                y.append(i)
        for i in cord[:,2]:
                z.append(i)
        x=np.array(x)
        y=np.array(y)
        z=np.array(z)
        ax.plot(x, y, z)
        return fig

def Hbonds(anum, aname, resno, coords):
        '''Find hydrogen bonds'''
        pairs = []
        nit=[i for i, x in enumerate(aname) if x[1] == 'N']
        oxy=[i for i, x in enumerate(aname) if x[1] == 'O']
        x=[]
        y=[]
        z=[]
        for i in coords:
                x.append(i[0])
                y.append(i[1])
                z.append(i[2])
        dist_nit=[]
        dist_oxy=[]
        for i in range(np.size(nit)):
                for j in range(np.size(oxy)):
                        dif=np.sqrt((x[nit[i]]-x[oxy[j]])**2+(y[nit[i]]-y[oxy[j]])**2+(z[nit[i]]-z[oxy[j]])**2)
                        if dif>=2.6 and dif<=3.2:
                                dist_nit.append(i)
                                dist_oxy.append(j)
        resno_x=[]
        resno_y=[]
        for i in range(np.size(dist_nit)):
                differ=abs(resno[nit[dist_nit[i]]]-resno[oxy[dist_oxy[i]]])
                if differ>=2:
                        resno_x.append(dist_nit[i])
                        resno_y.append(dist_oxy[i])
        for i in range(np.size(resno_x)):
                pairs.append([anum[resno_x[i]],anum[resno_y[i]]])
        return pairs


# Do NOT modify the following test code
for protein in ['7HVP', '1GFL']:
        num, name, rn, c = readPDBfile(protein + '.pdb')
        fig = drawCA(name, c)
        fig.savefig(protein+'.png')
        hydrogen_bonding_pairs = Hbonds(num, name, rn, c)
        print 'Number of hydrogen bonding pairs in', protein, 'is', len(hydrogen_bonding_pairs),'.'
