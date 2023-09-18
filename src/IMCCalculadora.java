import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMCCalculadora {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new IMCCalculadoraFrame();
            frame.setTitle("Calculadora IMC");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setSize(400, 170);
            frame.setResizable(false);
        });
    }
}

// Contém campos de texto para inserção do peso e altura, e um campo de texto para exibir o resultado do cálculo do IMC.
class IMCCalculadoraFrame extends JFrame {
    private JTextField pesoField;
    private JTextField alturaField;
    private JTextField resultField;

    public IMCCalculadoraFrame() {
        setLayout(new BorderLayout());
        //Criando e Configurando a posições dos botões
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel pesoLabel = new JLabel("Peso (kg):");
        pesoField = new JTextField();

        JLabel alturaLabel = new JLabel("Altura (m):");
        alturaField = new JTextField();

        JLabel resultLabel = new JLabel("Resultado:");
        resultField = new JTextField();
        resultField.setEditable(false);

        panel.add(pesoLabel);
        panel.add(pesoField);
        panel.add(alturaLabel);
        panel.add(alturaField);
        panel.add(resultLabel);
        panel.add(resultField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new CalcularListener());

        panel.add(calcularButton);

        return panel;
    }

    private class CalcularListener implements ActionListener {
        @Override
         //No método actionPerformed, extrai os valores de peso e altura, calcula o IMC e determina a categoria de acordo com o valor do IMC.
        public void actionPerformed(ActionEvent e) {
             //Calcula o IMC e lida com casos onde a altura é zero.
            try {
                double peso = Double.parseDouble(pesoField.getText());
                double altura = Double.parseDouble(alturaField.getText());
                double imc = calcularIMC(peso, altura);
                String categoria = getIMCCategoria(imc);

                resultField.setText(String.format("IMC: %.2f (%s)", imc, categoria));
            } catch (NumberFormatException ex) {
                resultField.setText("Valor inválido");
            } catch (ArithmeticException ex) {
                resultField.setText("Altura não pode ser zero");
            }
        }

        private double calcularIMC(double peso, double altura) {
            if (altura == 0) {
                throw new ArithmeticException("Altura não pode ser zero");
            }
            return peso / (altura * altura);
        }


        // Determina a categoria com base no IMC calculado
        private String getIMCCategoria(double imc) {
            if (imc < 16.0) {
                return "Severamente abaixo do peso";
            } else if (imc < 18.5) {
                return "Abaixo do peso";
            } else if (imc < 25.0) {
                return "Normal";
            } else if (imc < 30.0) {
                return "Sobrepeso";
            } else if (imc < 35.0) {
                return "Obeso Classe I (Moderado)";
            } else if (imc < 40.0) {
                return "Obeso Classe II (grave)";
            } else {
                return "Obeso Classe III (muito grave)";
            }
        }
    }
}
