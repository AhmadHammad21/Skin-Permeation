# Dataset
* The dataset brought from [here](https://data.mendeley.com/datasets/8bs7hb2wj2) which was gathered by the [following research](https://www.sciencedirect.com/science/article/pii/S2352340922004449).
* We will use J<sub>Max</sub> and K<sub>p</sub> as a target for the prediction.
* CAS numbers are converted to smiles using PubChem website and some of them was written manually.
* 4 salts and 31 H<sub>2</sub>O results were removed from the dataset.
* 2 Hydrates are converted to their anhydrous forms.
* data-original.csv is used to generate the descriptors

# Results
* CDK 2.8 where used to calculate 226 descriptors.
* The generated descriptors are stored in data-descriptors.csv