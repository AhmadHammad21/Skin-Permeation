# Trial 1
* CDK descriptors 2.8
* Salts are removed and molecules are neutralized.
* All the molecules are used including the salts and water.
## Method
1. Use all the descriptors in data-descriptors.csv except the first one "SMILES".
1. Combine them with inputs from data-original.csv. The molecules data in both files are ordered in the same order.
1. Include only `logKp` from data-original.csv file as a label.
