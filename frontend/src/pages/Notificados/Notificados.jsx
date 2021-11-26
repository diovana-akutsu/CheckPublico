import React from "react";

import styles from "./Notificados.module.css";
import Coment1P from "../../assets/images/Coment1P.png";
import Coment2P from "../../assets/images/Coment2P.png";
import Obra1 from "../../assets/images/Obra1.png";
import LogoAzul from "../../assets/images/LogoAzul.png";
import Filtro from "../../assets/images/Filtro.png";
import Lupa from "../../assets/images/Lupa.png";
import Mapa from "../../assets/images/Mapa.png";
import Perfil from "../../assets/images/user-profile-white.png";
import CriarPublicacao from "../../assets/images/CriarPublicacao.png";
import Notificacao from "../../assets/images/Notificacao.png";
import Config from "../../assets/images/Config.png";
import Logout from "../../assets/images/Logout.png";
import ImgNotObra from "../../assets/images/ImgNotObra.png";
import Feed from "../../assets/images/feed.png"
import { Postagem2 } from "../../components/Postagem2/Postagem2";
import { Noticias } from "../../components/Noticias/Noticias";
import { Input } from "../../components/Input/Input";
import { Link } from "react-router-dom";
import { Div } from "../../components/Div/Div";
import { useEffect, useState } from "react";
import { useCookies } from 'react-cookie';
import api from "../../services/api";


export function Notificados() {

    const [cookies, setCookie] = useCookies(['public', 'token']);
    const [categoriaObra, setCategoriaObra] = useState(null);
    const [listaObras, setListaObras] = useState([]);
    const [estiloChecked, setEstiloChecked] = useState('');
    let token = cookies.token;

    useEffect(() => {
        console.log("valor da categoria no useEffect: " + categoriaObra)
        async function buscarPublicacoes() {
            console.log("Categoria no back:", categoriaObra);
            if (categoriaObra === null) {
                const resposta = await api.get("/obras");
                console.log(resposta.data);
                console.log("hmmmmmmmmmm")
                if (resposta.status === 200) {
                    //resposta.data.estiloChecked="";
                    setListaObras(resposta.data);
                }
            } else {
                const resposta = await api.get("/obras?categoriaObra=" + categoriaObra);
                console.log(resposta.data);
                console.log("Satus", resposta.status);

                if (resposta.status === 200) {
                    setListaObras(resposta.data);
                }
                if (resposta.status === 204) {
                    setListaObras([
                        {
                            "nome": "Sistema avisa:",
                        }

                    ]);
                }
            }

        }



        buscarPublicacoes();

    }, [categoriaObra])

    return (
        <div className={styles.display}>
            <div className={styles.estrutura1}>
                <img src={LogoAzul} />
                <div>
                    <hr />
                    <div className={styles.opcoes}>
                        <h5>Categorias:</h5>
                        <div>
                            <input type="radio" name="gender" value="todos" id="all" onClick={
                                event => {
                                    setCategoriaObra(null);
                                }
                            } />
                            <label htmlFor="null">Todos</label>
                        </div>
                        <div>
                            <input type="radio" name="gender" value="Educação" id="Educação" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Educação">Educação</label>
                        </div>
                        <div>
                            <input type="radio" name="gender" value="Saúde" id="Saúde" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Saúde">Saúde</label>
                        </div>
                        <div>
                            <input type="radio" name="gender" value="Transporte" id="Transporte" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Transporte">Transporte</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" value="Patrimônio cultural" id="Patrimônio cultural" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Patrimônio cultural">Patrimônio cultural</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" value="Lazer" id="Lazer" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Lazer">Lazer</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" value="Segurança" id="Segurança" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Segurança">Segurança</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" value="Urbanização" id="Urbanização" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Urbanização">Urbanização</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" value="Serviços básicos" id="Serviços básicos" onClick={
                                event => {
                                    setCategoriaObra(event.target.value);
                                }
                            } />
                            <label htmlFor="Serviços básicos">Serviços básicos</label>
                        </div>
                    </div>
                    <hr />
                </div>
            </div>
            <section className={styles.estrutura2}>

                {listaObras.map((obras) => (

                    obras.nome == "Sistema avisa:" ?
                        <Div variant="CINZA" className={styles.avisoNaoEncontrado}>
                            <p>Obra não encontrada!</p>
                        </Div>
                        :
                        <div
                        id={obras.id_obra}
                            onClick={
                                (e)=>{
                                   console.log(obras.id_obra)

                                   async function notificar(){
                                        await await api.post("/notificados/"+obras.id_obra,{

                                        },
                                            {
                                                headers: { 'Authorization': token }
                                            })

                                        .then((resposta)=>{
                                          
                                            if (resposta.status === 200) {
                                                alert("Agora você não receberá mais notificações dessa obra");
                                
                                            }
                                            if (resposta.status === 201) {
                                                alert("Agora você receberá as notificações dessa obra");
                                
                                            }
                                        })
                                        .catch(
                                            (resposta)=>{
                                          
                                                if (resposta.status === 400|| resposta.status === 500) {
                                                    alert("Ops, algo deu errado!")                                    
                                                }
                                              
                                            }
                                          
                                        )
                                   }

                                   notificar(obras.id_obra);

                                //    setEstiloChecked(styles.checked)
                                //    if(obras.estiloChecked=="" || obras.estiloChecked==undefined){
                                //     obras.estiloChecked="checked"
                                //     setEstiloChecked(styles.checked)
                                //    }
                                //    else{
                                //     obras.estiloChecked=""
                                //     setEstiloChecked(styles.checked)
                                //    }
                                //    console.log(obras.estiloChecked)
                                }
                            }
                            className={styles.divGenerica} >
                            <div
                                className={ estiloChecked }>
                                <Postagem2

                                    id={obras.id_obra}
                                    categoria={obras.categoria}
                                    endereco={obras.rua}
                                    status={obras.status}
                                    nome={obras.nome}>
                                </Postagem2>
                            </div>

                        </div>

                ))}


            </section>
            <aside className={styles.estruturaDireita}>
                <img src={Perfil} className={styles.imgAside} />
                <div className={styles.links}>
                    <Link to="/adicionar-publicacao">
                        <a>
                            <img src={CriarPublicacao} />
                        </a>
                    </Link>
                    <Link to="/Feed">
                        <a>
                            <img src={Feed} />
                        </a>
                    </Link>
                    <Link to="/">
                        <a>
                            <img src={Config} />
                        </a>
                    </Link>
                </div>
                <Link to="/" className={styles.logout}>
                    <a>
                        <img src={Logout} />
                    </a>
                </Link>
            </aside>
        </div>


    )
}