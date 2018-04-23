import pandas as pd
import csv

dataset_dis_symp_relationship = pd.read_excel('raw/ncomms5212-s8.xls', sheet_name='dis_symp_relationships')
dataset_disease_list = pd.read_excel('raw/ncomms5212-s8.xls', sheet_name='disease_list')
dataset_disease_terms = pd.read_excel('raw/ncomms5212-s8.xls', sheet_name='disease_terms')
dataset_symptom_list = pd.read_excel('raw/ncomms5212-s8.xls', sheet_name='symptom_list')
dataset_symptom_terms = pd.read_excel('raw/ncomms5212-s8.xls', sheet_name='symptom_terms')

# for symptom_cui3 in dataset_symptom_list:
#     for symptom_cui4 in dataset_symptom_terms:
#         if symptom_cui3[1] == symptom_cui4[1]:
#             dataset_symptom_terms = dataset_symptom_terms.join(dataset_symptom_list, lsuffix="symptom_cui",columns)
dirty_symptom_dataset = dataset_symptom_terms.set_index('symptom_cui').join(
    dataset_symptom_list.set_index('symptom_cui'), lsuffix='_caller')
dirty_disease_dataset = dataset_disease_terms.set_index('disease_cui').join(
    dataset_disease_list.set_index('disease_cui'), lsuffix='_caller')
dataset_dis_symp_relationship = dataset_dis_symp_relationship.set_index('symptom_cui').join(dirty_symptom_dataset,
                                                                                            lsuffix='_caller')
dirty_dataset = dataset_dis_symp_relationship.set_index('disease_cui').join(dirty_disease_dataset, lsuffix='_caller')
dirty_dataset.drop(['rid_caller_caller', 'snomed_code_caller', 'rid_caller', 'rid', 'disease_cui', 'snomed_code',
                    'Number of symptoms'], axis=1, inplace=True)
dirty_dataset.rename(columns={'Terms_caller': 'symptom', 'Terms': 'disease'}, inplace=True)
dirty_dataset.dropna(how="any", inplace=True)
dirty_dataset['values'] = 1
dirty_dataset.to_csv('clean_dataset.csv', columns=['disease', 'symptom', 'Number of diseases', 'values'], index=False)

with open("clean_dataset.csv", "rt", encoding='utf-8') as infile, open("final_dataset.csv", "wt", encoding='utf-8',
                                                               newline='') as outfile:
    reader = csv.reader(infile)
    writer = csv.writer(outfile)
    conversion = set('_"/.$()\'[X]')
    for row in reader:
        newrow = [''.join('' if c in conversion else c for c in entry) for entry in row]
        writer.writerow(newrow)

with open("clean_dataset.csv", "rt", encoding='utf-8') as infile, open("clean_test_dataset.csv", "wt", encoding='utf-8',
                                                               newline='') as outfile:
    reader = csv.reader(infile)
    writer = csv.writer(outfile)
    conversion = set('_"/.$()\'[X]')
    for row in reader:
        newrow = [''.join('' if c in conversion else c for c in entry) for entry in row]
        writer.writerow(newrow)
