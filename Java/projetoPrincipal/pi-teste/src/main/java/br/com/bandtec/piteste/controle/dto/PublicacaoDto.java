package br.com.bandtec.piteste.controle.dto;

import br.com.bandtec.piteste.dominio.Publicacao;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacaoDto {
    private Integer id_publicacao;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String nome;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String categoria;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String descricao;
    private LocalDate inicio_obra;
    private LocalDate final_obra;
    private LocalDateTime data_criacao;
    private Double orcamento;
    private String imagem_obra;
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String cep;
    private String rua;
    @NotNull
    @NotEmpty
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    @Column(length = 5_000_000, name = "conteudo_arquivo")
    private byte[] conteudoArquivo;

    @Column(name = "tipo_arquivo")
    private String tipoArquivo;


    public PublicacaoDto(Publicacao p) {
        this.id_publicacao = p.getId_publicacao();
        this.nome = p.getObra().getNome();
        this.categoria = p.getObra().getCategoria();
        this.descricao = p.getDescricao();
        this.inicio_obra = p.getInicio_obra();
        this.final_obra = p.getFinal_obra();
        this.data_criacao = p.getData_criacao();
        this.orcamento = p.getOrcamento();
        this.imagem_obra = p.getImagem_obra();
        this.cep = p.getObra().getCep();
        this.rua = p.getObra().getRua();
        this.numero = p.getObra().getNumero();
        this.bairro = p.getObra().getBairro();
        this.cidade = p.getObra().getCidade();
        this.estado = p.getObra().getEstado();
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public PublicacaoDto() {
    }

    public static List<PublicacaoDto> converter(List<Publicacao> topicos) {
     //   return topicos.map(PublicacaoDto::new);
        return topicos.stream().map(PublicacaoDto::new).collect(Collectors.toList());

    }
    public static Page<PublicacaoDto> converterPage(Page<Publicacao> topicos) {

        return topicos.map(PublicacaoDto::new);
    }


    public Integer getId_publicacao() {
        return id_publicacao;
    }

    public void setId_publicacao(Integer id_publicacao) {
        this.id_publicacao = id_publicacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getInicio_obra() {
        return inicio_obra;
    }

    public void setInicio_obra(LocalDate inicio_obra) {
        this.inicio_obra = inicio_obra;
    }

    public LocalDate getFinal_obra() {
        return final_obra;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setFinal_obra(LocalDate final_obra) {
        this.final_obra = final_obra;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public String getImagem_obra() {
        return imagem_obra;
    }

    public void setImagem_obra(String imagem_obra) {
        this.imagem_obra = imagem_obra;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
