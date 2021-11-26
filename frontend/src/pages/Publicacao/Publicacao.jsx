import React from "react";

import styles from "./Publicacao.module.css";
import Like from "../../assets/images/Like.png";
import ComentarioImg from "../../assets/images/Comentario.png";
import Compartilhar from "../../assets/images/Compartilhar.png";
// import Perfil from "../../assets/images/Perfil.png";
import Obra1 from "../../assets/images/Obra1.png";
import Obra2 from "../../assets/images/Obra2.png";
import Coment1P from "../../assets/images/profile-user.png";
import Coment2P from "../../assets/images/Coment2P.png";
import Coment3P from "../../assets/images/Coment3P.png";
import Clip from "../../assets/images/Clip.png";
import Emoticon from "../../assets/images/Emoticon.png";
import Enviar from "../../assets/images/Enviar.png";
import voltarBranco from "../../assets/images/SetaSair.png"
import voltarPreto from "../../assets/images/SetaVoltar.png"
import AddComment from "../../assets/images/addcomment.png"
import LikeBranco from "../../assets/images/LikeBranco.png"
import Perfil from "../../assets/images/user-profile-white.png";

import { Button } from "../../components/Button/Button";
import { Div } from "../../components/Div/Div";
import { Comentario } from "../../components/Comentario/Comentario";
import { useEffect, useState } from "react";
import api from "../../services/api";
import { useCookies } from 'react-cookie';
import { Link } from "react-router-dom";


