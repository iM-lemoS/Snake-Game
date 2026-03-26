package Jogo;

import java.util.ArrayList;

public class Pontos {
    public ArrayList<Integer> pontuacaoFACIL, pontuacaoMEDIO, pontuacaoDIFICIL, pontuacaoHARDCORE,
                              pontuacaoFACILcopia, pontuacaoMEDIOcopia, pontuacaoDIFICILcopia, pontuacaoHARDCOREcopia;

    public Pontos() {
        this.pontuacaoFACIL = new ArrayList<>();
        this.pontuacaoMEDIO = new ArrayList<>();
        this.pontuacaoDIFICIL = new ArrayList<>();
        this.pontuacaoHARDCORE = new ArrayList<>();
        this.pontuacaoFACILcopia = new ArrayList<>();
        this.pontuacaoMEDIOcopia = new ArrayList<>();
        this.pontuacaoDIFICILcopia = new ArrayList<>();
        this.pontuacaoHARDCOREcopia = new ArrayList<>();
    }

    public void addPontuacaoFacil(int pontos) {
        pontuacaoFACIL.add(pontos);
    }
    public void addPontuacaoMedio(int pontos) {
        pontuacaoMEDIO.add(pontos);
    }
    public void addPontuacaoDificil(int pontos) {
        pontuacaoDIFICIL.add(pontos);
    }
    public void addPontuacaoHardcore(int pontos) {
        pontuacaoHARDCORE.add(pontos);
    }

    public void addPontuacaoFacilCOPIA(int pontos) {
        pontuacaoFACILcopia.add(pontos);
    }
    public void addPontuacaoMedioCOPIA(int pontos) {
        pontuacaoMEDIOcopia.add(pontos);
    }
    public void addPontuacaoDificilCOPIA(int pontos) {
        pontuacaoDIFICILcopia.add(pontos);
    }
    public void addPontuacaoHardcoreCOPIA(int pontos) {
        pontuacaoHARDCOREcopia.add(pontos);
    }

    public ArrayList<Integer> getPontuacaoFacil() {
        return pontuacaoFACIL;
    }
    public ArrayList<Integer> getPontuacaoMedio() {
        return pontuacaoMEDIO;
    }
    public ArrayList<Integer> getPontuacaoDificil() {
        return pontuacaoDIFICIL;
    }
    public ArrayList<Integer> getPontuacaoHardcore() {
        return pontuacaoHARDCORE;
    }

    public ArrayList<Integer> getPontuacaoFacilCOPIA() {
        return pontuacaoFACILcopia;
    }
    public ArrayList<Integer> getPontuacaoMedioCOPIA() {
        return pontuacaoMEDIOcopia;
    }
    public ArrayList<Integer> getPontuacaoDificilCOPIA() {
        return pontuacaoDIFICILcopia;
    }
    public ArrayList<Integer> getPontuacaoHardcoreCOPIA() {
        return pontuacaoHARDCOREcopia;
    }

    public void cleanPontuacaoFacil() {
        pontuacaoFACIL.clear();
    }
    public void cleanPontuacaoMedio() {
        pontuacaoMEDIO.clear();
    }
    public void cleanPontuacaoDificil() {
        pontuacaoDIFICIL.clear();
    }
    public void cleanPontuacaoHardcore() {
        pontuacaoHARDCORE.clear();
    }

    public void cleanPontuacaoFacilCOPIA() {
        pontuacaoFACILcopia.clear();
    }
    public void cleanPontuacaoMedioCOPIA() {
        pontuacaoMEDIOcopia.clear();
    }
    public void cleanPontuacaoDificilCOPIA() {
        pontuacaoDIFICILcopia.clear();
    }
    public void cleanPontuacaoHardcoreCOPIA() {
        pontuacaoHARDCOREcopia.clear();
    }
}
