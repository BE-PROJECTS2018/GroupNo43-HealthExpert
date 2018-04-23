import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

data = pd.read_csv('test.csv')

print(data.shape)
data = data.fillna(0)
data.head(5)
cols = data.columns.tolist()
cols.remove('disease')
x = data[cols]
y = data.disease
# x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33, random_state=42)
mnb = RandomForestClassifier()
mnb = mnb.fit(x, y)
mnb.score(x, y)
print(mnb.score(x, y))
disease_prediction = mnb.predict(x)
disease_real = y.values
for i in range(0, len(disease_real)):
    if disease_prediction[i] != disease_real[i]:
        print('Pred: {0} Actual:{1}'.format(disease_prediction[i], disease_real[i]))

test_data = pd.read_csv("testing.csv")
testx = test_data[cols]
testy = test_data['disease']

score = 0
while score == 0.0:
    score = mnb.score(testx, testy)

predict = mnb.predict(testx)
print(predict[0])

print(score)

