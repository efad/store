import { SesionService } from 'app/core/services/sesion.service';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private sesionService: SesionService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.sesionService.usuario.usuario != null) {
            request = request.clone({
                setHeaders: { Authorization: `${this.sesionService.usuario.token}` }
            });
        }
        return next.handle(request);
    }

}