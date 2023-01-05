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
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.DescriptorEngine;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.molecular.AromaticAtomsCountDescriptor;
import org.openscience.cdk.qsar.descriptors.molecular.LongestAliphaticChainDescriptor;
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
 * @author 20180053
 */
public class DescriptorsGenerator {

    private final List<String> descriptorClasses = Arrays.asList(new String[]{
        "org.openscience.cdk.qsar.descriptors.molecular.ALOGPDescriptor",
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

    public DescriptorsGenerator() {
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
                .withTrim().parse(new FileReader("data.csv"));
        List<String> header = new ArrayList<>();
        header.add("SMILES");
        CDKHydrogenAdder hydrogenAdder = CDKHydrogenAdder.getInstance(DefaultChemObjectBuilder.getInstance());
        boolean addHeaders = false;
        int x = 0;
        for (CSVRecord record : records) {
            System.out.println(x);
            x++;
            List<String> descriptors = new ArrayList<>();
            descriptors.add(record.get(2));
            List<String> list = cache.get(record.get(2));
            if (list != null) {
                moleculesDescriptors.add(list);
            } else {
                IAtomContainer molecule = new SmilesParser(DefaultChemObjectBuilder.getInstance()).parseSmiles(record.get(2));
                AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(molecule);
                Aromaticity.cdkLegacy().apply(molecule);
                hydrogenAdder.addImplicitHydrogens(molecule);
                AtomContainerManipulator.convertImplicitToExplicitHydrogens(molecule);
                Cycles.markRingAtomsAndBonds(molecule);
                engine.process(molecule);
                for (Map.Entry<Object, Object> entry : molecule.getProperties().entrySet()) {
                    Object k = entry.getKey();
                    Object v = entry.getValue();
                    if (v instanceof DescriptorValue) {
                        DescriptorValue value = (DescriptorValue) v;
                        if (!addHeaders) {
                            header.addAll(Arrays.asList(value.getNames()));
                        }
                        IDescriptorResult result = value.getValue();
                        if (result instanceof DoubleArrayResult) {
                            for (int i = 0; i < result.length(); i++) {
                                descriptors.add(String.valueOf(((DoubleArrayResult) result).get(i)));
                            }
                        } else if (result instanceof DoubleResult) {
                            descriptors.add(String.valueOf(((DoubleResult) result).doubleValue()));
                        } else if (result instanceof IntegerArrayResult) {
                            for (int i = 0; i < result.length(); i++) {
                                descriptors.add(String.valueOf(((IntegerArrayResult) result).get(i)));
                            }
                        } else if (result instanceof IntegerResult) {
                            descriptors.add(String.valueOf(((IntegerResult) result).intValue()));
                        } else if (result instanceof BooleanResult) {
                            descriptors.add(String.valueOf(((BooleanResult) result).booleanValue()));
                        }
                    }
                }
                if (!addHeaders) {
                    addHeaders = true;
                    moleculesDescriptors.add(header);
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
