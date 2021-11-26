import React from "react";

import styles from "./Login.module.css";
import Logo from "../../assets/images/logoCheck.png";
import Google from "../../assets/images/GoogleAzul.png";
import voltarBranco from "../../assets/images/SetaSair.png"
import voltarPreto from "../../assets/images/SetaVoltar.png"
import Facebook from "../../assets/images/FacebookAzul.png";

import { Input } from "../../components/Input/Input";
import { LinkComponent } from "../../components/Link/Link";
import { Button } from "../../components/Button/Button";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import api from "../../services/api";
import { useCookies } from 'react-cookie';


export function Login() {

    const [cookies, setCookie] = useCookies(['token']);

    const [usuario, setUsuario] = useState({
        email: "",
        senha: "",
    });

    let history = useHistory();


    function handleInput(eventoDoOnChange) {

        const { name, value } = eventoDoOnChange.target;

        setUsuario({
            ...usuario,
            [name]: value,
        });

        console.log(usuario);
    }

    async function logar(event) {
        event.preventDefault();

        await api.post("/auth", {
            ...usuario,
        })
        .then((resposta)=>{
            if (usuario.email == "" || usuario.senha == "") {
                alert('Insira valor nos campos');
            }
            if (resposta.status === 200) {
                console.log("AAAAAAAA:", resposta);
                let token= resposta.data.tipo+ " " +resposta.data.token;
                setCookie('token', token, { path: '/' });
    
                console.log("Tolken:", token);
                  alert("Bem-vindo! :-)");
                  history.push("/feed");

            }
          
        })
        .catch((erro)=>{
            console.log('status',erro.response.status)
            if (erro.response.status === 400) {
                alert("E-mail e senha inválidos. Insira novamente");
            }
            if (erro.response.status === 500) {
                alert("E-mail já cadastrado. Insira novamente");
            }
        })

       

    }
    return (
        <>
            <div className={styles.container}>
                <Link to="/">
                    <img className={styles.voltar} src={voltarBranco} alt="VOLTAR" />
                    <img className={styles.voltar} src={voltarPreto} alt="VOLTAR" />
                </Link>
                <div className={styles.divLogin}>
                    <li className={styles.linksRedes}>
                        <LinkComponent className={styles.links} variant="LOGIN">
                            <img src={Google} alt="" />
                            <b>Entrar com google</b>
                        </LinkComponent>
                        <LinkComponent className={styles.links} variant="LOGIN">
                            <img src={Facebook} alt="" />
                            <b>Entrar com facebook</b>
                        </LinkComponent>
                    </li>
                    <div className={styles.separador}>
                        <div className={styles.bara1}></div>
                        <b>ou</b>
                        <div className={styles.bara1}></div>
                    </div>
                    {/* Mexer aqui */}
                    <form className={styles.formLogin}>
                        <div>
                            <label className={styles.labelLogin}>Endereço de e-mail:</label>
                            <Input id="inputEmail"
                                onChange={handleInput}
                                name="email"
                                variant="LOGININPUT" placeholder="Digite seu endereço de e-mail" />
                        </div>
                        <div>
                            <label className={styles.labelLogin}>Senha:</label>
                            <Input id="inputSenha"
                                onChange={handleInput}
                                name="senha"
                                variant="LOGININPUT"
                                type="password"
                                placeholder="Digite sua senha" />
                        </div>
                    </form>
                    <Link to="/recuperar-senha">
                        <h6 className={styles.senhaEsquecida}>esqueci minha senha</h6>
                    </Link>
                    <div className={styles.buttonLogin}>
                       
                            <Button variant="PRIMARY" onClick={logar}>Iniciar sessão</Button>
                      

                        <div>
                            <b>Não tem uma conta?</b>
                            <Link to="/cadastro" className={styles.linkLogin}>
                                Cadastre-se
                            </Link>
                            {/* <a href="" className={styles.linkLogin}>Cadastre-se</a> */}
                        </div>
                    </div>
                </div>
                <div className={styles.infoCheck}>
                    check.com- Suporte - Integrações - Fórum - Desenvolvedores e API - Recursos - <br />
             Guia - Modelos - Preços - Termos - Privacidade
            </div>
            </div>
        </>
    );
}
