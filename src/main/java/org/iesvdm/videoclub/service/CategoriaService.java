package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public List<Categoria> allByQueryFiltersStream(Optional<String> buscarOpcion, Optional<String> ordenarOpcional){
        List<Categoria> resultado = null;
    if(buscarOpcion.isPresent()){
        resultado = categoriaRepository.findByNombreContainingIgnoreCase(buscarOpcion.get());
    }
    if(ordenarOpcional.isPresent()){
           if(buscarOpcion.isPresent() && "asc".equalsIgnoreCase(ordenarOpcional.get())){
               resultado = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpcion.get());
           } else if (buscarOpcion.isPresent() && "desc".equalsIgnoreCase(ordenarOpcional.get())) {
               resultado = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpcion.get());
           }
    }
    return resultado;

    }


}
