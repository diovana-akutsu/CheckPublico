import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import { Feed } from "../pages/Feed/Feed";
import { Compartilhar } from "../pages/Compartilhar/Compartilhar";
import { Login } from "../pages/Login/Login";
import { MenuOficial } from "../pages/MenuOficial/MenuOficial";
import { Cadastro } from "../pages/Cadastro";
import { Publicacao } from "../pages/Publicacao/Publicacao";
import { RecuperacaoDeSenha } from "../pages/RecuperacaoDeSenha/RecuperacaoDeSenha";
import { QuemSomos } from "../pages/QuemSomos/QuemSomos";
import { FaleConosco } from "../pages/FaleConosco/FaleConosco";
import { Notificados } from "../pages/Notificados/Notificados";
import { Mapa } from "../pages/Mapa/Mapa";
import NotFound from "../pages/NotFound/NotFound";
import ImportacaoArquivo from "../pages/ImportacaoArquivo/ImportacaoArquivo";


export function Routes() {

    return (
        <Router>
            <Switch>
                <Route exact path="/" component={MenuOficial} />
                <Route exact path="/feed" component={Feed} />
                <Route exact path="/adicionar-publicacao" component={Compartilhar} />
                <Route exact path="/login" component={Login} />
                <Route exact path="/cadastro" component={Cadastro} />
                <Route exact path="/publicacao" component={Publicacao} />
                <Route exact path="/recuperar-senha" component={RecuperacaoDeSenha} />
                <Route exact path="/quem-somos" component={QuemSomos} />
                <Route exact path="/fale-conosco" component={FaleConosco} />
                <Route exact path="/notificados" component={Notificados} />
                <Route exact path="/mapa" component={Mapa} />
                <Route exact path="/importacao" component={ImportacaoArquivo} />
                <Route path="*" component={NotFound} />

                {/* Aqui é a página default como uma tela de erro */}
                {/* <Route exact path="*" component={...}/> */}
            </Switch>
        </Router>
    );

}