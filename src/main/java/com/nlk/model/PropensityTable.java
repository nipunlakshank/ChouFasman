package com.nlk.model;

import java.util.HashMap;

public class PropensityTable {
    private static final HashMap<Character, double[]> propensityTable = new HashMap<>();

    static {
        // Initialize with -helix, -sheet, and turn propensities for each amino acid
        propensityTable.put('A', new double[]{1.42, 0.83, 0.66});
        propensityTable.put('C', new double[]{0.70, 1.19, 1.19});
        propensityTable.put('D', new double[]{1.01, 0.54, 1.46});
        propensityTable.put('E', new double[]{1.51, 0.37, 0.74});
        propensityTable.put('F', new double[]{1.13, 1.38, 0.60});
        propensityTable.put('G', new double[]{0.57, 0.75, 1.56});
        propensityTable.put('H', new double[]{1.00, 0.87, 0.95});
        propensityTable.put('I', new double[]{1.08, 1.60, 0.47});
        propensityTable.put('K', new double[]{1.16, 0.74, 1.01});
        propensityTable.put('L', new double[]{1.34, 1.22, 0.59});
        propensityTable.put('M', new double[]{1.45, 1.05, 0.60});
        propensityTable.put('N', new double[]{0.67, 0.89, 1.56});
        propensityTable.put('P', new double[]{0.57, 0.55, 1.52});
        propensityTable.put('Q', new double[]{1.11, 1.10, 0.98});
        propensityTable.put('R', new double[]{0.98, 0.93, 0.95});
        propensityTable.put('S', new double[]{0.77, 0.75, 1.43});
        propensityTable.put('T', new double[]{0.83, 1.19, 0.96});
        propensityTable.put('V', new double[]{1.06, 1.70, 0.50});
        propensityTable.put('W', new double[]{1.08, 1.37, 1.01});
        propensityTable.put('Y', new double[]{0.69, 1.47, 1.14});
    }

    public static double[] getPropensities(char aminoAcid) {
        return propensityTable.getOrDefault(aminoAcid, new double[]{0.0, 0.0, 0.0});
    }
}
