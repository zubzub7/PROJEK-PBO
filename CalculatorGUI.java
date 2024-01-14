import javax.swing.*;
import java.awt.*;

class CalculatorGUI {
    private JFrame frame;
    private JPanel panel;
    private CalculatorLogic calculatorLogic;
    private JLabel displayLabel;
    private JLabel expressionLabel;

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 500);
        frame.setLocation(550, 250);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(new Color(217, 217, 217, 255));

        displayLabel = new JLabel("0", JLabel.RIGHT);
        displayLabel.setFont(new Font("Monospace", Font.BOLD, 30));
        displayLabel.setPreferredSize(new Dimension(frame.getWidth(), 35));
        displayPanel.add(displayLabel, BorderLayout.CENTER);

        expressionLabel = new JLabel("", JLabel.RIGHT);
        expressionLabel.setFont(new Font("Monospace", Font.PLAIN, 15));
        expressionLabel.setForeground(new Color(100, 100, 97));
        expressionLabel.setPreferredSize(new Dimension(frame.getWidth(), 25));
        displayPanel.add(expressionLabel, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(5, 6));
        
        addButton(new TrigonometryOperatorButton("sin"));
        addButton(new TrigonometryOperatorButton("cos"));
        addButton(new TrigonometryOperatorButton("tan"));
        // addButton(new BackspaceButton());
        addButton(new MathOperatorButton("C"));
        addButton(new MathOperatorButton("\u2797")); // Divide
        addButton(new MathOperatorButton("x\u00B2")); // x^2
        addButton(new MathOperatorButton("log"));
        addButton(new NumberButton("7"));
        addButton(new NumberButton("8"));
        addButton(new NumberButton("9"));
        addButton(new MathOperatorButton("\u2A09")); // X
        addButton(new MathOperatorButton("x\u00B3")); // x^3
        addButton(new MathOperatorButton("\u03C0")); // phi
        addButton(new NumberButton("4"));
        addButton(new NumberButton("5"));
        addButton(new NumberButton("6"));
        addButton(new MathOperatorButton("\u2796")); // -
        addButton(new TrigonometryOperatorButton("x\u02B8")); // x^y
        addButton(new NumberButton("1"));
        addButton(new NumberButton("2"));
        addButton(new NumberButton("3"));
        addButton(new MathOperatorButton("\u2795")); // +
        addButton(new MathOperatorButton("\u221A")); // sqrt
        addButton(new MathOperatorButton("!"));
        addButton(new MathOperatorButton("\u0025")); // %
        addButton(new NumberButton("0"));
        addButton(new NumberButton("."));
        addButton(new EqualButton());

        frame.add(displayPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void addButton(JButton button) {
        button.addActionListener((event) -> {
            if (button instanceof Operator) {
                Operator operator = (Operator) button;
                operator.performOperation(calculatorLogic);
            }

            displayLabel.setText(calculatorLogic.getCurrentNumberAsString());
            expressionLabel.setText(calculatorLogic.getExpressionAsString());
        });
        panel.add(button);
    }

    public void setCalculatorLogic(CalculatorLogic calculatorLogic) {
        this.calculatorLogic = calculatorLogic;
    }
}
