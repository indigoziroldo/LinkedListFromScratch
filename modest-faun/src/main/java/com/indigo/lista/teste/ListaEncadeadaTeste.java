package com.indigo.lista.teste;

import com.indigo.lista.ListaEncadeada;

public class ListaEncadeadaTeste {

    public static void main(String[] args) { // criado pra
//        testesIniciais();
//        adicionaNoInicio();
//        adicionaNaPosicao();
//        removeNoInicio();
//        removeNoFinal();
        removeNaPosicao();
    }

    private static void removeNaPosicao() {
        ListaEncadeada listaEncadeada = new ListaEncadeada();
        listaEncadeada.adiciona(1);
        listaEncadeada.adiciona(2);
        listaEncadeada.adiciona(3);
        listaEncadeada.adiciona(4);
        listaEncadeada.adiciona(5);
        System.out.println("elemento removido: " + listaEncadeada.removeNaPosicao(3));
        System.out.println("Lista: " + listaEncadeada);
    }

    private static void removeNoFinal() {
        ListaEncadeada<Integer> listaEncadeada = new ListaEncadeada();
        listaEncadeada.adiciona(1);
        listaEncadeada.adiciona(2);
        listaEncadeada.adiciona(3);
        System.out.println(listaEncadeada.removeNoFinal());
        System.out.println(listaEncadeada);


    }

    public static void removeNoInicio() {
        ListaEncadeada<Integer> listaEncadeada = new ListaEncadeada();
        listaEncadeada.adiciona(1);
        System.out.println(listaEncadeada.removeNoInicio());
        System.out.println(listaEncadeada);
    }

    public static void adicionaNoInicio() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.adicionaInicio(3);
        lista.adicionaInicio(5);
        lista.adicionaInicio(7);
        System.out.println(lista);

    }

    public static void adicionaNaPosicao(){
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
//        lista.adicionaNaPosicao(-1, 1); Testada a exceção
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(4);
        lista.adicionaNaPosicao(0,0);
        lista.adicionaNaPosicao(4,5);
        lista.adicionaNaPosicao(3,3);
        System.out.println(lista);
    }

    public static void testesIniciais(){
        ListaEncadeada<Integer> lista = new ListaEncadeada<>(); // está sendo definido que essa lista é do tipo Integer
        lista.adiciona(1); // puxa o metodo adicionar para a lista e bota o valor inserido
        lista.adiciona(2);
        lista.adiciona(3);
        lista.adiciona(4);
        System.out.println(lista); // imprime oq estiver dentro da lista

        // vai usar o método getTamanho na lista pra retornar quantos elementos possui
        System.out.println("O tamanho é: " + lista.getTamanho());

        //lista.limpa();
//        System.out.println(lista);

        // busca por elemento
        System.out.println("Busca por elemento\n");
        System.out.println(lista.buscaPorValor(0));
        System.out.println(lista.buscaPorValor(1));
        System.out.println(lista.buscaPorValor(2));
        System.out.println(lista.buscaPorValor(3));
        System.out.println("\n");

        // busca por posicao
        System.out.println("Busca por posicao\n");
        System.out.println(lista.buscaPorPosicao(0));
        System.out.println(lista.buscaPorPosicao(1));
        System.out.println(lista.buscaPorPosicao(2));
//        System.out.println(lista.buscaPorPosicao(-1)); todo EXCEPTIONS CHECADOS
//        System.out.println(lista.buscaPorPosicao(4));
    }
}
