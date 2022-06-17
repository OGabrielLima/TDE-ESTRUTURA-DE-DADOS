package Labirinto;

import com.sun.source.tree.ReturnTree;
import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Labirinto {
    private int idxLin;
    private int idxCol;
    private boolean fimLabirinto = false;
    private boolean movimentoInvalido = false;
    //Crio a tabela do meu labirinto
    public String[][] criaTabela(){
        String[][] tabLabirinto = new String[11][11];
        //Preencho a lista
        for (int lin = 0; lin < tabLabirinto.length; lin++) {
            for (int col = 0; col < tabLabirinto.length; col++) {
                tabLabirinto[lin][col] = "#";
            }
        }
        return criaTrilhaLabirinto(tabLabirinto);
    }

    //Desenho a trilha do labirinto
    public String[][] criaTrilhaLabirinto(String[][] labirinto){
        Random aleatory = new Random();
        idxLin = 1;
        idxCol = 1;
        for (int i = 1; i < 10; i++) {
            //labirinto[linha][coluna]
            labirinto[i][1] = " ";
            labirinto[9][i] = " ";
            labirinto[i][9] = " ";
            labirinto[5][i] = " ";
            labirinto[7][i] = " ";
            labirinto[3][i] = " ";
            labirinto[1][i] = " ";
            labirinto[6][3] = " ";
            labirinto[2][7] = " ";
            labirinto[2][5] = " ";
            labirinto[4][5] = " ";
            labirinto[5][2] = "#";
            labirinto[7][2] = "#";
            labirinto[5][8] = "#";
            labirinto[3][3] = "#";
            labirinto[3][6] = "#";
            labirinto[1][2] = "#";
            labirinto[1][6] = "#";
            labirinto[1][8] = "#";
        }
        labirinto[1][1] = "E";
        labirinto[1][3] = "S";
        return labirinto;
    }

    //Faço a impressão do labirinto
    public void imprimeLabirinto(String[][] labirinto){
        System.out.println("    0 1 2 3 4 5 6 7 8 9 0");
        for (int lin = 0; lin < labirinto.length; lin++) {
            //Printo a linha
            System.out.print((lin != 10 ? lin + " | " : "0 | "));
            for (int col = 0; col < labirinto.length; col++) {
                System.out.print(labirinto[lin][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }


    //Exibo os controles quando houver interação com o usuário
    public void exibeControles(){
        System.out.println("Insira as coordenadas do labirinto: ");
        System.out.print("1 - Up | 2 - Right | 3 - Down | 4 - Left | 5 - Go | 6 - Restart -> ");
    }

    //Movo o '-' conforme o que o user pede
    public String[][] doMover(String[][] labirinto, int opcao){
        try {
            int lin = idxLin;
            int col = idxCol;
            //Seleciono as opções do controle
            if(opcao == 1) {
                lin--; //up
            } else if (opcao == 2){
                col++; //Right
            } else if (opcao == 3){
                lin++; //Down
            } else if (opcao == 4){
                col--; //Left
            }
            //System.out.println("linha: "+lin+" coluna: "+col);
            if(labirinto[lin][col].equals(" ") || labirinto[lin][col].equals("-")){
                labirinto[idxLin][idxCol] = "-";
                labirinto[lin][col] = "P";
                System.out.println("-> Avancei uma casa. =)");
                idxLin = lin;
                idxCol = col;
            } else if (labirinto[lin][col].equals("S")) {
                labirinto[idxLin][idxCol] = "-";
                labirinto[lin][col] = "P";
                System.out.println("<3 =) |Voce zerou o labirinto| =) <3");
                setFimLabirinto(true);
            } else {
                System.out.println("*** POSICAO INVALIDA ***");
                setMovimentoInvalido(true);
            }
            return labirinto;
        } catch (Exception e){
            setMovimentoInvalido(true);
            return labirinto;
        }
    }

    public boolean isFimLabirinto() {
        return fimLabirinto;
    }

    public void setFimLabirinto(boolean fimLabirinto) {
        this.fimLabirinto = fimLabirinto;
    }

    public boolean isMovimentoInvalido() {
        return movimentoInvalido;
    }

    public void setMovimentoInvalido(boolean movimentoInvalido) {
        this.movimentoInvalido = movimentoInvalido;
    }
}
