export class MenuItem {
    id: number;
    nombre: string;
    orden: number;
    url: string;
    icono: string;
    lstMenuHijos: MenuItem[];
    controlador: string;
    menuMetodo: string;

    constructor() {
        this.id = 0;
        this.nombre = '';
        this.orden = 0;
        this.url = '';
        this.icono = '';
        this.lstMenuHijos = [];
        this.controlador = '';
        this.menuMetodo = '';
    }


    esLink(): Boolean {
        return false;
    }


}
