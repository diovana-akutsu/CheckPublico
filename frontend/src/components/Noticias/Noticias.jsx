import React from "react";
import styles from './Noticias.module.css'

export function Noticias({ imagem, span }) {
    return (
        <div className={styles.corpoNoticia}>
            <img src={imagem} className={styles.imagem}/>
            <span className={styles.span}>{span}</span>
            <div className={styles.div}></div>
        </div>
    )
};