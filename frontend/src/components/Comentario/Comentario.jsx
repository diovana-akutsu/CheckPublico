import React from "react";
import styles from './Comentario.module.css'

import { Div } from "../../components/Div/Div";

export function Comentario({ imagem, nome, comentario, hora }) {
    return (
        <div className={styles.corpoComent}>
            <img src={imagem} className={styles.imgPerfil}/>
            <div>
                <Div variant="CINZA" className={styles.coment}>
                    <span >{nome}</span>
                    <span >{comentario}</span>
                </Div>
                <div className={styles.footerComents}>
                    <span>Curtir-Responder</span>
                    <span >{hora}</span>
                </div>
            </div>
        </div>
    )
};