package com.gssg.gssgbe.domain.post.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gssg.gssgbe.domain.member.entity.Member;
import com.gssg.gssgbe.domain.post.dto.reponse.PostDto;
import com.gssg.gssgbe.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindPostService {

	private final PostRepository postRepository;

	public Slice<PostDto> findAll(final Pageable pageable) {
		return postRepository.findAllSlice(pageable)
			.map(PostDto::new);
	}

	public Slice<PostDto> findAll(final Member loginMember, final Pageable pageable) {
		return postRepository.findAllSlice(pageable)
			.map(post -> new PostDto(post, post.isLike(loginMember)));
	}

	public Slice<PostDto> findByMember(final Member loginMember, final Pageable pageable) {
		return postRepository.findAllByMember(loginMember, pageable)
			.map(PostDto::new);
	}
}
