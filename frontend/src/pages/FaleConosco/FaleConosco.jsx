import React from "react";

import styles from "./FaleConosco.module.css";
import CheckComCone from "../../assets/images/CheckComCone.png";
import CadastroComPrancheta from "../../assets/images/CadastroComPrancheta.png";
import MinhaConta from "../../assets/images/MinhaConta.png";
import AssistenteVirtual from "../../assets/images/AssistenteVirtual.png";
import Telefone from "../../assets/images/Telefone.png";
import Carta from "../../assets/images/Carta.png";

import { Header } from "../../components/Header2/Header2";
import { Button } from "../../components/Button/Button";
import { Contato } from "../../components/Contato/Contato";
// import { Link } from "react-router-dom";

export function FaleConosco() {
    return (
        <>
            <Header />
            <section className={styles.tirarDuvida}>
                <h1>Tire suas dúvidas</h1>
                <input type="text" placeholder="    Qual sua dúvida?" />
            </section>

            <section className={styles.infos}>
                <div className={styles.infoCheck}>
                    <img src={CheckComCone} alt="ERRO" />
                    <p>Como funciona?</p>
                    <p>O que é a Check?</p>
                    <p>Por que usar a plataforma?</p>
                    <p>Como fazer parte</p>
                    <p>Diretrizes da plataforma</p>
                </div>
                <div className={styles.infoCadastro}>
                    <img src={CadastroComPrancheta} alt="ERRO" />
                    <p>Não recebi e-mail de confirmação</p>
                    <p>Alteração de dados cadastrais</p>
                    <p>Por que devo informar meu CEP?</p>
                    <p>Não reconheço o e-mail cadastrado</p>
                    <p>Termos de Uso e Políticas</p>
                </div>
                <div className={styles.infoMinhaConta}>
                    <img src={MinhaConta} alt="ERRO" />
                    <p>Esqueci meu e-mail</p>
                    <p>Como publicar uma obra</p>
                    <p>Como utilizar o mapa</p>
                    <p>Inativar ou reativar conta</p>
                    <p>Denuncias</p>
                </div>
            </section>

            <section className={styles.entrarContato}>
                <h1>Entre em contato conosco</h1>
                <div className={styles.componentePai}>
                    <div className={styles.ComponenteAssistente}>
                        <img src={AssistenteVirtual} alt="" />
                        <h2>Assistente Virtual</h2>
                        <p>Tire dúvidas de maneira ágil e dinâmica.</p>
                        <Button variant="SECUNDARY">Acessar</Button>
                    </div>
                    <div className={styles.Componentes}>
                        <img src={Telefone} alt="" />
                        <h2>Telefones</h2>
                        <p>Confira nossos telefones e entre em contato.</p>
                        <Button variant="SECUNDARY">Nossos Telefones</Button>
                    </div>
                    <div className={styles.Componentes}>
                        <img src={Carta} alt="" />
                        <h2>E-mail</h2>
                        <p>Envie dúvidas, críticas ou sugestões clicando aqui.</p>
                        <Button variant="SECUNDARY">Enviar E-mail</Button>
                    </div>
                </div>
            </section>
            <Contato/>
        </>
    );
}
