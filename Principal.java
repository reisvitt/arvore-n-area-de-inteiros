import models.Arvore;
import models.Conteudo;
import models.No;

public class Principal{
    public static void main(String args[]){
        Arvore ar = new Arvore(3);

        ar.inserir(10);
        ar.inserir(20);
        ar.inserir(30);
        ar.inserir(40);
        ar.exibir();

        System.out.println();
        ar.inserir(21);
        ar.inserir(22);
        ar.inserir(23);
        ar.inserir(27);
        ar.inserir(24);
        ar.inserir(25);
        ar.inserir(60);
        ar.exibir();


    }
}
