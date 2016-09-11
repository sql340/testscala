#!/Users/shang/anaconda/bin/python
#!-*-coding:utf-8-*-
# File Name: GenData.py
# Author: by sql
# Created Time: 二  8/23 15:28:59 2016
# Description: 
#***************************************

import sys,os
import random
import math

def Hyperplane(x,y,z):
	# 超平面系数
	A = [3,5,-6]
	# 超平面方程
	res = A[0] * x + A[1] * y + A[2] *z
	
	return res

def sigmoid(x):
	return 1.0/(1+math.exp(-x))


def genNormalSample(N,maxN,minN):
	samNum = N
	for i in range(samNum):
		x , y , z = random.uniform(minN, maxN),random.uniform(minN, maxN),random.uniform(minN, maxN)
		getLabel(x,y,z)

def genMixedSample(N,maxN,minN):
	samNum = N
	for i in range(samNum):
		x, z = random.uniform(minN, maxN),random.uniform(minN, maxN)
		y = (-3*x + 6*z)/5
		getLabel(x,y,z)

def getLabel(x,y,z):
	res = sigmoid(Hyperplane(x,y,z))

	if res >= 0.5:
		label = 1
#		sys.stderr.write("{0} 0:{1} 1:{2} 2:{3}".format(label(x , y , z))
	else:
		label = 0
#		sys.stdout.write('\t'.join(['%s']*4) % (label,x , y , z))
#		sys.stdout.write("\n")
	print "{0} 1:{1} 2:{2} 3:{3}".format(label, x, y, z)




def main(N,M):
	normalSamNum = N
	mixedSample = M
	minN = -10; maxN = 10
	genNormalSample(N,maxN,minN)
	genMixedSample(M,maxN,minN)

if __name__ == '__main__':
#	if len(sys.argv) <= 1:
#		print "Usage %s" % "python GenData.py 10000 1> posTrain.txt 2> negTrain.txt"
#		sys.exit(-1)
	n = int(sys.argv[1])
	m = int(sys.argv[2])
	main(n,m)
	
	


