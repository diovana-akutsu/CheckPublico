import { Children } from 'react'
import styles from './Input.module.css'

export const Input = ({ className = ' ', variant, ...otherProps }) => {
    let variantClass = ''

    switch (variant) {
        case 'PRIMARY':
            variantClass = styles.inputPrimary
            break

        case 'CHECKBOX':
            variantClass = styles.inputCheckbox
            break

        case 'LOGININPUT':
            variantClass = styles.inputLogin
            break

        case 'AZULTRANSPARENTE':
            variantClass = styles.inputAzulTransparente
            break

        case 'AZULCLARO':
            variantClass = styles.inputAzulClaro
            break
    }

    return (
        <input {...otherProps} className={`${className} ${styles.input} ${variantClass}`} />
    );
}