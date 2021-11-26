import React from "react";

import styles from "./RecuperacaoDeSenha.module.css";
import Coment1P from "../../assets/images/Coment1P.png";
import Logo from "../../assets/images/logoCheck.png";
import voltarBranco from "../../assets/images/SetaSair.png"
import voltarPreto from "../../assets/images/SetaVoltar.png"

import { Input } from "../../components/Input/Input";
import { Button } from "../../components/Button/Button";
import { Link } from "react-router-dom";

export function RecuperacaoDeSenha() {
    return (
        <main className={styles.corpoSenha}>
            <Link to="/Login">
                <img className={styles.voltar} src={voltarBranco} alt="VOLTAR" />
                <img className={styles.voltar} src={voltarPreto} alt="VOLTAR" />
            </Link>
            <Link to="/">
                <img src={Logo} className={styles.imgLogo} />
            </Link>
            <section className={styles.sectionSenha}>
                <h1>
                    Esqueceu a senha?
                </h1>
                <div className={styles.inputSenha}>
                    <label>Endereço de e-mail:</label>
                    <Input variant="LOGININPUT"></Input>
                </div>
                <div className={styles.buttonSenha}>
                    <Button variant="PRIMARY">Envie-me instruções</Button>
                </div>
            </section>
        </main>
    );
}
