import { ConstantesUtil } from 'app/core/util/constantes.util';
import { StorageService } from './storage.service';
import { UsuarioModel } from './../models/usuario.model';
import { Router } from "@angular/router";
import { SeguridadService } from "./seguridad.service";
import { UserIdleService } from "angular-user-idle";
import { Injectable } from "@angular/core";
import Swal from "sweetalert2";
import { HttpClient } from '@angular/common/http';
import { MenuItem } from 'app/core/models/menu-item.model';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';

/**
 * @author Edison Agurto
 */

@Injectable({
  providedIn: "root",
})
export class SesionService {
  usuario: UsuarioModel = new UsuarioModel();

  constructor(
    private seguridadService: SeguridadService,
    private usuarioIdle: UserIdleService,
    private router: Router,
    private http: HttpClient,
    private storageService: StorageService
  ) { }

  inicializarIdleSesion() {

    this.usuarioIdle.startWatching();


    this.usuarioIdle.onTimerStart().subscribe((conteoSegundos) => {
      let segundosRestantes =
        (this.usuarioIdle.getConfigValue().timeout || 0) - conteoSegundos;
      if (conteoSegundos === 1) {
        Swal.fire({
          title: "Su sesión está a punto de caducar",
          text: `La sesión se cerrará en : ${segundosRestantes} segundos.`,
          icon: "warning",
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Extender la sesión actual",
          allowOutsideClick: false,
        }).then((result) => {
          if (result.isConfirmed) {
            this.usuarioIdle.stopTimer();
          }
        });
      } else {
        if (segundosRestantes != 0) {
          Swal.update({
            text: `La sesión se cerrará en : ${segundosRestantes} segundos.`,
          });
        }
      }
    });

    this.usuarioIdle.onTimeout().subscribe(() => {
      this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN);
      this.seguridadService.userToken = "";
      Swal.fire({
        title: "Sesión expirada",
        icon: "info",
        confirmButtonColor: "#3085d6",
        confirmButtonText: "Continuar",
        allowOutsideClick: false,
      }).then((result) => {
        if (result.isConfirmed) {
          this.salir();
        }
      });
    });

    //ping de verificación
    this.usuarioIdle.ping$.subscribe();
  }

  salir() {
    this.usuarioIdle.stopTimer();
    this.usuarioIdle.stopWatching();
    this.eliminarElementosLocalStorage();
    this.router.navigateByUrl("/login");
  }

  private eliminarElementosLocalStorage() {
    this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN);
    this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_USUARIO);
    this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_MENU_USUARIO);
    this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_CIERRE_PESTANA);
  }


  public obtenerMenuUsuario(): Observable<MenuItem[]> {
    let urlMenu = `${environment.urlSeguridades}/menu/v1.2?idPersona=${this.usuario.idPersona}&codigoAplicacion=${ConstantesUtil.CODIGO_APLICACION}`; 
    return this.http.get<MenuItem[]>(urlMenu);
  }

}
