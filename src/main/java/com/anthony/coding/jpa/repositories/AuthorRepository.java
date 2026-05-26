package com.anthony.coding.jpa.repositories;

import com.anthony.coding.jpa.models.embedded.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNamedQuery(@Param("age") int age);

    @Modifying(clearAutomatically = true)
    @Transactional
    //Update Author a  set a.age = 22 where a.id = 1
    @Query("update Author a set a.age = :age where a.id = :id")
    int updateAuthor(int age, int id);


    @Modifying(clearAutomatically = true)
    @Transactional
    //Update Author a  set a.age = 22 where a.id = 1
    @Query("update Author a set a.age = :age")
    void updateAllAuthorAges(int age);

    @Modifying(clearAutomatically = true)
    @Transactional
    void updateByNamedQuery(@Param("age") int age);

    
    // Fixed: Added 'e' to IgnoreCase
    List<Author> findAllByFirstNameIgnoreCase(String fn);

    // Fixed: Added 'e' to IgnoreCase
    List<Author> findAllByLastNameContainingIgnoreCase(String ln);

    // This one was correct
    List<Author> findAllByLastNameStartsWithIgnoreCase(String ln);

    // Fixed: Removed extra 's' and added 'e'
    List<Author> findAllByLastNameEndsWithIgnoreCase(String ln);

    // Fixed: Corrected name for IN clause or similar logic
    List<Author> findAllByFirstNameIn(List<String> firstNames);

}
