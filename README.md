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
- [License](#license)
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


## Methodology

![Workflow Methodology](./images/workflow.jpg)
<!-- ```bash
# Example installation steps
git clone https://github.com/your-username/your-repo.git
cd your-repo
pip install -r requirements.txt -->
