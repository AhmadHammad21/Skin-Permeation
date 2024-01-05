# Skin Permeation

Our study delves into the exciting realm of transdermal drug delivery, a growing preference for patients due to its convenience. Recognizing the challenge posed by the skin's natural barrier to drug permeation, we employed advanced machine learning models to predict skin permeability solely based on descriptors computationally calculated from the chemical structure of the molecule.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Dataset](#dataset)
- [Methodology](#methodology)
- [Results](#results)
- [Discussion](#discussion)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## Introduction

This GitHub repository presents a comprehensive study on predicting skin permeability of FDA-approved drugs through the development of AI-based predictive models. The research focuses on utilizing molecular descriptors and machine learning techniques to enhance the understanding of physicochemical properties and permeability patterns, with potential applications in drug candidate selection for transdermal formulations.

## Features

- Predictive Models: Utilize advanced AI algorithms, including MLR, ensemble methods, and artificial neural networks.
- Dataset: In vitro human skin permeation parameters for diverse molecules, including drugs and xenobiotics.
- Molecular Descriptors: Calculated using the Chemistry Development Kit (CDK) for one-dimensional and two-dimensional representations.

## Installation

Clone the repository to your local machine using:

#### Create Environment
    conda create --name myenv python=3.10

#### Activate Environment
    conda activate myenv

#### Install requirements
    pip install -r requirements.txt  

## Usage

#### Navigate to the project directory
    cd your-repository

#### Execute the main script
    python main_script.py

#### Explore the results and visualizations generated

## Dataset

The dataset used in this study was obtained from [Cheruvu et al.](https://doi.org/10.1016/j.dib.2022.108242), providing in vitro human skin permeation parameters for a diverse range of molecules.

## Methodology

1. Molecular Descriptor Calculation: Employ the CDK to generate descriptors for molecules.
2. AI Models: Develop regression models using Scikit-Learn with algorithms such as MLR, ensemble methods, and artificial neural networks.
3. Cluster Analysis: Predict FDA-approved drug permeability, conduct K-means clustering, and use the Anatomical Therapeutic Chemical (ATC) code for drug classification.
![Workflow Methodology](./images/workflow.jpg)

## Results

Explore the outcomes of the developed predictive models, including R-squared, RMSE, and MAE metrics. Visualize the systematic study design workflow chart and the clustering of FDA-approved drugs based on their descriptors.

## Discussion

Engage in discussions around the implications of the study results, potential applications in drug development, and future research directions.

## Contributing

Contributions are welcome! Fork the repository, make your changes, and submit a pull request.

## Acknowledgments

The study acknowledges the work of [Cheruvu et al](https://doi.org/10.1016/j.dib.2022.108242). for providing the foundational skin permeability dataset.


<!-- ```bash
# Example installation steps
git clone https://github.com/your-username/your-repo.git
cd your-repo
pip install -r requirements.txt -->
