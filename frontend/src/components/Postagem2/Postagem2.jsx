import React from "react";
import styles from './Postagem2.module.css'

import { Button } from "../../components/Button/Button";
import { Link } from "react-router-dom";

import Curtir from "../../assets/images/CurtirBranco.png";
import Comentar from "../../assets/images/ComentarBranco.png";
import EnviarBranco from "../../assets/images/EnviarBranco.png";
import Pontos from "../../assets/images/Pontos.png";
import { useCookies } from 'react-cookie';


export function Postagem2({ id, nome, status, endereco, categoria }) {

    const [cookies, setCookie] = useCookies(['public']);

    function pegarId(newId) {
        console.log("OldSpice", newId.target)

    }


    return (
        <div className={styles.corpoPostagem}>
            <div className={styles.headerPostagem} id={id} status={status}>
                <div className={styles.infoPost}>
                    <h1>{nome}</h1>
                    <h3>{endereco}</h3>
                    <h3>categoria: {categoria}</h3>
                </div>
                <Link to="/publicacao"
                    onClick={() => {
                        console.log(nome);
                        console.log(id);
                        setCookie('public', id, { path: '/' });
                        console.log(cookies.public);
                    }

                    }
                >
                </Link>
            </div>
        </div>
    )
};