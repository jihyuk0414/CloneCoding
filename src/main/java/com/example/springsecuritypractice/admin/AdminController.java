package com.example.springsecuritypractice.admin;

import com.example.springsecuritypractice.notes.Note;
import com.example.springsecuritypractice.notes.NoteService;
import com.example.springsecuritypractice.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final NoteService noteService ;

    @GetMapping
    public String getNoteForAdmin(Authentication authentication, Model model)
    {
        User user = (User) authentication.getPrincipal() ;
        List<Note> notes = noteService.findByUser(user);
        model.addAttribute("notes", notes) ;
        return "admin/index";
    }
}
