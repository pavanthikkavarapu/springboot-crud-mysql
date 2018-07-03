package com.example.basicapp.controller;

import com.example.basicapp.exception.ResourceNotFoundException;
import com.example.basicapp.model.Data;
import com.example.basicapp.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    DataRepository dataRepository;

    @GetMapping("/demo")
    public List<Data> getAllNotes() {
        return dataRepository.findAll();
    }

    @PostMapping("/demo")
    public String createNote(@Valid @RequestBody Data data) {
        dataRepository.save(data);
        return "success";
    }

    @GetMapping("/demo/{id}")
    public Data getNoteById(@PathVariable(value = "id") Long noteId) {
        return dataRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Data", "id", noteId));
    }

  @PutMapping("/demo/{id}")
    public Data updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Data noteDetails) {

        Data note = dataRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Data", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Data updatedNote = dataRepository.save(note);
        return updatedNote;
    }

  @DeleteMapping("/demo/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Data note = dataRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Data", "id", noteId));

        dataRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
