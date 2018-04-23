import csv


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


import os


def printData():
    with open(os.path.join(__file__, "../", "rollup_dataset.csv"), 'rt', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            print(row)
