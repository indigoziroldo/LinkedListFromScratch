package com.indigo.lista;

public class ListaEncadeada<T>{

    private No<T> inicio; //é criado um apontador para o inicio da lista

    private No<T> ultimo; // referencia para o ultimo elemento

    private int tamanho; // para contar quantos elementos a lista possui
    // por padrao ele começa com 0 no Java

    private final int NAO_ENCONTRADO = -1;
    private final String NAO_EXISTE = "Posição Não Existe.";
    private final String LISTA_VAZIA = "Lista Vazia!";


    //todo INSERE NO FINAL DA LISTA
    public void adiciona(T elemento){
        // criado metodo para adicionar um elemento na lista
        // este sendo passado dentro do argumento,
        No<T> celula = new No<T>(elemento); // cria um elemento com o valor sugerido

        if(this.tamanho == 0){ // se a lista estiver vazia
            this.inicio = celula; // vai adicionar o primeiro elemento
        } else this.ultimo.setProximo(celula); // e a pártir disso vai adicionar os sucessores
        this.ultimo = celula; // aponta pro último
        this.tamanho++; // vai adicionar +1 toda vez ao tamanho

    }

    // todo INSERE NO INICIO DA LISTA
    public void adicionaInicio(T elemento){
        if(this.tamanho == 0){
            No<T> novoNo = new No<>(elemento);
            this.inicio = novoNo;
            this.ultimo = novoNo;

        } else {
            No <T> novoNo = new No<>(elemento, this.inicio);
            this.inicio = novoNo;
        }
        this.tamanho++;
    }

    // todo INSERE EM QUALQUER POSIÇÃO DA LISTA
    public void adicionaNaPosicao(int posicao, T elemento){
        if(posicao < 0 || posicao > this.tamanho){ // verifica se quer botar em lugares impossíveis na lista
            throw new IndexOutOfBoundsException(NAO_EXISTE);
        }
        if(posicao == 0){ // pede pra adicionar no primeiro elemento
            this.adicionaInicio(elemento);
        } else if (posicao == this.tamanho){ // verifica se a posicao desejada é o ultimo lugar da lista pra adicionar no final da lista
            this.adiciona(elemento);
        } else { // aqui finalmente adiciona no meio da lista
            No<T> noAnterior = this.buscaPorPosicaoRetornaNo(posicao);
            No<T> proximoNo = noAnterior.getProximo();
            No<T> novoNo = new No<>(elemento, proximoNo);
            noAnterior.setProximo(novoNo);
            this.tamanho++;
        }
    }

    // metodo pra pegar o tamanho
    public int getTamanho(){ // fazemos um get manual pra retornar o tamanho da lista/ quantos elementos possui
        return this.tamanho;
    }

    // todo REMOVE EM QUALQUER POSICAO DA LISTA, vai pegar a posicao inserida e remover o nó
    public T removeNaPosicao(int posicao){
        if(this.posicaoNaoExiste(posicao)){
            throw new IllegalArgumentException(NAO_EXISTE);
        }
        if(posicao == 0){
            return this.removeNoInicio();
        } else if(posicao == this.tamanho -1) {
            return this.removeNoFinal();
        }
        No<T> noAnterior = this.buscaPorPosicaoRetornaNo(posicao - 1);
        No<T> atual = noAnterior.getProximo();
        No<T> proximo = atual.getProximo();
        noAnterior.setProximo(proximo);
        atual.setProximo(null);// só pra limpar o cache
        this.tamanho--;

        return atual.getElemento();
    }

    //todo REMOVE DO FINAL, vai pegar o ultimo elemento
    public T removeNoFinal(){
        if(this.tamanho == 0){
            throw new RuntimeException(LISTA_VAZIA);
        } else if (this.tamanho == 1){
            return this.removeNoInicio();
        }
        No<T> penultimoNo = this.buscaPorPosicaoRetornaNo(this.tamanho - 2); // aqui ele remove 2 pra chegar no penultimo elemento
        T removido = penultimoNo.getProximo().getElemento();
        penultimoNo.setProximo(null);
        this.ultimo = penultimoNo;
        this.tamanho--;
        return removido;
    }

    //todo REMOVE DO INICIO, vai pegar o primeiro elemento
    public T removeNoInicio(){ // nesse caso ele retorna o elemento que foi removido quando tem sucesso
        if(this.tamanho == 0){
            throw new RuntimeException(LISTA_VAZIA);
        }
        T removido = this.inicio.getElemento();
        this.inicio = this.inicio.getProximo();
        this.tamanho--;

        if(this.tamanho == 0){ // se removermos o 1º e ele for o único na lista, logo precisamos também retirar o último
            this.ultimo = null; // dessa forma
        }
        return removido;
    }

    //FIXME METODO PRA VERIFICAR SE A POSIÇÃO EXISTE
    private boolean posicaoNaoExiste(int posicao){
        return !(posicao >= 0 && posicao <= this.tamanho);
    }

