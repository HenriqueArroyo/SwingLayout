import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    public static void main(String[] args) {
        //Setando o Frame/Janela

            JFrame frame = new CalculadoraFrame();
            frame.setTitle("Calculadora");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(180, 260);
            frame.setResizable(false);
            frame.setVisible(true);
        
    }
}


// Define a interface gráfica da calculadora.
// Inclui um campo de texto para exibição, variáveis para números e operações, e métodos.
class CalculadoraFrame extends JFrame {
   
    //Criando Variaveis

    //Campo de texto para mostrar números e operações.
    private JTextField display;

    //Armazena o número atual.
    private double atualNumero;

    //Armazena a operação atual (+, -, *, /).
    private String atualOperacao;

    //Indica se um novo número está sendo digitado.
    private boolean novoNum;



    //cria os componentes da interface (campo de texto e botões numéricos e operacionais).
    public CalculadoraFrame() {

// Define o layout do frame como FlowLayout.
    setLayout(new FlowLayout()); 

    // Cria um campo de texto para exibir números e resultados com espaço para 15 caracteres.
    display = new JTextField(15); 

    // Define o campo de texto como somente leitura (não pode ser editado).
    display.setEditable(false); 

    // Adiciona o campo de texto ao frame.
    add(display); 

    // Define um array de rótulos para os botões da calculadora.
    String[] botaoLabel = {
        "1", "2", "3", "4",
        "5", "6", "7", "8",
        "9", "0", "+", "-",
        "*", "/", ".", "="
    };

    // Loop que cria e configura botões com base nos rótulos.
    for (String label : botaoLabel) {
      // Cria um botão com o rótulo atual.
        JButton botao = new JButton(label); 

       // Associa um ouvinte de ação (listener) aos botões.
        botao.addActionListener(new BotaoListener()); 

        // Adiciona o botão ao frame.
        add(botao); 
    }
    // Ajusta automaticamente o tamanho do frame para acomodar todos os componentes.
    pack(); 
    }



    // Implementa ActionListener para lidar com eventos de clique em botões.
    private class BotaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Definindo a Ação de calculo
            String botaoTexto = ((JButton) e.getSource()).getText();
        
            //verifica se o texto do botão pressionado é um dígito ou um ponto decimal. Se for, o código dentro deste bloco é executado.
            if (Character.isDigit(botaoTexto.charAt(0)) || botaoTexto.equals(".")) {

                //verifica se é necessário começar a digitar um novo número no visor. Se for verdadeiro, o visor é limpo
                if (novoNum) {
                    display.setText("");
                    novoNum = false;
                }

                // Aqui, o texto do botão pressionado é adicionado ao visor.
                display.setText(display.getText() + botaoTexto);

               // verifica se o botão pressionado é o sinal de igual           
              } else if (botaoTexto.equals("=")) {

                // verifica se já foi selecionada uma operação matemática anteriormente.
                if (atualOperacao != null) {

                    // Converte o texto atual do visor em um número decimal.
                    double novoNumero = Double.parseDouble(display.getText());

                    // Chama uma função operacaoSelecionada com o número atual, o novo número e a operação atualmente selecionada. 
                    // Realiza a operação matemática desejada e retornar o resultado.
                    double result = operacaoSelecionada(atualNumero, novoNumero, atualOperacao);

                    // Exibe o resultado no visor.
                    display.setText(Double.toString(result));

                    // Atualiza a variável atualNumero com o resultado da operação.
                    atualNumero = result;

                    //  Limpa a operação atualmente selecionada.
                    atualOperacao = null;

                    // Define a variável novoNum como true, indicando que um novo número pode ser digitado.
                    novoNum = true;
                }
            } else {
                // Converte o texto atual do visor em um número decimal e armazena na variável atualNumero.
                atualNumero = Double.parseDouble(display.getText());

                // Define a operação atualmente selecionada com base no botão pressionado.
                atualOperacao = botaoTexto;

                // Define novoNum como true, indicando que um novo número pode ser digitado.
                novoNum = true;
            }
        }
    }


   
    private double operacaoSelecionada(double num1, double num2, String operator) {

    // Executa operações matemáticas (adição, subtração, multiplicação, divisão).
     //	Retorna o resultado da operação.
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
