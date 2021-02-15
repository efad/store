/**
 * @author Edison Agurto
 */

export class UsuarioModel {
  usuario: string = "";
  idPersona: number = 0;
  nombre: string = "";
  email: string = "";
  identificacion: string = "";
  token: string = "";

  constructor() {
    return { usuario: "", nombre: "", email: "", identificacion: "", token: "", idPersona: 0 };
  }
}
