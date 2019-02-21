package models;


public class No{
    No[] ponteiros;
    Conteudo[] conteudos;

    private static Integer size;

    public No(){
        this.ponteiros = new No[this.size];
        this.conteudos = new Conteudo[this.size - 1];
    }

    public No(int size){
        this.size = size;
        this.ponteiros = new No[this.size];
        this.conteudos = new Conteudo[this.size - 1];
    }

    public boolean isFull(){
        for(int i = 0; i < conteudos.length; i++){
            if(this.conteudos[i] == null){
                return false;
            }
        }
        return true;
    }

    // Método responsável por inserir os elemento no vetor de conteudos;
    // OBS: a proteção a este método (isFull) será feita na classe Arvore.
    public void setarConteudo(int elemento){
        for(int i = 0; i < conteudos.length; i++){
            if(this.conteudos[i] == null){
                Conteudo novo = new Conteudo(elemento);
                this.conteudos[i] = novo;
                if(i == conteudos.length - 1){
                    // se o indice chegar ao final do vetor, significa que está cheio e deverá fazer uma ordenação
                    ordenar();

                }
                return;
            }
        }
    }

    // método pela ordenação do vetor de conteudo
    private void ordenar(){
		for(int i = 0; i < conteudos.length; i++){
			for(int j = (conteudos.length - 1); j > i; j--){
				if(this.conteudos[j].getConteudo() < this.conteudos[j - 1].getConteudo()){
					troca(j, (j -1));
				}
			}//fim loop interno
		}//fim do loop externo
	}

    // método auxiliar ao ordenar para fazer as trocas de posições necessárias
	private void troca(int j, int j2){
		int aux;
		aux = this.conteudos[j].getConteudo();
		this.conteudos[j].setConteudo(this.conteudos[j2].getConteudo());
		this.conteudos[j2].setConteudo(aux);
	}

    // encapsulamento do Vetor conteudo
    public Integer getConteudo(int indice){
        if(this.conteudos[indice] == null){
            return null;
        }
        return (Integer) this.conteudos[indice].getConteudo();
    }

    public No getNo(int indice){
        return this.ponteiros[indice];
    }

    public void setNo(int i, No novo){
        this.ponteiros[i] = novo;
    }



}// fim classe
