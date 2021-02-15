import { ConstantesUtil } from './../util/constantes.util';
import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

const SecureStorage = require('secure-web-storage');

/**
 * @author Edison Agurto
 */

@Injectable({
    providedIn: 'root'
})
export class StorageService {

    constructor() { }

    guardarStorage(key: string, value: String) {
        this.secureStorage.setItem(key, value);
    }

    obtenerStorage(key: String) {
        return this.secureStorage.getItem(key);
    }
    eliminarStorage(key: String) {
        return this.secureStorage.removeItem(key);
    }

    private secureStorage = new SecureStorage(localStorage, {
        hash: function hash(key:any) {
            //key = CryptoJS.SHA256(key, ConstantesUtil.LOCAL_STORAGE_CLAVE_ENCRIPTAR);
            return key.toString();
        },
        encrypt: function encrypt(cadena:any) {
            //cadena = CryptoJS.AES.encrypt(cadena, ConstantesUtil.LOCAL_STORAGE_CLAVE_ENCRIPTAR);
            cadena = cadena.toString();
            return cadena;
        },
        decrypt: function decrypt(cadena:any) {
            //cadena = CryptoJS.AES.decrypt(cadena, ConstantesUtil.LOCAL_STORAGE_CLAVE_ENCRIPTAR);
            //cadena = cadena.toString(CryptoJS.enc.Utf8);
            return cadena;
        }
    });
}