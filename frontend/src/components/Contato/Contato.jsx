import React from "react";

import styles from './Contato.module.css';
import insta from '../../assets/images/instagram.png';
import face from '../../assets/images/twitter.png';
import twitter from '../../assets/images/facebook.png';
import linkSite from '../../assets/images/cone.png';
import voltar from '../../assets/images/voltar.png';
import { Link } from "react-router-dom";

export function Contato() {

    return (
        <section className={styles.contato}>
            {/* <a className={styles.voltar}><img src={voltar} /></a> */}
            <article className={styles.orientacoes}>
                <div className={styles.quemSomos}>
                    <h1>Quem somos</h1>
                    <Link to="/quem-somos" className={styles.orientacoesLink}>
                        Os criadores
                    </Link>
                    <Link to="/quem-somos" className={styles.orientacoesLink}>
                        Objetivo visão e valores
                    </Link>
                    <Link to="/quem-somos" className={styles.orientacoesLink}>
                        A Check
                    </Link>
                </div>
                <div className={styles.barra}></div>
                <div className={styles.estast}>
                    <h1>Estatísticas</h1>
                    <Link to="/" className={styles.orientacoesLink}>
                        Valores de obras
                    </Link>
                    <Link to="/" className={styles.orientacoesLink}>
                        Gráfico
                    </Link>
                    <Link to="/" className={styles.orientacoesLink}>
                        Ranking
                    </Link>
                </div>
                <div className={styles.barra}></div>
                <div className={styles.ajuda}>
                    <h1>Fale conosco</h1>
                    <Link to="/fale-conosco" className={styles.orientacoesLink}>
                        Ajuda
                    </Link>
                    <Link to="/fale-conosco" className={styles.orientacoesLink}>
                        Atendimento
                    </Link>
                    <Link to="/fale-conosco" className={styles.orientacoesLink}>
                        Envie-nos um e-mail
                    </Link>
                    <Link to="/fale-conosco" className={styles.orientacoesLink}>
                        SAC
                    </Link>
                </div>
            </article>
        </section>
    );
}