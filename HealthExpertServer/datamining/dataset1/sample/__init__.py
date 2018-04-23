import csv
with open(
        "../association/rollup_dataset.csv",
        "r", encoding='utf-8') as csvfile:
    reader = csv.reader(csvfile)
    for row in reader:
        print(row)
