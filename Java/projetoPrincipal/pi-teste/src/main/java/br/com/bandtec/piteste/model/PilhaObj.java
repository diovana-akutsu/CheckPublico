package br.com.bandtec.piteste.model;

public class PilhaObj<T> {

    private Integer topo;
    private T[] pilha;
    private int capacidade;

    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
        this.capacidade = capacidade;
    }

    public Boolean isEmpty() {
        if (this.topo.equals(-1))
            return true;
        else
            return false;
    }

    public Boolean isFull() {
        if (this.topo.equals(this.capacidade-1))
            return true;
        else
            return false;
    }

    public void push(T info) {
        if (!isFull()) {
            this.topo++;
            pilha[topo] = info;
        }
    }

    public T pop(){
        if (!isEmpty()){
            return pilha[topo--];
        }
        return null;
    }

    public T peek(){
        if (!isEmpty()){
            return pilha[topo];
        }else
            return null;
    }

    public void exibe(){
        if(isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for(int i = 0; i <= topo; i++) {
                System.out.println(pilha[i]);
            }
        }
    }

    public PilhaObj <T> multiPop(int n){
        PilhaObj auxilar= new PilhaObj(n);
        if (n>topo)
            return null;
        else{
            for (int i=0;i<n;i++){
                auxilar.push(pop());
            }
            return auxilar;
        }
    }

    public void multiPush(PilhaObj <T> aux){
        for (int i=0; !aux.isEmpty();i++){
            push(aux.pop());
        }
    }


}
