package com.wineexchange.api.services;

import com.wineexchange.api.domain.Tag;
import com.wineexchange.api.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagRepository tagRepository;
    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }
}
