import pandas as pd
import csv

with open('../clean_dataset.csv', 'r', encoding='utf-8', newline='') as f:
    r = csv.reader(f)
    data = [line for line in r]
with open('../clean_dataset.csv', 'w', encoding='utf-8', newline='') as f:
    w = csv.writer(f)
    w.writerow(['disease', 'symptom', 'count','values'])
    w.writerows(data)

clean_datasets = pd.read_csv("../clean_dataset.csv", index_col='disease')
rollup_datasets = clean_datasets.groupby('disease')['symptom'].apply(
    lambda x: pd.DataFrame(x.values)).unstack().reset_index()
# rollup_datasets.columns = rollup_datasets.columns.droplevel()
# rollup_datasets.columns = ['disease','symptom']
rollup_datasets.set_index('disease', inplace=True)
rollup_datasets.to_csv('rollup_dataset.csv', header=None)