    // TODO metodo que limpa a lista
    public void limpa(){
        // esse for vai atual da seguinte maneira
        // o i será o atual, que vai pegar o inicio da lista
        // enquanto o atual não for nulo ele vai ficar percorrendo esse loop
        for(No<T> atual = this.inicio; atual != null;){
            No<T> proximo = atual.getProximo(); // vai ser nosso aux
            // ele pega o prox do atual
            // quando esse prox for null ele zera tbm essa variavel
            atual.setProximo(null); // seta como nulo o valor do prox para o elemento atual
            atual.setElemento(null); // seta como nulo o valor do elemento no elemento atual
            atual = proximo; // aqui ele vai setar o atual como o prox
            // já que ele já tinha pegado esse valor anteriormente pra atuar como aux
            // aí ele fica fazendo esse looping zerando todos os valores até chegar no último
        }
        // saindo do looping, apagamos os indicadores de inicio, fim e tamanho da lista
        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;

    }

    //todo essa é a forma mais simples de se "apagar uma lista"
    // em contrapartida, os valores do meio ficam perdidos e isso acumula valor lixo na memória
//    public void limpa(){
//        this.inicio = null;
//        this.ultimo = null;
//        this.tamanho = 0;
//    }

    //todo busca por posicao e retorna o no completo
    // esse metodo é privado pra nao expor esse no pra fora da classe
    private No<T> buscaPorPosicaoRetornaNo(int posicao){
        //valida primeiro se a posicao existe
        // caso retornasse null ia dar um exception, PORTANTO:
        if(this.posicaoNaoExiste(posicao)){ // verifica se a posicao existe e nega essa preposicao
            throw new IllegalArgumentException(NAO_EXISTE); // joga uma exceção
        }
        No<T> noAtual = this.inicio;// cria um no que recebe o primeiro da lista
        for(int i = 0; i < posicao; i++){
            noAtual = noAtual.getProximo(); // até chegar na posicao desejada ele vai iterar
        }
        return noAtual; // retorna o no que está na posicao desejada
    }

    // todo buscar valor do elemento dando a posicao
    // retorna o tipo do elemento e não o nó inteiro
    public T buscaPorPosicao(int posicao){
        // ele busca no metodo privado acima e aí ele vai retornar apenas o componente requerido
        return this.buscaPorPosicaoRetornaNo(posicao).getElemento();
    }

    // todo busca posicao do elemento dando o valor
    // retorna a posicao correspondente e não o nó inteiro
    public int buscaPorValor(T elemento){
        // precisamos fazer um looping pra procurar um elemento que contenha esse valor
        No<T> noAtual = this.inicio; // vamos fazer um no pra representar o elemento atual selecionado, no caso aqui é o inicio
        int pos = 0; // variavel auxiliar que vai mostrar a posicao, por padrao qualquer lista no java começa no 0

        while(noAtual != null){
            if(noAtual.getElemento().equals(elemento)){
                return pos;
            }
            pos++; // aqui a gente vai incrementar no aux pos
            noAtual = noAtual.getProximo(); // pra fzr a iteração e ir percorrendo a lista

        }

        // caso não encontremos esse valor na lista
        // o padrao é retornar "não encontrado na lista
        // ou o padrao pro java que é -1, porém
        // como algumas pessoas podem não compreender, vamos usar essa variavel a seguir que foi definida como -1
        return NAO_ENCONTRADO;
    }

    // cria o ToString pra fazer a demonstração visual
    // vamos modificar ele pra ter um resultado mais legível e semelhante a como um array fica
    @Override
    public String toString() {

        if(this.tamanho == 0){
            return "[]"; // se a lista tiver vazia vai retornar esse []
        }

        // vamo usar o StringBuilder que serve pra concatenar várias coisas em uma string
        StringBuilder builder = new StringBuilder("["); // iniciamos a string com um [
        // vamos agora percorrer a lista
        No<T> atual = this.inicio;

        // TODO FORMA MODERNA PRO JAVA PARA ITERAR E PERCORRER A LISTA
        for(int i = 0; i < this.tamanho - 1; i++){ // ele faz exatamente o mesmo procedimento do clássico mas usando um for
            // o tamanho -1 é pra chegar apenas até o penúltimo elemento, pra evitar aquela virgula lá no final
            builder.append(atual.getElemento()).append(",");
            // primeiro ele concatena e DEPOIS ele atualiza o atual pra pegar o proximo elemento
            atual = atual.getProximo();
        }
        builder.append(atual.getElemento()).append("]"); // dessa forma a gente ainda pega o ultimo elemento
        // mas usando dessa forma a gente não faz append com uma ,
        // POREM eu uso o append pra botar um ] no final

        /*
        // TODO FORMA CLÁSSICA DE ITERAR E PERCORRER A LISTA //
        builder.append(atual.getElemento()).append(","); // pega o primeiro elemento, enfia na string e bota uma virgula
        // aqui vamos percorrer pela lista
        // ele pega o alemento atual e verifica se o próximo dele é nulo, se não for ele entra nesse loop
        while(atual.getProximo() != null){
            atual = atual.getProximo(); // a partir disso ele torna esse proximo elemento o atual
            builder.append(atual.getElemento()).append(","); // aqui ele vai ir concatenando na string o elemento atual
        }
         */
        return builder.toString();

    }
}
