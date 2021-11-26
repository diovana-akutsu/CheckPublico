import React from "react";

import styles from "./MenuOficial.module.css";
import imgmenu from '../../assets/images/imagem.svg';
import facaParte1 from '../../assets/images/facaParte1.png';
import facaParte2 from '../../assets/images/facaParte2.png';
import facaParte3 from '../../assets/images/facaParte3.png';
import biobra from '../../assets/images/46bi.png';
import dash from '../../assets/images/dash.png';
import insta from '../../assets/images/instagram.png';
import face from '../../assets/images/twitter.png';
import twitter from '../../assets/images/facebook.png';
import linkSite from '../../assets/images/cone.png';

import { LinkComponent } from "../../components/Link/Link";
import { Header } from "../../components/Header2/Header2";
import { Button } from "../../components/Button/Button";
import { Contato } from "../../components/Contato/Contato";
import { Link } from "react-router-dom";


export function MenuOficial() {
    return (
        
        <main className={styles.corpoMenu}>
            <Header />
            <section className={styles.sectionInicial}>
                <img src={imgmenu} />
                <div className={styles.textoInicial}>
                    <article>
                        <h1>
                            Participe da comunidade CHECK, ajude, informe-se e saiba para o que seu dinheiro está sendo usado.
                        </h1>
                        <h1>
                            Venha descobrir como sua região está se desenvolvendo.
                        </h1>
                    </article>
                    <footer className={styles.buttonsInicial}>
                        <Link to="/cadastro" className={styles.buttonMenu}>
                            <Button variant="PRIMARY" >
                                Crie sua conta
                           </Button>
                        </Link>
                        <Link to="/login" className={styles.buttonMenu}>
                            <Button variant="PRIMARY">
                                Acesse sua conta
                            </Button>
                        </Link>
                    </footer>
                </div>
            </section>
            <section className={styles.sectionJuntese}>
                <div className={styles.corpoJuntese}>
                    <h1>
                        Junte-se e faça parte da comunidade
                </h1>
                    <article className={styles.articleJuntese}>
                        <div>
                            <img src={facaParte1} />
                            <img src={facaParte2} />
                            <img src={facaParte3} />
                        </div>
                        <Link to="/cadastro">
                            <Button variant="SECUNDARY" >
                                Crie sua conta
                           </Button>
                        </Link>
                    </article>
                </div>
            </section>
            <section className={styles.sectionDados}>
                <article className={styles.articleDados}>
                    <img src={biobra} />
                    <span className={styles.spanDados}>
                        Foram gastos em obras que atualmente estão paradas ou atrasadas na cidade de São Paulo.
                        </span>
                </article>
                <div className={styles.divDash}>
                    <img src={dash} />
                </div>
            </section>
            <section className={styles.sectionContato}>
                <h1>
                    Acompanhe-nos em nossas redes sociais
                </h1>
                <article className={styles.linksContato}>

                    <LinkComponent variant="CONTATO">
                        <img src={insta} alt="" />
                    </LinkComponent>

                    <LinkComponent variant="CONTATO">
                        <img src={twitter} alt="" />
                    </LinkComponent>

                    <LinkComponent variant="CONTATO">
                        <img src={face} alt="" />
                    </LinkComponent>

                    <LinkComponent variant="CONTATO">
                        <img src={linkSite} alt="" />
                    </LinkComponent>

                </article>
            </section>
            <Contato />
        </main>
    );
}
