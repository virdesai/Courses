#############################
## Homework 4              ##
## Name:Vir Desai          ##
## Collaborators:None      ## 
#############################

import sys, os, random, Image, svmutil

class Run(object):
    def __init__(self):
        self.images = [Class("bags_clutch"),Class("bags_hobo"),
                       Class("womens_flats"),Class("womens_pumps")]#instantiates all the images in each of the classes for training and testing values
        self.hml = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]#hml confusion matrix
        self.vml = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]#vml confusion matrix
        self.matrix,self.hist,self.vector,self.problem = [],[],[],[]
        self.mr,self.ml = [],[]
        self.hmr = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]#hmr confusion matrix
        self.vmr = [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]#vmr confusion matrix
        self.train()#training all the models with all the classifications
        self.test()#testing all the test data with all the trained models
        print "HML: " + str(self.hml)#all print statements below are just visualizations for the confusion matrix and overall accuracy percentages
        print "HML: " + str(float(self.hml[0][0])/float(sum(self.hml[0]))*100) + ", " + str(float(self.hml[1][1])/float(sum(self.hml[1]))*100) + ", " + str(float(self.hml[2][2])/float(sum(self.hml[2]))*100) + ", " + str(float(self.hml[3][3])/float(sum(self.hml[3]))*100)
        print "HML AVG: " + str((float(self.hml[0][0])/float(sum(self.hml[0]))+float(self.hml[1][1])/float(sum(self.hml[1]))+float(self.hml[2][2])/float(sum(self.hml[2]))+float(self.hml[3][3])/float(sum(self.hml[3])))/float(4)*100)
        print "HMR: " + str(self.hmr)
        print "HMR: " + str(float(self.hmr[0][0])/float(sum(self.hmr[0]))*100) + ", " + str(float(self.hmr[1][1])/float(sum(self.hmr[1]))*100) + ", " + str(float(self.hmr[2][2])/float(sum(self.hmr[2]))*100) + ", " + str(float(self.hmr[3][3])/float(sum(self.hmr[3]))*100)
        print "HMR AVG: " + str((float(self.hmr[0][0])/float(sum(self.hmr[0]))+float(self.hmr[1][1])/float(sum(self.hmr[1]))+float(self.hmr[2][2])/float(sum(self.hmr[2]))+float(self.hmr[3][3])/float(sum(self.hmr[3])))/float(4)*100)
        print "VML: " + str(self.vml)
        print "VML: " + str(float(self.vml[0][0])/float(sum(self.vml[0]))*100) + ", " + str(float(self.vml[1][1])/float(sum(self.vml[1]))*100) + ", " + str(float(self.vml[2][2])/float(sum(self.vml[2]))*100) + ", " + str(float(self.vml[3][3])/float(sum(self.vml[3]))*100)
        print "VML AVG: " + str((float(self.vml[0][0])/float(sum(self.vml[0]))+float(self.vml[1][1])/float(sum(self.vml[1]))+float(self.vml[2][2])/float(sum(self.vml[2]))+float(self.vml[3][3])/float(sum(self.vml[3])))/float(4)*100)
        print "VMR: " + str(self.vmr)
        print "VMR: " + str(float(self.vmr[0][0])/float(sum(self.vmr[0]))*100) + ", " + str(float(self.vmr[1][1])/float(sum(self.vmr[1]))*100) + ", " + str(float(self.vmr[2][2])/float(sum(self.vmr[2]))*100) + ", " + str(float(self.vmr[3][3])/float(sum(self.vmr[3]))*100)
        print "VMR AVG: " + str((float(self.vmr[0][0])/float(sum(self.vmr[0]))+float(self.vmr[1][1])/float(sum(self.vmr[1]))+float(self.vmr[2][2])/float(sum(self.vmr[2]))+float(self.vmr[3][3])/float(sum(self.vmr[3])))/float(4)*100)


    def convert(self):
        count = True
        self.hist = []
        self.vector = []
        self.problem = []
        true = []
        for i in self.images:#all training histogram and vector data together
            for j in i.trainDataHist:
                self.hist.append(j)
                if count == True:
                    true.append(1)
                else:
                    true.append(0)
            for k in i.trainDataVector:
                self.vector.append(k)
            count = False
        self.problem.append(svmutil.svm_problem(true,self.hist))#hist
        self.problem.append(svmutil.svm_problem(true,self.vector))#vector

    def train(self):
        for i in range(4):
            self.convert()
            #rbf
            param1 = svmutil.svm_parameter("-t 2 -b 1 -c 1 -g 0.001")
            param2 = svmutil.svm_parameter("-t 2 -b 1 -c 0.1 -g 0.001")
            self.mr.append(svmutil.svm_train(self.problem[0], param1))#hist
            self.mr.append(svmutil.svm_train(self.problem[1], param2))#vector
            #linear
            param3 = svmutil.svm_parameter("-t 0 -b 1 -c 0.1")
            param4 = svmutil.svm_parameter("-t 0 -b 1 -c 0.01")
            self.ml.append(svmutil.svm_train(self.problem[0], param3))#hist
            self.ml.append(svmutil.svm_train(self.problem[1], param4))#vector
            self.images = self.images[1:]+self.images[:1]

    def test(self):
        count = 0
        d = 1
        for i in self.images:
            for j in i.testDataHist:
                ra = []
                la = []
                for l in range(len(self.mr)/2):#from the RBF models
                    a,b,c = svmutil.svm_predict([d],[j],self.mr[l*2],'-b 1')
                    ra.append(c)
                for l in range(len(self.ml)/2):#from the Linear models
                    a,b,c = svmutil.svm_predict([d],[j],self.ml[l*2],'-b 1')
                    la.append(c)
                self.hmr[count][ra.index(max(ra))]+=1#populating the confusion matricies
                self.hml[count][la.index(max(la))]+=1
            for k in i.testDataVector:
                ra = []
                la = []
                for l in range(len(self.mr)/2):#from the RBF models
                    a,b,c = svmutil.svm_predict([d],[k],self.mr[l*2+1],'-b 1')
                    ra.append(c)
                for l in range(len(self.ml)/2):#from the Linear models
                    a,b,c = svmutil.svm_predict([d],[k],self.ml[l*2+1],'-b 1')
                    la.append(c)
                self.vmr[count][ra.index(max(ra))]+=1
                self.vml[count][la.index(max(la))]+=1
            count+=1
            d = 0


