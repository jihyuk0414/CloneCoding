package com.example.springsecuritypractice.notes;

import com.example.springsecuritypractice.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/note")
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public String getNote(Authentication authentication, Model model)
    {
        User user = (User) authentication.getPrincipal() ;
        List<Note> notes = noteService.findByUser(user);
        model.addAttribute("notes", notes) ;
        return "note/index" ;

    }

    @PostMapping
    public String saveNote(Authentication authentication, @ModelAttribute NoteRegisterDto noteDto)
    {
        User user = (User) authentication.getPrincipal() ;
        noteService.saveNote(user,noteDto.getTitle(),noteDto.getContent());
        return "redirect:note" ;
    }

    @DeleteMapping
    public String deleteNote(Authentication authentication, @RequestParam(value = "id") Long id)
    {
        User user = (User) authentication.getPrincipal();
        noteService.deleteNote(user,id);
        log.info("{}", id);

        return "redirect:note" ;
    }
}
