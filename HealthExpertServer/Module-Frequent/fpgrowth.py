import pyfpgrowth

# 1 -Headache
# 2 -Vomit
# 3 -Itching
# 'BodyPain' -BodyPain
# 5 -StomachAche
transactions = [['Headache', 'Vomit', 'StomachAche'],
                ['Vomit', 'BodyPain'],
                ['Vomit', 'Itching'],
                ['Headache', 'Vomit', 'BodyPain'],
                ['Headache', 'Itching'],
                ['Vomit', 'Itching'],
                ['Headache', 'Itching'],
                ['Headache', 'Vomit', 'Itching', 'StomachAche'],
                ['Headache', 'Vomit', 'Itching']]
patterns = pyfpgrowth.find_frequent_patterns(transactions, 2)
print("Pattern", patterns)
rules = pyfpgrowth.generate_association_rules(patterns, 0.8)
print("Rules", rules)
