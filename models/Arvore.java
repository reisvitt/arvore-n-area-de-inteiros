package models;

public class Arvore{
    private No raiz;
    private static Integer size;

    public Arvore(int size){
        this.size = size;
    }


    // método auxilar para inserção de forma recursiva
    public void inserir(int elemento){
        if(this.raiz == null){
            No novo = new No(this.size);
            novo.setarConteudo(elemento);
            this.raiz = novo;
        }else{
            inserir(this.raiz, elemento);
        }

    }// fim método adicionar


    private void inserir(No noAtual, int elemento){
        //verificar se no esta cheio
            //se sim, comparar os ponteiros para saber a localização do ponteiro onde deve ser inserido o elemento
                // se o ponteiro existir, passar o ponteiro e o elemento como parametro para esta mesma função
                // se não, criar o ponteiro e adicionar o elemento;
            //se não, inserir no No atual;


        // inserir no No atual se houver posições vazias
        if(!noAtual.isFull()){
            noAtual.setarConteudo(elemento);

        } // isFull -> deverá verificar os ponteiros
        else{
            // for para percorrer o conteudo
            for(int i = 0; i < this.size - 1; i++){

                if(elemento < noAtual.getConteudo(i)){
                    // verificar se existe o ponteiro nessa posição
                    if(noAtual.getNo(i) != null){
                        inserir(noAtual.getNo(i), elemento);

                        return; // para sair do FOR
                    }
                    else{
                        // cria um novo Ponteiro, seta o conteudo, e mandar o indice do ponteiro junto com o novo ponteiro que irá substituir o null
                        No novo = new No();
                        novo.setarConteudo(elemento);
                        noAtual.setNo(i, novo);

                        return; // para sair do FOR
                    }

                }else if((i+1) == this.size - 1){ // ultimo caso do FOR
                    if(elemento >= noAtual.getConteudo(i)){ // se o elemento for maior que o ultimo elemento do vetor de conteudos
                        if(noAtual.getNo(i+1) != null){
                            inserir(noAtual.getNo(i+1), elemento);

                            return; // para sair do FOR
                        }
                        else{
                            // cria um novo Ponteiro, seta o conteudo, e mandar o indice do ponteiro junto com o novo ponteiro que irá substituir o null
                            No novo = new No();
                            novo.setarConteudo(elemento);
                            noAtual.setNo(i+1, novo);

                            return; // para sair do FOR
                        }
                    }

                }
            }// fim FOR

        }// fim if externo

    }// fim método inserir



    public void exibir(){
        if(this.raiz != null){
            exibir(this.raiz);
            System.out.println();

        }else{
            System.out.println("Árvore vazia!");
        }
    }

    private void exibir(No noAtual){
        if(noAtual != null){
            System.out.print(" {");

            // percore os ponteiros
            for(int i = 0; i < this.size; i++){

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

                exibir(noAtual.getNo(i));
            }
            System.out.print("}");
        }
    }


}// fim classe
