import pandas as pd

clean_datasets = pd.read_csv("../final_dataset.csv",index_col='disease')
rollup_datasets = clean_datasets.groupby('disease')['symptom'].apply(lambda x: pd.DataFrame(x.values)).unstack().reset_index()
# rollup_datasets.columns = rollup_datasets.columns.droplevel()
# rollup_datasets.columns = ['disease','symptom']
rollup_datasets.set_index('disease',inplace=True)
rollup_datasets.to_csv('rollup_dataset.csv',header=None)