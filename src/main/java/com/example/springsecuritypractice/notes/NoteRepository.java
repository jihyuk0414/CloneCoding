package com.example.springsecuritypractice.notes;

import com.example.springsecuritypractice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserOrderByIdDesc(User user);

    Note findByUserAndId(User user,Long id) ;


}
