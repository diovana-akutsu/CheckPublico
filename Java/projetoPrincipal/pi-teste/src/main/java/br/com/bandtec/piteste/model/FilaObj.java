package br.com.bandtec.piteste.model;

public class FilaObj<T> {
    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public Boolean isEmpty() {
        if (this.tamanho == 0)
            return true;
        else
            return false;
    }

    public Boolean isFull() {
        if (this.tamanho==(fila.length))
            return true;
        else
            return false;
    }

    public void insert(T info) {
        if (!isFull()) {
            fila[tamanho++] = info;
        } else {
            System.out.println("Fila cheia");
        }
    }

    public T peek(){
        return fila[0];
    }

    public T pol(){
        T primeiro = peek();

        if (!isEmpty()) {
            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }
            fila[tamanho - 1] = null;
            tamanho--;
        }
        return primeiro;
    }

    public void exibe(){
        if(isEmpty()) {
            System.out.println("Fila vazia");
        }
        else {
            for(int i = 0; i < tamanho; i++) {
                System.out.println(fila[i]);
            }
        }
    }

//    public PilhaObj <T> multiPop(int n){
//        PilhaObj auxilar= new PilhaObj(n);
//        if (n>topo)
//            return null;
//        else{
//            for (int i=0;i<n;i++){
//                auxilar.push(pop());
//            }
//            return auxilar;
//        }
//    }
//
//    public void multiPush(PilhaObj <T> aux){
//        for (int i=0; !aux.isEmpty();i++){
//            push(aux.pop());
//        }
//    }


}
