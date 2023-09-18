import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraMilhasQuilometros {
    public static void main(String[] args) {
        //Construindo o Frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new CalculatorFrame();
            frame.setTitle("Milhas para Quilometros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}

class CalculatorFrame extends JFrame {
    private JTextField milhaField;
    private JTextField kmField;

    public CalculatorFrame() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel milhaLabel = new JLabel("Milhas:");
        milhaField = new JTextField(10);

        JLabel kmLabel = new JLabel("Quilômetros:");
        kmField = new JTextField(10);
        kmField.setEditable(false);

            JButton ConverterBotao = new JButton("Converter");
        ConverterBotao.addActionListener(new ConvertButtonListener());
        ConverterBotao.setBackground(Color.WHITE);
        ConverterBotao.setForeground(Color.black);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(milhaLabel, constraints);

        constraints.gridx = 1;
        add(milhaField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel(), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(ConverterBotao, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(kmLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(kmField, constraints);

        pack();
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double milhas = Double.parseDouble(milhaField.getText());
                double quilometros = milhas * 1.60934;
                kmField.setText(String.format("%.2f", quilometros));
            } catch (NumberFormatException ex) {
                kmField.setText("Valor Inválido");
            }
        }
    }
}
