package Jogo;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        int larguraDaJanela = 600;
        int alturaDaJanela = larguraDaJanela;

        JogoDaCobra jogoDaCobra = new JogoDaCobra(larguraDaJanela, alturaDaJanela);

        JFrame janelaDeJogo = new JFrame("Jogo da Cobra");
        janelaDeJogo.setVisible(true);

        janelaDeJogo.setSize(larguraDaJanela, alturaDaJanela);
        janelaDeJogo.setResizable(false);
        janelaDeJogo.setLocationRelativeTo(null);
        janelaDeJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janelaDeJogo.add(jogoDaCobra);
        janelaDeJogo.pack();
        jogoDaCobra.requestFocus();
    }
}
