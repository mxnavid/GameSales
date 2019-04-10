import csv
import random

def fileWriter(st):
    stringCon= str(st)
    file = open("price.csv", "a")
    file.write(stringCon +'\n')
    file.close()

def price():
    for i in range(0,1485):
        print("i am "+ str(i))
        randomFloat = random.uniform(20.00,100.00)
        rounded = round(randomFloat,2)
        fileWriter(rounded)
price()
