/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.rami.dg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.ConnectivityChecker;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.io.SMILESWriter;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

/**
 *
 * @author Rami Manaf Abdullah
 */
public class SMILESCleaner {

    private List<List<String>> generateCleanSMILESTable() throws InvalidSmilesException, FileNotFoundException, IOException, CDKException {
        List<List<String>> cleanSmilesTable = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim().parse(new FileReader("data-original.csv"));
        List<String> header = new ArrayList<>();
        header.add("SMILES");
        header.add("Texpi");
        cleanSmilesTable.add(header);
        CDKHydrogenAdder hydrogenAdder = CDKHydrogenAdder.getInstance(DefaultChemObjectBuilder.getInstance());
        int x = 0;
        for (CSVRecord record : records) {
            System.out.println(x);
            x++;
            List<String> cleanSmiles = new ArrayList<>();
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
            StringWriter stringWriter = new StringWriter();
            SMILESWriter writer = new SMILESWriter(stringWriter);
            writer.writeAtomContainer(molecule);
            cleanSmiles.add(stringWriter.toString().replace("\n", ""));
            cleanSmiles.add(record.get(13));
            cleanSmilesTable.add(cleanSmiles);
        }
        return cleanSmilesTable;
    }

    private void writeCSV(List<List<String>> cleanSmilesTable) throws IOException {
        File data = new File("smiles.csv");
        data.createNewFile();
        CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .print(new FileWriter(data));
        for (int i = 0; i < cleanSmilesTable.size(); i++) {
            csvPrinter.printRecord(cleanSmilesTable.get(i).toArray());
        }
        csvPrinter.flush();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SMILESCleaner generator = new SMILESCleaner();
        generator.writeCSV(generator.generateCleanSMILESTable());
    }

}
