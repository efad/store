import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AdministracionRoutes } from './administracion.routing';
import { AdministracionComponent } from './administracion/administracion.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DemoMaterialModule } from 'app/demo-material-module';

/**
 * @author Edison Agurto
 */

@NgModule({
  imports: [
    CommonModule, FlexLayoutModule,
    DemoMaterialModule,
    RouterModule.forChild(AdministracionRoutes),
  ], declarations: [AdministracionComponent]
})
export class AdministracionModule { }