export function Publicacao() {

    const [cookies, setCookie] = useCookies(['public', 'token']);
    const [comentario, setComentario] = useState("");
    const [statusLike, setStatusLiked] = useState(Like);
    const [dataHoraFormatada, setDataHoraFormatada] = useState('');

    const [verLike, setVerLike] = useState(false);

    let token = cookies.token;

    let id = cookies.public;
    console.log("AAAAAAAA" + id);
    const [listaPublicacoes, getPublicacoes] = useState(
        {

            "descricao": "",
            "orcamento": "",
            "imagem_obra": "arquivofoto.png",
            "usuario": {
                "nome": "Rafael dos Santos",
                "email": "rafael.santos@bandtec.com.br",
                "id_usuario": ""
            },
            "obra": {
                "nome": "",
                "categoria": "",
                "cep": "",
                "rua": "",
                "numero": "",
                "bairro": "",
                "cidade": "",
                "estado": "",
                "id_obra": ""
            },
            "data_criacao": [
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            ],
            "id_publicacao": ""
        }


    );

    const [listaComentarios, setListaComentarios] = useState([]);
    useEffect(() => {
        async function buscarPublicacoes() {
            const resposta = await api.get(`/publicacoes/${id}`);
            console.log(resposta.data);
            console.log("Data criação", resposta.data.data_criacao);
            let res = resposta.data.data_criacao.split("T");
            let data = res[0].split("-");
            let hora = res[1].split(":");
            console.log("Data", data)
            console.log("Hora", hora)
            setDataHoraFormatada(data[2] + '/' + data[1] + '/' + data[0] + ' ' + hora[0] + ':' + hora[1]);

            getPublicacoes(resposta.data);

        }

        buscarPublicacoes()
    }, [])



    useEffect(() => {
        async function buscarComentario() {
            const resposta = await api.get(`/interacao/comentario/${listaPublicacoes.id_publicacao}`);
            console.log("comentarios", resposta.data);
            setListaComentarios(resposta.data);
        }

        buscarComentario()
    }, [listaPublicacoes, comentario])

    useEffect(() => {
        async function verificarLike() {
            const resposta = await api.get(`interacao/like/${listaPublicacoes.id_publicacao}`);
            console.log('like:', resposta.data);

            if (resposta.data) {
                setStatusLiked(LikeBranco)
            } else {
                setStatusLiked(Like)
            }
        }

        verificarLike()
    }, [listaPublicacoes])

    async function enviarComentario(event) {
        event.preventDefault();
        console.log('Comentario:', comentario);
        if (comentario === "") {
            alert("Não é possivel fazer comentários vazios")
        }
        else {
            await api.post(`interacao/${id}/comentar`, {
                "comentario": comentario
            },
                {
                    headers: { 'Authorization': token }
                })

                .then((resposta) => {
                    if (resposta.status === 201) {
                        alert("Comentário criado com sucesso!");
                        // por algum motivo não funciona
                        setComentario("");
                    }
                })

                .catch((erro) => {
                    if (erro.response.status === 500) {
                        alert("Dados inválidos. Insira novamente");
                    }
                    if (erro.response.status === 400) {
                        alert("Ops, algo deu errado. Insira novamente");
                    }
                    if (erro.response.status === 403) {
                        alert("Você precisa estar logado para comentar!");
                    }
                })



        }




        // setComentario = "";
    }

    async function darLike(event) {
        event.preventDefault();
        await api.post(`interacao/${id}/curtir`, {

        },
            {
                headers: { 'Authorization': token }
            })

            .then((resposta) => {
                if (resposta.status === 200) {
                    setStatusLiked(Like)
                }
                if (resposta.status === 201) {
                    setStatusLiked(LikeBranco)
                }

            })

            .catch((erro) => {
                if (erro.response.status === 500) {
                    alert("Dados inválidos. Insira novamente");
                }
                if (erro.response.status === 400) {
                    alert("Ops, algo deu errado. Insira novamente");
                }
                if (erro.response.status === 403) {
                    alert("Você precisa estar logado para curtir!");
                }
            })
    }
    console.log(listaPublicacoes);
    return (
        <>
            <section className={styles.secao}>
                <article className={styles.filter}>
                    <span className={styles.spanData}>{dataHoraFormatada}</span>
                    <div>
                        <Div variant="PRIMARY">
                            Obra:
                           {listaPublicacoes.obra.nome}
                        </Div>
                        <Div variant="PRIMARY">
                            Status: Paralisada
                        </Div>
                        <Div variant="PRIMARY">
                            Valor: R$ {listaPublicacoes.orcamento}
                        </Div>
                    </div>
                    <Div variant="PRIMARY" className={styles.imgObra}>
                        <img src={`http://52.73.218.16/arquivos/${id}`} />
                    </Div><br></br>
                    <Div variant="PRIMARY">
                        {listaPublicacoes.descricao}
                    </Div>
                </article>
                <div className={styles.vertical}></div>
                <aside className={styles.aside}>
                    <Link to="/Feed">
                        <img className={styles.voltar} src={voltarBranco} alt="VOLTAR" />
                        <img className={styles.voltar} src={voltarPreto} alt="VOLTAR" />
                    </Link>
                    <header className={styles.headerPerfil}>
                        <img src={Perfil} alt="" className={styles.imgPerfil} />
                        <span className={styles.spanData}>{listaPublicacoes.usuario.nome}</span>
                    </header>
                    <div className={styles.horizontal}></div>
                    <div className={styles.linksPublicacao}>
                        <Button
                            onClick={darLike}
                            variant="TRANSPARENTE">
                            {/* LIKE */}
                            <img src={statusLike}

                                alt="" />
                            {/* LIKE */}
                        </Button>
                        <Button variant="TRANSPARENTE">
                            <img src={ComentarioImg} alt="" />
                        </Button>
                        <Button variant="TRANSPARENTE">
                            <img src={Compartilhar} alt="" />
                        </Button>
                    </div>
                    <div className={styles.horizontal}></div>
                    <footer>
                        <header className={styles.infoPublic}>
                            <Div variant="CINZA">{listaPublicacoes.obra.cidade}</Div>
                            <Div variant="CINZA">{listaPublicacoes.obra.categoria}</Div>
                        </header>
                        <article className={styles.articleComentarios}>

                            {
                                listaComentarios.length === 0 ?

                                    <Div variant="COMENTARIO" className={styles.comentarioVazio}>
                                        <p>Não há comentários cadastrados</p>
                                        <img src={AddComment} alt="" className={styles.imgComentarioVazio}/>
                                    </Div>
                                    :
                                    listaComentarios.map((com) => (
                                        <Comentario imagem={Coment1P} nome={com.usuario.nome} comentario={com.comentario} //hora="13h"
                                        />
                                    ))
                            }

                            {/* <Comentario imagem={Coment2P} nome="Mayara Fernandes" comentario="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" hora="17h" /> */}

                        </article>
                        <footer className={styles.footerComents}>
                            <Div variant="PRIMARY" className={styles.divComentPublic}>
                                <Button variant="REDONDOAZUL"><img src={Clip} alt="" /></Button>
                                <input className={styles.inputComent}
                                    value={comentario}
                                    onChange={event => {
                                        setComentario(event.target.value);
                                        console.log(comentario);
                                    }}
                                    type="text" placeholder="Escreva seu comentário..." />
                                <Button variant="REDONDOAZUL"
                                    onChange={event => {
                                        console.log("saudades dela");
                                    }}
                                ><img src={Emoticon} alt="" /></Button>
                            </Div>
                            <Button variant="REDONDOAZUL"
                                onClick={enviarComentario}
                            ><img src={Enviar} alt="" /></Button>
                        </footer>
                    </footer>
                </aside>
            </section>
        </>
    );
}