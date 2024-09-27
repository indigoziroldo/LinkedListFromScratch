package com.indigo.lista;

public class No <T> { //utiliza-se da classe generics do Java
    // para permitir a criacao de um No de qualquer tipo

    private T elemento; // valor armazenado no nosso nó
    private No<T> proximo; // no sucessor, armazena o no inteiro


    public No(T elemento) { // gerado um construtor que possui como parametro apenas o elemento
        this.elemento = elemento;
        this.proximo = null; // toda vez que for criado um elemento,
        // o proximo elemento é setado como null explicitamente aqui
    }

    // esse construtor é usado quqando já se sabe qual é o proximo elemento a ser usado
    public No(T elemento, No<T> proximo) { // gerado um construtor que possui todos os argumentos
        this.elemento = elemento;
        this.proximo = proximo;
    }

    // gerado o setter e getter built-in do intellij pro elemento e pro proximo
    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }

    // gerado o toString pra possubilitar a leitura facilitada dos nós

    @Override
    public String toString() {
        return "Nó{ " +
                "elemento= " + elemento +
                ", proximo= " + proximo +
                '}';
    }
}
