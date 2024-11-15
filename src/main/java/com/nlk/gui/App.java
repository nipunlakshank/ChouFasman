package com.nlk.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.nlk.model.Theme;
import com.nlk.service.ChouFasmanPredictor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class App extends JFrame {
    private JTextField tf_sequence;
    private JPanel panel1;
    private JButton clearButton;
    private JButton predictButton;

    private App(Builder builder) {
        init(builder);
    }

    private void init(Builder builder) {
        add(panel1);
        setTitle(builder.title);
        setSize(builder.windowSize);
        if (builder.generateCenter) setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        assert clearButton != null;
        clearButton.addActionListener(e -> tf_sequence.setText(""));

        assert predictButton != null;
        predictButton.addActionListener(e -> {
            String predicted = ChouFasmanPredictor.predictStructure(tf_sequence.getText());
            JTextArea textArea = new JTextArea(predicted);
            textArea.setEditable(false);
            textArea.setLineWrap(false);
            textArea.setCaretPosition(0);
            String[] options = {"Copy and close", "Ok"};
            int option = JOptionPane.showOptionDialog(null, textArea, "Predicted secondary structure", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, null);

            if (option == 0) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(predicted), null);
            }
        });
    }

    public static class Builder {
        private Dimension windowSize;
        private boolean generateCenter;
        private String title;
        private Theme theme;

        public Builder setWindowSize(int width, int height) {
            this.windowSize = new Dimension(width, height);
            return this;
        }

        public Builder generateCenter() {
            this.generateCenter = true;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        public App build() {
            FlatLaf.setup(theme == Theme.DARK ? new FlatDarkLaf() : new FlatLightLaf());
            App app = new App(this);
            app.setVisible(true);
            return app;
        }
    }
}
