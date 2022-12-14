package com.example.mainservice.service.serviceImplementation;

import com.example.mainservice.dto.TagDTO;
import com.example.mainservice.entity.Tag;
import com.example.mainservice.facade.TagFacade;
import com.example.mainservice.repository.TagRepository;
import com.example.mainservice.service.serviceInterface.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceI implements TagService {
    private final TagRepository tagRepository;
    private final TagFacade tagFacade;

    @Override
    public Optional<Tag> findTagByTagName(String tagName) {
        return Optional.empty();
    }

    @Override
    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public TagDTO getByTagName(String tagName) {
        return tagFacade.convertTagToDTO(tagRepository.findByTagName(tagName));
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagFacade::convertTagToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Page<TagDTO> findAllTags(Pageable pageable, int pageNumber, String direction, String sort) {
        Pageable changePageable = PageRequest.of(pageNumber - 1, pageable.getPageSize()
                ,direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        Page<Tag> tagPage = tagRepository.findAll(changePageable);
        return null;
    }

    @Override
    public Page<TagDTO> findAllUserPreferTags(String login, Pageable pageable, int pageNumber, String direction, String sort) {
        Pageable changePageable = PageRequest.of(pageNumber - 1, pageable.getPageSize()
                ,direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        Page<Tag> tagPage = tagRepository.findTagsByUserTagSet_Login(login,changePageable);
        return null;
    }

    @Override
    public void setTagValidity(String tagName) {

    }

    @Override
    @Transactional
    public void deleteTagByTagName(String tagName) {
        tagRepository.deleteTagByTagName(tagName);
    }

//    private TagDTO convertTagToDTO(Tag tag) {
//        TagDTO tagDTO = new TagDTO();
//        tagDTO.setId(tag.getId());
//        tagDTO.setCreated(tag.getCreated());
//        tagDTO.setUpdated(tag.getUpdated());
//        tagDTO.setCreatedBy(tag.getCreatedBy());
//        tagDTO.setLastModifiedBy(tag.getLastModifiedBy());
//
//        tagDTO.setEnable(true);
//        tagDTO.setUserDTOSet();
//
//        return tagDTO;
//    }
}
