import React from "react";

import styles from "./Feed.module.css";
//import Coment1P from "../../assets/images/Coment1P.png";
import Coment1P from "../../assets/images/profile-user.png";
import Robot from "../../assets/images/robot.png";

import Coment2P from "../../assets/images/Coment2P.png";
import Obra1 from "../../assets/images/Obra1.png";
import LogoAzul from "../../assets/images/LogoAzul.png";
import Filtro from "../../assets/images/Filtro.png";
import Lupa from "../../assets/images/Lupa.png";
import Mapa from "../../assets/images/Mapa.png";
//import Perfil from "../../assets/images/Perfil.png";
import Perfil from "../../assets/images/user-profile-white.png";
import CriarPublicacao from "../../assets/images/CriarPublicacao.png";
import Notificacao from "../../assets/images/Notificacao.png";
import mapIcon from "../../assets/images/map-icon.png";
import Config from "../../assets/images/Config.png";
import Logout from "../../assets/images/Logout.png";
import ImgNotObra from "../../assets/images/Obra1.png";
import Hospital from "../../assets/images/hospital.jpeg";
import Parque from "../../assets/images/parque.jpg";
import ImgNotFound from "../../assets/images/notFound.svg";
import { Div } from "../../components/Div/Div";
import { Postagem } from "../../components/Postagem/Postagem";
import { Noticias } from "../../components/Noticias/Noticias";
import { Input } from "../../components/Input/Input";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../../services/api";
import { useCookies } from 'react-cookie';



export function Feed() {

    const [cookies, setCookie] = useCookies(['public']);

    const [nomeObra, setNomeObra] = useState(null);
    const [imagemObra, setimagemObra] = useState(Obra1);
    const [perfilFile, setPerfilFile] = useState(Coment1P);


    function pegarId(newId) {
        setCookie('public', newId, { path: '/' });
        console.log(cookies.publi);
    }


    const [listaPublicacoes, getPublicacoes] = useState([]);

    useEffect(() => {
        console.log("Pato:", nomeObra);

        async function buscarPublicacoes() {
            console.log("TESTE:", nomeObra);
            if (nomeObra === null) {
                const resposta = await api.get("/publicacoes");
                console.log(resposta.data);
                getPublicacoes(resposta.data);
                console.log("hmmmmmmmmmm")
            } else {
                const resposta = await api.get("/publicacoes?nomeObra=" + nomeObra);
                console.log(resposta.data);
                console.log("Status", resposta.status);
                if (resposta.status === 200) {

                    setimagemObra(Obra1);
                    setPerfilFile(Coment1P);
                    getPublicacoes(resposta.data);
                }
                if (resposta.status === 204) {
                    setimagemObra(ImgNotFound);
                    setPerfilFile(Robot);

                    getPublicacoes([
                        {
                            "imagem_obra": "arquivofoto.png",
                            "nome": "Sistema avisa:",
                            "rua": "Obra não encontrada"
                        }

                    ]);
                }
            }

        }

        buscarPublicacoes()
    }, [nomeObra])


    // function pesquisar(){
    //     buscarPublicacoes();

    //
    //<img src={LogoAzul} className={styles.LogoAzul} />
    //<img src={Mapa} className={styles.mapa} />
    // }

    console.log(listaPublicacoes);

    return (
        <>
            <div className={styles.flex} >
                <div className={styles.estruturaEsquerda}>
                <img src={LogoAzul} className={styles.LogoAzul} />
                <img src={Mapa} className={styles.mapa} />
                    <div className={styles.noticias}>
                        <Noticias imagem={ImgNotObra} span="1800 trabalhadores, dos 9 mil previstos, estão nas obras da Linha 6 do Metrô"></Noticias>
                        <Noticias imagem={Hospital} span="Governo anuncia início das obras do primeiro hospital oncológico do DF"></Noticias>
                        <Noticias imagem={Parque} span="Com investimento de R$ 18,9 milhões, governo dá início a obra de revitalização do Parque dos Poderes
"></Noticias>
                    </div>
                </div>
                <div className={styles.estruturaCentral}>
                    <header className={styles.headerFeed}>
                        <div className={styles.barraPesquisa}>
                            <img src={Filtro} />
                            <Input variant="AZULCLARO"
                                onChange={event => {
                                    console.log(event.target.value);
                                    setNomeObra(event.target.value);
                                }} />
                            <img src={Lupa} />
                            {/* onClick={pesquisar} */}
                        </div>
                    </header>
                    <div className={styles.divPublicacoes}>
                        <section className={styles.sectionFeed}>
                            {/* <Postagem id="1" imagem={Coment1P} nome="Nicole Gregorio Alves" endereco="Freguesia do Ó - São Paulo" imgObra={Obra1}></Postagem>
                            <Postagem id="2" imagem={Coment2P} nome="Mayara Rodrigues" endereco="Freguesia do Ó - São Paulo" imgObra={Obra1}></Postagem> */}

                            {listaPublicacoes.map((publi) => (

                                publi.nome == "Sistema avisa:" ?
                                    <Div variant="CINZA">
                                        <p>Obra não encontrada!</p>
                                    </Div>
                                    :
                                    <Postagem

                                        id={publi.id_publicacao}
                                        imagem={perfilFile}
                                        nome={publi.nome}
                                        endereco={publi.rua}
                                        imgObra={`http://52.73.218.16/arquivos/${publi.id_publicacao}`}
                                    />
                            ))}


                        </section>
                    </div>
                </div>
                <aside className={styles.estruturaDireita}>
                    <img src={Perfil} className={styles.imgAside} />
                    <div className={styles.links}>
                        <Link to="/adicionar-publicacao">
                            <a>
                                <img src={CriarPublicacao} />
                            </a>
                        </Link>
                        <Link to="/notificados">
                            <a>
                                <img src={Notificacao} />
                            </a>
                        </Link>
                        <Link to="/mapa">
                            <a>
                                <img width="50px" src={mapIcon} />
                            </a>
                        </Link>
                        {/* <Link to="/">
                            <a>
                                <img src={Config} />
                            </a>
                        </Link> */}
                    </div>
                    <Link to="/" className={styles.logout}>
                        <a>
                            <img src={Logout} />
                        </a>
                    </Link>
                </aside>
            </div>
        </>
    );
}
