import pandas as pd
import csv
from mlxtend.preprocessing import OnehotTransactions
from mlxtend.frequent_patterns import apriori

def get_disease(symptomlist, buckets):
    disease_score = {}
    score = 0
    for bucket in buckets:
        score = set(symptomlist) & set(bucket)
        score = float(len(score)) / float(len(symptomlist)) * 100
        if score > 0:
            print(score)
            disease = get_disease_given_bucket(bucket)
            print(disease)
            disease_score[disease] = score
    print(disease_score)

    with open("disease_probabilityscores_from_symptomlist.csv", "wt", encoding='utf-8', newline='') as csvfile:
        writer = csv.writer(csvfile)

        writer.writerow(["Symptomlist"])
        writer.writerow(symptomlist)
        writer.writerow(["Disease", "Probability scores"])
        for key, value in disease_score.items():
            writer.writerow([key, value])

def get_disease_given_bucket(bucket):
    disease = ""
    print("ENTER")
    with open("rollup_dataset.csv", "rt", encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            row_clean = [i for i in row if i]
            bucket_clean = [i for i in bucket if i]
            if len(row_clean) == (len(bucket_clean) + 1):
                if all(values in row_clean for values in bucket_clean):
                    disease = row_clean[0]
                    break

    return disease


buckets = []

with open("buckets_new.csv") as csvfile:
    reader = csv.reader(csvfile)

    for row in reader:
        buckets.append(row)


oht = OnehotTransactions()
oht_ary = oht.fit(buckets).transform(buckets)
df = pd.DataFrame(oht_ary, columns=oht.columns_)
frequent_itemsets = apriori(df, min_support=0.4, use_colnames=True)
frequent_itemsets = frequent_itemsets [frequent_itemsets['support'] >= 0.7]
frequent_itemsets = frequent_itemsets[frequent_itemsets['itemsets']]
print(list(frequent_itemsets))
#get_disease(frequent_itemsets[0]['itemsets'], buckets)