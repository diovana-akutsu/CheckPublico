import React from "react";

import styles from "./Mapa.module.css";
import { MapContainer, TileLayer, Marker, Popup, Icon } from 'react-leaflet'
import l from 'leaflet'
import { Feed } from "../Feed/Feed";
import { Postagem } from "../../components/Postagem/Postagem";
import Coment1P from "../../assets/images/profile-user.png";
import { Div } from "../../components/Div/Div";
import marker from "../../assets/images/map-marker-icon.png";
import markerBranco from "../../assets/images/map-marker-icon-white.png";
import { useEffect, useState } from "react";
import api from "../../services/api";

// import "./leaflet.css";

export function Mapa() {
  const [estiloLateral, setEstiloLateral] = useState(styles.escondido);

  const [perfilFile, setPerfilFile] = useState(Coment1P);

  const [listaPublicacoes, getPublicacoes] = useState([""]);

  useEffect(() => {

    async function buscarPublicacoes() {

      const resposta = await api.get("/publicacoes/04251-010/369");
      console.log(resposta.data);
      getPublicacoes(resposta.data);
      console.log("hmmmmmmmmmm")


    }

    buscarPublicacoes()
  }, [])

  return (


    <section className={styles.sectionMapa}>

      <div className={styles.divMapa}>
        <MapContainer className={styles.mapa} style={{ height: "100vh" }} center={[-23.6252599, -46.5991461]} zoom={13} scrollWheelZoom={false}>
          <TileLayer
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker
            eventHandlers={{
              click: () => {
                setEstiloLateral(styles.leftSide)
                //alert('marker clicked')
              },
            }}
            icon={l.icon({
              iconUrl: marker,
              shadowUrl: markerBranco,

              iconSize: [32, 52.125], // size of the icon
              shadowSize: [32, 52.125], // size of the shadow
              iconAnchor: [-42, 62], // point of the icon which will correspond to marker's location
              shadowAnchor: [-40, 60],  // the same for the shadow
              popupAnchor: [57, -60] // point from which the popup should open relative to the iconAnchor
            })} position={[-23.6252599, -46.5991461]}>
            <Popup>

              {
                listaPublicacoes == "" ?
                  <p>Carregando!</p>
                  :
                  <p>{listaPublicacoes[0].obra.nome}</p>
              }




            </Popup>
          </Marker>
        </MapContainer>
      </div>

      <div className={styles.estiloLateral}>
        {listaPublicacoes.map((publi) => (
          publi == "" ?
            <Div variant="CINZA">
              <p>Obra não encontrada!</p>
            </Div>
            :
            <Postagem

              id={publi.id_publicacao}
              imagem={perfilFile}
              nome={publi.obra.nome}
              endereco={publi.obra.rua}
              imgObra={`http://52.73.218.16//arquivos/${publi.id_publicacao}`}
            />
        ))}
      </div>
    </section>


  );
}
