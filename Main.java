package src;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static double backupValue = 0;
    private static double currentValue = 0;
    private static String currentSign = "";
    private static boolean firstnumb = true;
    private static boolean isResult = false;
    private static double currentGuess = 0;
    private static double newGuess = 0;
    private static int guessCounter = 0;

    public static void main(String[] args) {

        // Das Fenster erstellen:
        JFrame calcFrame = new JFrame("Calculator");

        // Einstellungen für das Fenster
        calcFrame.setResizable(false);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setSize(500, 600);
        calcFrame.setVisible(true);

        // Das ZahlenFeld erstellen:
        JLabel currentNumbs = new JLabel("0", JLabel.RIGHT);

        // Die Buttons erstellen:
        JButton[] buttons = { new JButton("%"), new JButton("CE"), new JButton("C"), new JButton("PI"),
                new JButton("1/x"), new JButton("x²"), new JButton("x^(1/2)"), new JButton("/"),
                new JButton("7"), new JButton("8"), new JButton("9"), new JButton("x"),
                new JButton("4"), new JButton("5"), new JButton("6"), new JButton("-"),
                new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+"),
                new JButton("+/-"), new JButton("0"), new JButton(","), new JButton("=")
        };

        // Ein Grid-Panel zur Anordnung der Buttons hinzufügen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));

        // Die Borders für das Button Panel hinzufügen

        Border spaceBorder = BorderFactory.createEmptyBorder(10, 20, 20, 20);
        buttonPanel.setBorder(spaceBorder);

        Border raisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border loweredBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        // Hinzufügen der Buttons zum Button Panel + Border einstellen:

        for (JButton button : buttons) {
            button.setBorder(raisedBorder);
            buttonPanel.add(button);

            button.getModel().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (button.getModel().isPressed()) {
                        button.setBorder(loweredBorder);
                    } else {
                        button.setBorder(raisedBorder);
                    }
                }

            });

            // Aktionen für Buttons hinzufügen:

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String buttonText = button.getText();

                    switch (buttonText) {

                        case "PI":
                            if (firstnumb == true) {
                                currentNumbs.setText("3.141592653589");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "3.141592653589");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "C":
                            currentNumbs.setText("0");
                            currentValue = 0;
                            backupValue = 0;
                            firstnumb = true;
                            break;
                        case "CE":
                            currentNumbs.setText("0");
                            currentValue = 0;
                            firstnumb = true;
                            break;
                        case "%":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentValue = currentValue / 100;
                            currentNumbs.setText("" + currentValue);
                            break;

                        case "1/x":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentValue = 1 / currentValue;
                            currentNumbs.setText("" + currentValue);
                            break;
                        case "x²":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentValue = currentValue * currentValue;
                            currentNumbs.setText("" + currentValue);
                            break;
                        case "x^(1/2)":
                            currentGuess = 1;
                            do {
                                guessCounter += 1;
                                newGuess = (currentGuess + (currentValue / currentGuess)) / 2;
                                currentGuess = newGuess;
                            } while (currentGuess * currentGuess != currentValue && guessCounter < 100);
                            guessCounter = 0;
                            currentValue = currentGuess;
                            currentNumbs.setText("" + currentValue);
                            break;
                        case "/":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentSign = "/";
                            backupValue = currentValue;
                            currentValue = 0;
                            currentNumbs.setText("0");
                            firstnumb = true;
                            break;
                        case "x":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentSign = "x";
                            backupValue = currentValue;
                            currentValue = 0;
                            currentNumbs.setText("0");
                            firstnumb = true;
                            break;
                        case "-":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentSign = "-";
                            backupValue = currentValue;
                            currentValue = 0;
                            currentNumbs.setText("0");
                            firstnumb = true;
                            break;
                        case "+":
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            currentSign = "+";
                            backupValue = currentValue;
                            currentValue = 0;
                            currentNumbs.setText("0");
                            firstnumb = true;
                            break;
                        case "=":
                            switch (currentSign) {
                                case "+":
                                    currentValue = backupValue + currentValue;
                                    currentNumbs.setText(Double.toString(currentValue));
                                    break;
                                case "-":
                                    currentValue = backupValue - currentValue;
                                    currentNumbs.setText(Double.toString(currentValue));
                                    break;
                                case "x":
                                    currentValue = backupValue * currentValue;
                                    currentNumbs.setText(Double.toString(currentValue));
                                    break;
                                case "/":
                                    currentValue = backupValue / currentValue;
                                    currentNumbs.setText(Double.toString(currentValue));
                                    break;
                            }
                            break;

                        case "9":
                            if (firstnumb == true) {
                                currentNumbs.setText("9");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "9");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "8":
                            if (firstnumb == true) {
                                currentNumbs.setText("8");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "8");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "7":
                            if (firstnumb == true) {
                                currentNumbs.setText("7");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "7");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "6":
                            if (firstnumb == true) {
                                currentNumbs.setText("6");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "6");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "5":
                            if (firstnumb == true) {
                                currentNumbs.setText("5");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "5");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "4":
                            if (firstnumb == true) {
                                currentNumbs.setText("4");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "4");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "3":
                            if (firstnumb == true) {
                                currentNumbs.setText("3");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "3");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "2":
                            if (firstnumb == true) {
                                currentNumbs.setText("2");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "2");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "1":
                            if (firstnumb == true) {
                                currentNumbs.setText("1");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "1");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;
                        case "0":
                            if (firstnumb == true) {
                                currentNumbs.setText("0");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + "0");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;

                        case "+/-":
                            currentValue -= currentValue * 2;
                            currentNumbs.setText(Double.toString(currentValue));
                            isResult = true;
                            break;
                        case ",":
                            if (firstnumb == true) {
                                currentNumbs.setText("0");
                                firstnumb = false;
                            } else {
                                currentNumbs.setText(currentNumbs.getText() + ".");
                            }
                            currentValue = Double.parseDouble(currentNumbs.getText());
                            break;

                    }

                }
            });
        }

        // Hinzufügen des Zahlenfelds und Button Panels
        calcFrame.add(currentNumbs, BorderLayout.NORTH);
        calcFrame.add(buttonPanel);

        // Erstellen der Borders für das Zahlenfeld
        Border outerSpaceBorder = new EmptyBorder(20, 20, 20, 20);
        Border lineBorder = new LineBorder(Color.BLACK, 5);

        // Stil des Zahlenfelds festsetzen
        currentNumbs.setFont(new Font("Arial", Font.BOLD, 50));
        currentNumbs.setBorder(BorderFactory.createCompoundBorder(outerSpaceBorder, lineBorder));

    }

}
