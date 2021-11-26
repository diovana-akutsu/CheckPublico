// import React from 'react';
// import {useDropzone} from 'react-dropzone';

// import styles from "../../pages/Compartilhar/Compartilhar.module.css";
// import imagemFundoImportar from "../../assets/images/imagemFundoImportar.png";

// export function Dropzone(props) {
//   const {acceptedFiles, getRootProps, getInputProps} = useDropzone();
  
//   const files = acceptedFiles.map(file => (
//     <li key={file.path}>
//       {file.path} - {file.size} bytes
//     </li>
//   ));

//   return (
//     <section className="container">
//       <div {...getRootProps({className: 'dropzone'})} className={styles.inputImg}>
//         <input {...getInputProps()} />
//         <img src={imagemFundoImportar} alt="" className={styles.imagemFundoImportar}/>
//       </div>
//       <aside className={styles.asideDropzone}>
//         <p>Files:</p>
//         <ul>{files}</ul>
//       </aside>
//     </section>
//   );

  
// }