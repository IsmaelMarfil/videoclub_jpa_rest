package org.iesvdm.videoclub.domain;

import java.util.Date;
import java.util.Set;

public class CategoriaDTO extends Categoria{

    public CategoriaDTO(Categoria categoria) {
        super(categoria.getId(), categoria.getNombre(), categoria.getPeliculas(), categoria.getUltimaActualizacion());
        this.conteo=categoria.peliculas.size();
    }
    private int conteo;
}
