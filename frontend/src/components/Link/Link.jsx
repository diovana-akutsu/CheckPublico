import styles from './Link.module.css'

export const LinkComponent = ({ children, className = '', variant, ...otherProps }) => {
    let variantClass = ''

    switch (variant) {
        case 'CONTATO':
            variantClass = styles.linkContato
            break

        case 'CADASTRO':
            variantClass = styles.linkCadastro
            break

        case 'LOGIN':
            variantClass = styles.linkLogin
            break

        case 'BRANCO':
            variantClass = styles.linkBranco
            break
    }

    return <a {...otherProps} className={`${className} ${styles.links} ${variantClass}`}>{children}</a>
}