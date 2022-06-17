package TrabalhoLabirintoPilhas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Principal{
    //Main com o computador resolvendo tudo sozinho.
    public static void main(String[] args) {
        //Instancio meu labirinto
        Labirinto labirinto = new Labirinto();
        //Crio a tabela do labirinto
        String[][] table = labirinto.criaTabela();
        //INICIO O LABIRINTO
        System.out.println("         *TrabalhoLabirintoPilhas.Labirinto*");
        labirinto.imprimeLabirinto(table);
        while(!labirinto.isFimLabirinto()) {
            labirinto.imprimeLabirinto(table);
            table = labirinto.doMoverValidandoPosicao(table);
        }
        System.out.println("    *TrabalhoLabirintoPilhas.Labirinto Resolvido*");
        labirinto.imprimeLabirinto(table);
        labirinto.viewPilha();

    }

    //Main do labirinto com interação do usuário
    /*public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        //Instancio meu labirinto
        TrabalhoLabirintoPilhas.Labirinto labirinto = new TrabalhoLabirintoPilhas.Labirinto();
        //Crio a tabela do labirinto
        String[][] table = labirinto.criaTabela();
        //Crio as trilhas do labirinto
        table = labirinto.criaTrilhaLabirinto(table);
        //INICIO O LABIRINTO
        System.out.println("**** RESOLVA O LABIRINTO ****");
        labirinto.imprimeLabirinto(table);
        while (true) {
            //Exibo os controle e solicito ao operador uma entrada.
            labirinto.exibeControles();
            Integer result = leitor.nextInt();
            //Seleciono a opção desejada
            if (result >= 0 && result <= 3) {
                table = labirinto.doMover(table, result);
            } else if ( result == 4) {
               labirinto.viewPilha();
            } else if ( result == 5) {
                labirinto.imprimeLabirinto(table);
            } else {
                System.out.println("-> Entrada invalida, tente novamente.");
            }
        }
    }*/
}
