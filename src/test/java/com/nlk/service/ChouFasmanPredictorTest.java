package com.nlk.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChouFasmanPredictorTest {

    @Test
    void predictStructure() {
        String sequence = "MADEEKLPPGWEKRMSAAEEIRAGEAGVGIVNSGQGMVVTNVHNLQEDKLFGLFGGGSGASGDR";
        String expectedOutput = "HHHHHHCCTCHHHHHHHHHHHHHHHHHEEEEETHEEEEEEEEEHHHHHEEEEECCTCTCCCTCC";

        // Call the method to test
        String predictedStructure = ChouFasmanPredictor.predictStructure(sequence);

        // Assert that the entire predicted structure matches the expected output
        assertEquals(expectedOutput, predictedStructure, "The predicted structure does not match the expected structure.");

        // Additional specific segment tests for more precision
        assertEquals("HHHHHHHHHHHH", predictedStructure.substring(0, 12), "First helix region mismatch.");
        assertEquals("EEEEEEEEEEEE", predictedStructure.substring(12, 24), "First sheet region mismatch.");
        assertEquals("HHHHHHHHHHHH", predictedStructure.substring(24, 36), "Second helix region mismatch.");
        assertEquals("EEEEEEEEEEEE", predictedStructure.substring(36, 48), "Second sheet region mismatch.");
        assertEquals("CCCCCCCCCCCCCCCCCCCCCCCCCCCC", predictedStructure.substring(48), "Final coil region mismatch.");
    }
}