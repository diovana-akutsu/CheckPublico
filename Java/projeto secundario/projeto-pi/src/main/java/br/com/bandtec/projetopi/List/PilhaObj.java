package br.com.bandtec.projetopi.List;

public class PilhaObj<P> {
    private int topo;
    private P[] pilha;

    public PilhaObj(int capacidade){
        topo = -1;
        pilha = (P[]) new Object[capacidade];
    }

    public boolean isEmpty(){
        return topo ==-1;
    }

    public boolean isFull() {
        return (topo == pilha.length -1);
    }

    public void push(P info){
        if (!isFull()) {
            pilha[++topo] = info;
        } else {
            System.out.println("Pilha cheia");
        }
    }

    public P pop(){
        if (!isFull()) {
            return pilha[topo--];
        }
        return null;
    }

    public P peek(){
        if(!isEmpty()) {
            return pilha[topo];
        }
        return null;
    }

    public void exibe(){
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            for (int i = 0; i <= topo; i++){
                System.out.println(pilha[i]);
            }
        }
    }
}
