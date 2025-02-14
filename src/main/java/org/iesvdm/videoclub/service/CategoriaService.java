package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.CategoriaDTO;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> all() {

        List <Categoria> listaCat = this.categoriaRepository.findAll();

        List<CategoriaDTO> listDTO = listaCat.stream()
                .map(CategoriaDTO::new)
                .toList();

        return listDTO;

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
    public Map<String, Object> all(int pagina, int tamano){
        Pageable paginado = PageRequest.of(pagina, tamano, Sort.by("id").ascending());
        Page<Categoria> pageAll = this.categoriaRepository.findAll(paginado);

        Map<String,Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());
        return response;

    }



}
