import React from "react";
import styles from './Header2.module.css'

import { LinkComponent } from "../Link/Link";
import { Link } from "react-router-dom";

import Logo from '../../assets/images/logoCheck.png';
import hanburguer from '../../assets/images/Vector.png';

export function Header() {
    return (
        <main className={styles.corpoHeader}>
            <Link to="/" >
                <img src={Logo} className={styles.imgLogo} />
            </Link>
            <div className={styles.divLinks}>
                <Link to="/quem-somos" >
                    <h1 className={styles.link}>Quem somos</h1>
                </Link>
                <Link to="/" >
                    <h1 className={styles.link}>Estatísticas</h1>
                </Link>
                <Link to="/fale-conosco" >
                    <h1 className={styles.link}>Fale conosco</h1>
                </Link>
            </div>
            <div className={styles.divButtons}>
                <Link to="/cadastro" >
                    <LinkComponent variant="BRANCO" className={styles.Buttons}>
                        Crie sua conta
                </LinkComponent>
                </Link>

                <Link to="/login" >
                    <LinkComponent variant="BRANCO" className={styles.Buttons}>
                        Acesse sua conta
                </LinkComponent>
                </Link>
            </div>
            <div className={styles.hanburguer}>
                <Link to="/login" >
                    <img src={hanburguer}/>
                </Link>
            </div>

        </main>
    )
}   