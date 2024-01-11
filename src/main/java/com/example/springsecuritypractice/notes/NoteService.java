package com.example.springsecuritypractice.notes;

import com.example.springsecuritypractice.user.User;
import com.example.springsecuritypractice.user.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository ;

    @Transactional(readOnly = true)
    public List<Note> findByUser(User user)
    {
        if (user == null)
        {
            throw new UserNotFoundException() ;
        }
        if (user.isAdmin())
        {
            return noteRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        }
        return noteRepository.findByUserOrderByIdDesc(user);
    }

    public Note saveNote(User user, String title, String content)
    {
        if(user == null)
        {
            throw new UserNotFoundException();
        }
        return noteRepository.save(new Note(title,content,user)) ;
    }

    public void deleteNote(User user, Long noteid)
    {
        if(user==null)
        {
            throw new UserNotFoundException();
        }
        Note note = noteRepository.findByUserAndId(user,noteid);
        if (note!= null)
        {
            log.info("Deleted note with id: {}", note.getId());
            noteRepository.delete(note);
        }

    }

}
