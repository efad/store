import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';


import { MenuItem } from 'app/core/models/menu-item.model';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { SesionService } from 'app/core/services/sesion.service';
import { Router } from '@angular/router';
import { StorageService } from 'app/core/services/storage.service';
import { ConstantesUtil } from 'app/core/util/constantes.util';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class AppSidebarComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;
  treeControl = new NestedTreeControl<MenuItem>(node => node.lstMenuHijos);
  dataSource = new MatTreeNestedDataSource<MenuItem>();

  private _mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    public srvMenuUsuario: SesionService,
    private router: Router,
    private storageService: StorageService,
  ) {
    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit() {
    this.cargarMenu();
  }

  /**
   * Permite obtener el menu desde el servicio web o desde el local storage
   */
  cargarMenu() {
    const menuUsuarioString = this.storageService.obtenerStorage(ConstantesUtil.LOCAL_STORAGE_MENU_USUARIO);
    if (null !== menuUsuarioString) {
      this.dataSource.data = JSON.parse(menuUsuarioString)
    } else {
      this.srvMenuUsuario.obtenerMenuUsuario().subscribe(r => {
        this.dataSource.data = r;
        this.storageService.guardarStorage(ConstantesUtil.LOCAL_STORAGE_MENU_USUARIO, JSON.stringify(r));
      });
    }
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  hasChild = (_: number, node: MenuItem) => !!node.lstMenuHijos && node.lstMenuHijos.length > 0;

  redireccionarMenu(componente: MenuItem) {
    if ('' !== componente.controlador) {
      this.router.navigate([`principal/${componente.controlador}`]);
    } else {
      this.router.navigate(['principal']);
    }
  }
}
