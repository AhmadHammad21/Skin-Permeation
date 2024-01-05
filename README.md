# Skin Permeability Prediction using Machine Learning

Our study delves into the exciting realm of transdermal drug delivery, a growing preference for patients due to its convenience. Recognizing the challenge posed by the skin's natural barrier to drug permeation, we employed advanced machine learning models to predict skin permeability solely based on descriptors computationally calculated from the chemical structure of the molecule.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Dataset](#dataset)
- [Methodology](#methodology)
- [Results](#results)
- [Discussion](#discussion)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)
- [License](#license)

## Introduction

This repository contains code and resources for predicting skin permeability of drug molecules using machine learning models. The predictive models are trained on a diverse dataset of molecules, encompassing drugs, xenobiotics, and other chemical compounds.

## Features

- Predictive Models: Utilize advanced AI algorithms, including MLR, ensemble methods, and artificial neural networks.
- Dataset: In vitro human skin permeation parameters for diverse molecules, including drugs and xenobiotics.
- Molecular Descriptors: Calculated using the [Chemistry Development Kit (CDK)](https://cdk.github.io/) for one-dimensional and two-dimensional representations.

## Installation

Clone the repository to your local machine using:

#### Create Environment
    conda create --name myenv python=3.10

#### Activate Environment
    conda activate myenv

#### Install requirements
    pip install -r requirements.txt  


## Dataset

The dataset used in this study was obtained from [Cheruvu et al.](https://doi.org/10.1016/j.dib.2022.108242), providing in vitro human skin permeation parameters for a diverse range of molecules.

## Methodology

1. Molecular Descriptor Calculation: Employ the CDK to generate descriptors for molecules.
2. AI Models: Develop regression models using [Scikit-Learn](https://scikit-learn.org/stable/) with algorithms such as MLR, ensemble methods, and artificial neural networks.
3. Cluster Analysis: Predict FDA-approved drug permeability, conduct K-means clustering, and use the [Anatomical Therapeutic Chemical (ATC)](https://www.who.int/tools/atc-ddd-toolkit/atc-classification) code for drug classification.
![Workflow Methodology](./images/workflow.jpg)

## Results

Explore the outcomes of the developed predictive models, including R-squared, RMSE, and MAE metrics. Visualize the systematic study design workflow chart and the clustering of FDA-approved drugs based on their descriptors.

## Discussion

Engage in discussions around the implications of the study results, potential applications in drug development, and future research directions.

## Contributing

Contributions are welcome! Fork the repository, make your changes, and submit a pull request.

## Acknowledgments

The study acknowledges the work of [Cheruvu et al](https://doi.org/10.1016/j.dib.2022.108242). for providing the foundational skin permeability dataset. We acknowledge [DrugBank](https://go.drugbank.com/releases/latest) for providing the dataset of FDA-approved drugs.

## License

This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/license/afl-3-0-php/) file for details.

