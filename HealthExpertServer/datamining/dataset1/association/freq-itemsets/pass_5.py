import csv

symptomlist = ['asthenia', 'nightmare', 'drowsiness', 'pain']

disease_ = []
disease_list = []

for s in symptomlist:
    with open("../rollup_dataset.csv", "rt", encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        for row in reader:
            if s in row:
                disease_.append((row[0], s))
                disease_list.append(row[0])

with open("node.csv", "wt", encoding='utf-8', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Id", "Label", "Attribute"])
    for n in symptomlist:
        writer.writerow([n, n, "Symptom"])
    for d in disease_list:
        writer.writerow([d, d, "Disease"])

with open("edge.csv", "wt", encoding='utf-8', newline='') as csvfile:
    writer = csv.writer(csvfile)

    writer.writerow(["Source", "Target"])
    for d in disease_:
        dis, sym = d
        writer.writerow([dis, sym])
