import { SeguridadService } from './../services/seguridad.service';
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";



@Injectable({
  providedIn: "root",
})
export class AutenticacionGuard implements CanActivate {
  constructor(
    private seguridadService: SeguridadService,
    private router: Router
  ) { }

  canActivate(next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {
    if (!this.seguridadService.estaAutenticado()) {
      this.router.navigateByUrl("/login");
      return false;
    } else if (state.url === '/principal') {
      return true;
    } else if (this.seguridadService.tienePermisoComponente(state.url)) {

      return true;
    } else {
      this.router.navigateByUrl("/principal");
    }
    return false;

  }

}


