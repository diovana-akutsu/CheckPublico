package br.com.bandtec.projetopi.List;

public class ListObj <T>{
    private T[] vetor;
    private int numElem;

    public ListObj(int tam) {
        this.vetor = (T[]) new Object[tam];
        this.numElem = 0;
    }

    public boolean adiciona(T valor){
        if (numElem >= vetor.length) {
            System.out.println("Lista está cheia");
            return false;
        }else {
            vetor[numElem++] = valor;
            return true;
        }
    }

    public void exibe() {
        System.out.println("\nExibindo elemntos da lista:");
        for (int i=0; i< numElem; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println();
    }

    public int busca(T valor) {
        for (int i=0; i < numElem; i++){
            if (vetor[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice){
        if (indice < 0 || indice >= numElem) {
            return false;
        } else {
            for (int i=indice; i < numElem - 1; i++) {
                vetor [i] = vetor[i+1];
            }
            numElem --;
            return true;
        }
    }

    public boolean removeElemento(T valor) {
        return removePeloIndice(busca(valor));
    }

    public int getTamanho() {
        return numElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= numElem) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa(){
        numElem = 0;
    }
}
