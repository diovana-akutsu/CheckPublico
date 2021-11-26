import { Link } from 'react-router-dom'
import { Button } from "../../components/Button/Button";
import styles from "./NotFound.module.css";

export default function NotFound() {
  return (
    <div className={styles.corpo}>
      <div className={styles.info}>
        <h1>Oops!</h1>
        <h3>A página que você procura não foi encontrada.</h3>
        <Link to="/">
          <Button variant="SECUNDARY" >Voltar</Button>
        </Link>
      </div>
    </div>
  );
}
