package com.example.sanktpederbackend.service;

import com.example.sanktpederbackend.model.MenuCard;
import com.example.sanktpederbackend.repository.MenuCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuCardService {

    @Autowired
    private MenuCardRepository menuCardRepository;

    public List<MenuCard> getMenuCards() {
        return menuCardRepository.findAll();
    }

    public Optional<MenuCard> getMenuCardById(Integer id) {
        return menuCardRepository.findById(id);
    }

    public ResponseEntity<MenuCard> putMenuCard(MenuCard menuCard, Integer menuCardId) {
        if (doesMenuCardExist(menuCardId)) {
            menuCard.setId(menuCardId);
            MenuCard updatedMenuCard = menuCardRepository.save(menuCard);
            return new ResponseEntity<>(updatedMenuCard, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<MenuCard> deleteMenuCard(Integer menuCardId) {
        Optional<MenuCard> optionalMenuCard = menuCardRepository.findById(menuCardId);
        if (doesMenuCardExist(menuCardId)) {
            MenuCard deletedMenuCard = optionalMenuCard.get();
            menuCardRepository.deleteById(menuCardId);
            return new ResponseEntity<>(deletedMenuCard, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<MenuCard> postMenuCard(MenuCard menuCard) {
        MenuCard postedMenuCard = menuCardRepository.save(menuCard);
        return new ResponseEntity<>(postedMenuCard, HttpStatus.CREATED);
    }

    private boolean doesMenuCardExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && menuCardRepository.existsById(id);
    }
}
