package com.nlk;

import com.nlk.gui.App;
import com.nlk.model.Theme;

public class Main {
    public static void main(String[] args) {
        new App.Builder()
                .setWindowSize(500, 200)
                .setTitle("Chou-Fasman Predictor")
                .generateCenter()
                .setTheme(Theme.LIGHT)
                .build();
    }
}