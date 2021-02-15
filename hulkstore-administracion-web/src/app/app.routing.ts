import { AutenticacionGuard } from './core/guards/autenticacion.guard';
import { LoginComponent } from './components/login/login.component';
import { Routes } from '@angular/router';

import { FullComponent } from './shared/layouts/full/full.component';
import { AdministracionComponent } from './components/administracion/administracion/administracion.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: FullComponent,
    
    children: [
      {
        path: '',
        canActivate: [AutenticacionGuard],
        redirectTo: '/principal',
        pathMatch: 'full'
      },
     
      {
        path: 'principal',
        canActivate: [AutenticacionGuard],
         component: AdministracionComponent
      }
    ]
  },{
    path: 'login',
    component: LoginComponent
  },
  {
    path: '**',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];
