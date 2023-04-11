/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rami.dg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.IImplementationSpecification;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.qsar.DescriptorEngine;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.IDescriptor;
import org.openscience.cdk.qsar.descriptors.molecular.AromaticAtomsCountDescriptor;
import org.openscience.cdk.qsar.result.BooleanResult;
import org.openscience.cdk.qsar.result.DoubleArrayResult;
import org.openscience.cdk.qsar.result.DoubleResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.qsar.result.IntegerArrayResult;
import org.openscience.cdk.qsar.result.IntegerResult;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

/**
 *
 * @author Rami Manaf Abdullah
 */
public class DescriptorsGenerator {

    private final List<String> descriptorClasses = Arrays.asList(new String[]{
        "org.openscience.cdk.qsar.descriptors.molecular.ALOGPDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.APolDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.AcidicGroupCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.AromaticAtomsCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.AromaticBondsCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.AtomCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.AutocorrelationDescriptorCharge",
        "org.openscience.cdk.qsar.descriptors.molecular.AutocorrelationDescriptorMass",
        "org.openscience.cdk.qsar.descriptors.molecular.AutocorrelationDescriptorPolarizability",
        "org.openscience.cdk.qsar.descriptors.molecular.BCUTDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.BPolDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.BasicGroupCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.BondCountDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.CPSADescriptor",//need 3D coordinates
        "org.openscience.cdk.qsar.descriptors.molecular.CarbonTypesDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.ChiChainDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.ChiClusterDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.ChiPathClusterDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.ChiPathDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.EccentricConnectivityIndexDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.FMFDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.FractionalCSP3Descriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.FractionalPSADescriptor",//just devide TPSA/mw
        "org.openscience.cdk.qsar.descriptors.molecular.FragmentComplexityDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.GravitationalIndexDescriptor",//need 3D coordinates
        "org.openscience.cdk.qsar.descriptors.molecular.HBondAcceptorCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.HBondDonorCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.HybridizationRatioDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.JPlogPDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.KappaShapeIndicesDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.KierHallSmartsDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.LargestChainDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.LargestPiSystemDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.LengthOverBreadthDescriptor",//need 3D coordinates
        "org.openscience.cdk.qsar.descriptors.molecular.LongestAliphaticChainDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.MDEDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.MannholdLogPDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.MomentOfInertiaDescriptor",//need 3D coordinates
        "org.openscience.cdk.qsar.descriptors.molecular.PetitjeanNumberDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.PetitjeanShapeIndexDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.RotatableBondsCountDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.RuleOfFiveDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.SmallRingDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.SpiroAtomCountDescriptor",useless and give wrong values
        "org.openscience.cdk.qsar.descriptors.molecular.TPSADescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.VABCDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.VAdjMaDescriptor",
        //        "org.openscience.cdk.qsar.descriptors.molecular.WHIMDescriptor",//need 3D coordinates
        "org.openscience.cdk.qsar.descriptors.molecular.WeightDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.WeightedPathDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.WienerNumbersDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.XLogPDescriptor",
        "org.openscience.cdk.qsar.descriptors.molecular.ZagrebIndexDescriptor"
    });

    private DescriptorEngine engine = new DescriptorEngine(descriptorClasses, DefaultChemObjectBuilder.getInstance());
    private HashMap<String, List<String>> cache = new HashMap<>();

