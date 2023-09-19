import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraMilhasQuilometros {
    public static void main(String[] args) {
        //Construindo o Frame
            JFrame frame = new CalculatorFrame();
            frame.setTitle("Milhas para Quilometros");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
    }
}

class CalculatorFrame extends JFrame {
    // Declarando variaveis
    private JTextField milhaField;
    private JTextField kmField;

   public CalculatorFrame() {
    // Este é o construtor

    setLayout(new GridBagLayout()); // Define o layout do frame como GridBagLayout (posicionamento flexível).

    GridBagConstraints constraints = new GridBagConstraints(); // Cria um objeto GridBagConstraints para configurar o posicionamento dos componentes no layout.
    constraints.insets = new Insets(5, 5, 5, 5); // Define margens internas de 5 pixels para todos os lados dos componentes.

    // Cria uma label "Milhas" e um campo de texto para inserção de milhas.
    JLabel milhaLabel = new JLabel("Milhas:");
    milhaField = new JTextField(10);

    // Cria uma label "Quilômetros" e um campo de texto para exibição de quilômetros 
    JLabel kmLabel = new JLabel("Quilômetros:");
    kmField = new JTextField(10);
    kmField.setEditable(false);

    // Cria um botão chamado Converter
    JButton ConverterBotao = new JButton("Converter");
    ConverterBotao.addActionListener(new botaoConverter());
    ConverterBotao.setBackground(Color.WHITE); // Define a cor de fundo do botão como branca.
    ConverterBotao.setForeground(Color.black); // Define a cor do texto do botão como preta.

    // Configura as posições dos componentes usando as restrições GridBagConstraints.
    constraints.gridx = 0; 
    constraints.gridy = 0; 
    add(milhaLabel, constraints); // Adiciona a label das milhas

    constraints.gridx = 1; 
    add(milhaField, constraints); // Adiciona o campo de texto de milhas ao frame.

    constraints.gridx = 1; 
    constraints.gridy = 2; 
    add(ConverterBotao, constraints); // Adiciona o botão Converter ao frame.

    constraints.gridx = 0; 
    constraints.gridy = 1; 
    add(kmLabel, constraints); // Adiciona a label dos quilometros

    constraints.gridx = 1; 
    constraints.gridy = 1; 
    add(kmField, constraints); // Adiciona o campo de texto de quilômetros ao frame.

    pack(); // Ajusta automaticamente o tamanho do frame para acomodar todos os componentes.
}


  private class botaoConverter implements ActionListener {
    // Esta classe lida com eventos de ação, como cliques em botões.

    @Override
    public void actionPerformed(ActionEvent e) {
        // O método actionPerformed é chamado quando botão associado a esta classe é clicado.

        try {
            // Tenta realizar a conversão de milhas para quilômetros.

            double milhas = Double.parseDouble(milhaField.getText());
            // Obtém o valor de milhas do campo de texto milhaField e converte para um valor double.

            double quilometros = milhas * 1.60934;
            // Realiza a conversão multiplicando o valor em milhas pela taxa de conversão para quilômetros.

            kmField.setText(String.format("%.2f", quilometros));
            // Define o valor convertido (quilômetros) no campo de texto kmField com formatação para duas casas decimais.

        } catch (NumberFormatException ex) {
            // Se ocorrer uma exceção NumberFormatException, significa que o valor inserido em milhaField não pôde ser convertido em double.

            kmField.setText("Valor Inválido");
            // Define a mensagem "Valor Inválido" no campo de texto kmField.

        }
    }
}

}
