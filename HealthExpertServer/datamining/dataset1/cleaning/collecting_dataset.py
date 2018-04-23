import pandas as pd
import numpy as np

dirty_dataset_html = pd.read_html("http://people.dbmi.columbia.edu/~friedma/Projects/DiseaseSymptomKB/index.html")
data_dirty_np = np.asarray(dirty_dataset_html[0])

dirty_dataset = pd.DataFrame(data=data_dirty_np[1:, 1:], index=data_dirty_np[1:, 0], columns=data_dirty_np[0, 1:])
dirty_dataset.to_csv("raw/dirty_dataset.csv")
