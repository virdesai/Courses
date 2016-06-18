#############################
## Homework 3              ##
## Name:Vir Desai          ##
## Collaborators:None      ## 
#############################

import sys, os, math, random

class Training(object):
    def __init__(self, k, m):
        self.count = {"spam":0,"ham":0,"total":0}
        self.countFiles = {"spam":0,"ham":0,"total":0}
        self.klassWords = {"spam":{},"ham":{}}
        self.words = {}
        self.lexicon = {}
        self.wordProb = {}
        self.classProb = {}
        self.k = k
        self.m = m
        self.parseLocation()
        self.probability()

    def addWord(self, klass, word):
        self.klassWords[klass][word] = self.klassWords[klass][word]+1 if self.klassWords[klass].has_key(word) else 1
        self.count["total"] += 1
        self.count[klass] += 1
        self.words[word] = self.words[word]+1 if self.words.has_key(word) else 1
        if self.words[word] >= self.k:
            self.lexicon[word] = self.words[word]

    def parseFile(self, fileName, klass):
        with open(fileName) as f:
            for line in f:
                for word in line.rstrip('\r\n'):
                    self.addWord(klass, word.lower())

    def parseLocation(self):
        path = os.getcwd()
        uFiles = os.listdir(path)
        folders = []
        for thing in uFiles:
            if '.' not in thing:
                folders.append(thing)
        for folder in folders:
            if "training" in folder:
                newPath = os.path.join(path,folder)
                files = os.listdir(newPath)
                klass = ""
                if "ham" in folder:
                    klass = "ham"
                elif "spam" in folder:
                    klass = "spam"
                for f in files:
                    self.countFiles[klass] += 1
                    self.countFiles["total"] += 1
                    self.parseFile(os.path.join(newPath,f),klass)
        self.probClass()

    def probability(self):
        denom = self.m * len(self.lexicon)
        for word in self.lexicon:
            s = self.klassWords["spam"][word] if self.klassWords["spam"].has_key(word) else 0
            h = self.klassWords["ham"][word] if self.klassWords["ham"].has_key(word) else 0
            spam = (float)(self.m+s)/(float)(denom+self.count["spam"])
            ham = (float)(self.m+h)/(float)(denom+self.count["ham"])
            self.wordProb[word] = {"spam":spam,"ham":ham}

    def probClass(self):
        self.classProb["spam"] = (float)(self.countFiles["spam"])/(float)(self.countFiles["total"])
        self.classProb["ham"] = (float)(self.countFiles["ham"])/(float)(self.countFiles["total"])


class Testing(object):
    def __init__(self, k, m):
        self.train = Training(k, m)
        self.testDoc = {}
        self.count = {"spam":0,"ham":0,"total":0}
        self.correct = {"spam":0,"ham":0,"total":0}
        self.parseLocation()

    def parseLocation(self):
        path = os.getcwd()
        uFiles = os.listdir(path)
        folders = []
        for thing in uFiles:
            if '.' not in thing:
                folders.append(thing)
        for folder in folders:
            if "testing" in folder:
                newPath = os.path.join(path,folder)
                files = os.listdir(newPath)
                klass = ""
                if "ham" in folder:
                    klass = "ham"
                elif "spam" in folder:
                    klass = "spam"
                for f in files:
                    self.count[klass] += 1
                    self.count["total"] += 1
                    out = self.postProb(os.path.join(newPath,f))
                    if out == klass:
                        self.correct[klass] += 1
                        self.correct["total"] += 1
        self.output()

    def postProb(self, fileName):
        sProb = math.log(self.train.classProb["spam"],2)
        hProb = math.log(self.train.classProb["ham"],2)
        with open(fileName) as f:
            for line in f:
                for word in line.rstrip('\r\n'):
                    if self.train.lexicon.has_key(word): #eliminating unknown words
                        sProb += math.log(self.train.wordProb[word]["spam"],2)
                        hProb += math.log(self.train.wordProb[word]["ham"],2)
        self.testDoc[fileName] = {"spam":sProb,"ham":hProb}
        if sProb >= hProb:
            return "spam"
        return "ham"

    def output(self):
        print "Overall Accuracy: " + str(((float)(self.correct["total"])/(float)(self.count["total"]))*100)
        print "Spam Accuracy: " + str(((float)(self.correct["spam"])/(float)(self.count["spam"]))*100)
        print "Ham Accuracy: " + str(((float)(self.correct["ham"])/(float)(self.count["ham"]))*100) + "%\n"

#Main Function
if __name__== '__main__':
    k = random.sample(range(1,41),10)
    m = range(1,7)
    for i in k:
        for j in m:
            print "k: " + str(i) + ", m: " + str(j)
            Testing(i,j)
