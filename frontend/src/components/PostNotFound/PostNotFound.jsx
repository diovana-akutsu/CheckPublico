import React from "react";
import styles from './PostNotFound.module.css'

import { Button } from "../../components/Button/Button";
import { Link } from "react-router-dom";

import Curtir from "../../assets/images/CurtirBranco.png";
import robot from "../../assets/images/robot.png";
import Comentar from "../../assets/images/ComentarBranco.png";
import EnviarBranco from "../../assets/images/EnviarBranco.png";
import Pontos from "../../assets/images/Pontos.png";
import { useCookies } from 'react-cookie';


export function Postagem2({ imgObra, id, nome, status , endereco, categoria }) {

    const [cookies, setCookie] = useCookies(['public']);

    function pegarId(newId) {
        console.log("OldSpice", newId.target)

    }


    return (
        <div className={styles.corpoPostagem}>
        <div className={styles.headerPostagem}  
         id={id}>
            <img width="55px" src={robot} />
            <div className={styles.infoPost}>
                <span>Sistema Avisa:</span>
                <span>Obra não encontrada</span>
            </div>
            <Link to="/publicacao"
            onClick={()=>{
                console.log(nome);
                console.log(id);
                setCookie('public', id, { path: '/' });
                console.log(cookies.public);
            }
                
            }
            >
                <img src={Pontos} className={styles.pontos}/>
            </Link>
        </div>
        <img src={imgObra} />
        <div className={styles.buttonsPost}>
            <Button variant="TRANSPARENTE">
                <img src={Curtir} />
            </Button>
            <Button variant="TRANSPARENTE">
                <img src={Comentar} />
            </Button>
            <Button variant="TRANSPARENTE">
                <img src={EnviarBranco} />
            </Button>
        </div>
    </div>
    )
};