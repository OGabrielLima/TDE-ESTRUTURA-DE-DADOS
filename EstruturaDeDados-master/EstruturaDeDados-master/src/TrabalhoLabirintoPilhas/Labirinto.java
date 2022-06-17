package TrabalhoLabirintoPilhas;

import com.sun.source.tree.ReturnTree;
import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Labirinto {
    private int idxLin;
    private int idxCol;
    private Deque<String> stack = new ArrayDeque<String>();
    private List<String> lastPositions = new ArrayList<>();
    private boolean fimLabirinto;

    //Crio a tabela do meu labirinto
    public String[][] criaTabela(){
        String[][] tabLabirinto = new String[11][11];
        //String[0][0] = 0
        //[ [0,1,2,3],
        //  [0,1,5,6]  ]
        //Preencho a lista
        for (int lin = 0; lin < tabLabirinto.length; lin++) {
            for (int col = 0; col < tabLabirinto.length; col++) {
                //Strint[2][0]
                //String[2][1];;;
                tabLabirinto[lin][col] = "#";
            }
        }
        return criaTrilhaLabirinto(tabLabirinto);
    }

    //Desenho a trilha do labirinto
    public String[][] criaTrilhaLabirinto(String[][] labirinto){
        Random aleatory = new Random();
        int col = 1; //aleatory.nextInt(10);
        setIdxCol(col);
        int lin = 1; //aleatory.nextInt(10);
        setIdxLin(lin);
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
        labirinto[col][lin] = "E";
        labirinto[1][3] = "S";
        return labirinto;
    }

    //Faço a impressão do labirinto
    public void imprimeLabirinto(String[][] labirinto){
        System.out.println("    0 1 2 3 4 5 6 7 8 9 0");
        for (int coluna = 0; coluna < labirinto.length; coluna++) {
            //Printo a linha
                System.out.print((coluna != 10 ? coluna + " | " : "0 | "));
            for (int linha = 0; linha < labirinto.length; linha++) {
                System.out.print(labirinto[coluna][linha] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    //Visualizo minha pilha
    public void viewPilha(){
        System.out.println("Esta e sua pilha: (linha,coluna) - opcao escolhida");
        System.out.println(getStack().toString());
        System.out.println("Fim da pilha");
    }

    //Exibo os controles quando houver interação com o usuário
    public void exibeControles(){
        System.out.println("Insira as coordenadas do labirinto: ");
        System.out.print("0 - Subir | 1 - Direita | 2 - Descer | 3 - Esquerda | 4 - Ver pilha | 5 - Ver labirinto -> ");
    }

    //Movo o labirinto valida a posição (computador descobre o caminho)
    public String[][] doMoverValidandoPosicao(String[][] labirinto){
        //Valido se devo finalizar o programa
        for (int i = 0; i <= 3; i++) { //4 posicoes
            int x = getIdxLin();
            int y = getIdxCol();
            //Seleciono as opções do controle
            if(i == 0) {
                y--;
            } else if (i == 1){
                x++;
            } else if (i == 2){
                y++;
            } else if (i == 3){
                x--;
            }
            if (labirinto[y][x].equals("S")) {
                setFimLabirinto(true);
                return labirinto;
            }
        }
        //valido casa em blank status
        for (int i = 0; i <= 3; i++) {
            int x = getIdxLin();
            int y = getIdxCol();
            //Seleciono as opções do controle
            if(i == 0) {
                y--;
            } else if (i == 1){
                x++;
            } else if (i == 2){
                y++;
            } else if (i == 3){
                x--;
            }
            //Faço as validações, altero a pilha e finalizo o for para validar a seguinte posição.
            if(labirinto[y][x].equals(" ")){
                labirinto[y][x] = "-";
                setIdxCol(y);
                setIdxLin(x);
                stack.add("("+y+","+x+") - "+i);
                return labirinto;
            }
        }
        //Valido casa onde já passei
        for (int i = 0; i <= 3; i++) {
            int x = getIdxLin();
            int y = getIdxCol();
            lastPositions.add(stack.getLast());
            //Seleciono as opções do controle
            if (i == 0) {
                y--;
            } else if (i == 1) {
                x++;
            } else if (i == 2) {
                y++;
            } else if (i == 3) {
                x--;
            }
            //Faço as validações, altero a pilha e finalizo o for para validar a seguinte posição.
            if (labirinto[y][x].equals("-")) {
                //faço um reverse da posição (posição alterada deve ser a que saí)
                String atualPosition = "("+y+","+x+") - "+i;
                if(!lastPositions.contains(atualPosition)){
                    stack.removeLast();
                    setIdxCol(y);
                    setIdxLin(x);
                    return labirinto;
                }
            }
        }
        return labirinto;
    }

    //Movo o '-' conforme o que o user pede
    public String[][] doMover(String[][] labirinto, int opcao){
        int x = getIdxLin();
        int y = getIdxCol();
        //Seleciono as opções do controle
        if(opcao == 0) {
            //Subir
            y--;
        } else if (opcao == 1){
            //Ir para direita
            x++;
        } else if (opcao == 2){
            //Descer
            y++;
        } else if (opcao == 3){
            //Ir para esquerda
            x--;
        }
        //Faço as validações e altero a pilha.
        if(labirinto[y][x].equals(" ")){
            labirinto[y][x] = "-";
            System.out.println("-> Avancei uma casa. =)");
            setIdxCol(y);
            setIdxLin(x);
            stack.add("("+x+","+y+") - "+opcao);
        } else if (labirinto[y][x].equals("-")) {
            //faço um reverse da posição (posição alterada deve ser a que saí)
            labirinto[getIdxCol()][getIdxLin()] = " ";
            System.out.println("-> Retornei uma casa. =/");
            setIdxCol(y);
            setIdxLin(x);
            stack.removeLast();
        } else if (labirinto[y][x].equals("S")) {
            System.out.println("<3 =) |Voce zerou o labirinto| =) <3");
            setFimLabirinto(true);
        } else {
            System.out.println("-> Posicao invalida, tente novamente!");
        }
        imprimeLabirinto(labirinto);
        return labirinto;
    }


    public int getIdxLin() {
        return idxLin;
    }

    public void setIdxLin(int idxLin) {
        this.idxLin = idxLin;
    }

    public int getIdxCol() {
        return idxCol;
    }

    public void setIdxCol(int idxCol) {
        this.idxCol = idxCol;
    }

    public Deque<String> getStack() {
        return stack;
    }

    public void setStack(Deque<String> stack) {
        this.stack = stack;
    }

    public boolean isFimLabirinto() {
        return fimLabirinto;
    }

    public void setFimLabirinto(boolean fimLabirinto) {
        this.fimLabirinto = fimLabirinto;
    }

    public List<String> getLastPositions() {
        return lastPositions;
    }

    public void setLastPositions(List<String> lastPositions) {
        this.lastPositions = lastPositions;
    }
}
