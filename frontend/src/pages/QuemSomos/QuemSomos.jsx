import React from "react";

import styles from "./QuemSomos.module.css";
import Imagem1 from "../../assets/images/Imagem1.png";
import Diogo from "../../assets/images/imgDiogo.png";
import Diovana from "../../assets/images/imgDiovana.png";
import Nicole from "../../assets/images/imgNicole.png";
import Ciebra from "../../assets/images/imgCiebra.png";
import Santos from "../../assets/images/imgSantos.png";
import Turquette from "../../assets/images/imgTurquette.png";
import TextoACheck from "../../assets/images/TextoACheck.png";
import logoFundoAzul from "../../assets/images/logoFundoAzul.png";

import { Header } from "../../components/Header2/Header2";
import { Button } from "../../components/Button/Button";
import { Contato } from "../../components/Contato/Contato";
// import { Link } from "react-router-dom";

export function QuemSomos() {
    return (
        <>
            <Header />
            <section className={styles.linearGradiant}>
                <div className={styles.introducao}>
                    <h1>Conectando e informando milhares de pessoas por toda São Paulo com maior transparência e qualidade possível.</h1>
                </div>
                <div className={styles.imagem}>
                    <img src={Imagem1} alt="ERRO" />
                </div>
            </section>

            <section className={styles.integrantes}>
                <div className={styles.componente}>
                    <figcaption>Diogo Ivan</figcaption>
                    <img src={Diogo} alt="ERRO" />
                </div>
                <div className={styles.componente}>
                    <figcaption>Diovana Katsu</figcaption>
                    <img src={Diovana} alt="ERRO" />
                </div>
                <div className={styles.componente}>
                    <figcaption>Nicole Alves</figcaption>
                    <img src={Nicole} alt="ERRO" />
                </div>
                <div className={styles.componente}>
                    <figcaption>Rafael Ciebra</figcaption>
                    <img src={Ciebra} alt="ERRO" />
                </div>
                <div className={styles.componente}>
                    <figcaption>Rafael Santos</figcaption>
                    <img src={Santos} alt="ERRO" />
                </div>
                <div className={styles.componente}>
                    <figcaption>Vinicius Turquette</figcaption>
                    <img src={Turquette} alt="ERRO" />
                </div>
            </section>

            <section className={styles.opcoes}>
                <div className={styles.botoes}>
                    <button>Objetivo</button>
                    <button>Visão</button>
                    <button>Valores</button>
                </div>
                <div className={styles.texto}>
                    <h3>Ser a maior plataforma de informação sobre obras formada por cidadãos de São Paulo</h3>
                </div>
            </section>

            <section className={styles.imgsCheck}>
                <img src={TextoACheck} alt="" />
                <img src={logoFundoAzul} alt="" />
            </section>

            <section className={styles.mensagem}>
                <h5>Formamos uma rede social que busca lhe informar com maior transparencia sobre obras estatais em sua região.</h5>
            </section>
            <Contato/>
        </>
    );
}
