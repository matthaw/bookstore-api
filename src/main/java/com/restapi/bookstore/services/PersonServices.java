package com.restapi.bookstore.services;

import com.restapi.bookstore.converter.DozerConverter;
import com.restapi.bookstore.data.model.Person;
import com.restapi.bookstore.data.vo.v1.PersonVO;
import com.restapi.bookstore.exception.ResourceNotFoundException;
import com.restapi.bookstore.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return (PersonVO) vo;
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        var pages = repository.findAll(pageable);

        return pages.map(this::convertToVO);
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        var pages = repository.findPersonByName(firstName, pageable);

        return pages.map(this::convertToVO);
    }

    private PersonVO convertToVO(Person person) {
        return DozerConverter.parseObject(person, PersonVO.class);
    }

    public PersonVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePersons(id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }


    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
