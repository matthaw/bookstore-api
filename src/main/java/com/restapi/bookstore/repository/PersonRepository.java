package com.restapi.bookstore.repository;

import com.restapi.bookstore.data.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("update Person set enabled = false where id =:id")
    void disablePersons(@Param("id") Long id);

    @Query(value = "select p from Person p where p.firstName like lower(concat('%', :firstName, " +
                   "'%'))", nativeQuery = false)
    Page<Person> findPersonByName(@Param("firstName") String firstName, Pageable pageable);
}