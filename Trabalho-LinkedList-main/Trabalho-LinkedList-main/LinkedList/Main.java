package LinkedList;

public class Main {
    public static void main(String[] args) {
        //Inicio minha lista com 11 posições
        LinkedList<String> lista = new LinkedList<String>(10);
        //Adiciono alguns elementos na lista
        System.out.println("-------------------------------");
        System.out.println("Adiciono varios itens em minha lista");
        lista.add("ElementZero");
        lista.add("ElementOne");
        lista.add("ElementTwo");
        lista.add("ElementThree");
        lista.add("ElementFour");
        lista.add("ElementFive");
        lista.add("ElementSix");
        lista.add("ElementSeven");
        lista.add("ElementEight");
        lista.add("ElementNine");
        lista.add("ElementTen");
        lista.add("ElementEleven");
        System.out.println("Imprimo a lista");
        System.out.println(lista.toString());
        System.out.println("-------------------------------");
        System.out.println("Apago o elemento 'ElementFour' pela seu valor");
        lista.remove("ElementFour");
        System.out.println("Imprimo a lista");
        System.out.println(lista.toString());
        System.out.println("-------------------------------");
        System.out.println("Apago o elemento '4' pela seu valor");
        lista.remove(4);
        System.out.println("Imprimo a lista");
        System.out.println(lista.toString());
        System.out.println("-------------------------------");
        System.out.println("Adiciono um item no idx 1");
        lista.set(1, "ElementHalf");
        lista.set(9, "ElementToFinish");
        System.out.println("Imprimo a lista");
        System.out.println(lista.toString());
        System.out.println("-------------------------------");
    }
}
