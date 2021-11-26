import { Link } from 'react-router-dom'
import { Button } from "../../components/Button/Button";
import styles from "./ImportacaoArquivo.module.css";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import api from "../../services/api";

export default function ImportacaoArquivo() {

  const [arquivoUpload, setArquivoUpload] = useState("");
  let history = useHistory();


  function handleInputArquivo(eventoDoOnChange) {

    const { name, value } = eventoDoOnChange.target;
    console.log('arquivo', [name], value)
    let files = eventoDoOnChange.target.files[0];
    setArquivoUpload(
      files
    );

    console.log(arquivoUpload);
  }

  async function cadastrar(event) {

    async function cadastrarImagem() {

      let form = new FormData();
      form.append('arquivo', arquivoUpload);
      form.append('texto', "sonim")
      await api.post("/arquivos/importacao",
        form
      )
        .then((resposta) => {
          alert("Importação efetuada com sucesso!");
          history.push("/");
        })
        .catch((erro) => {
          alert('Ops, algo deu errado! Insira um arquivo.')
        })
    }
    cadastrarImagem();
  }

  return (
    <div className={styles.corpo}>
      <div className={styles.info}>
        <h1>Importação de arquivo!</h1>
        <h3>Por favor insira um arquivo txt para iniciar a importação.</h3>
        <input
          onChange={handleInputArquivo}
          name="arquivo"
          className={styles.fileChooser}
          type="file" id="inputFileToLoad" accept=".txt" />
        <Button
         onClick={cadastrar}
         variant="SECUNDARY"
         className={styles.button} >Importar</Button>
      </div>
      {/* <Link className={styles.volta} to="/">
          <Button variant="SECUNDARY" >Voltar</Button>
        </Link> */}
    </div>
  );
}
