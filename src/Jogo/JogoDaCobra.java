package Jogo;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class JogoDaCobra extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    public int larguraDaJanela;
    public int alturaDaJanela;
    public int tamanhoDaGrelha = 50;
    public Grelha cobra;
    public ArrayList<Grelha> cobraCorpo;
    public Grelha comida;
    public Grelha botao1MenuPausa, botao2MenuPausa, botao3MenuPausa, botao1MenuGameOver, botao2MenuGameOver, botaoMenuInicialPlay,
                  botao1MenuDificuldade, botao2MenuDificuldade, botao3MenuDificuldade, botao4MenuDificuldade;
    public boolean sobreBotao1MenuPausa, sobreBotao2MenuPausa, sobreBotao3MenuPausa, sobreBotao1MenuGameOver, sobreBotao2MenuGameOver,
                   sobreBotaoMenuInicialPlay, sobreBotao1MenuDificuldade, sobreBotao2MenuDificuldade, sobreBotao3MenuDificuldade,
                   sobreBotao4MenuDificuldade, sobreBotaoSetaMenuCores, sobreBotao1MenuCores, sobreBotao2MenuCores, sobreBotao3MenuCores,
                   sobreBotao4MenuCores, sobreBotao5MenuCores, sobreBotao6MenuCores, sobreBotao7MenuCores, sobreBotao8MenuCores,
                   sobreBotao9MenuCores, sobreBotaoMenuCores, sobreBotaoCobraComida, sobreBotaoMenuSkins, sobreBotaoSetaMenuSkins,
                   sobreBotao1MenuSkins, sobreBotao2MenuSkins, sobreBotao3MenuSkins, sobreBotao4MenuSkins, sobreBotao5MenuSkins,
                   sobreBotao6MenuSkins, sobreBotao7MenuSkins, sobreBotao8MenuSkins, sobreBotao9MenuSkins, sobreBotao10MenuSkins,
                   sobreBotao11MenuSkins, sobreBotao12MenuSkins, sobreBotao13MenuSkins, sobreBotao1MenuDef, sobreBotao2MenuDef,
                   sobreBotao3MenuDef, sobreBotaoSetaMenuDef, sobreBotaoMenuDef, sobreBotaoSetaMenuScores, sobreBotaoMenuScores = false;
    public Pontos pontuacao;
    public int pontuacaoTotal = 0;
    public int pontosAtuaisFacil, pontosAtuaisMedio, pontosAtuaisDificil, pontosAtuaisHardcore = 0;
    public Random random;

    public Timer loopDeJogo;
    public boolean começarJogo = false;
    public boolean gameOver = false;
    public boolean menuInicial = true;
    public boolean menuDificuldade, menuDefinições, menuCores, menuSkins, menuScores = false;
    public boolean modoFacil, modoMedio, modoDificil, modoHardcore = false;
    public boolean jogoPausado = false;
    public int velX;
    public int velY;

    public Image definições_preto, snakeGameLogo_preto, skinsLogo_preto, scoresLogo_preto, coresSkinLogo_preto,
                 definições_branco, snakeGameLogo_branco, skinsLogo_branco, scoresLogo_branco, coresSkinLogo_branco,
                 skin_Coca_Cola, skin_Lego, skin_Maçã,
                 seta_para_trás_BRANCO, seta_para_trás_PRETO,
                 COBRA_comida_BRANCO, COBRA_comida_PRETO, cobra_COMIDA_BRANCO, cobra_COMIDA_PRETO,
                 skin_chateado, skin_narigudo, skin_ninja_azul, skin_ninja_vermelho, skin_oculos_branco,
                 skin_oculos_preto, skin_olhudo, skin_vr,
                 modo_Escuro, modo_Claro, modo_Escuro_2, modo_Claro_2,
                 n50, n100, n200, n400, n700, n1500;
    public boolean skinMaçã, skinCocaCola, skinLego, escolherSkinCobraComida,
                   skinChateado, skinNarigudo, skinNinjaAzul, skinNinjaVermelho, skinOculosBranco,
                   skinOculosPreto, skinOlhudo, skinVr = false;
    public boolean skins50, skins100, skins200, skins400, skins700, skins1500 = true;
    public boolean corCobraPreto_Branco, corCobraVermelho, corCobraMagenta, corCobraRosa, corCobraAzul, corCobraCiano,
                   corCobraVerde, corCobraAmarelo, corCobraLaranja, corObjetoPreto_Branco, corObjetoVermelho,
                   corObjetoMagenta, corObjetoRosa, corObjetoAzul, corObjetoCiano, corObjetoVerde, corObjetoAmarelo,
                   corObjetoLaranja = false;
    public boolean modoEscuro = true;
    public Clip musicaDeFundo, somComer;
    public boolean musicaDeFundo_ON_OFF, sons_ON_OFF = true;

    public static final Color brancoMedioEscuro = new Color(220, 220, 220, 204);
    public static final Color pretoMedioEscuro = new Color(25, 25, 25, 204);
    public static final Color brancoEscuro = new Color(185,185,185, 153);
    public static final Color pretoEscuro = new Color(70,70,70, 153);
    public static final Color vermelhoMedioEscuro = new Color(150, 0, 0);
    public static final Color transparente = new Color(0, 0, 0, 255);
    public Color aleatorio = transparente;
    public Color[] listaDeCoresStandard = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.ORANGE,
            Color.PINK,
            Color.RED,
            Color.YELLOW
    };

    public JogoDaCobra(int larguraDaJanela, int alturaDaJanela) {
        this.alturaDaJanela = alturaDaJanela;
        this.larguraDaJanela = larguraDaJanela;
        setPreferredSize(new Dimension(this.alturaDaJanela, this.larguraDaJanela));
        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);

        try {
            snakeGameLogo_preto = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\snake logo (preto) - MOP.png"));
            skinsLogo_preto = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\skins logo (preto) - MOP.png"));
            scoresLogo_preto = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\scores logo (preto) - MOP.png"));
            coresSkinLogo_preto = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\cores skin logo (preto) - MOP.png"));
            definições_preto = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\RODA DENTADA (preto) - MOP.png"));

            snakeGameLogo_branco = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\snake logo (branco) - MOP.png"));
            skinsLogo_branco = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\skins logo (branco) - MOP.png"));
            scoresLogo_branco = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\scores logo (branco) - MOP.png"));
            coresSkinLogo_branco = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\cores skin logo (branco) - MOP.png"));
            definições_branco = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\RODA DENTADA (branco) - MOP.png"));

            seta_para_trás_BRANCO = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\SETA PARA TRÁS - BRANCO.png"));
            seta_para_trás_PRETO = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\SETA PARA TRÁS - PRETO.png"));

            cobra_COMIDA_BRANCO = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\BOTAO COBRA-COMIDA - BRANCO (2).png"));
            cobra_COMIDA_PRETO = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\BOTAO COBRA-COMIDA - PRETO (2).png"));
            COBRA_comida_BRANCO = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\BOTAO COBRA-COMIDA - BRANCO (1).png"));
            COBRA_comida_PRETO = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\BOTAO COBRA-COMIDA - PRETO (1).png"));

            skin_Maçã = ImageIO.read(new File("SKINS OBJETO\\SKIN MAÇÃ.png"));
            skin_Coca_Cola = ImageIO.read(new File("SKINS OBJETO\\SKIN COCA-COLA.png"));
            skin_Lego = ImageIO.read(new File("SKINS OBJETO\\SKIN LEGO.png"));

            skin_chateado = ImageIO.read(new File("SKINS COBRA\\SKIN CHATEADO.png"));
            skin_narigudo = ImageIO.read(new File("SKINS COBRA\\SKIN NARIGUDO.png"));
            skin_ninja_azul = ImageIO.read(new File("SKINS COBRA\\SKIN NINJA AZUL.png"));
            skin_ninja_vermelho = ImageIO.read(new File("SKINS COBRA\\SKIN NINJA VERMELHO.png"));
            skin_oculos_branco = ImageIO.read(new File("SKINS COBRA\\SKIN OCULOS THUG LIFE BRANCO.png"));
            skin_oculos_preto = ImageIO.read(new File("SKINS COBRA\\SKIN OCULOS THUG LIFE PRETO.png"));
            skin_olhudo = ImageIO.read(new File("SKINS COBRA\\SKIN OLHUDO.png"));
            skin_vr = ImageIO.read(new File("SKINS COBRA\\SKIN VR.png"));

            modo_Escuro = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\modo escuro.png"));
            modo_Escuro_2 = ImageIO.read(new File("MENUS - IMAGENS\\MODO ESCURO\\modo escuro 2.png"));
            modo_Claro = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\modo claro.png"));
            modo_Claro_2 = ImageIO.read(new File("MENUS - IMAGENS\\MODO CLARO\\modo claro 2.png"));

            n50 = ImageIO.read(new File("MENUS - IMAGENS\\50.png"));
            n100 = ImageIO.read(new File("MENUS - IMAGENS\\100.png"));
            n200 = ImageIO.read(new File("MENUS - IMAGENS\\200.png"));
            n400 = ImageIO.read(new File("MENUS - IMAGENS\\400.png"));
            n700 = ImageIO.read(new File("MENUS - IMAGENS\\700.png"));
            n1500 = ImageIO.read(new File("MENUS - IMAGENS\\1500.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            File ficheiro = new File("SONS\\Musica de fundo ORIGINAL - MOP.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(ficheiro);
            musicaDeFundo = AudioSystem.getClip();
            musicaDeFundo.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (sobreBotaoMenuInicialPlay) {
            int numeroCor = random.nextInt(1, listaDeCoresStandard.length);
            aleatorio = listaDeCoresStandard[numeroCor];
        }

        cobra = new Grelha(1,1);
        cobraCorpo = new ArrayList<Grelha>();
        comida = new Grelha(5, 5);
        botao1MenuPausa = new Grelha(2, 3);
        botao2MenuPausa = new Grelha(3, 6);
        botao3MenuPausa = new Grelha(3, 8);
        botao1MenuGameOver = new Grelha(2, 4);
        botao2MenuGameOver = new Grelha(3, 7);
        botaoMenuInicialPlay = new Grelha(4, 6);
        botao1MenuDificuldade = new Grelha(2,5);
        botao2MenuDificuldade = new Grelha(7,5);
        botao3MenuDificuldade = new Grelha(2,8);
        botao4MenuDificuldade = new Grelha(7,8);
        pontuacao = new Pontos();
        random = new Random();
        spawnDaComida();
        corCobraPreto_Branco = true;
        corObjetoVermelho = true;

        velX = 0;
        velY = 0;

        loopDeJogo = new Timer(150, this);
        loopDeJogo.start();
    }
    private void reiniciarJogo() {
        cobra = new Grelha(1,1);
        cobraCorpo.clear();
        comida = new Grelha(5, 5);
        pontosAtuaisFacil = 0;
        pontosAtuaisMedio = 0;
        pontosAtuaisDificil = 0;
        pontosAtuaisHardcore = 0;
        velX = 0;
        velY = 0;
        gameOver = false;
        spawnDaComida();
        loopDeJogo.start();
    }

    public void skinAtual(Graphics g, String nomeObjeto, String nomeSkin, int x, int y) {
        if (nomeObjeto.equals("cobra")) {
            switch (nomeSkin) {
                case "chateado" -> g.drawImage(skin_chateado, x, y, 50, 50, this);
                case "narigudo" -> g.drawImage(skin_narigudo, x, y, 50, 50, this);
                case "ninja azul" -> g.drawImage(skin_ninja_azul, x, y, 50, 50, this);
                case "ninja vermelho" -> g.drawImage(skin_ninja_vermelho, x, y, 50, 50, this);
                case "oculos branco" -> g.drawImage(skin_oculos_branco, x, y, 50, 50, this);
                case "oculos preto" -> g.drawImage(skin_oculos_preto, x, y, 50, 50, this);
                case "olhudo" -> g.drawImage(skin_olhudo, x, y, 50, 50, this);
                case "vr" -> g.drawImage(skin_vr, x, y, 50, 50, this);
            }
        }
        else if (nomeObjeto.equals("comida")) {
            switch (nomeSkin) {
                case "maçã" -> g.drawImage(skin_Maçã, x, y, 50, 50, this);
                case "coca-cola" -> g.drawImage(skin_Coca_Cola, x, y, 50, 50, this);
                case "lego" -> g.drawImage(skin_Lego, x, y, 50, 50, this);
            }
        }
    }

    public void corAtual(Graphics g, String nome) {
        if (nome.equals("cobra")) {
            coresBooleans(g, corCobraPreto_Branco, corCobraVermelho, corCobraMagenta, corCobraRosa, corCobraAzul, corCobraCiano,
                    corCobraVerde, corCobraAmarelo, corCobraLaranja);
        }
        else if (nome.equals("comida")) {
            coresBooleans(g, corObjetoPreto_Branco, corObjetoVermelho, corObjetoMagenta, corObjetoRosa, corObjetoAzul, corObjetoCiano,
                    corObjetoVerde, corObjetoAmarelo, corObjetoLaranja);
        }
    }

    public void coresBooleans(Graphics g, boolean corCobraPretoBranco, boolean corCobraVermelho, boolean corCobraMagenta,
                               boolean corCobraRosa, boolean corCobraAzul, boolean corCobraCiano, boolean corCobraVerde,
                               boolean corCobraAmarelo, boolean corCobraLaranja) {
        if (corCobraPretoBranco) {
            if (modoEscuro) {
                g.setColor(Color.white);
            }
            else {
                g.setColor(Color.black);
            }
        }
        else if (corCobraVermelho) {
            g.setColor(Color.red);
        }
        else if (corCobraMagenta) {
            g.setColor(Color.magenta);
        }
        else if (corCobraRosa) {
            g.setColor(Color.pink);
        }
        else if (corCobraAzul) {
            g.setColor(Color.blue);
        }
        else if (corCobraCiano) {
            g.setColor(Color.cyan);
        }
        else if (corCobraVerde) {
            g.setColor(Color.green);
        }
        else if (corCobraAmarelo) {
            g.setColor(Color.yellow);
        }
        else if (corCobraLaranja) {
            g.setColor(Color.orange);
        }
    }

    public void desativarSkins(String nome) {
        if (nome.equals("cobra")) {
            skinChateado = false;
            skinNarigudo = false;
            skinNinjaAzul = false;
            skinNinjaVermelho = false;
            skinOculosBranco = false;
            skinOculosPreto = false;
            skinOlhudo = false;
            skinVr = false;
        }
        else if (nome.equals("comida")) {
            skinMaçã = false;
            skinCocaCola = false;
            skinLego = false;
        }
    }

    public void bloqueioSkins(Graphics g, String nome) {
        if (nome.equals("cobra")) {
            if (skins50) {
                g.drawImage(n50, 112, 287, 75, 75, this);
                g.drawImage(n50, 112, 412, 75, 75, this);
                g.drawImage(n100, 262, 162, 75, 75, this);
                g.drawImage(n100, 262, 287, 75, 75, this);
                g.drawImage(n200, 262, 412, 75, 75, this);
                g.drawImage(n400, 412, 162, 75, 75, this);
                g.drawImage(n700, 412, 162, 75, 75, this);
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
            if (skins100) {
                g.drawImage(n100, 262, 162, 75, 75, this);
                g.drawImage(n100, 262, 287, 75, 75, this);
                g.drawImage(n200, 262, 412, 75, 75, this);
                g.drawImage(n400, 412, 162, 75, 75, this);
                g.drawImage(n700, 412, 162, 75, 75, this);
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
            if (skins200) {
                g.drawImage(n200, 262, 412, 75, 75, this);
                g.drawImage(n400, 412, 162, 75, 75, this);
                g.drawImage(n700, 412, 162, 75, 75, this);
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
            if (skins400) {
                g.drawImage(n400, 412, 162, 75, 75, this);
                g.drawImage(n700, 412, 162, 75, 75, this);
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
            if (skins700) {
                g.drawImage(n700, 412, 162, 75, 75, this);
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
            if (skins1500) {
                g.drawImage(n1500, 412, 412, 75, 75, this);
            }
        }
        else if (nome.equals("comida")) {
            if (skins100) {
                g.drawImage(n100, 350, 225, 75, 75, this);
                g.drawImage(n400, 175, 400, 75, 75, this);
                g.drawImage(n700, 350, 400, 75, 75, this);
            }
            if (skins400) {
                g.drawImage(n400, 175, 400, 75, 75, this);
                g.drawImage(n700, 350, 400, 75, 75, this);
            }
            if (skins700) {
                g.drawImage(n700, 350, 400, 75, 75, this);
            }
        }
    }

    public void desativarCores(String nome) {
        if (nome.equals("cobra")) {
            corCobraVermelho = false;
            corCobraMagenta = false;
            corCobraRosa = false;
            corCobraAzul = false;
            corCobraCiano = false;
            corCobraVerde = false;
            corCobraAmarelo = false;
            corCobraLaranja = false;
            corCobraPreto_Branco = false;
        }
        else if (nome.equals("comida")) {
            corObjetoVermelho = false;
            corObjetoMagenta = false;
            corObjetoRosa = false;
            corObjetoAzul = false;
            corObjetoCiano = false;
            corObjetoVerde = false;
            corObjetoAmarelo = false;
            corObjetoLaranja = false;
            corObjetoPreto_Branco = false;
        }
    }
    public void ativarCor(String cor, String nome) {
        if (nome.equals("cobra")) {
            desativarCores("cobra");
            switch (cor) {
                case "preto_branco":
                    corCobraPreto_Branco = true;
                    break;
                case "vermelho":
                    corCobraVermelho = true;
                    break;
                case "magenta":
                    corCobraMagenta = true;
                    break;
                case "rosa":
                    corCobraRosa = true;
                    break;
                case "azul":
                    corCobraAzul = true;
                    break;
                case "ciano":
                    corCobraCiano = true;
                    break;
                case "verde":
                    corCobraVerde = true;
                    break;
                case "amarelo":
                    corCobraAmarelo = true;
                    break;
                case "laranja":
                    corCobraLaranja = true;
                    break;
            }
        }
        else if (nome.equals("comida")) {
            desativarCores("comida");
            switch (cor) {
                case "preto_branco":
                    corObjetoPreto_Branco = true;
                    break;
                case "vermelho":
                    corObjetoVermelho = true;
                    break;
                case "magenta":
                    corObjetoMagenta = true;
                    break;
                case "rosa":
                    corObjetoRosa = true;
                    break;
                case "azul":
                    corObjetoAzul = true;
                    break;
                case "ciano":
                    corObjetoCiano = true;
                    break;
                case "verde":
                    corObjetoVerde = true;
                    break;
                case "amarelo":
                    corObjetoAmarelo = true;
                    break;
                case "laranja":
                    corObjetoLaranja = true;
                    break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderizar(g);
    }

    public void renderizar(Graphics g) {
        if (menuInicial) {
            if (modoEscuro) {
                g.drawImage(snakeGameLogo_preto, 25, 70, 550, 207, this);
                g.drawImage(skinsLogo_preto, 75, 450, 100, 100, this);
                g.drawImage(scoresLogo_preto, 250, 450, 100, 100, this);
                g.drawImage(coresSkinLogo_preto, 425, 450, 100, 100, this);
                g.drawImage(definições_preto, 535, 15, 50, 50, this);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));

                g.setColor(Color.white);
                g.fillRoundRect(botaoMenuInicialPlay.x * tamanhoDaGrelha, botaoMenuInicialPlay.y * tamanhoDaGrelha, 200, 75, 3, 3);
                if (sobreBotaoMenuInicialPlay) {
                    aleatorio = new Color(random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255), 255);
                    g.setColor(aleatorio);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("PLAY", botaoMenuInicialPlay.x * tamanhoDaGrelha + 48, botaoMenuInicialPlay.y * tamanhoDaGrelha + 51);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);
                g.drawImage(snakeGameLogo_branco, 25, 70, 550, 207, this);
                g.drawImage(skinsLogo_branco, 75, 450, 100, 100, this);
                g.drawImage(scoresLogo_branco, 250, 450, 100, 100, this);
                g.drawImage(coresSkinLogo_branco, 425, 450, 100, 100, this);
                g.drawImage(definições_branco, 535, 15, tamanhoDaGrelha, tamanhoDaGrelha, this);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));

                g.setColor(Color.black);
                g.fillRoundRect(botaoMenuInicialPlay.x * tamanhoDaGrelha, botaoMenuInicialPlay.y * tamanhoDaGrelha, 200, 75, 3, 3);
                if (sobreBotaoMenuInicialPlay) {
                    aleatorio = new Color(random.nextInt(0, 255), random.nextInt(0, 255), random.nextInt(0, 255), 255);
                    g.setColor(aleatorio);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("PLAY", botaoMenuInicialPlay.x * tamanhoDaGrelha + 48, botaoMenuInicialPlay.y * tamanhoDaGrelha + 51);
            }
        }

        if (começarJogo) {
            if (!gameOver) {
                if (modoEscuro) {
                    corAtual(g, "comida");
                    if (skinMaçã) {
                        skinAtual(g, "comida", "maçã",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else if (skinLego) {
                        skinAtual(g, "comida", "lego",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else if (skinCocaCola) {
                        skinAtual(g, "comida", "coca-cola",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else {
                        g.fillOval(comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha, 50, 50);
                    }

                    corAtual(g, "cobra");
                    g.fillRoundRect(cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha, tamanhoDaGrelha, tamanhoDaGrelha, 10, 10);
                    if (skinChateado) {
                        skinAtual(g, "cobra", "chateado",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNarigudo) {
                        skinAtual(g, "cobra", "narigudo",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNinjaAzul) {
                        skinAtual(g, "cobra", "ninja azul",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNinjaVermelho) {
                        skinAtual(g, "cobra", "ninja vermelho",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOculosBranco) {
                        skinAtual(g, "cobra", "oculos branco",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOculosPreto) {
                        skinAtual(g, "cobra", "oculos preto",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOlhudo) {
                        skinAtual(g, "cobra", "olhudo",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinVr) {
                        skinAtual(g, "cobra", "vr",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }

                    for (Grelha cobraPedaco : cobraCorpo) {
                        g.fillRoundRect(cobraPedaco.x * tamanhoDaGrelha, cobraPedaco.y * tamanhoDaGrelha, tamanhoDaGrelha, tamanhoDaGrelha, 5, 5);
                    }
                }
                else {
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 600, 600);

                    corAtual(g, "comida");
                    if (skinMaçã) {
                        skinAtual(g, "comida", "maçã",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else if (skinLego) {
                        skinAtual(g, "comida", "lego",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else if (skinCocaCola) {
                        skinAtual(g, "comida", "coca-cola",comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha);
                    }
                    else {
                        g.fillOval(comida.x * tamanhoDaGrelha, comida.y * tamanhoDaGrelha, 50, 50);
                    }

                    corAtual(g, "cobra");
                    g.fillRoundRect(cobra.x * tamanhoDaGrelha , cobra.y * tamanhoDaGrelha, tamanhoDaGrelha, tamanhoDaGrelha, 10, 10);
                    if (skinChateado) {
                        skinAtual(g, "cobra", "chateado",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNarigudo) {
                        skinAtual(g, "cobra", "narigudo",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNinjaAzul) {
                        skinAtual(g, "cobra", "ninja azul",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinNinjaVermelho) {
                        skinAtual(g, "cobra", "ninja vermelho",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOculosBranco) {
                        skinAtual(g, "cobra", "oculos branco",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOculosPreto) {
                        skinAtual(g, "cobra", "oculos preto",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinOlhudo) {
                        skinAtual(g, "cobra", "olhudo",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }
                    else if (skinVr) {
                        skinAtual(g, "cobra", "vr",cobra.x * tamanhoDaGrelha, cobra.y * tamanhoDaGrelha);
                    }

                    for (Grelha cobraPedaco : cobraCorpo) {
                        g.fillRoundRect(cobraPedaco.x * tamanhoDaGrelha, cobraPedaco.y * tamanhoDaGrelha, tamanhoDaGrelha, tamanhoDaGrelha, 5, 5);
                    }
                }
            }
        }

        if (jogoPausado) {
            if (modoEscuro) {
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 45));

                g.setColor(Color.white);
                g.fillRoundRect(botao1MenuPausa.x * tamanhoDaGrelha, botao1MenuPausa.y * tamanhoDaGrelha, 400, 90, 3, 3);
                if (sobreBotao1MenuPausa) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("Retomar Jogo", botao1MenuPausa.x * tamanhoDaGrelha + 30, botao1MenuPausa.y * tamanhoDaGrelha + 59);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));

                g.setColor(brancoMedioEscuro);
                g.fillRoundRect(botao2MenuPausa.x * tamanhoDaGrelha, botao2MenuPausa.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao2MenuPausa) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("Reiniciar", botao2MenuPausa.x * tamanhoDaGrelha + 49, botao2MenuPausa.y * tamanhoDaGrelha + 45);

                g.setColor(brancoEscuro);
                g.fillRoundRect(botao3MenuPausa.x * tamanhoDaGrelha, botao3MenuPausa.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao3MenuPausa) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("Sair", botao3MenuPausa.x * tamanhoDaGrelha + 105, botao3MenuPausa.y * tamanhoDaGrelha + 45);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 45));

                g.setColor(Color.black);
                g.fillRoundRect(botao1MenuPausa.x * tamanhoDaGrelha, botao1MenuPausa.y * tamanhoDaGrelha, 400, 90, 3, 3);
                if (sobreBotao1MenuPausa) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("Retomar Jogo", botao1MenuPausa.x * tamanhoDaGrelha + 30, botao1MenuPausa.y * tamanhoDaGrelha + 59);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));

                g.setColor(pretoMedioEscuro);
                g.fillRoundRect(botao2MenuPausa.x * tamanhoDaGrelha, botao2MenuPausa.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao2MenuPausa) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("Reiniciar", botao2MenuPausa.x * tamanhoDaGrelha + 49, botao2MenuPausa.y * tamanhoDaGrelha + 45);

                g.setColor(pretoEscuro);
                g.fillRoundRect(botao3MenuPausa.x * tamanhoDaGrelha, botao3MenuPausa.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao3MenuPausa) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("Sair", botao3MenuPausa.x * tamanhoDaGrelha + 105, botao3MenuPausa.y * tamanhoDaGrelha + 45);
            }
        }

        if (gameOver) {
            if (modoEscuro) {
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 45));

                g.setColor(Color.white);
                g.fillRoundRect(botao1MenuGameOver.x * tamanhoDaGrelha, botao1MenuGameOver.y * tamanhoDaGrelha, 400, 90, 3, 3);
                if (sobreBotao1MenuGameOver) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("Reiniciar", botao1MenuGameOver.x * tamanhoDaGrelha + 71, botao1MenuGameOver.y * tamanhoDaGrelha + 59);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));

                g.setColor(brancoMedioEscuro);
                g.fillRoundRect(botao2MenuGameOver.x * tamanhoDaGrelha, botao2MenuGameOver.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao2MenuGameOver) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.black);
                }
                g.drawString("Sair", botao2MenuGameOver.x * tamanhoDaGrelha + 105, botao2MenuGameOver.y * tamanhoDaGrelha + 45);

                g.setColor(Color.red);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Pontuação:", 208, 75);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 50));
                g.drawString(String.valueOf(cobraCorpo.size()), 284, 150);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 45));

                g.setColor(Color.black);
                g.fillRoundRect(botao1MenuGameOver.x * tamanhoDaGrelha, botao1MenuGameOver.y * tamanhoDaGrelha, 400, 90, 3, 3);
                if (sobreBotao1MenuGameOver) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("Reiniciar", botao1MenuGameOver.x * tamanhoDaGrelha + 71, botao1MenuGameOver.y * tamanhoDaGrelha + 59);

                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));

                g.setColor(pretoMedioEscuro);
                g.fillRoundRect(botao2MenuGameOver.x * tamanhoDaGrelha, botao2MenuGameOver.y * tamanhoDaGrelha, 300, 65, 3, 3);
                if (sobreBotao2MenuGameOver) {
                    g.setColor(vermelhoMedioEscuro);
                }
                else {
                    g.setColor(Color.white);
                }
                g.drawString("Sair", botao2MenuGameOver.x * tamanhoDaGrelha + 105, botao2MenuGameOver.y * tamanhoDaGrelha + 45);

                g.setColor(Color.red);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Pontuação:", 208, 75);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 50));
                g.drawString(String.valueOf(cobraCorpo.size()), 284, 150);
            }
        }

        if (menuDificuldade) {
            if (modoEscuro) {
                g.setColor(Color.white);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));
                g.drawString("Escolhe a ", 199, 115);
                g.drawString("dificuldade:", 168, 165);

                g.setColor(Color.green);
                g.fillRoundRect(botao1MenuDificuldade.x * tamanhoDaGrelha, botao1MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.yellow);
                g.fillRoundRect(botao2MenuDificuldade.x * tamanhoDaGrelha, botao2MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.orange);
                g.fillRoundRect(botao3MenuDificuldade.x * tamanhoDaGrelha, botao3MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.red);
                g.fillRoundRect(botao4MenuDificuldade.x * tamanhoDaGrelha, botao4MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);

                g.setColor(Color.white);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 25));
                g.drawString("Fácil", botao1MenuDificuldade.x * tamanhoDaGrelha + 36, botao1MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("Médio", botao2MenuDificuldade.x * tamanhoDaGrelha + 36, botao2MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("Difícil", botao3MenuDificuldade.x * tamanhoDaGrelha + 19, botao3MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("HARDCORE", botao4MenuDificuldade.x * tamanhoDaGrelha + 11, botao4MenuDificuldade.y * tamanhoDaGrelha + 45);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);

                g.setColor(Color.black);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 35));
                g.drawString("Escolhe a ", 199, 115);
                g.drawString("dificuldade:", 168, 165);

                g.setColor(Color.green);
                g.fillRoundRect(botao1MenuDificuldade.x * tamanhoDaGrelha, botao1MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.yellow);
                g.fillRoundRect(botao2MenuDificuldade.x * tamanhoDaGrelha, botao2MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.orange);
                g.fillRoundRect(botao3MenuDificuldade.x * tamanhoDaGrelha, botao3MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);
                g.setColor(Color.red);
                g.fillRoundRect(botao4MenuDificuldade.x * tamanhoDaGrelha, botao4MenuDificuldade.y * tamanhoDaGrelha, 150, 75, 3, 3);

                g.setColor(Color.black);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 25));
                g.drawString("Fácil", botao1MenuDificuldade.x * tamanhoDaGrelha + 36, botao1MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("Médio", botao2MenuDificuldade.x * tamanhoDaGrelha + 36, botao2MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("Difícil", botao3MenuDificuldade.x * tamanhoDaGrelha + 19, botao3MenuDificuldade.y * tamanhoDaGrelha + 45);
                g.drawString("HARDCORE", botao4MenuDificuldade.x * tamanhoDaGrelha + 11, botao4MenuDificuldade.y * tamanhoDaGrelha + 45);
            }
        }

        if (menuCores) {
            if (modoEscuro) {
                g.setColor(Color.white);
                g.fillRoundRect(112, 162, 75, 75, 10, 10);
                if (sobreBotao1MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(110, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.pink);
                g.fillRoundRect(112, 287, 75, 75, 10, 10);
                if (sobreBotao4MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(110, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.green);
                g.fillRoundRect(112, 412, 75, 75, 10, 10);
                if (sobreBotao7MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(110, 410, 79, 79, 10, 10);
                }
                g.setColor(Color.red);
                g.fillRoundRect(262, 162, 75, 75, 10, 10);
                if (sobreBotao2MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(260, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.blue);
                g.fillRoundRect(262, 287, 75, 75, 10, 10);
                if (sobreBotao5MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(260, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.yellow);
                g.fillRoundRect(262, 412, 75, 75, 10, 10);
                if (sobreBotao8MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(260, 410, 79, 79, 10, 10);
                }
                g.setColor(Color.magenta);
                g.fillRoundRect(412, 162, 75, 75, 10, 10);
                if (sobreBotao3MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(410, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.cyan);
                g.fillRoundRect(412, 287, 75, 75, 10, 10);
                if (sobreBotao6MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(410, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.orange);
                g.fillRoundRect(412, 412, 75, 75, 10, 10);
                if (sobreBotao9MenuCores) {
                    g.setColor(Color.white);
                    g.drawRoundRect(410, 410, 79, 79, 10, 10);
                }

                g.drawImage(seta_para_trás_BRANCO, 525, 25, 50, 50, this);

                g.setColor(Color.white);
                g.fillRoundRect(465, 525, 120, 50, 10, 10);
                if (escolherSkinCobraComida) {
                    g.drawImage(cobra_COMIDA_PRETO, 465, 525, 120, 50, this);
                    corAtual(g, "comida");
                    g.fillRoundRect(225, 525, 50, 50, 10, 10);
                }
                else {
                    g.drawImage(COBRA_comida_PRETO, 465, 525, 120, 50, this);
                    corAtual(g, "cobra");
                    g.fillRoundRect(225, 525, 50, 50, 10, 10);
                }

                g.setColor(Color.white);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));
                g.drawString("CORES", 237, 90);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Cor atual:", 25, 562);
                g.drawString("Objeto: " , 325, 562);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);

                g.setColor(Color.black);
                g.fillRoundRect(112, 162, 75, 75, 10, 10);
                if (sobreBotao1MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(110, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.pink);
                g.fillRoundRect(112, 287, 75, 75, 10, 10);
                if (sobreBotao4MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(110, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.green);
                g.fillRoundRect(112, 412, 75, 75, 10, 10);
                if (sobreBotao7MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(110, 410, 79, 79, 10, 10);
                }
                g.setColor(Color.red);
                g.fillRoundRect(262, 162, 75, 75, 10, 10);
                if (sobreBotao2MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(260, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.blue);
                g.fillRoundRect(262, 287, 75, 75, 10, 10);
                if (sobreBotao5MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(260, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.yellow);
                g.fillRoundRect(262, 412, 75, 75, 10, 10);
                if (sobreBotao8MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(260, 410, 79, 79, 10, 10);
                }
                g.setColor(Color.magenta);
                g.fillRoundRect(412, 162, 75, 75, 10, 10);
                if (sobreBotao3MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(410, 160, 79, 79, 10, 10);
                }
                g.setColor(Color.cyan);
                g.fillRoundRect(412, 287, 75, 75, 10, 10);
                if (sobreBotao6MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(410, 285, 79, 79, 10, 10);
                }
                g.setColor(Color.orange);
                g.fillRoundRect(412, 412, 75, 75, 10, 10);
                if (sobreBotao9MenuCores) {
                    g.setColor(Color.black);
                    g.drawRoundRect(410, 410, 79, 79, 10, 10);
                }

                g.drawImage(seta_para_trás_PRETO, 525, 25, 50, 50, this);

                g.setColor(Color.black);
                g.fillRoundRect(465, 525, 120, 50, 10, 10);
                if (escolherSkinCobraComida) {
                    g.drawImage(cobra_COMIDA_BRANCO, 465, 525, 120, 50, this);
                    corAtual(g, "comida");
                    g.fillRoundRect(225, 525, 50, 50, 10, 10);
                }
                else {
                    g.drawImage(COBRA_comida_BRANCO, 465, 525, 120, 50, this);
                    corAtual(g, "cobra");
                    g.fillRoundRect(225, 525, 50, 50, 10, 10);
                }

                g.setColor(Color.black);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));
                g.drawString("CORES", 237, 90);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Cor atual:", 25, 562);
                g.drawString("Objeto: " , 325, 562);
            }
        }

        if (menuSkins) {
            if (modoEscuro) {
                g.drawImage(seta_para_trás_BRANCO, 525, 25, 50, 50, this);

                g.setColor(Color.white);
                g.fillRoundRect(465, 525, 120, 50, 10, 10);
                if (escolherSkinCobraComida) {
                    g.setColor(Color.gray);
                    g.fillRoundRect(175, 225, 75, 75, 10, 10);
                    g.fillRoundRect(350, 225, 75, 75, 10, 10);
                    g.drawImage(skin_Maçã, 350, 225, 75, 75, this);
                    g.fillRoundRect(175, 400, 75, 75, 10, 10);
                    g.drawImage(skin_Coca_Cola, 175, 400, 75, 75, this);
                    g.fillRoundRect(350, 400, 75, 75, 10, 10);
                    g.drawImage(skin_Lego, 350, 400, 75, 75, this);
                    g.setColor(Color.white);
                    if (sobreBotao10MenuSkins) {
                        g.drawRoundRect(173, 223, 79, 79, 10, 10);
                    }
                    else if (sobreBotao11MenuSkins) {
                        g.drawRoundRect(348, 223, 79, 79, 10, 10);
                    }
                    else if (sobreBotao12MenuSkins) {
                        g.drawRoundRect(173, 398, 79, 79, 10, 10);
                    }
                    else if (sobreBotao13MenuSkins) {
                        g.drawRoundRect(348, 398, 79, 79, 10, 10);
                    }

                    g.drawImage(cobra_COMIDA_PRETO, 465, 525, 120, 50, this);
                    g.setColor(Color.gray);
                    g.fillRoundRect(235, 525, 50, 50, 10, 10);
                    if (skinMaçã) {
                        skinAtual(g, "comida", "maçã",235, 525);
                    }
                    else if (skinCocaCola) {
                        skinAtual(g, "comida", "coca-cola",235, 525);
                    }
                    else if (skinLego) {
                        skinAtual(g, "comida", "lego",235, 525);
                    }
                    if (skins100) {
                        g.drawImage(n100, 350, 225, 75, 75, this);
                    }
                    else if (skins400) {
                        g.drawImage(n400, 175, 400, 75, 75, this);
                    }
                    else if (skins700) {
                        g.drawImage(n700, 350, 400, 75, 75, this);
                    }
                }
                else {
                    g.setColor(Color.gray);
                    g.fillRoundRect(112, 162, 75, 75, 10, 10);
                    g.fillRoundRect(112, 287, 75, 75, 10, 10);
                    g.drawImage(skin_oculos_preto, 112, 287, 75, 75, this);
                    g.fillRoundRect(112, 412, 75, 75, 10, 10);
                    g.drawImage(skin_oculos_branco, 112, 412, 75, 75, this);
                    g.fillRoundRect(262, 162, 75, 75, 10, 10);
                    g.drawImage(skin_ninja_vermelho, 262, 162, 75, 75, this);
                    g.fillRoundRect(262, 287, 75, 75, 10, 10);
                    g.drawImage(skin_ninja_azul, 262, 287, 75, 75, this);
                    g.fillRoundRect(262, 412, 75, 75, 10, 10);
                    g.drawImage(skin_olhudo, 262, 412, 75, 75, this);
                    g.fillRoundRect(412, 162, 75, 75, 10, 10);
                    g.drawImage(skin_narigudo, 412, 162, 75, 75, this);
                    g.fillRoundRect(412, 287, 75, 75, 10, 10);
                    g.drawImage(skin_chateado, 412, 287, 75, 75, this);
                    g.fillRoundRect(412, 412, 75, 75, 10, 10);
                    g.drawImage(skin_vr, 412, 412, 75, 75, this);
                    g.setColor(Color.white);
                    if (sobreBotao1MenuSkins) {
                        g.drawRoundRect(110, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao4MenuSkins) {
                        g.drawRoundRect(110, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao7MenuSkins) {
                        g.drawRoundRect(110, 410, 79, 79, 10, 10);
                    }
                    else if (sobreBotao2MenuSkins) {
                        g.drawRoundRect(260, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao5MenuSkins) {
                        g.drawRoundRect(260, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao8MenuSkins) {
                        g.drawRoundRect(260, 410, 79, 79, 10, 10);
                    }
                    else if (sobreBotao3MenuSkins) {
                        g.drawRoundRect(410, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao6MenuSkins) {
                        g.drawRoundRect(410, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao9MenuSkins) {
                        g.drawRoundRect(410, 410, 79, 79, 10, 10);
                    }

                    g.drawImage(COBRA_comida_PRETO, 465, 525, 120, 50, this);
                    g.setColor(Color.gray);
                    g.fillRoundRect(235, 525, 50, 50, 10, 10);
                    if (skinChateado) {
                        skinAtual(g, "cobra", "chateado",235, 525);
                    }
                    else if (skinNarigudo) {
                        skinAtual(g, "cobra", "narigudo",235, 525);
                    }
                    else if (skinNinjaAzul) {
                        skinAtual(g, "cobra", "ninja azul",235, 525);
                    }
                    else if (skinNinjaVermelho) {
                        skinAtual(g, "cobra", "ninja vermelho",235, 525);
                    }
                    else if (skinOculosBranco) {
                        skinAtual(g, "cobra", "oculos branco",235, 525);
                    }
                    else if (skinOculosPreto) {
                        skinAtual(g, "cobra", "oculos preto",235, 525);
                    }
                    else if (skinOlhudo) {
                        skinAtual(g, "cobra", "olhudo",235, 525);
                    }
                    else if (skinVr) {
                        skinAtual(g, "cobra", "vr",235, 525);
                    }

                    if (skins50) {
                        g.drawImage(n50, 112, 287, 75, 75, this);
                        g.drawImage(n50, 112, 412, 75, 75, this);
                        g.drawImage(n100, 262, 162, 75, 75, this);
                        g.drawImage(n100, 262, 287, 75, 75, this);
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins100) {
                        g.drawImage(n100, 262, 162, 75, 75, this);
                        g.drawImage(n100, 262, 287, 75, 75, this);
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins200) {
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins400) {
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins700) {
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins1500) {
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                }

                g.setColor(Color.white);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));
                g.drawString("SKINS", 237, 90);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Skin atual:", 20, 562);
                g.drawString("Objeto: " , 325, 562);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);

                g.drawImage(seta_para_trás_PRETO, 525, 25, 50, 50, this);

                g.setColor(Color.black);
                g.fillRoundRect(465, 525, 120, 50, 10, 10);
                if (escolherSkinCobraComida) {
                    g.setColor(Color.gray);
                    g.fillRoundRect(175, 225, 75, 75, 10, 10);
                    g.fillRoundRect(350, 225, 75, 75, 10, 10);
                    g.drawImage(skin_Maçã, 350, 225, 75, 75, this);
                    g.fillRoundRect(175, 400, 75, 75, 10, 10);
                    g.drawImage(skin_Coca_Cola, 175, 400, 75, 75, this);
                    g.fillRoundRect(350, 400, 75, 75, 10, 10);
                    g.drawImage(skin_Lego, 350, 400, 75, 75, this);
                    g.setColor(Color.black);
                    if (sobreBotao10MenuSkins) {
                        g.drawRoundRect(173, 223, 79, 79, 10, 10);
                    }
                    else if (sobreBotao11MenuSkins) {
                        g.drawRoundRect(348, 223, 79, 79, 10, 10);
                    }
                    else if (sobreBotao12MenuSkins) {
                        g.drawRoundRect(173, 398, 79, 79, 10, 10);
                    }
                    else if (sobreBotao13MenuSkins) {
                        g.drawRoundRect(348, 398, 79, 79, 10, 10);
                    }

                    g.drawImage(cobra_COMIDA_BRANCO, 465, 525, 120, 50, this);
                    g.setColor(Color.gray);
                    g.fillRoundRect(235, 525, 50, 50, 10, 10);
                    if (skinMaçã) {
                        skinAtual(g, "comida", "maçã",235, 525);
                    }
                    else if (skinCocaCola) {
                        skinAtual(g, "comida", "coca-cola",235, 525);
                    }
                    else if (skinLego) {
                        skinAtual(g, "comida", "lego",235, 525);
                    }

                    if (skins100) {
                        g.drawImage(n100, 350, 225, 75, 75, this);
                        g.drawImage(n400, 175, 400, 75, 75, this);
                        g.drawImage(n700, 350, 400, 75, 75, this);
                    }
                    if (skins400) {
                        g.drawImage(n400, 175, 400, 75, 75, this);
                        g.drawImage(n700, 350, 400, 75, 75, this);
                    }
                    if (skins700) {
                        g.drawImage(n700, 350, 400, 75, 75, this);
                    }
                }
                else {
                    g.setColor(Color.gray);
                    g.fillRoundRect(112, 162, 75, 75, 10, 10);
                    g.fillRoundRect(112, 287, 75, 75, 10, 10);
                    g.drawImage(skin_oculos_preto, 112, 287, 75, 75, this);
                    g.fillRoundRect(112, 412, 75, 75, 10, 10);
                    g.drawImage(skin_oculos_branco, 112, 412, 75, 75, this);
                    g.fillRoundRect(262, 162, 75, 75, 10, 10);
                    g.drawImage(skin_ninja_vermelho, 262, 162, 75, 75, this);
                    g.fillRoundRect(262, 287, 75, 75, 10, 10);
                    g.drawImage(skin_ninja_azul, 262, 287, 75, 75, this);
                    g.fillRoundRect(262, 412, 75, 75, 10, 10);
                    g.drawImage(skin_olhudo, 262, 412, 75, 75, this);
                    g.fillRoundRect(412, 162, 75, 75, 10, 10);
                    g.drawImage(skin_narigudo, 412, 162, 75, 75, this);
                    g.fillRoundRect(412, 287, 75, 75, 10, 10);
                    g.drawImage(skin_chateado, 412, 287, 75, 75, this);
                    g.fillRoundRect(412, 412, 75, 75, 10, 10);
                    g.drawImage(skin_vr, 412, 412, 75, 75, this);
                    g.setColor(Color.black);
                    if (sobreBotao1MenuSkins) {
                        g.drawRoundRect(110, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao4MenuSkins) {
                        g.drawRoundRect(110, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao7MenuSkins) {
                        g.drawRoundRect(110, 410, 79, 79, 10, 10);
                    }
                    else if (sobreBotao2MenuSkins) {
                        g.drawRoundRect(260, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao5MenuSkins) {
                        g.drawRoundRect(260, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao8MenuSkins) {
                        g.drawRoundRect(260, 410, 79, 79, 10, 10);
                    }
                    else if (sobreBotao3MenuSkins) {
                        g.drawRoundRect(410, 160, 79, 79, 10, 10);
                    }
                    else if (sobreBotao6MenuSkins) {
                        g.drawRoundRect(410, 285, 79, 79, 10, 10);
                    }
                    else if (sobreBotao9MenuSkins) {
                        g.drawRoundRect(410, 410, 79, 79, 10, 10);
                    }

                    g.drawImage(COBRA_comida_BRANCO, 465, 525, 120, 50, this);
                    g.setColor(Color.gray);
                    g.fillRoundRect(235, 525, 50, 50, 10, 10);
                    if (skinChateado) {
                        skinAtual(g, "cobra", "chateado",235, 525);
                    }
                    else if (skinNarigudo) {
                        skinAtual(g, "cobra", "narigudo",235, 525);
                    }
                    else if (skinNinjaAzul) {
                        skinAtual(g, "cobra", "ninja azul",235, 525);
                    }
                    else if (skinNinjaVermelho) {
                        skinAtual(g, "cobra", "ninja vermelho",235, 525);
                    }
                    else if (skinOculosBranco) {
                        skinAtual(g, "cobra", "oculos branco",235, 525);
                    }
                    else if (skinOculosPreto) {
                        skinAtual(g, "cobra", "oculos preto",235, 525);
                    }
                    else if (skinOlhudo) {
                        skinAtual(g, "cobra", "olhudo",235, 525);
                    }
                    else if (skinVr) {
                        skinAtual(g, "cobra", "vr",235, 525);
                    }

                    if (skins50) {
                        g.drawImage(n50, 112, 287, 75, 75, this);
                        g.drawImage(n50, 112, 412, 75, 75, this);
                        g.drawImage(n100, 262, 162, 75, 75, this);
                        g.drawImage(n100, 262, 287, 75, 75, this);
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins100) {
                        g.drawImage(n100, 262, 162, 75, 75, this);
                        g.drawImage(n100, 262, 287, 75, 75, this);
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins200) {
                        g.drawImage(n200, 262, 412, 75, 75, this);
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins400) {
                        g.drawImage(n400, 412, 162, 75, 75, this);
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins700) {
                        g.drawImage(n700, 412, 162, 75, 75, this);
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                    if (skins1500) {
                        g.drawImage(n1500, 412, 412, 75, 75, this);
                    }
                }

                g.setColor(Color.black);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 40));
                g.drawString("SKINS", 237, 90);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 30));
                g.drawString("Skin atual:", 20, 562);
                g.drawString("Objeto: " , 325, 562);
            }
        }

        if (menuDefinições) {
            if (modoEscuro) {
                g.setColor(Color.white);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 37));
                g.drawString("Modo Claro:", 50, 162);
                g.drawString("Música de fundo:", 50, 315);
                g.drawString("Sons de fundo:", 50, 462);
                g.drawImage(modo_Escuro, 480, 112, 75, 75, this);
                g.drawImage(seta_para_trás_BRANCO, 525, 25, 50, 50, this);
                if (musicaDeFundo_ON_OFF) {
                    g.drawImage(modo_Escuro_2, 480, 262, 75, 75, this);
                }
                else {
                    g.drawImage(modo_Escuro, 480, 262, 75, 75, this);
                }
                if (sons_ON_OFF) {
                    g.drawImage(modo_Escuro_2, 480, 412, 75, 75, this);
                }
                else {
                    g.drawImage(modo_Escuro, 480, 412, 75, 75, this);
                }
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);

                g.setColor(Color.black);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 37));
                g.drawString("Modo Claro:", 50, 162);
                g.drawString("Música de fundo:", 50, 315);
                g.drawString("Sons de fundo:", 50, 462);
                g.drawImage(modo_Claro, 480, 112, 75, 75, this);
                g.drawImage(seta_para_trás_PRETO, 525, 25, 50, 50, this);
                if (musicaDeFundo_ON_OFF) {
                    g.drawImage(modo_Claro, 480, 262, 75, 75, this);
                }
                else {
                    g.drawImage(modo_Claro_2, 480, 262, 75, 75, this);
                }
                if (sons_ON_OFF) {
                    g.drawImage(modo_Claro, 480, 412, 75, 75, this);
                }
                else {
                    g.drawImage(modo_Claro_2, 480, 412, 75, 75, this);
                }
            }
        }

        if (menuScores) {
            if (modoEscuro) {
                g.setColor(Color.white);
                g.drawImage(seta_para_trás_BRANCO, 525, 25, 50, 50, this);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 60));
                g.drawString("PONTUAÇÃO:", 112, 150);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 100));
                g.setColor(Color.red);
                if (Integer.toString(getPontuacaoTotal()).length() == 1) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 267, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 2) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 235, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 3) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 205, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 4) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 173, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 5) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 140, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 6) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 110, 400);
                }
                else {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 78, 400);
                }
            }
            else {
                g.setColor(Color.white);
                g.fillRect(0, 0, 600, 600);

                g.setColor(Color.black);
                g.drawImage(seta_para_trás_PRETO, 525, 25, 50, 50, this);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 60));
                g.drawString("PONTUAÇÃO:", 112, 150);
                g.setFont(new Font("OCR A Extended", Font.PLAIN + Font.BOLD, 100));
                g.setColor(Color.red);
                if (Integer.toString(getPontuacaoTotal()).length() == 1) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 267, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 2) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 235, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 3) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 205, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 4) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 173, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 5) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 140, 400);
                }
                else if (Integer.toString(getPontuacaoTotal()).length() == 6) {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 110, 400);
                }
                else {
                    g.drawString(String.valueOf(getPontuacaoTotal()), 78, 400);
                }
            }
        }
        /*

        for (int i = 0; i < larguraDaJanela/tamanhoDaGrelha; i++){
            if (modoEscuro) {
                g.setColor(Color.white);
            }
            else {
                g.setColor(Color.black);
            }
            g.drawLine(i*tamanhoDaGrelha, 0, i*tamanhoDaGrelha, larguraDaJanela);
            g.drawLine(0, i*tamanhoDaGrelha, larguraDaJanela, i*tamanhoDaGrelha);
        }

         */

    }

    public void spawnDaComida() {
        comida.x = random.nextInt(larguraDaJanela/tamanhoDaGrelha);
        comida.y = random.nextInt(alturaDaJanela/tamanhoDaGrelha);
        for (Grelha peçaAtual : cobraCorpo) {
            while (colisao(peçaAtual, comida)) {
                comida.x = random.nextInt(larguraDaJanela / tamanhoDaGrelha);
                comida.y = random.nextInt(alturaDaJanela / tamanhoDaGrelha);
            }
        }
    }

    public boolean colisao(Grelha grelha1, Grelha grelha2) {
        return grelha1.x == grelha2.x && grelha1.y == grelha2.y;
    }

    public void movimento() {
        if (colisao(cobra, comida)) {
            cobraCorpo.add(new Grelha(comida.x, comida.y));
            spawnDaComida();
            if (modoFacil) {
                pontosAtuaisFacil++;
            }
            else if (modoMedio) {
                pontosAtuaisMedio++;
            }
            else if (modoDificil) {
                pontosAtuaisDificil++;
            }
            else if (modoHardcore) {
                pontosAtuaisHardcore++;
            }

            if (skinMaçã) {
                try {
                    File ficheiro = new File("SONS\\MORDER MAÇÃ.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(ficheiro);
                    somComer = AudioSystem.getClip();
                    somComer.open(audioStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sons_ON_OFF) {
                    somComer.start();
                }
            }
            else if (skinLego) {
                try {
                    File ficheiro = new File("SONS\\SOM LEGO.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(ficheiro);
                    somComer = AudioSystem.getClip();
                    somComer.open(audioStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sons_ON_OFF) {
                    somComer.start();
                }
            }
            else if (skinCocaCola) {
                try {
                    File ficheiro = new File("SONS\\SOM COCA-COLA.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(ficheiro);
                    somComer = AudioSystem.getClip();
                    somComer.open(audioStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sons_ON_OFF) {
                    somComer.start();
                }
            }
        }

        for (int i = cobraCorpo.size()-1; i >= 0; i--) {
            Grelha cobraParte = cobraCorpo.get(i);
            if (i == 0) {
                cobraParte.x = cobra.x;
                cobraParte.y = cobra.y;
            }
            else {
                Grelha cobraParteAnterior = cobraCorpo.get(i - 1);
                cobraParte.x = cobraParteAnterior.x;
                cobraParte.y = cobraParteAnterior.y;
            }
        }

        cobra.x += velX;
        cobra.y += velY;

        for (Grelha cobraParte : cobraCorpo) {
            if (colisao(cobra, cobraParte)) {
                gameOver = !(menuInicial || menuDificuldade || jogoPausado);
            }
        }

        if (cobra.x * tamanhoDaGrelha < 0 || cobra.x * tamanhoDaGrelha > larguraDaJanela ||
            cobra.y * tamanhoDaGrelha < 0 || cobra.y * tamanhoDaGrelha > alturaDaJanela) {
            gameOver = !(menuInicial || menuDificuldade || jogoPausado);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (musicaDeFundo_ON_OFF) {
            musicaDeFundo.loop(1000000000);
        }
        else {
            musicaDeFundo.stop();
        }

        if (!jogoPausado) {
            movimento();
        }
        repaint();
        if (gameOver) {
            if (modoFacil) {
                pontuacao.addPontuacaoFacil(pontosAtuaisFacil);
            }
            else if (modoMedio) {
                pontuacao.addPontuacaoMedio(pontosAtuaisMedio);
            }
            else if (modoDificil) {
                pontuacao.addPontuacaoDificil(pontosAtuaisDificil);
            }
            else if (modoHardcore) {
                pontuacao.addPontuacaoHardcore(pontosAtuaisHardcore);
            }
            escreverXML();
            loopDeJogo.stop();
        }
        pontuacaoTotal = getPontuacaoTotal();
        if (pontuacaoTotal >= 50) {
            skins50 = false;
        }
        if (pontuacaoTotal >= 100) {
            skins100 = false;
        }
        if (pontuacaoTotal >= 200) {
            skins200 = false;
        }
        if (pontuacaoTotal >= 400) {
            skins400 = false;
        }
        if (pontuacaoTotal >= 700) {
            skins700 = false;
        }
        if (pontuacaoTotal >= 1500) {
            skins1500 = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (começarJogo) {
            if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && velY != 1) {
                velX = 0;
                velY = -1;
            }
            else if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && velY != -1) {
                velX = 0;
                velY = 1;
            }
            else if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && velX != 1) {
                velX = -1;
                velY = 0;
            }
            else if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && velX != -1) {
                velX = 1;
                velY = 0;
            }
            else if (e.getKeyCode() == KeyEvent.VK_O) {
                modoEscuro = !modoEscuro;
            }
            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if (!menuInicial && !gameOver) {
                    jogoPausado = !jogoPausado;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (jogoPausado) {
                if (sobreBotao1MenuPausa) {
                    jogoPausado = false;
                }
                else if (sobreBotao2MenuPausa) {
                    reiniciarJogo();
                    jogoPausado = !jogoPausado;
                }
                else if (sobreBotao3MenuPausa) {
                    menuInicial = !menuInicial;
                    reiniciarJogo();
                    começarJogo = !começarJogo;
                    jogoPausado = !jogoPausado;
                }
            }
            else if (gameOver) {
                if (sobreBotao1MenuGameOver) {
                    reiniciarJogo();
                }
                else if (sobreBotao2MenuGameOver) {
                    menuInicial = !menuInicial;
                    reiniciarJogo();
                    começarJogo = !começarJogo;
                    modoFacil = false;
                    modoMedio = false;
                    modoDificil = false;
                    modoHardcore = false;
                }
            }
            else if (menuInicial) {
                if (sobreBotaoMenuInicialPlay) {
                    menuDificuldade = !menuDificuldade;
                    menuInicial = false;
                }
                else if (sobreBotaoMenuCores) {
                    menuInicial = false;
                    menuCores = !menuCores;
                }
                else if (sobreBotaoMenuSkins) {
                    menuInicial = false;
                    menuSkins = !menuSkins;
                }
                else if (sobreBotaoMenuDef) {
                    menuDefinições = !menuDefinições;
                    menuInicial = false;
                }
                else if (sobreBotaoMenuScores) {
                    menuInicial = false;
                    menuScores = !menuScores;
                }
            }
            else if (menuDificuldade) {
                if (sobreBotao1MenuDificuldade) {
                    loopDeJogo.stop();
                    loopDeJogo = new Timer(160, this);
                    loopDeJogo.start();
                    começarJogo = !começarJogo;
                    menuDificuldade = !menuDificuldade;
                    modoFacil = true;
                    modoMedio = false;
                    modoDificil = false;
                    modoHardcore = false;
                }
                else if (sobreBotao2MenuDificuldade) {
                    loopDeJogo.stop();
                    loopDeJogo = new Timer(130, this);
                    loopDeJogo.start();
                    começarJogo = !começarJogo;
                    menuDificuldade = !menuDificuldade;
                    modoFacil = false;
                    modoMedio = true;
                    modoDificil = false;
                    modoHardcore = false;
                }
                else if (sobreBotao3MenuDificuldade) {
                    loopDeJogo.stop();
                    loopDeJogo = new Timer(100, this);
                    loopDeJogo.start();
                    começarJogo = !começarJogo;
                    menuDificuldade = !menuDificuldade;
                    modoFacil = false;
                    modoMedio = false;
                    modoDificil = true;
                    modoHardcore = false;
                }
                else if (sobreBotao4MenuDificuldade) {
                    loopDeJogo.stop();
                    loopDeJogo = new Timer(70, this);
                    loopDeJogo.start();
                    começarJogo = !começarJogo;
                    menuDificuldade = !menuDificuldade;
                    modoFacil = false;
                    modoMedio = false;
                    modoDificil = false;
                    modoHardcore = true;
                }
            }
            else if (menuCores) {
                if (sobreBotaoSetaMenuCores) {
                    menuCores = false;
                    menuInicial = true;
                }
                else if (sobreBotaoCobraComida) {
                    escolherSkinCobraComida = !escolherSkinCobraComida;
                }
                if (escolherSkinCobraComida) {
                    if (sobreBotao1MenuCores) {
                        ativarCor("preto_branco", "comida");
                    }
                    else if (sobreBotao2MenuCores) {
                        ativarCor("vermelho", "comida");
                    }
                    else if (sobreBotao3MenuCores) {
                        ativarCor("magenta", "comida");
                    }
                    else if (sobreBotao4MenuCores) {
                        ativarCor("rosa", "comida");
                    }
                    else if (sobreBotao5MenuCores) {
                        ativarCor("azul", "comida");
                    }
                    else if (sobreBotao6MenuCores) {
                        ativarCor("ciano", "comida");
                    }
                    else if (sobreBotao7MenuCores) {
                        ativarCor("verde", "comida");
                    }
                    else if (sobreBotao8MenuCores) {
                        ativarCor("amarelo", "comida");
                    }
                    else if (sobreBotao9MenuCores) {
                        ativarCor("laranja", "comida");
                    }
                }
                else {
                    if (sobreBotao1MenuCores) {
                        ativarCor("preto_branco", "cobra");
                    }
                    else if (sobreBotao2MenuCores) {
                        ativarCor("vermelho", "cobra");
                    }
                    else if (sobreBotao3MenuCores) {
                        ativarCor("magenta", "cobra");
                    }
                    else if (sobreBotao4MenuCores) {
                        ativarCor("rosa", "cobra");
                    }
                    else if (sobreBotao5MenuCores) {
                        ativarCor("azul", "cobra");
                    }
                    else if (sobreBotao6MenuCores) {
                        ativarCor("ciano", "cobra");
                    }
                    else if (sobreBotao7MenuCores) {
                        ativarCor("verde", "cobra");
                    }
                    else if (sobreBotao8MenuCores) {
                        ativarCor("amarelo", "cobra");
                    }
                    else if (sobreBotao9MenuCores) {
                        ativarCor("laranja", "cobra");
                    }
                }
            }
            else if (menuSkins) {
                if (sobreBotaoSetaMenuSkins) {
                    menuSkins = false;
                    menuInicial = true;
                }
                else if (sobreBotaoCobraComida) {
                    escolherSkinCobraComida = !escolherSkinCobraComida;
                }
                if (!escolherSkinCobraComida) {
                    if (sobreBotao1MenuSkins) {
                        desativarSkins("cobra");
                    }
                    else if (sobreBotao2MenuSkins) {
                        if (!skins100) {
                            desativarSkins("cobra");
                            skinNinjaVermelho = true;
                        }
                    }
                    else if (sobreBotao3MenuSkins) {
                        if (!skins400) {
                            desativarSkins("cobra");
                            skinNarigudo = true;
                        }
                    }
                    else if (sobreBotao4MenuSkins) {
                        if (!skins50) {
                            desativarSkins("cobra");
                            skinOculosPreto = true;
                        }
                    }
                    else if (sobreBotao5MenuSkins) {
                        if (!skins100) {
                            desativarSkins("cobra");
                            skinNinjaAzul = true;
                        }
                    }
                    else if (sobreBotao6MenuSkins) {
                        if (!skins700) {
                            desativarSkins("cobra");
                            skinChateado = true;
                        }
                    }
                    else if (sobreBotao7MenuSkins) {
                        if (!skins50) {
                            desativarSkins("cobra");
                            skinOculosBranco = true;
                        }
                    }
                    else if (sobreBotao8MenuSkins) {
                        if (!skins200) {
                            desativarSkins("cobra");
                            skinOlhudo = true;
                        }
                    }
                    else if (sobreBotao9MenuSkins) {
                        if (!skins1500) {
                            desativarSkins("cobra");
                            skinVr = true;
                        }
                    }
                }
                else {
                    if (sobreBotao10MenuSkins) {
                        desativarSkins("comida");
                    }
                    else if (sobreBotao11MenuSkins) {
                        if (!skins100) {
                            desativarSkins("comida");
                            skinMaçã = true;
                        }
                    }
                    else if (sobreBotao12MenuSkins) {
                        if (!skins400) {
                            desativarSkins("comida");
                            skinCocaCola = true;
                        }
                    }
                    else if (sobreBotao13MenuSkins) {
                        if (!skins700) {
                            desativarSkins("comida");
                            skinLego = true;
                        }
                    }
                }
            }
            else if (menuDefinições) {
                if (sobreBotao1MenuDef) {
                    modoEscuro = !modoEscuro;
                }
                else if (sobreBotao2MenuDef) {
                    musicaDeFundo_ON_OFF = !musicaDeFundo_ON_OFF;
                }
                else if (sobreBotao3MenuDef) {
                    sons_ON_OFF = !sons_ON_OFF;
                }
                else if (sobreBotaoSetaMenuDef) {
                    menuInicial = true;
                    menuDefinições = false;
                }
            }
            else if (menuScores) {
                if (sobreBotaoSetaMenuScores) {
                    menuScores = false;
                    menuInicial = true;
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (jogoPausado) {
            sobreBotao1MenuPausa = x > 100 && x < 500 && y > 150 && y < 240;
            sobreBotao2MenuPausa = x > 150 && x < 450 && y > 300 && y < 363;
            sobreBotao3MenuPausa = x > 150 && x < 450 && y > 400 && y < 563;
        }
        else if (gameOver) {
            sobreBotao1MenuGameOver = x > 100 && x < 500 && y > 200 && y < 290;
            sobreBotao2MenuGameOver = x > 150 && x < 450 && y > 350 && y < 413;
            repaint();
        }
        else if (menuInicial) {
            sobreBotaoMenuInicialPlay = x > 200 && x < 400 && y > 300 && y < 385;
            sobreBotaoMenuCores = x > 425 && x < 525 && y > 450 && y < 550;
            sobreBotaoMenuSkins = x > 75 && x < 175 && y > 450 && y < 550;
            sobreBotaoMenuDef = x > 535 && x < 585 && y > 15 && y < 65;
            sobreBotaoMenuScores = x > 250 && x < 350 && y > 450 && y < 550;
        }
        else if (menuDificuldade) {
            sobreBotao1MenuDificuldade = x > 100 && x < 250 && y > 250 && y < 325;
            sobreBotao2MenuDificuldade = x > 350 && x < 500 && y > 250 && y < 325;
            sobreBotao3MenuDificuldade = x > 100 && x < 250 && y > 400 && y < 475;
            sobreBotao4MenuDificuldade = x > 350 && x < 500 && y > 400 && y < 475;

        }
        else if (menuCores) {
            sobreBotaoSetaMenuCores = x > 537 && x < 575 && y > 25 && y < 75;
            sobreBotao1MenuCores = x > 112 && x < 187 && y > 162 && y < 237;
            sobreBotao2MenuCores = x > 262 && x < 337 && y > 162 && y < 237;
            sobreBotao3MenuCores = x > 412 && x < 487 && y > 162 && y < 237;
            sobreBotao4MenuCores = x > 112 && x < 187 && y > 287 && y < 362;
            sobreBotao5MenuCores = x > 262 && x < 337 && y > 287 && y < 362;
            sobreBotao6MenuCores = x > 412 && x < 487 && y > 287 && y < 362;
            sobreBotao7MenuCores = x > 112 && x < 187 && y > 412 && y < 487;
            sobreBotao8MenuCores = x > 262 && x < 337 && y > 412 && y < 487;
            sobreBotao9MenuCores = x > 412 && x < 487 && y > 412 && y < 487;
            sobreBotaoCobraComida = x > 465 && x < 585 && y > 525 && y < 575;
        }
        else if (menuSkins) {
            sobreBotaoSetaMenuSkins = x > 537 && x < 575 && y > 25 && y < 75;
            sobreBotao1MenuSkins = x > 112 && x < 187 && y > 162 && y < 237;
            sobreBotao2MenuSkins = x > 262 && x < 337 && y > 162 && y < 237;
            sobreBotao3MenuSkins = x > 412 && x < 487 && y > 162 && y < 237;
            sobreBotao4MenuSkins = x > 112 && x < 187 && y > 287 && y < 362;
            sobreBotao5MenuSkins = x > 262 && x < 337 && y > 287 && y < 362;
            sobreBotao6MenuSkins = x > 412 && x < 487 && y > 287 && y < 362;
            sobreBotao7MenuSkins = x > 112 && x < 187 && y > 412 && y < 487;
            sobreBotao8MenuSkins = x > 262 && x < 337 && y > 412 && y < 487;
            sobreBotao9MenuSkins = x > 412 && x < 487 && y > 412 && y < 487;
            sobreBotao10MenuSkins = x > 150 && x < 250 && y > 200 && y < 300;
            sobreBotao11MenuSkins = x > 350 && x < 450 && y > 200 && y < 300;
            sobreBotao12MenuSkins = x > 150 && x < 250 && y > 400 && y < 500;
            sobreBotao13MenuSkins = x > 350 && x < 450 && y > 400 && y < 500;
            sobreBotaoCobraComida = x > 465 && x < 585 && y > 525 && y < 575;
        }
        else if (menuDefinições) {
            sobreBotaoSetaMenuDef = x > 537 && x < 575 && y > 25 && y < 75;
            sobreBotao1MenuDef = x > 480 && x < 555 && y > 112 && y < 187;
            sobreBotao2MenuDef = x > 480 && x < 555 && y > 262 && y < 337;
            sobreBotao3MenuDef = x > 480 && x < 555 && y > 412 && y < 487;
        }
        else if (menuScores) {
            sobreBotaoSetaMenuScores = x > 537 && x < 575 && y > 25 && y < 75;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}

    private void escreverXML() {
        try {
            copiarValoresXML2ArrayList();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("BaseDados");
            doc.appendChild(rootElement);

            Element pontuaçõesElement = doc.createElement("Pontuações");
            rootElement.appendChild(pontuaçõesElement);

            Element facilElement = doc.createElement("Fácil");
            pontuaçõesElement.appendChild(facilElement);
            Element medioElement = doc.createElement("Médio");
            pontuaçõesElement.appendChild(medioElement);
            Element dificilElement = doc.createElement("Difícil");
            pontuaçõesElement.appendChild(dificilElement);
            Element hardcoreElement = doc.createElement("Hardcore");
            pontuaçõesElement.appendChild(hardcoreElement);

            for (int pontos : pontuacao.getPontuacaoFacil()) {
                Element pontosElement = doc.createElement("F");
                pontosElement.setTextContent(String.valueOf(pontos));
                facilElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoFacil();
            for (int pontos : pontuacao.getPontuacaoFacilCOPIA()) {
                Element pontosElement = doc.createElement("F");
                pontosElement.setTextContent(String.valueOf(pontos));
                facilElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoFacilCOPIA();

            for (int pontos : pontuacao.getPontuacaoMedio()) {
                Element pontosElement = doc.createElement("M");
                pontosElement.setTextContent(String.valueOf(pontos));
                medioElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoMedio();
            for (int pontos : pontuacao.getPontuacaoMedioCOPIA()) {
                Element pontosElement = doc.createElement("M");
                pontosElement.setTextContent(String.valueOf(pontos));
                medioElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoMedioCOPIA();

            for (int pontos : pontuacao.getPontuacaoDificil()) {
                Element pontosElement = doc.createElement("D");
                pontosElement.setTextContent(String.valueOf(pontos));
                dificilElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoDificil();
            for (int pontos : pontuacao.getPontuacaoDificilCOPIA()) {
                Element pontosElement = doc.createElement("D");
                pontosElement.setTextContent(String.valueOf(pontos));
                dificilElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoDificilCOPIA();

            for (int pontos : pontuacao.getPontuacaoHardcore()) {
                Element pontosElement = doc.createElement("H");
                pontosElement.setTextContent(String.valueOf(pontos));
                hardcoreElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoHardcore();
            for (int pontos : pontuacao.getPontuacaoHardcoreCOPIA()) {
                Element pontosElement = doc.createElement("H");
                pontosElement.setTextContent(String.valueOf(pontos));
                hardcoreElement.appendChild(pontosElement);
            }
            pontuacao.cleanPontuacaoHardcoreCOPIA();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream("BaseDeDados.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copiarValoresXML2ArrayList() {
        try {
            File xmlFile = new File("BaseDeDados.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            NodeList pontuacaoFacilList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Fácil/F", doc, XPathConstants.NODESET);
            NodeList pontuacaoMedioList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Médio/M", doc, XPathConstants.NODESET);
            NodeList pontuacaoDificilList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Difícil/D", doc, XPathConstants.NODESET);
            NodeList pontuacaoHardcoreList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Hardcore/H", doc, XPathConstants.NODESET);

            for (int i = 0; i < pontuacaoFacilList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoFacilList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontuacao.addPontuacaoFacilCOPIA(pontos);
            }
            for (int i = 0; i < pontuacaoMedioList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoMedioList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontuacao.addPontuacaoMedioCOPIA(pontos);
            }
            for (int i = 0; i < pontuacaoDificilList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoDificilList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontuacao.addPontuacaoDificilCOPIA(pontos);
            }
            for (int i = 0; i < pontuacaoHardcoreList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoHardcoreList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontuacao.addPontuacaoHardcoreCOPIA(pontos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPontuacaoTotal() {
        int pontosTotais = 0;
        try {
            File xmlFile = new File("BaseDeDados.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            NodeList pontuacaoFacilList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Fácil/F", doc, XPathConstants.NODESET);
            NodeList pontuacaoMedioList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Médio/M", doc, XPathConstants.NODESET);
            NodeList pontuacaoDificilList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Difícil/D", doc, XPathConstants.NODESET);
            NodeList pontuacaoHardcoreList = (NodeList) xpath.evaluate("/BaseDados/Pontuações/Hardcore/H", doc, XPathConstants.NODESET);

            for (int i = 0; i < pontuacaoFacilList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoFacilList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontosTotais += pontos;
            }
            for (int i = 0; i < pontuacaoMedioList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoMedioList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontosTotais += pontos;
            }
            for (int i = 0; i < pontuacaoDificilList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoDificilList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontosTotais += pontos;
            }
            for (int i = 0; i < pontuacaoHardcoreList.getLength(); i++) {
                Element pontuacaoElement = (Element) pontuacaoHardcoreList.item(i);
                int pontos = Integer.parseInt(pontuacaoElement.getTextContent());
                pontosTotais += pontos;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pontosTotais;
    }

}
