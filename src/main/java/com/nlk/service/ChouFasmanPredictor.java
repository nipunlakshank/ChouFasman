package com.nlk.service;

import com.nlk.model.PropensityTable;

import java.util.Arrays;

public class ChouFasmanPredictor {
    private static final int HELIX_WINDOW = 6;
    private static final int SHEET_WINDOW = 5;
    private static final int TURN_WINDOW = 4;
    private static final double HELIX_THRESHOLD = 6.0;
    private static final double SHEET_THRESHOLD = 4.2;
    private static final double TURN_THRESHOLD = 3.0;

    public static String predictStructure(String sequence) {
        char[] structure = new char[sequence.length()];
        Arrays.fill(structure, 'C'); // Initialize as coil ('C')

        // Identify alpha-helices
        for (int i = 0; i <= sequence.length() - HELIX_WINDOW; i++) {
            double helixPropensitySum = 0.0;
            for (int j = i; j < i + HELIX_WINDOW; j++) {
                helixPropensitySum += PropensityTable.getPropensities(sequence.charAt(j))[0];
            }
            if (helixPropensitySum >= HELIX_THRESHOLD) {
                for (int j = i; j < i + HELIX_WINDOW; j++) {
                    if (structure[j] == 'C') {
                        structure[j] = 'H';
                    }
                }
                i += HELIX_WINDOW - 1;
            }
        }

        // Identify beta-sheets with conflict check
        for (int i = 0; i <= sequence.length() - SHEET_WINDOW; i++) {
            double sheetPropensitySum = 0.0;
            for (int j = i; j < i + SHEET_WINDOW; j++) {
                sheetPropensitySum += PropensityTable.getPropensities(sequence.charAt(j))[1];
            }
            if (sheetPropensitySum >= SHEET_THRESHOLD) {
                for (int j = i; j < i + SHEET_WINDOW; j++) {
                    if (structure[j] == 'C') {
                        structure[j] = 'E';
                    }
                }
                i += SHEET_WINDOW - 1;
            }
        }

        // Identify turns based on turn propensity
        for (int i = 0; i <= sequence.length() - TURN_WINDOW; i++) {
            double turnPropensitySum = 0.0;
            for (int j = i; j < i + TURN_WINDOW; j++) {
                turnPropensitySum += PropensityTable.getPropensities(sequence.charAt(j))[2];
            }
            if (turnPropensitySum >= TURN_THRESHOLD) {
                for (int j = i; j < i + TURN_WINDOW; j++) {
                    if (structure[j] == 'C') {
                        structure[j] = 'T';
                    }
                }
                i += TURN_WINDOW - 1;
            }
        }

        // Fill in remaining coil regions explicitly
        for (int i = 0; i < structure.length; i++) {
            if (structure[i] == 'C') {
                if ((i > 0 && structure[i - 1] == 'H') || (i < structure.length - 1 && structure[i + 1] == 'H')) {
                    structure[i] = 'C';
                }
            }
        }

        return new String(structure);
    }
}