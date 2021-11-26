import logo from "./logo.svg";
import "./assets/index.css";
import "./App.css";
import { MenuOficial } from "./pages/MenuOficial/MenuOficial";
import { Login } from "./pages/Login/Login";
import { Cadastro } from "./pages/Cadastro";
import { Publicacao } from "./pages/Publicacao/Publicacao";
import { Compartilhar } from "./pages/Compartilhar/Compartilhar";
import { Feed } from "./pages/Feed/Feed";
import { RecuperacaoDeSenha } from "./pages/RecuperacaoDeSenha/RecuperacaoDeSenha";
import { FaleConosco } from "./pages/FaleConosco/FaleConosco";
import { QuemSomos } from "./pages/QuemSomos/QuemSomos";
import { Routes } from "./routes/routes";

function App() {
  return (
    <div className="App">
      {/* <MenuOficial/> */}

      {/* <Cadastro />  */}

      {/* <Login />  */}

      {/* <Publicacao />  */}

      {/* <Compartilhar/> */}

      {/* <Feed/>  */}

      {/* <RecuperacaoDeSenha/> */}

      {/* <FaleConosco/> */}

      {/* <QuemSomos/> */}

      <Routes/>
    </div>
  );
}

export default App;
