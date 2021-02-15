import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';
import { element } from 'protractor';

/**
 * @author Edison Agurto
 */

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  constructor(private http: HttpClient) { }

  getURL(query: string){
    const url = `${environment.urlInventarioServicio}/${query}`;
   
    return this.http.get(url);
  }

  postURL(query: string){
    const url = `${environment.urlInventarioServicio}/${query}`;
    const data = {
 
      returnSecureToken: true
    };
    return this.http.post(url,data);
  }

  putURL(query: string){
    const url = `${environment.urlInventarioServicio}/${query}`;
    const data = {
 
      returnSecureToken: true
    };
    return this.http.post(url,data);
  }

  getProductos(termino: string) {
    return this.getURL(`producto/${termino}`);
  }

  getProducto(termino: string) {
    return this.getURL(`producto/id/${termino}`);
  }

  actualizarProducto(termino: string) {
    return this.putURL(`producto/actualizar/id/${termino}`);
  }

  crearProducto(termino: string) {
    return this.postURL(`producto/actualizar/id/${termino}`);
  }

  verificarExistenciaProducto(termino: string) {
    return this.getURL(`producto/verificarExistencia/id/${termino}`);
  }
}
