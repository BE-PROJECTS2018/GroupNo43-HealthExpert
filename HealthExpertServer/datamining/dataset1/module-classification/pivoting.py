import pandas as pd

df = pd.read_csv('../clean_dataset.csv')
d = pd.pivot_table(df, index='disease', columns='symptom', values='values')
d.fillna(0, inplace=True)
# index = d.index.union(d.columns)
# d = d.reindex(index=index, columns=index, fill_value=0)
d.to_csv('pivoted_data.csv')
