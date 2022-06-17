package LinkedList;

import java.lang.reflect.GenericArrayType;

public class LinkedList<T> {
    Node[] dados;
    Node base;
    Node top;
    int size = -1;

    public LinkedList(int tamanho) {
        this.dados = new Node[tamanho];
    }

    public void set(int idx, T data){
        if(!isFull()) {
            for (int i = 0; i < dados.length; i++) {
                if (dados[i] == null) {
                    Node node = getNode(idx);
                    if(node != null) {
                        if (node == base) {
                            dados[i] = new Node(null, data, node);
                            node.previous = dados[i];
                            base = dados[i];
                        } else if (node == top) {
                            dados[i] = new Node(node.previous, data, node);
                            node.previous.next = dados[i];
                            node.previous = dados[i];
                        } else {
                            dados[i] = new Node(node.previous, data, node);
                            node.previous.next = dados[i];
                            node.next.previous = dados[i];
                        }
                    } else {
                        dados[i] = new Node(top, data, null);
                        top = dados[i];
                    }
                    System.out.println("Elemento '" + data + "' incluido com sucesso. ");
                    size++;
                    break;
                }
            }
        } else {
            System.out.println("Nao foi possivel adicionar o item '"+ data+ "', pois sua lista ja esta cheia.");
        }
    }

    public void add(T data){
        if(isEmpty()){
            dados[0] = new Node(null, data, null);
            base = dados[0];
            top = dados[0];
            size++;
            System.out.println("Elemento '"+data+"' incluido com sucesso. ");
        } else {
            if(isFull()){
                System.out.println("Nao foi possivel adicionar o item '"+ data+ "', pois sua lista ja esta cheia.");
            } else {
                size++;
                dados[size] = new Node(top, data, null);
                dados[size-1].next = dados[size];
                top = dados[size];
                System.out.println("Elemento '"+data+"' incluido com sucesso. ");
            }
        }
    }

    public Node remove(T value){
        Node node = base;
        while (node.next != null || node.previous != null){
            if(node != null && node.data == value){
                if(node == top){
                    node.previous.next = null;
                    top = node.previous;
                } else if (node == base){
                    node.next.previous = null;
                    base = node.next;
                } else {
                    Node nextNode = node.next;
                    Node previousNode = node.previous;
                    previousNode.next = nextNode;
                    nextNode.previous = previousNode;
                }
                node.next = null;
                node.previous = null;
                size--;
                break;
            }
            node = node.next;
        }
        System.out.println("--> Item removido '"+ node.data+"'");
        atualizaLista();
        return node;
    }

    public Node remove(int pos){
        Node node = getNode(pos);
        if(node != null) {
            if(node == top){
                node.previous.next = null;
                top = node.previous;
            } else if (node == base){
                node.next.previous = null;
                base = node.next;
            } else {
                Node nextNode = node.next;
                Node previousNode = node.previous;
                previousNode.next = nextNode;
                nextNode.previous = previousNode;
            }
            node.next = null;
            node.previous = null;
            size--;
        }
        System.out.println("--> Item removido "+ node.data);
        atualizaLista();
        return node;
    }

    public Node getNode(int pos) {
        Node myNode = null;
        if(pos <= getSize()){
            int middle = (int) getSize()/2;
            if(pos <= middle){
                myNode = base;
                for (int i = 0; i < pos; i++) {
                    myNode = myNode.next;
                }
            }
            if(pos > middle){
                myNode = top;
                for(int i = getSize(); i > pos; i--){
                    myNode = myNode.previous;
                }
            }
        }
        return myNode;
    }

    public T getNodeT(int pos){
        if(getNode(pos) != null){
            return (T) getNode(pos).data;
        }
        return null;
    }

    public boolean isEmpty(){
        return getSize() == -1;
    }

    public boolean isFull(){
        return (getSize()+1) == dados.length;
    }

    public int getSize(){
        return size;
    }

    public void atualizaLista(){
        for (int i = 0; i < dados.length; i++) {
            if(dados[i] != top && dados[i] != base && dados[i] != null){
                if(dados[i].previous == null || dados[i].next == null){
                    dados[i] = null;
                }
            }
        }
    }

    public String toString(){
        return base.toString();
    }

    public static class Node<T> {
        Node<T> previous;
        T data;
        Node<T> next;

        @Override
        public String toString() {
            return data + (next != null ? ", "+ next.toString() : "");
        }

        public Node(Node<T> previous, T data, Node<T> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }



}