class Class(object):
    def __init__(self, klass):
        self.klass = klass
        self.train = []
        self.test = []
        self.trainDataHist = []
        self.trainDataVector = []
        self.testDataHist = []
        self.testDataVector = []
        self.enum()
        self.data()

    def data(self):
        path = os.getcwd()
        path = os.path.join(path,"images")
        true = os.path.join(path,"img_" + self.klass + "_")
        for i in range(len(self.train)):
            img = NewImage(true + str(self.train[i]) + ".jpg")
            self.trainDataHist.append(img.histo)#getting all training data for class from histograms
            self.trainDataVector.append(img.vector)#getting all training data for class from tiny image vector
        for i in range(len(self.test)):
            img = NewImage(true + str(self.test[i]) + ".jpg")
            self.testDataHist.append(img.histo)#getting all testing data for class frrom histogram
            self.testDataVector.append(img.vector)#getting all testing data for class from tiny image vector

    def enum(self):
        self.train = random.sample(range(2,1000),int(989*0.7))#randomizing which image numbers for class will be training and testing
        hund = [100,200,300,400,500,600,700,800,900]#eliminating the images that dont exist in the files
        self.train = [x for x in self.train if x not in hund]
        for i in range(2,1000):
            if i not in self.train and i not in hund:
                self.test.append(i)


class NewImage(object):
    def __init__(self, image):
        self.image = Image.open(image)
        self.vector = {}
        self.hist = []
        self.histo = {}
        self.create()
        self.vectorize()
        self.histogram()

    def create(self):
        self.image = self.image.resize((32,32), Image.ANTIALIAS)#resizing image to 32x32

    def vectorize(self):
        count = 0
        for i in list(self.image.getdata()):#RGB data of opened image
            count += 1
            if type(i) == tuple:
                for j in i:
                    self.vector[count] = j
            else:#when image is not a tuple I use the current value 3 times
                self.vector[count] = i
                self.vector[count+1] = i
                self.vector[count+2] = i
                count += 2

    def histogram(self):
        for i in range(8):
            self.hist.append([])
            for j in range(8):
                self.hist[i].append([])
                for k in range(8):
                    self.hist[i][j].append(0)#end of creating the 8x8x8 3D array of 0s
        for i in range(1,len(self.vector)/3+1):#populizing image histogram values in proper bins
            self.hist[self.vector[i]/32][self.vector[i+1]/32][self.vector[i+2]/32]+=1
        count = 0
        for i in self.hist:
            for j in i:
                for k in j:
                    count += 1
                    self.histo[count] = k

#Main Function
if __name__== '__main__':
    Run()#automatically runs and starts program
