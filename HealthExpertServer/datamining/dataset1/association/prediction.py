import csv, os
import pandas as pd
from ..classification.nb_classifier import classification
from ..cleaning.clean_dataset import clean_dataset
import numpy as np

def get_disease_new(symptoms):
    disease_list = []
    print("ENTER")

    # Creating Training dataset
    with open(os.path.join(__file__, "../", "../classification/training_dataset.csv"), 'rt',
              encoding='utf-8') as myfile:
        test = csv.reader(myfile)
        with open(os.path.join(__file__, "../", "rollup_dataset.csv"), "rt", encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)
            for row in reader:
                row_clean = [i for i in row if i] #Eliminates white spaces
                for sym in symptoms: #[['cough','vomit']['vomit','headache']]
                    disease = []
                    if sym in row_clean:
                        disease.append(row_clean[0])
                        disease.append(sym)
                        disease_list.append(disease)

            print(disease_list)

        new_list = []
        for trow in test:
            sample = [trow[0], trow[1]]

            if sample not in disease_list:
                trow[3] = 0
                new_list.append(trow)
            else:
                new_list.append(trow)
    with open(os.path.join(__file__, "../", '../classification/training_dataset.csv'), 'w', encoding='utf-8',
              newline='') as file:
        writer = csv.writer(file)
        writer.writerow(['disease', 'symptom', 'count', 'values'])
        for row_t in new_list:
            writer.writerow(row_t)
        file.close()

    disease_list = []

    # Creating Testing dataset
    with open(os.path.join(__file__, "../", "../classification/testing_dataset.csv"), 'rt', encoding='utf-8') as myfile:
        test = csv.reader(myfile)
        with open(os.path.join(__file__, "../", "rollup_dataset.csv"), "rt", encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)
            for row in reader:
                row_clean = [i for i in row if i]
                for sym in symptoms:
                    disease = []
                    if sym in row_clean:
                        disease.append(row_clean[0])
                        disease.append(sym)
                        disease_list.append(disease)

            print(disease_list)

        new_list = []
        for trow in test:
            sample = [trow[0], trow[1]]

            if sample not in disease_list:
                trow[3] = 0
                new_list.append(trow)
            else:
                new_list.append(trow)
    with open(os.path.join(__file__, "../", '../classification/testing_dataset.csv'), 'w', encoding='utf-8',
              newline='') as file:
        writer = csv.writer(file)
        writer.writerow(['disease', 'symptom', 'count', 'values'])
        for row_t in new_list:
            writer.writerow(row_t)
        file.close()



#Association Rule
def Apriori_gen(Itemset, lenght):
    canditate = []
    for i in range(0, lenght):
        element = str(Itemset[i])
        for j in range(i + 1, lenght):
            element1 = str(Itemset[j])
            if element[0:(len(element) - 1)] == element1[0:(len(element1) - 1)]:
                unionset = element[0:(len(element) - 1)] + element1[len(element1) - 1] + element[
                    len(element) - 1]  # Combine (k-1)-Itemset to k-Itemset
                unionset = ''.join(sorted(unionset))  # Sort itemset by dict order
                canditate.append(unionset)
    return canditate

#Frequent Item sets
def Apriori_prune(Ck, minsupport):
    L = []
    for i in Ck:
        if Ck[i] >= minsupport:
            L.append(i)
    return sorted(L)


def Apriori_count_subset(Canditate, Canditate_len):
    Lk = dict()
    file = open(os.path.join(__file__, "../", "buckets_new.csv"), 'r', encoding='utf-8')
    for l in file:
        l = str(l.split(","))
        count = 0
        for i in range(0, Canditate_len):
            key = str(Canditate[i])
            if key not in Lk:
                Lk[key] = 0
            flag = True
            for k in key:
                if k not in l:
                    flag = False
            if flag:
                Lk[key] += 1
    file.close()
    return Lk


def predict():
    clean_dataset()
    minsupport = 2
    C1 = {}
    file = open(os.path.join(__file__, "../", "buckets_new.csv"), 'r')
    """Count one canditate"""
    for line in file:
        print(line)
        for item in line.split(","):
            if item in C1:
                C1[item] += 1
            else:
                C1[item] = 1
    file.close()
    sorted(C1.keys())
    print(C1)
    L1 = Apriori_prune(C1, minsupport)
    L = Apriori_gen(L1, len(L1))
    print('====================================')
    print('Frequent 1-itemset is', L1)
    print('====================================')
    k = 2
    buckets = []

    with open(os.path.join(__file__, "../", "buckets_new.csv"), 'r') as csvfile:
        reader = csv.reader(csvfile)



        for row in reader:
            buckets.append(row)
    print(buckets)
    while L != []:
        C = Apriori_count_subset(L, len(L))
        fruquent_itemset = Apriori_prune(C, minsupport)
        print('====================================')
        print('Frequent', k, '-itemset is', fruquent_itemset)
        print('====================================')
        L = Apriori_gen(fruquent_itemset, len(fruquent_itemset))
        k += 1

    # Transforming Training & Testing dataset for Pivoting
    get_disease_new(L1)

    # Pivoting Training dataset for Classification
    df = pd.read_csv(os.path.join(__file__, "../", "../classification/training_dataset.csv"))
    d = pd.pivot_table(df, index='disease', columns='symptom', values='values')
    d.fillna(0, inplace=True)
    d.to_csv(os.path.join(__file__, "../", "../classification/training.csv"))

    # Pivoting Testing dataset for Classification
    df = pd.read_csv(os.path.join(__file__, "../", "../classification/testing_dataset.csv"))
    d = pd.pivot_table(df, index='disease', columns='symptom', values='values')
    d.fillna(np.NaN, inplace=True)
    d.to_csv(os.path.join(__file__, "../", "../classification/testing.csv"))

    df = pd.read_csv(os.path.join(__file__, "../", "../classification/testing.csv"),index_col=['disease'])
    df.replace(0.0,np.NaN,inplace=True)
    df.dropna(how='all',inplace=True)
    df.replace(np.NaN, 0.0, inplace=True)
    df.to_csv(os.path.join(__file__, "../", "../classification/testing.csv"))

    return classification()
