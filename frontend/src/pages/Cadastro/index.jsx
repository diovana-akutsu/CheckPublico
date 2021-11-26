import React from "react";
import styles from "./index.module.css";
import Logo from "../../assets/images/logoCheck.png";
import Google from "../../assets/images/GoogleCads.png";
import Facebook from "../../assets/images/FaceCads.png";
import { Link } from "react-router-dom";

import { Input } from "../../components/Input/Input";
import { LinkComponent } from "../../components/Link/Link";
import { Button } from "../../components/Button/Button";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

import api from "../../services/api";


// import { Link } from "react-router-dom";

export function Cadastro() {

  const [concordo, setConcordo] = useState(true);


  const [usuario, setUsuario] = useState({
    nome: "",
    email: "",
    senha: "",
  });

  let history = useHistory();

  function handleInput(eventoDoOnChange) {

    const { name, value } = eventoDoOnChange.target;

    setUsuario({
      ...usuario,
      [name]: value,
    });

    console.log(usuario);
  }

  async function cadastrar(event) {
    event.preventDefault();

    if (!concordo) {
      alert("Você precisa concordar com os termos")
    }
    else {
      const resposta = await api.post("/user", {
        ...usuario,
      })

        .then((resposta) => {
          if (resposta.status === 201) {
            alert("Usuário cadastrado com sucesso!");
            history.push("/");
          }
        })

        .catch((erro) => {
          if (erro.response.status === 500) {
            alert("Dados inválidos. Insira novamente");
          }
          if (erro.response.status === 400) {
            if(erro.response.data[0].erro=="deve ser um endereço de e-mail bem formado"){
              alert("Deve ser um endereço de e-mail bem formado");

            }
            else{
              console.log(erro.response)
              let msgErro = erro.response.data[0].erro
              msgErro = msgErro.toLowerCase().replace(/(?:^|\s)\S/g, function(a) {
                return a.toUpperCase();
              });
              
              alert(msgErro);
            }

          }
        })



    }



  }

  return (
    <div className={styles.container}>
      <div className={styles.divcontainer}>
        <form className={styles.form}      >
          <img src={Logo} alt="" className={styles.logoMobile} />
          <h1 className={styles.heading}>Cadastro</h1>
          <div>
            <label className={styles.labelInput}>Nome:</label>
            <Input
              onChange={handleInput}
              id="inputNome" name="nome" variant="PRIMARY" placeholder="Digite seu nome..." required />
          </div>
          <div>
            <label className={styles.labelInput}>E-mail:</label>
            <Input
              onChange={handleInput}
              id="inputEmail" name="email" variant="PRIMARY" placeholder="Digite seu e-mail..." required />
          </div>
          <div>
            <label className={styles.labelInput}>Senha:</label>
            <Input
              onChange={handleInput}
              id="inputSenha" name="senha" variant="PRIMARY" type="password" placeholder="Digite sua senha..." required />
          </div>
          <label className={styles.checkboxLabel}>
            <Input id="inputCheckbox" variant="CHECKBOX" type="checkbox"
              checked={concordo}
              onChange={event => {
                setConcordo(event.target.checked);
              }}
              name="concordo"
              required />
            <p className={styles.label}>
              Li e concordo com os Termos de Uso do Check
          </p>
          </label>
          <Link className={styles.socialLoginDescription} to="/importacao">
            <b>Para importar algum aquivo, clique aqui.</b>
          </Link>

          <p className={styles.socialLoginDescription}>
            <b>Se preferir, entre com uma rede social</b>
          </p>
          <div className={styles.linksCadastroRedes}>
            {/* <Link to=""> */}
            <LinkComponent variant="CADASTRO">
              <img src={Google} />
            </LinkComponent>
            {/* </Link> */}
            {/* <Link to=""> */}
            <LinkComponent variant="CADASTRO">
              <img src={Facebook} />
            </LinkComponent>
            {/* </Link> */}
            <div>
              <Button className={styles.buttonCadastro} variant="SECUNDARY" onClick={cadastrar}><b>Cadastre-se</b></Button>
            </div>
          </div>

        </form>
        <Link to="/">
          <img src={Logo} alt="" className={styles.logo} />
        </Link>
      </div>
    </div>
  );
}
