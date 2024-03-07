package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.CategoriaDTO;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.CategoriaService;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamano"})
    public List<CategoriaDTO> all() {
        log.info("Accediendo a todas las categorías");
        return this.categoriaService.all();
    }
    @GetMapping(value = {"", "/"}, params={"!buscar", "!ordenar"} )
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                   @RequestParam(value="tamano", defaultValue = "3") int tamano){
        log.info("Accediendo a todas las categorías con paginación");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamano);
        return ResponseEntity.ok(responseAll);
    }
    @GetMapping(value = {"","/"})
    public List<Categoria> all(@RequestParam("buscar") Optional<String> buscarOptional,
                               @RequestParam("ordenar") Optional<String> ordenarOptional
    ) {
        log.info("Accediendo a todas las categorías con filtro buscar: %s y ordenar: %s" +
        buscarOptional.orElse("VOID"), ordenarOptional.orElse("VOID")
        );
        return this.categoriaService.allByQueryFiltersStream(buscarOptional, ordenarOptional);
    }

    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }


}