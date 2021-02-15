import { StorageService } from './storage.service';
import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';

import { environment } from './../../../environments/environment';
import { UsuarioModel } from './../models/usuario.model';
import { ConstantesUtil } from './../util/constantes.util';
import { MenuItem } from '../models/menu-item.model';

/**
 * @author Edison Agurto
 */

@Injectable({
  providedIn: 'root'
})
export class SeguridadService {
  userToken: String = "";

  constructor(private http: HttpClient, private storageService: StorageService) {
    this.leerToken();
  }

  logIn(usuario: String, password: String) {
    const loginRequest = {
      usuario: usuario,
      password: password,
      codigoAplicacion: ConstantesUtil.CODIGO_APLICACION,
    };
    return this.http
      .post<UsuarioModel>(`${environment.urlSeguridades}/login`, loginRequest);
  }

  guardarToken(idToken: string) {
    this.userToken = idToken;
    this.storageService.guardarStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN, idToken);
  }

  leerToken() {
    if (this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN)) {
      this.userToken = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN) || "";
    } else {
      this.userToken = "";
    }
    return this.userToken;
  }
  /**
   * 
   */
  estaAutenticado() {
    //se puede verficar por fecha la expiración del token, cuando ingresa se pone una fecha
    if (this.userToken == null || this.userToken.length < 5) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Permite identificar si el usuario tiene permiso al componente que intenta acceder.
   * @param componente 
   */
  tienePermisoComponente(nmbComponente: String): Boolean {
    const menuUsuarioString = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_MENU_USUARIO);
    let bandera: Boolean = false;
    let i = 0;

    if (null != menuUsuarioString) {
      let menuUsuarioJSON = JSON.parse(menuUsuarioString)
      do {
        bandera = this.tienePermisoComponenteHijos(menuUsuarioJSON[i], nmbComponente);
        i++;
      } while (!bandera && menuUsuarioJSON.length > i)
    }
    return bandera;

  }

  /**
   * 
   * @param nodo 
   * @param nmbComponente 
   */
  tienePermisoComponenteHijos(nodo: MenuItem, nmbComponente: String): Boolean {
    let bandera: Boolean = false;
    if (nmbComponente.endsWith(nodo.controlador) || (nmbComponente.endsWith('principal'))) {
      bandera = true;
    } else if (nodo.lstMenuHijos.length !== 0) {
      let i = 0;
      do {
        bandera = this.tienePermisoComponenteHijos(nodo.lstMenuHijos[i], nmbComponente);
        i++;
      } while (!bandera && nodo.lstMenuHijos.length > i)

    }
    return bandera;

  }





}