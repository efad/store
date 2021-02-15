import { Component, OnInit } from '@angular/core';
import { ProductoModel } from '../../../core/models/producto.model';
import { InventarioService } from '../../../services/inventario.service';

/**
 * @author Edison Agurto
 */

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent implements OnInit {

  displayedColumns: string[] = ['id', 'descripcion', 'estado', 'nombre', 'existencia', 'valor'];
 
  productos: ProductoModel[] = []; 
  dataSource = this.productos;

  constructor(private inventario: InventarioService) { }

  ngOnInit(): void {
    this.inventario.getProductos('todos').subscribe(
      (data: any) => {
        this.productos = data;
        this.dataSource = this.productos;
        
      });
  }

}
