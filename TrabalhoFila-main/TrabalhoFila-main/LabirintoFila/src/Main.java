import Labirinto.Labirinto;
import LinkedQueue.CircularQueue;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    //Main do labirinto com interação do usuário
    public static void main(String[] args) throws InterruptedException {
        Scanner leitor = new Scanner(System.in);
        //Instancio meu labirinto
        Labirinto labirinto = new Labirinto();
        //Crio a tabela do labirinto
        String[][] table = labirinto.criaTabela();
        //Instancio a fila com x posicoes
        CircularQueue fila = new CircularQueue(100);
        //INICIO O LABIRINTO
        System.out.println("**** RESOLVA O LABIRINTO E TENTE MEMORIZAR SUAS POSICOES ****");
        while (!labirinto.isFimLabirinto() ) {
            //Exibo os controle e solicito ao operador uma entrada.
            labirinto.imprimeLabirinto(table);
            labirinto.exibeControles();
            //System.out.println(fila.toString());
            Integer result = leitor.nextInt();
            //Seleciono a opção desejada
            if (result >= 1 && result <= 4){
                fila.add(result);
            } else if (result == 5) {
                if(!fila.isEmpty()){
                    System.out.println("-----EXECUTANDO LABIRINTO-----");
                    while (!labirinto.isFimLabirinto() && !labirinto.isMovimentoInvalido()) {
                        if(fila.isEmpty()){
                            labirinto.setMovimentoInvalido(true);
                            System.out.println("*** O CAMINHO INFORMADO NAO CHEGOU ATE O FINAL DO LABIRINTO ***");
                        } else {
                            int value = fila.remove();
                            System.out.println("TESTADO MOVIMENTO NUM.: "+value);
                            table = labirinto.doMover(table, value);
                            labirinto.imprimeLabirinto(table);
                            TimeUnit.SECONDS.sleep(3);
                        }
                    }
                    if(labirinto.isMovimentoInvalido()){
                        System.out.println();
                        System.out.println("********************** TENTE NOVAMENTE **********************");
                        System.out.println("**** RESOLVA O LABIRINTO E TENTE MEMORIZAR SUAS POSICOE S****");
                        fila.clear();
                        table = labirinto.criaTabela();
                    }
                }
            } else if ( result == 6) {
                fila.clear();
                table = labirinto.criaTabela();
            } else {
                System.out.println("-> Entrada invalida, tente novamente.");
            }
        }
    }
}
