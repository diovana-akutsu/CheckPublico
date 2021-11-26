import React, { useRef } from "react";

import styles from "./Compartilhar.module.css";
import add from "../../assets/images/add.png";

import voltarBranco from "../../assets/images/SetaSair.png"
import voltarPreto from "../../assets/images/SetaVoltar.png"
import { Input } from "../../components/Input/Input";
import { Button } from "../../components/Button/Button";
import { Div } from "../../components/Div/Div";
import { Dropzone } from "../../components/Dropzone/Dropzone";
import { Link } from "react-router-dom";

import imagemFundoImportar from "../../assets/images/imagemFundoImportar.png";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import { useCookies } from 'react-cookie';
import api from "../../services/api";
import consultaCep from "../../services/consultaCep";
//import formData from "form-data";
import fs from "fs";

// import Dropzone from 'react-dropzone'

export function Compartilhar() {

    const [cookies, setCookie] = useCookies(['token']);
    let token = cookies.token;

    const [publicacao, setPublicacao] = useState({
        "nome": "",
        "categoria": "",
        "descricao": "",
        "inicio_obra": "",
        "final_obra": "",
        "orcamento": "",
        "imagem_obra": "",
        "cep": "",
        "rua": "",
        "numero": "",
        "bairro": "",
        "cidade": "",
        "estado": ""
    });

    const [arquivoUpload, setArquivoUpload] = useState("");
    let history = useHistory();

    function handleInput(eventoDoOnChange) {

        const { name, value } = eventoDoOnChange.target;
        console.log('cep', [name], value)

        setPublicacao({
            ...publicacao,
            [name]: value,
        });

        console.log(publicacao);
    }

    function handleInputArquivo(eventoDoOnChange) {

        const { name, value } = eventoDoOnChange.target;
        console.log('arquivo', [name], value)
        let files = eventoDoOnChange.target.files[0];
        setArquivoUpload(
            files
        );

        console.log(arquivoUpload);
    }

    async function consultaCEP(cep) {

        await consultaCep.get(`/${cep}/json/`)
            .then((resposta) => {
                if (resposta.status === 200) {
                    console.log("EEE", resposta.data);
                    let local = resposta.data;
                    setPublicacao({
                        ...publicacao,
                        bairro: local.bairro,
                        cep: local.cep,
                        complemento: local.complemento,
                        cidade: local.localidade,
                        rua: local.logradouro,
                        estado: local.uf
                    });

                    console.log('Hora da verdade', publicacao);
                    //alert("Ui papai!");
                    //history.push("/");
                }
                console.log(resposta);
                if (resposta.status === 500) {
                    alert("Nome e email devem ter mais que 10 caracteres. Insira novamente");
                }
                if (resposta.status === 400) {
                    alert("E-mail já cadastrado. Insira novamente");
                }
            }
            )
    }

    async function cadastrar(event) {

        event.preventDefault();

        if (arquivoUpload == "") {
            alert("Insira uma imagem para prosseguir")
        }
        else {
            await api.post("/publicacoes", {
                ...publicacao,
            },
                {
                    headers: { 'Authorization': token }
                }
            )
                .then((resposta) => {
                    if (resposta.status === 201) {

                        async function cadastrarImagem() {

                            let form = new FormData();
                            form.append('arquivo', arquivoUpload);
                            form.append('texto', "sonim")
                            await api.post("/arquivos/imagem",
                                form
                                ,
                                {
                                    headers: {
                                        'Authorization': token
                                    }
                                })
                                .then((resposta) => {
                                    alert("Publicação cadastrado com sucesso!");
                                    history.push("/feed");
                                })
                                .catch((erro) => {
                                    alert('Ops, algo deu errado')
                                })
                        }
                        cadastrarImagem();
                    }
                })
                .catch((erro) => {
                    console.log('status', erro.response.status)
                    if (erro.response.status === 403) {
                        alert("Você precisa estar logado!");
                        history.push("/login");

                    }
                    if (erro.response.status === 400) {
                        alert("Dados inválidos");
                    }
                    if (erro.response.status === 500) {
                        alert("Não sei o que pode ser");
                    }
                })

        }





    }


    return (
        <container className={styles.pageCompartilhar}>
            <Link to="/Feed">
                <img className={styles.voltar} src={voltarPreto} alt="VOLTAR" />
            </Link>
            <section className={styles.sectionConteudo}>

                <Div variant="PRIMARY" className={styles.imgConteudo}>
                    <img className={styles.perfil} alt="" id="perfil" />
                    <label className={styles.customFileFpload}>
                        <input
                            onChange={handleInputArquivo}
                            name="arquivo"
                            className={styles.fileChooser} type="file" id="inputFileToLoad" accept=".png, .jpg, .jpeg, .pdf" />
                        <img className={styles.add} src={add} alt="Foto Perfil" id="trocfoto" />
                    </label>
                </Div>

                <div className={styles.descricacaoConteudo}>
                    <label>Descrição:</label>
                    <Input
                        onChange={handleInput}
                        name="descricao" className={styles.inputConteudo} variant="AZULTRANSPARENTE" />
                </div>
            </section>
            <div className={styles.vertical}></div>
            <section className={styles.sectionFiltro}>
                <div className={styles.nomeFiltro}>
                    <label>Nome da obra:</label>
                    <Input onChange={handleInput}
                        name="nome" className={styles.inputNome} variant="AZULTRANSPARENTE" />
                </div>
                <div>
                    <label>Categoria:</label>
                    <select
                        onChange={handleInput}
                        name="categoria"
                        className={styles.combobox}>
                        <option value="">Selecione uma categoria</option>
                        <option value="Educação">Educação</option>
                        <option value="Saúde">Saúde</option>
                        <option value="Transporte">Transporte</option>
                        <option value="Patrimônio cultural">Patrimônio cultural</option>
                        <option value="Lazer">Lazer</option>
                        <option value="Segurança">Segurança</option>
                        <option value="Urbanização">Urbanização</option>
                        <option value="Serviços básicos">Serviços básicos</option>
                        <option value="Outros">Outros</option>
                    </select>
                </div>
                <div className={styles.doisValores}>
                    <div>
                        <label>CEP:</label>
                        <Input
                            // onChange={handleInput}
                            onBlur={event => {
                                consultaCEP(event.target.value)
                            }}
                            className={styles.inputNome} name="cep" variant="AZULTRANSPARENTE" />
                    </div>
                    <div>
                        <label>Número:</label>
                        <Input
                            onChange={handleInput}
                            name="numero" className={styles.inputNome} variant="AZULTRANSPARENTE" />
                    </div>
                </div>
                <div className={styles.localFiltro}>
                    <label>Endereço:</label>
                    <Input value={publicacao.rua} className={styles.inputLocalFiltro} variant="AZULTRANSPARENTE" />
                </div>
                <div className={styles.valorFiltro}>
                    <label>Valor da obra:</label>
                    <Input type="number" className={styles.InputvalorFiltro}
                        onChange={handleInput}
                        name="orcamento" variant="AZULTRANSPARENTE" />
                </div>
                <div className={styles.dataFiltro}>
                    <div className={styles.dataIncFiltro}>
                        <label>Data de inicio:</label>
                        <Input type="date"
                            onChange={handleInput}
                            name="inicio_obra" className={styles.inputDataIncFiltro} variant="AZULTRANSPARENTE" />
                    </div>
                    <div className={styles.dataFimFiltro}>
                        <label>Data prevista término:</label>


                        <Input type="date"
                            onChange={handleInput}
                            name="final_obra" className={styles.inputDataFimFiltro} variant="AZULTRANSPARENTE" />
                    </div>
                </div>
                <div className={styles.buttonPublicar}>
                    <Button variant="PRIMARY"
                        onClick={cadastrar}>
                        Publicar</Button>
                </div>
            </section>
        </container>
    );
}