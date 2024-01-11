package com.example.springsecuritypractice.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository ;

    @Transactional(readOnly = true)
    public List<Notice> findAll()
    {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public Notice saveNotice(String title, String content)
    {
        return noticeRepository.save(new Notice(title,content)) ;
    }

    public void deleteNotice(Long id)
    {
        if (id == null)
        {
            throw new NoticeNotFoundException();
        }
        noticeRepository.deleteById(id);
    }



}
