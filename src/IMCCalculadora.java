import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMCCalculadora {
    public static void main(String[] args) {
            JFrame frame = new IMCCalculadoraFrame();
            frame.setTitle("Calculadora IMC");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setSize(400, 170);
            frame.setResizable(false);
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
        JPanel inputPanel = criarInput();
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = criarBotao();
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }

    private JPanel criarInput() {
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

private JPanel criarBotao() {
    JPanel panel = new JPanel();

    // Define o layout do painel para FlowLayout
    panel.setLayout(new FlowLayout());

    // Cria um botão (JButton) 
    JButton calcularButton = new JButton("Calcular");

   
    // será notificado quando o botão for clicado. 
    calcularButton.addActionListener(new CalcularListener());

    // Adiciona o botão  ao painel.
    panel.add(calcularButton);

    // Retorna o painel 
    return panel;
}

    private class CalcularListener implements ActionListener {
    // Esta classe interna implementa a interface ActionListener, o que significa que ela lida com eventos de ação, como cliques em botões.

    @Override
    public void actionPerformed(ActionEvent e) {
        // O método actionPerformed é chamado quando ocorre um evento de ação, neste caso, quando o botão de cálculo de IMC é clicado.

        try {
            // Tenta realizar o cálculo do IMC com base nos valores de peso e altura inseridos.

            double peso = Double.parseDouble(pesoField.getText());
            // Obtém o valor de peso do campo de texto pesoField e converte para um valor double.

            double altura = Double.parseDouble(alturaField.getText());
            // Obtém o valor de altura do campo de texto alturaField e converte para um valor double.

            double imc = calcularIMC(peso, altura);
            // Calcula o IMC com base nos valores de peso e altura usando uma função calcularIMC, logo abaixo.

            String categoria = getIMCCategoria(imc);
            // Obtém a categoria do IMC com base no valor do IMC usando uma função getIMCCategoria.

            resultField.setText(String.format("IMC: %.2f (%s)", imc, categoria));
            // Define o formato correto do valor, 2 casas após a virgula

        } catch (NumberFormatException ex) {
            // significa que os valores inseridos não puderam ser convertidos em números válidos.

            resultField.setText("Valor inválido");

        } catch (ArithmeticException ex) {
            // significa que houve uma tentativa de divisão por zero (altura igual a zero).

            resultField.setText("Altura não pode ser zero");

        }
    }
}

        private double calcularIMC(double peso, double altura) {
            if (altura == 0) {
                throw new ArithmeticException("Altura não pode ser zero");
            }
            return peso / (altura * altura); //CALCULO DO IMC
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

