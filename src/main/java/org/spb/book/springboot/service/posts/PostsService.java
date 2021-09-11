package org.spb.book.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.spb.book.springboot.domain.posts.Posts;
import org.spb.book.springboot.domain.posts.PostsRepository;
import org.spb.book.springboot.web.dto.PostsResponseDto;
import org.spb.book.springboot.web.dto.PostsSaveRequestDto;
import org.spb.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. ID :"+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){

        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. ID: "+id));

        return new PostsResponseDto(entity);
    }
}