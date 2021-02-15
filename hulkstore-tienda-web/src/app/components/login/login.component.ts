import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import Swal from "sweetalert2";
import { SeguridadService } from './../../core/services/seguridad.service';
import { SesionService } from './../../core/services/sesion.service';
import { StorageService } from './../../core/services/storage.service';
import { ConstantesUtil } from './../../core/util/constantes.util';

/**
 * @author Edison Agurto
 */

@Component({
  selector: "app-login",
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService],
})
export class LoginComponent implements OnInit {
  usuario = "";
  password = "";
  recordarUsuario = false;

  constructor(
    private seguridadService: SeguridadService,
    private router: Router,
    private sesionService: SesionService,
    private storageService: StorageService,
    private messageService: MessageService,
    private primengConfig: PrimeNGConfig
  ) { }

  ngOnInit() {
    if (this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_TOKEN) != null) {
      this.router.navigateByUrl("/principal");
    }

    if (this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_RECORDAR_USUARIO)) {
      this.usuario = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_RECORDAR_USUARIO);
      this.recordarUsuario = true;
    }
  }


  login(form: NgForm) {
    if (form.invalid) {
      return;
    }

    Swal.fire({
      allowOutsideClick: false,
      icon: "info",
      text: "Espere por favor",
    });
    Swal.showLoading();

    this.seguridadService.logIn(this.usuario, this.password).subscribe(
      (respuesta) => {
        this.storageService.guardarStorage(ConstantesUtil.LOCAL_STORAGE_USUARIO, JSON.stringify(respuesta));
        this.seguridadService.guardarToken(respuesta.token);

        Swal.close();
        if (this.recordarUsuario) {
          this.storageService.guardarStorage(ConstantesUtil.LOCAL_STORAGE_RECORDAR_USUARIO, this.usuario);
        }

        this.sesionService.inicializarIdleSesion();
        this.router.navigateByUrl("/principal");
      },
      (err) => {
        let mensaje =
          "Error al ingresar a la aplicación, comuníquese a los números de soporte.";
        if (err.status === 400) {
          mensaje = err.error;
        } else {
          console.log("Error al conectarse al servicio: " + err.message);
        }
        Swal.close();
        // Swal.fire({
        //   icon: "error",
        //   title: mensaje,
        // });
        this.messageService.add({
          severity: "error",
          summary: "Error",
          detail: mensaje,
          life:5000,
        });
      }
    );
  }
}
