package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.model.Foto;
import com.example.sanktpederbackend.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
public class FotoController {

    @Autowired
    FotoService fotoService;

    @GetMapping("/fotos")
    public List<Foto> getFotos() {
        return fotoService.getFotos();
    }

    @GetMapping("/fotos/{id}")
    public Foto getFotoById(@PathVariable Integer id) {
        return fotoService.getFotoById(id).orElseThrow(() -> new RuntimeException("Foto with id: " + id + " not found"));
    }

    @PostMapping("/foto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Foto> postFoto(@RequestBody Foto foto) {
        return fotoService.postFoto(foto);
    }

    @PutMapping("/foto/{id}")
    public ResponseEntity<Foto> putItem(@PathVariable Integer id, @RequestBody Foto foto) {
        return fotoService.putFoto(foto, id);
    }

    // put multiple items
    @PutMapping("/foto")
    public ResponseEntity<Foto> putItems(@RequestBody List<Foto> fotos) {
        return fotoService.putFotos(fotos);
    }



    @DeleteMapping("/foto/{id}")
    public ResponseEntity<Foto> deleteShop(@PathVariable Integer id) {
        return fotoService.deleteFoto(id);
    }

}
