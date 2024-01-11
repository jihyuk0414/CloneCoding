package com.example.springsecuritypractice.notice;

import com.example.springsecuritypractice.notes.Note;
import com.example.springsecuritypractice.notes.NoteRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService ;

    @GetMapping
    public String getNotice(Model model)
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        List<Notice> notices =  noticeService.findAll();
        model.addAttribute("notices", notices) ;
        return "notice/index" ;
    }

    @PostMapping
    public String PostNotice(@ModelAttribute NoteRegisterDto noteDto)
    {
        noticeService.saveNotice(noteDto.getTitle(), noteDto.getContent()) ;
        return "redirect:notice" ;

    }

    @DeleteMapping
    public String DeleteNotice(@RequestParam Long id)
    {
        noticeService.deleteNotice(id);
        return "redirect:notice" ;
    }

}
