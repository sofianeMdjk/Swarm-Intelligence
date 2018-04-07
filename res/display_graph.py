import matplotlib.pyplot as plt
import pandas as pd 
colnames = ['instance', 'max_clause']
data = pd.read_csv("stats.txt", names=colnames)
instances = data.instance.tolist()
max_clauses = data.max_clause.tolist()
plt.xticks(instances, instances);
plt.bar(instances, height= max_clauses)
plt.show()