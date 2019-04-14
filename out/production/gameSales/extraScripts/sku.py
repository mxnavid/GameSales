import csv
import random

def fileWriter(st):
    stringCon= str(st)
    file = open("sku.csv", "a")
    file.write(stringCon +'\n')
    file.close()

def sky():
    listRandom = random.sample(range(70000), 2000)
    print(listRandom)
    for i in listRandom:
        fileWriter(i)

sky()
