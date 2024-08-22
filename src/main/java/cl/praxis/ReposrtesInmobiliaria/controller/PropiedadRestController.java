package cl.praxis.ReposrtesInmobiliaria.controller;

import cl.praxis.ReposrtesInmobiliaria.model.entities.Propiedades;
import cl.praxis.ReposrtesInmobiliaria.model.service.PropiedadesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadRestController {
    private final PropiedadesService service;

    public PropiedadRestController(PropiedadesService service) {
        this.service = service;
    }

    private HttpStatus status = HttpStatus.OK;

    @GetMapping
    public ResponseEntity<List<Propiedades>> findAll(){
        List<Propiedades> actors = service.findAll();
        if(actors == null || actors.isEmpty()){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(actors, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propiedades> findOne(@PathVariable("id") int id){
        Propiedades propiedades = service.findOne(id);
        if(propiedades == null){
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(propiedades, status);
    }

    @PostMapping
    public ResponseEntity<Propiedades> create(@RequestBody Propiedades p){
        Propiedades propiedades = service.create(p);
        if(propiedades == null){
            status = HttpStatus.CONFLICT;
        }else{
            status = HttpStatus.CREATED;
        }
        return new ResponseEntity<>(propiedades, status);
    }

    @PutMapping
    public ResponseEntity<Propiedades> update(@RequestBody Propiedades p){
        Propiedades propiedades = service.update(p);
        if(propiedades == null){
            status = HttpStatus.CONFLICT;
        }else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(propiedades, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = service.delete(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
