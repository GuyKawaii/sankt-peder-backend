package com.example.sanktpederbackend.service;

import com.example.sanktpederbackend.model.Foto;
import com.example.sanktpederbackend.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;

    public List<Foto> getFotos() {
        return fotoRepository.findAll();
    }
    public Optional<Foto> getFotoById(Integer id) {
        return fotoRepository.findById(id);
    }
    public ResponseEntity<Foto> putFoto(Foto foto, Integer fotoId) {
        if (doesFotoExist(fotoId)) {
            foto.setId(fotoId);
            Foto updatedFoto = fotoRepository.save(foto);
            return new ResponseEntity<>(updatedFoto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Foto> deleteFoto(Integer fotoId) {
        Optional<Foto> optionalFoto = fotoRepository.findById(fotoId);
        if (doesFotoExist(fotoId)) {
            Foto deleteFoto = optionalFoto.get();
            fotoRepository.deleteById(fotoId);
            return new ResponseEntity<>(deleteFoto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Foto> putFotos(List<Foto> fotos) {
        for (Foto foto : fotos) {
            if (doesFotoExist(foto)) fotoRepository.save(foto);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Foto> postFoto(Foto foto) {
        if (doesFotoExist(foto)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Foto postedFoto = fotoRepository.save(foto);
        return new ResponseEntity<>(postedFoto, HttpStatus.OK);
    }
    private boolean doesFotoExist(Foto foto) {
        Integer fotoId = foto.getId();
        return doesFotoExist(fotoId);
    }

    private boolean doesFotoExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && fotoRepository.existsById(id);
    }
}
