import csv
import random

def fileWriter(st):
    stringCon= str(st)
    file = open("sku.csv", "a")
    file.write(stringCon +'\n')
    file.close()

def sky():
    listRandom = random.sample(range(1,90000), 2000)
    print(listRandom)
    for i in listRandom:
        fileWriter(i)

sky()
