# Trial 1
* PaDEL-Descriptor 2.21
* All PaDEL 1D/2D descriptors are used and All 12 fingerprints.
* Salts are neutralized and other parameters are set to default.
* All the molecules are used including the salts and water.
## Method
1. Use all the descriptors in output.csv except the first one "Name".
1. Combine them with inputs from data-original.csv. The molecules data in both files are ordered in the same order.
1. Include only `Texpi` from data-original.csv file and `logKp` as a label.

This means that all descriptors from file output.csv and `Texpi` from data-original.csv are used to build a model that can predict `logKp`.