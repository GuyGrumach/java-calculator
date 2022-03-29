package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;

public class Controller {

    //organized names for better understanding
    private static final int counter = 1;
    private static final String ZERO_BUTTON = "ZeroButton";
    private static final String ONE_BUTTON = "OneButton";
    private static final String TWO_BUTTON = "TwoButton";
    private static final String THREE_BUTTON = "ThreeButton";
    private static final String FOUR_BUTTON = "FourButton";
    private static final String FIVE_BUTTON = "FiveButton";
    private static final String SIX_BUTTON = "SixButton";
    private static final String SEVEN_BUTTON = "SevenButton";
    private static final String EIGHT_BUTTON = "EightButton";
    private static final String NINE_BUTTON = "NineButton";
    private static final String EMPTY_BUTTON = "EmptyButton";
    private static final String MULTIPLY_BUTTON = "MultipleButton";
    private static final String MINUS_BUTTON = "MinusButton";
    private static final String PLUS_BUTTON = "PlusButton";
    private static final String DIVIDE_BUTTON = "DivideButton";
    private static final String DOT_BUTTON = "dotButton";
    private static final String MINUS_OR_PLUS_BUTTON = "minusOrPlusButton";


    @FXML
    private TextField textField;

    //allocate different actions when the different buttons are pressed
    public void onMouseClick(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()) {
            case ZERO_BUTTON: {
                setOnScreen("0");

                break;
            }
            case ONE_BUTTON: {
                setOnScreen("1");

                break;

            }
            case TWO_BUTTON: {
                setOnScreen("2");

                break;
            }
            case THREE_BUTTON: {
                setOnScreen("3");

                break;
            }
            case FOUR_BUTTON: {
                setOnScreen("4");

                break;
            }
            case FIVE_BUTTON: {
                setOnScreen("5");

                break;
            }
            case SIX_BUTTON: {
                setOnScreen("6");

                break;
            }
            case SEVEN_BUTTON: {
                setOnScreen("7");

                break;
            }
            case EIGHT_BUTTON: {
                setOnScreen("8");

                break;
            }
            case NINE_BUTTON: {
                setOnScreen("9");

                break;
            }
            case PLUS_BUTTON: {
                setOnScreen("+");

                break;
            }
            case MINUS_BUTTON: {
                setOnScreen("-");

                break;
            }
            case MULTIPLY_BUTTON: {
                setOnScreen("*");

                break;
            }
            case DIVIDE_BUTTON: {
                setOnScreen("/");

                break;
            }
            case EMPTY_BUTTON: {
                setOnScreen("");
                break;
            }
            case DOT_BUTTON: {
                setOnScreen(".");
                break;
            }
            case MINUS_OR_PLUS_BUTTON: {
                setOnScreen("change to negative or positive");
                break;

            }
        }
    }

    //algorithm for getting the data
    public void onEqualsClicked() {
        boolean isFirst = true;
        boolean isAfterDot = false;
        double num = 0;
        int counter = 0;
        double multi = 1;
        double[] numArray = new double[5];
        double[] actionArray = new double[5];

        for (int i = 0; i < textField.getText().length(); i++) {
            char curChar = textField.getText().charAt(i);
            if (curChar >= 48 && curChar <= 57) {
                if (isFirst) {
                    num = Character.getNumericValue(curChar);
                    isFirst = false;
                    multi = 10;
                } else if (isAfterDot) {
                    num += multi * Character.getNumericValue(curChar);
                    multi /= 10;
                } else {
                    num = (num * multi) + Character.getNumericValue(curChar);
                }
            } else if (curChar == '.') {
                isAfterDot = true;
                multi = 0.1;
            } else {
                numArray[counter] = num;
                actionArray[counter] = curChar;
                counter++;
                num = 0;
                multi = 1;
                isFirst = true;
                isAfterDot = false;
            }
        }

        numArray[counter] = num;
        double total = performCalculation(numArray, actionArray);
        textField.setText(Double.toString(total));


    }

    //algorithm for calculation
    public double performCalculation(double[] numbers, double[] operators) {
        boolean isFirstCalc = true;
        double total = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            switch ((int) operators[i]) {
                case '+': {
                    if (isFirstCalc) {
                        total += numbers[i] + numbers[i + 1];
                        isFirstCalc = false;
                    } else {
                        total += numbers[i + 1];
                    }
                    break;
                }
                case '-': {
                    if (isFirstCalc) {
                        total += numbers[i] - numbers[i + 1];
                        isFirstCalc = false;
                    } else {
                        total -= numbers[i + 1];
                    }
                    break;
                }
                case '*': {
                    if (isFirstCalc) {
                        total += numbers[i] * numbers[i + 1];
                        isFirstCalc = false;
                    } else {
                        total *= numbers[i + 1];
                    }
                    break;
                }
                case '/': {
                    if (isFirstCalc) {
                        total += numbers[i] / numbers[i + 1];
                        isFirstCalc = false;
                    } else {
                        total /= numbers[i + 1];
                    }
                    break;
                }
                case '.': {

                }
            }
        }
        return total;

    }

    //validate right typing and sets on screen
    private void setOnScreen(String str) {
        if (str.isEmpty()) {
            textField.setText("");
        } else if (textField.getText().isEmpty()) {
            if ((!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/") && !str.contains("."))) {
                textField.setText(textField.getText() + str);
            }
        } else {
            char lastChar = textField.getText().charAt(textField.getText().length() - 1);
            if ((lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '.') && (str.charAt(0) < 48 || str.charAt(0) > 57)) {

            } else {
                textField.setText(textField.getText() + str);
            }
        }

    }


}





