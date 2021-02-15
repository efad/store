import { Component, HostListener, OnInit } from '@angular/core';
import { ConstantesUtil } from 'app/core/util/constantes.util';
import { Observable, Observer } from 'rxjs';
import { SesionService } from '../../../../core/services/sesion.service';
import { environment } from './../../../../../environments/environment';
import { StorageService } from './../../../../core/services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: [],
})
export class AppHeaderComponent implements OnInit {

  fotoUsuarioBase64: any;

  constructor(public sesionService: SesionService,
    private storageService: StorageService) {
  }

  @HostListener('window:unload', ['$event'])
  onWindowCloseUn(event: any) {
    this.storageService.guardarStorage(ConstantesUtil.LOCAL_STORAGE_CIERRE_PESTANA, "" + Date.now());
  }

  salir() {
    this.sesionService.salir();
  }

  ngOnInit() {
    let verificarRefrescoPagina: boolean = true;
    if (this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_CIERRE_PESTANA) != null) {
      let fechaAnterior = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_CIERRE_PESTANA) || 0;
      if (fechaAnterior < (Date.now() - 2000)) {
        this.storageService.eliminarStorage(ConstantesUtil.LOCAL_STORAGE_CIERRE_PESTANA)
        this.salir();
        verificarRefrescoPagina = false;
      }
    }

    if (verificarRefrescoPagina) {
      const usuarioString = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_USUARIO);
      if (usuarioString != null) {
        this.sesionService.usuario = JSON.parse(usuarioString);
      }
      this.sesionService.inicializarIdleSesion();
      console.log(this.sesionService.usuario.identificacion);
      //const imageUrl = `${environment.urlSeguridades}/usuario/foto/${this.sesionService.usuario.identificacion}`;
      const imageUrl = `assets/images/hulk-store-logo.png`;
      this.getBase64ImageFromURL(imageUrl).subscribe((base64data: string) => {
        this.fotoUsuarioBase64 = 'data:image/jpg;base64,' + base64data;
      });
    }
  }

  getBase64ImageFromURL(url: string) {
    return Observable.create((observer: Observer<string>) => {
      const img = new Image();
      img.crossOrigin = 'Anonymous';
      img.src = url;
      if (!img.complete) {
        img.onload = () => {
          observer.next(this.getBase64Image(img));
          observer.complete();
        };
        img.onerror = (err) => {
          observer.error(err);
        };
      } else {
        observer.next(this.getBase64Image(img));
        observer.complete();
      }
    });
  }

  getBase64Image(img: HTMLImageElement) {
    const canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;
    const ctx = canvas.getContext('2d');
    if (null != ctx) {
      ctx.drawImage(img, 0, 0);
    }
    const dataURL = canvas.toDataURL('image/png');
    return dataURL.replace(/^data:image\/(png|jpg);base64,/, '');
  }

}
