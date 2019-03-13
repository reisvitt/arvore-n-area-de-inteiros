package models;

public class Arvore{
    private No raiz;
    private static Integer size;

    /*  Se não for passado nenhum parâmetro identificando a quantidade de
        ponteiros da árvore, será criada uma com tamanho padrão igual a 4. */
    public Arvore(){
        this.size = 4;
    }

    // construtor que recebe a quantiade de Ponteiros da árvore
    public Arvore(int size){
        this.size = size;
    }

    // método para inserção de forma recursiva
    public void inserir(int elemento){
        if(this.raiz == null){
            No novo = new No(this.size);
            novo.setarConteudo(elemento);
            this.raiz = novo;
        }else{
            No noAtual = busca(this.raiz, elemento);
            if(!noAtual.isFull()){
                // verificar se possui algum elemento igual, se tiver não será adicionado o novo elemento, se não tiver, será adicionado
                for(int i = 0; i < this.size - 1; i++){ // percorre o vetor de conteudo
                    if(noAtual.getConteudo(i) == null){
                        noAtual.setarConteudo(elemento);
                        return;
                    }
                }
            }else{
                for(int i = 0; i < this.size - 1; i++){
                    if(noAtual.getConteudo(i).equals(elemento)){
                        return;

                    }else if(elemento < noAtual.getConteudo(i)){

                        // cria um novo Ponteiro, seta o conteudo, e mandar o indice do ponteiro junto com o novo ponteiro que irá substituir o null
                        No novo = new No();
                        novo.setarConteudo(elemento);
                        noAtual.setNo(i, novo);

                        return; // para sair do FOR


                    }else if((i+1) == this.size - 1){ // ultimo caso do FOR
                        if(elemento >= noAtual.getConteudo(i)){ // se o elemento for maior que o ultimo elemento do vetor de conteudos

                            // cria um novo Ponteiro, seta o conteudo, e mandar o indice do ponteiro junto com o novo ponteiro que irá substituir o null
                            No novo = new No();
                            novo.setarConteudo(elemento);
                            noAtual.setNo(i+1, novo);

                            return; // para sair do FOR
                        }// fim IF interno
                    }// fim IF externo
                }// fim FOR
            }// fim IF interno
        }// fim IF externo

    }// fim método inserir

    private No busca(No noAtual, int elemento){
        //Percorre a arvore e ao achar aonde pode ser adicionado ou o elemento procurando, retorna o No referente;

        // Retornar No atual caso não estiver cheio
        if(!noAtual.isFull()){
            return noAtual;

        } // isFull -> deverá verificar o conteudo e os ponteiros
        else{
            // for para percorrer o conteudo
            for(int i = 0; i < this.size - 1; i++){
                if(noAtual.getConteudo(i).equals(elemento)){ // se o elemento for igual ao conteudo, retornar o NoAtual
                    return noAtual;

                }else if(elemento < noAtual.getConteudo(i)){
                    // verificar se existe o ponteiro nessa posição
                    if(noAtual.getNo(i) != null){ // se existir ponteiro, ele chama este método passando este ponteiro
                        return busca(noAtual.getNo(i), elemento);
                    }else{ // se não exister ponteiro, ele retorna o NoAtual
                        return noAtual;
                    }

                }else if((i+1) == this.size - 1){ // ultimo caso do FOR
                    if(elemento >= noAtual.getConteudo(i)){ // se o elemento for maior que o ultimo elemento do vetor de conteudos
                        if(noAtual.getNo(i+1) != null){
                            return busca(noAtual.getNo(i+1), elemento); // para sair do FOR
                        }
                        else{
                            return noAtual;
                        }
                    }// fim IF intermediário
                }// fim IF externo
            }// fim FOR

        }// fim if externo
        return noAtual;
    }// fim método busca

    //exibir em pre_ordem
    public void pre_ordem(){
        if(this.raiz != null){
            pre(this.raiz);
            System.out.println();

        }else{
            System.out.println("Árvore vazia!");
        }
    }

    // método auxiliar para exibição em pre_ordem
    private void pre(No noAtual){
        if(noAtual != null){
            System.out.print(" {");

            // percore os ponteiros
            for(int i = 0; i < this.size; i++){
                pre(noAtual.getNo(i));
                // percore o conteudo
                if(i < this.size - 1){
                    if(noAtual.getConteudo(i) != null){

                        // condicional apenas para melhorar a vizualização da saída
                        if(i == 0){
                            System.out.print(noAtual.getConteudo(i));
                        }else{
                            System.out.print(", " + noAtual.getConteudo(i));
                        }
                    }
                }
            }
            System.out.print("}");
        }
    }

    // remoção de elementos na árvore
    public void remover(int elemento){
        if(this.raiz != null){
            No noAtual = busca(this.raiz, elemento);
            // percorer o vetor de conteudo para procurar o elemento
            for(int i = 0; i < this.size - 1; i++){
                if(noAtual.getConteudo(i).equals(elemento)){
                    noAtual.setConteudo(i);
                    reogarnizar(noAtual);
                    return;
                }else if(i == this.size - 2){
                    System.out.println("Elemento não existe");
                }
            }
        }else{
            System.out.println("Árvore Vazia");
        }
    }

    // método responspavel para reogarnização das sub-árvores do No que estava o elemento removido
    public void reogarnizar(No noAtual){
        No[] ponteiros_auxiliares = new No[this.size];

        // fazendo cópias dos ponteiros do NoAtual;
        for(int i = 0; i < this.size; i++){
            ponteiros_auxiliares[i] = noAtual.getNo(i);
        }

        // limpar as refências dos ponteiros do NoAtual;
        for(int i = 0; i < this.size; i++){
            noAtual.setNo(i, null);
        }

        for(int i = 0; i < this.size; i++){
            re_inserir(ponteiros_auxiliares[i]);
        }
    }

    //re-inserir todos os elementos dos ponteiros_auxiliares na Árvore Principal
    private void re_inserir(No noAtual){
        // percorre todos os elementos do NoAtual, re-inserindo na Árvore Principal
        if(noAtual != null){
            // percore os ponteiros
            for(int i = 0; i < this.size; i++){
                re_inserir(noAtual.getNo(i));
                // percore o conteudo
                if(i < this.size - 1){
                    if(noAtual.getConteudo(i) != null){
                        inserir(noAtual.getConteudo(i));
                    }
                }
            }
        }

    }

}// fim classe
