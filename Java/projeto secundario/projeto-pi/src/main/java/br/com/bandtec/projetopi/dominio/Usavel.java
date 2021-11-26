package br.com.bandtec.projetopi.dominio;

public interface Usavel {


    //Metodos
    public abstract String getComentar();

    public abstract String getVerMapa();

    public abstract String getReagir();



    //Explicar a relação do padrão de projeto Strategy e a sua implementação:

    /*o padrão Strategy pode ser utilizado quando se tem as seguintes a situação em que

Quando muitas classes relacionadas se diferem apenas no seu comportamento, ou seja em seus metodos

dessa forma para as duas classes herdeiras  (Obras e Relato) estamos tratando o metodo abstrato de maneira diferente

onde em publicações de obras apenas comentamos, já em relato além de comentarmos enviamos o comentario a um orgao

que capta opiniões publicas;*/

}
