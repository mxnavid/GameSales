import csv
import random

def fileWriter(st):
    stringCon= str(st)
    file = open("age.csv", "a")
    file.write(stringCon +'\n')
    file.close()


def age():
   
    for i in range(0,1001):
        print("i am "+ str(i))
        fileWriter(random.randint(5,60))
    

age()
