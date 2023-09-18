import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    public static void main(String[] args) {
        //Setando o Frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new CalculadoraFrame();
            frame.setTitle("Calculadora");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(180, 260);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}

class CalculadoraFrame extends JFrame {
    //Criando Variaveis
    private JTextField display;
    private double atualNumero;
    private String atualOperacao;
    private boolean novoNum;

    public CalculadoraFrame() {
        //Definindo Layout e Botões
        setLayout(new FlowLayout());

        display = new JTextField(15);
        display.setEditable(false);
        add(display);

        String[] botaoLabel = {
            "1", "2", "3", "4",
            "5", "6", "7", "8",
            "9", "0", "+", "-",
            "*", "/", ".", "="
        };
  
        for (String label : botaoLabel) {
            JButton botao = new JButton(label);
            botao.addActionListener(new BotaoListener());
            add(botao);
          
        }

        pack();
    }

    private class BotaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Definindo a Ação de calculo
            String botaoTexto = ((JButton) e.getSource()).getText();
        
            if (Character.isDigit(botaoTexto.charAt(0)) || botaoTexto.equals(".")) {
                if (novoNum) {
                    display.setText("");
                    novoNum = false;
                }
                display.setText(display.getText() + botaoTexto);
            } else if (botaoTexto.equals("=")) {
                if (atualOperacao != null) {
                    double newNumber = Double.parseDouble(display.getText());
                    double result = performOperation(atualNumero, newNumber, atualOperacao);
                    display.setText(Double.toString(result));
                    atualNumero = result;
                    atualOperacao = null;
                    novoNum = true;
                }
            } else {
                atualNumero = Double.parseDouble(display.getText());
                atualOperacao = botaoTexto;
                novoNum = true;
            }
        }
    }

    private double performOperation(double num1, double num2, String operator) {
        //Escolha das Operações seguindo os Simbolos 
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Não é possivel dividir por 0", "Erro", JOptionPane.ERROR_MESSAGE);
                    return num1;
                }
            default:
                return num1;
        }
    }
}
