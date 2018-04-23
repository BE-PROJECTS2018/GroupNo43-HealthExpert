import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import train_test_split
import os


def classification():
    data = pd.read_csv(os.path.join(__file__, "../", "training.csv"))

    print(data.shape)
    cols = data.columns.tolist()
    cols.remove('disease')
    x = data[cols]
    y = data.disease
    # x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33, random_state=42)
    mnb = MultinomialNB()
    mnb = mnb.fit(x, y)
    # mnb.score(x_test, y_test)

    print(mnb.score(x, y))
    disease_prediction = mnb.predict(x)
    disease_real = y.values
    for i in range(0, len(disease_real)):
        if disease_prediction[i] != disease_real[i]:
            print('Pred: {0} Actual:{1}'.format(disease_prediction[i], disease_real[i]))

    test_data = pd.read_csv(os.path.join(__file__, "../", "testing.csv"))
    testx = test_data[cols]
    testy = test_data['disease']
    predict = mnb.predict(testx)
    print(predict[0])
    score = 0.0
    while score == 0.0:
        score = mnb.score(testx, testy)
    print(score)
    score = round(score * 100, 1) #0.2112121212 -> 2.1
    res = "Disease" + " - "+"Probability of having disease\n\n"
    res += str(predict[0] + " - " +str(score)+"%")
    return res
