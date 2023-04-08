# Trial 1
* CDK descriptors 2.8
* Salts are removed and molecules are neutralized.
* All the molecules are used excluding water.
## Method
1. Use the SMILES and temperature from smiles.csv as input for GNN.
1. Combine them with inputs from data-original.csv. The molecules data in both files are ordered in the same order.
1. Include only `logKp` from data-original.csv file as a label.
1. Exclude water (lines 447-477 if we started counting from 1 including the header row).

See the data-original.csv file to check that you are exculding the right part. The molecules name should be water.