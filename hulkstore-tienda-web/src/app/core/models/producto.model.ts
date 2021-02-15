/**
 * @author Edison Agurto
 */

export class ProductoModel {
  id: number = 0;
  descripcion: string = "";
  estado: boolean = false;
  nombre: string = "";
  existencia: string = "";
  valor: number = 0;


  constructor() {
    return { id: 0, descripcion: "", estado: false, nombre: "", existencia: "", valor: 0 };
  }
}
