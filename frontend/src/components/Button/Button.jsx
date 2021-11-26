import styles from './Button.module.css'

export const Button = ({ children, className = '', variant, onClick,...otherProps }) => {
    let variantClass = ''

    switch (variant) {
        case 'PRIMARY':
            variantClass = styles.buttonPrimary
            break

        case 'SECUNDARY':
            variantClass = styles.buttonSecundary
            break

        case 'TRANSPARENTE':
            variantClass = styles.buttonTransparente
            break

        case 'REDONDOAZUL':
            variantClass = styles.buttonRedondoAzul
            break
    }

    return <button onClick={onClick} {...otherProps} className={`${className} ${styles.buttons} ${variantClass}`}>{children}</button>
}