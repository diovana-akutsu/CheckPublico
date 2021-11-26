import styles from './Div.module.css'

export const Div = ({ children, className = '', variant, ...otherProps }) => {
    let variantClass = ''

    switch (variant) {
        case 'PRIMARY':
            variantClass = styles.divPrimary
            break

        case 'CINZA':
            variantClass = styles.divCinza
            break

        case 'COMENTARIO':
            variantClass = styles.divComentario
            break
    }

    return <div {...otherProps} className={`${className} ${styles.divs} ${variantClass}`}>{children}</div>
}