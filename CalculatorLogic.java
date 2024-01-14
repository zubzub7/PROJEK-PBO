class CalculatorLogic {
    private double currentNumber;
    private double result;
    private String currentOperator;
    private boolean isNewInput;
    private String currentExpression;
    private double baseNumber;

    public CalculatorLogic() {
        clear();
    }

    public void inputDigit(int digit) {
        if (isNewInput) {
            currentNumber = digit;
            isNewInput = false;
            currentExpression = String.valueOf(currentNumber);
        } else {
            currentNumber = currentNumber * 10 + digit;
            currentExpression += digit + " ";
            currentExpression = " ";
        }
    }

    public void inputOperator(String operator) {
        if(!isNewInput) {
            currentExpression = " " + operator + " ";
        }
        if (operator.equals("C")) {
            clear();
        } else if (operator.equals("x\u02B8")) {
            baseNumber = currentNumber;
            currentOperator = operator;
            isNewInput = true;
        } else if (!operator.equals("\u003D")) {
            calculateTotal();
            currentOperator = operator;
            result = currentNumber;
            isNewInput = true;
        } else {
            calculateTotal();
            currentOperator = "";
            isNewInput = true;
        }
    }

    public void calculateTotal() {
        if (!currentOperator.isEmpty()) {
            double operand2 = currentNumber;
            currentExpression = " ";
            currentExpression += result;
            switch (currentOperator) {
                case "sin":
                    result = Math.sin(Math.toRadians(operand2));
                    currentExpression = "sin(" + operand2 + ") ";
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(operand2));
                    currentExpression = "cos(" + operand2 + ") ";
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(operand2));
                    currentExpression = "tan(" + operand2 + ") ";
                    break;
                case "log":
                    result = Math.log(operand2);
                    currentExpression = "log(" + operand2 + ") ";
                    break;
                case "!": // Faktorial
                    currentExpression = operand2 + "! ";
                    if (operand2 < 0) {
                        currentExpression = "Error! ";
                        result = Double.NaN;
                    } else {
                        result = 1;
                        for (int i = 2; i <= operand2; i++) {
                            result *= i;
                        }
                    }
                    break;
                case "\u03C0": // Kali phi
                    result = Math.PI * operand2;
                    currentExpression = operand2 + " x \u03C0 ";
                    break;
                case "x\u00B2": // Pangkat 2
                    currentExpression = operand2 + "\u00B2 ";
                    result = Math.pow(operand2, 2);
                    break;
                case "x\u00B3": // Pangkat 3
                    currentExpression = operand2 + "\u00B3 ";
                    result = Math.pow(operand2, 3);
                    break;
                case "\u221A": // Akar kuadrat
                    result = Math.sqrt(operand2);
                    currentExpression = "\u221A" + operand2 + " ";
                    break;
                case "\u0025":
                    result %= operand2; 
                    currentExpression = currentExpression + " % " + operand2 + " ";
                    break;
                case "\u2797": // Pembagian
                    try {
                        if (operand2 != 0) {
                            result /= operand2;
                        } else {
                            throw new ArithmeticException("Error: Division By Zero ");
                        }
                    } catch (ArithmeticException e) {
                        String errorMessage = e.getMessage();
                        currentExpression = errorMessage;
                        result = Double.NaN;
                    }
                    currentExpression = currentExpression + " / " + operand2 + " ";
                    break;
                case "x\u02B8": // x pangkat y
                    result = Math.pow(baseNumber, operand2);
                    currentExpression = baseNumber + " ^ " + operand2 + " ";
                    break;
                case "\u2A09": // Perkalian
                    result *= operand2;
                    currentExpression = currentExpression + " x " + operand2 + " ";
                    break;
                case "\u2796": // Pengurangan
                    result -= operand2;
                    currentExpression = currentExpression + " - " + operand2 + " ";
                    break;
                case "\u2795": // Penjumlahan
                    result += operand2;
                    currentExpression = currentExpression + " + " + operand2 + " ";
                    break;
                default:
                    break;
            }
            currentNumber = result;
        }
    }

    public void clear() {
        currentNumber = 0;
        result = 0;
        currentOperator = "";
        currentExpression = "";
        isNewInput = true;
    }

    public String getCurrentNumberAsString() {
        return String.valueOf(currentNumber);
    }

    public String getResultAsString() {
        return String.valueOf(result);
    }

    public String getExpressionAsString() {
        return currentExpression;
    }
}