    public DescriptorsGenerator() throws CDKException {
        engine.getDescriptorInstances().forEach((descriptor) -> {
            if (descriptor instanceof AromaticAtomsCountDescriptor) {
//                try {
//                    descriptor.setParameters(new Object[]{true});
//                } catch (CDKException ex) {
//                    Logger.getLogger(DescriptorsGenerator.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        });
    }

    private List<List<String>> generateDescriptorsTable() throws InvalidSmilesException, FileNotFoundException, IOException, CDKException {
        cache.clear();
        List<List<String>> moleculesDescriptors = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim().parse(new FileReader("data-original.csv"));
        List<String> header = new ArrayList<>();
        header.add("SMILES");
        header.add("Texpi");
        for (IDescriptor descriptor : engine.getDescriptorInstances()) {
            for (String descriptorName : descriptor.getDescriptorNames()) {
                header.add(descriptorName);
            }
        }
        moleculesDescriptors.add(header);
        CDKHydrogenAdder hydrogenAdder = CDKHydrogenAdder.getInstance(DefaultChemObjectBuilder.getInstance());
        int x = 0;
        for (CSVRecord record : records) {
            System.out.println(x);
            x++;
            List<String> descriptors = new ArrayList<>();
            descriptors.add(record.get(2));
            descriptors.add(record.get(13));
            List<String> list = cache.get(record.get(2));
            if (list != null) {
                moleculesDescriptors.add(list);
            } else {
                IAtomContainer molecule = new SmilesParser(DefaultChemObjectBuilder.getInstance()).parseSmiles(record.get(2));
                HashMap<IAtom, Integer> formalCharges = new HashMap<>(molecule.getAtomCount());
                //remove salts and hydrates
                if (!ConnectivityChecker.isConnected(molecule)) {
                    IAtomContainerSet molecules = ConnectivityChecker.partitionIntoMolecules(molecule);
                    molecule = molecules.getAtomContainer(0);
                    for (IAtomContainer container : molecules.atomContainers()) {
                        if (container.getBondCount() > molecule.getBondCount()) {
                            molecule = container;
                        }
                    }
                    for (IAtom atom : molecule.atoms()) {
                        formalCharges.put(atom, atom.getFormalCharge());
                        atom.setFormalCharge(0);
                    }
                }
                AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(molecule);
                boolean unknownAtomType = false;
                for (IAtom atom : molecule.atoms()) {
                    if (atom.getAtomTypeName() == null || atom.getAtomTypeName().equals("X")) {
                        unknownAtomType = true;
                        atom.setFormalCharge(formalCharges.get(atom));
                    }
                }
                if (unknownAtomType) {
                    AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(molecule);
                }
                hydrogenAdder.addImplicitHydrogens(molecule);
                AtomContainerManipulator.convertImplicitToExplicitHydrogens(molecule);
                Aromaticity.cdkLegacy().apply(molecule);
                Cycles.markRingAtomsAndBonds(molecule);

                engine.process(molecule);
                for (int i = 0; i < engine.getDescriptorSpecifications().size(); i++) {
                    IImplementationSpecification specification = engine.getDescriptorSpecifications().get(i);
                    Object v = molecule.getProperty(specification);
                    if (v == null) {
                        for (String name : engine.getDescriptorInstances().get(i).getDescriptorNames()) {
                            descriptors.add("NaN");
                        }
                    } else if (v instanceof DescriptorValue) {
                        DescriptorValue value = (DescriptorValue) v;
                        IDescriptorResult result = value.getValue();
                        if (result instanceof DoubleArrayResult) {
                            for (int j = 0; j < result.length(); j++) {
                                descriptors.add(String.valueOf(((DoubleArrayResult) result).get(j)));
                            }
                        } else if (result instanceof DoubleResult) {
                            descriptors.add(String.valueOf(((DoubleResult) result).doubleValue()));
                        } else if (result instanceof IntegerArrayResult) {
                            for (int j = 0; j < result.length(); j++) {
                                descriptors.add(String.valueOf(((IntegerArrayResult) result).get(j)));
                            }
                        } else if (result instanceof IntegerResult) {
                            descriptors.add(String.valueOf(((IntegerResult) result).intValue()));
                        } else if (result instanceof BooleanResult) {
                            descriptors.add(String.valueOf(((BooleanResult) result).booleanValue()));
                        }
                    }
                }
                moleculesDescriptors.add(descriptors);
                cache.put(record.get(2), descriptors);
            }
        }
        return moleculesDescriptors;
    }

    private void writeCSV(List<List<String>> moleculesDescriptors) throws IOException {
        File data = new File("data-descriptors.csv");
        data.createNewFile();
        CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .print(new FileWriter(data));
        for (int i = 0; i < moleculesDescriptors.size(); i++) {
            csvPrinter.printRecord(moleculesDescriptors.get(i).toArray());
        }
        csvPrinter.flush();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        DescriptorsGenerator generator = new DescriptorsGenerator();
        generator.writeCSV(generator.generateDescriptorsTable());
    }

}
