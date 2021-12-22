package com.restapi.bookstore.controller;

import com.restapi.bookstore.data.vo.v1.PersonVO;
import com.restapi.bookstore.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@Tag(name = "Person controller")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    @Operation(summary = "Find all people")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<CollectionModel<PersonVO>> findAll(@RequestParam(value = "page",
                                                                           defaultValue =
                                                                                   "0") int page,
                                                             @RequestParam(value = "limit", defaultValue = "12") int limit,
                                                             @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(
                direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = service.findAll(pageable);
        persons.stream().forEach(person -> {
            person.add(linkTo(methodOn(PersonController.class).findById(
                    person.getKey())).withSelfRel());
        });

        return ResponseEntity.ok(CollectionModel.of(persons));
    }

    @Operation(summary = "Find people by firstName")
    @GetMapping(value = "/findPersonByName/{firstName}", produces = {"application/json",
                                                                     "application/xml",
                                                                     "application/x-yaml"})
    public ResponseEntity<CollectionModel<PersonVO>> findPersonByName(@PathVariable("firstName") String firstName,
                                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                                      @RequestParam(value = "limit", defaultValue = "12") int limit,
                                                                      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(
                direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = service.findPersonByName(firstName, pageable);
        persons.stream().forEach(person -> {
            person.add(linkTo(methodOn(PersonController.class).findById(
                    person.getKey())).withSelfRel());
        });

        return ResponseEntity.ok(CollectionModel.of(persons));
    }

    @Operation(summary = "Find people by your ID")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml",
                                             "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO personVO = service.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Create a new people")
    @PostMapping(produces = {"application/json", "application/xml",
                             "application/x-yaml"}, consumes = {"application/json",
                                                                "application/xml",
                                                                "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = service.create(person);
        personVO.add(
                linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Update a specific people")
    @PutMapping(produces = {"application/json", "application/xml",
                            "application/x-yaml"}, consumes = {"application/json",
                                                               "application/xml",
                                                               "application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = service.update(person);
        personVO.add(
                linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Disable people by your a ID")
    @PatchMapping(value = "/{id}", produces = {"application/json", "application/xml",
                                               "application/x-yaml"})
    public PersonVO disablePerson(@PathVariable("id") Long id) {
        PersonVO personVO = service.disablePerson(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @Operation(summary = "Delete a specific people by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}