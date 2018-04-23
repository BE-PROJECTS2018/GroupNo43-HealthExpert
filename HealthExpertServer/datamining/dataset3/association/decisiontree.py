import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn import tree
from sklearn.model_selection import train_test_split

import graphviz

data = pd.read_csv('pivoted_data.csv')

print(data.shape)
cols = data.columns.tolist()
cols.remove('disease')
x = data[cols]
y = data.disease
# x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33, random_state=42)
mnb = tree.DecisionTreeClassifier()
mnb = mnb.fit(x, y)
# mnb.score(x_test, y_test)

# mnb_tot = DecisionTreeClassifier()
# mnb_tot = mnb_tot.fit(x, y)
print(mnb.score(x, y))
disease_prediction = mnb.predict(x)
disease_real = y.values
for i in range(0, len(disease_real)):
    if disease_prediction[i] != disease_real[i]:
        print('Pred: {0} Actual:{1}'.format(disease_prediction[i], disease_real[i]))

test_data = pd.read_csv("testing.csv")
testx = test_data[cols]
testy = test_data['disease']
predict = mnb.predict(testx)
print(predict[0])
print(mnb.score(testx, testy))
