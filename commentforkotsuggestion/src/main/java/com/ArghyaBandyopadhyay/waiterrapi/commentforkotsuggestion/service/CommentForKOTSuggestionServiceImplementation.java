package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.service;


import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.CommentForKOTSuggestion;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.repository.CommentForKOTSuggestionRepository;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentForKOTSuggestionServiceImplementation implements CommentForKOTSuggestionService {

    @Autowired
    CommentForKOTSuggestionRepository commentForKOTSuggestionRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public CommentForKOTSuggestionServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public CommentForKOTSuggestion addCommentOnKot(CommentForKOTSuggestion commentForKOTSuggestion) throws CommentForKOTSuggestionAlreadyExistsException {
        CommentForKOTSuggestion added = null;
        try{
            if(commentForKOTSuggestion.getId()==null)throw new EntityNotFoundException();
            commentForKOTSuggestionRepository.getReferenceById(commentForKOTSuggestion.getId());
            throw new CommentForKOTSuggestionAlreadyExistsException();
        }catch(EntityNotFoundException e){
            added = commentForKOTSuggestionRepository.save(commentForKOTSuggestion);
        }
        return added;
    }

    @Override
    public void updateCommentOnKot(CommentForKOTSuggestion commentForKOTSuggestion) throws CommentForKOTSuggestionNotExistsException {
        Optional<CommentForKOTSuggestion> optionalCommentOnKot= commentForKOTSuggestionRepository.findById(commentForKOTSuggestion.getId());
        if(optionalCommentOnKot.isPresent()){
            commentForKOTSuggestionRepository.save(commentForKOTSuggestion);
        }
        else throw new CommentForKOTSuggestionNotExistsException();
    }

    @Override
    public void deleteCommentOnKot(UUID id) throws CommentForKOTSuggestionNotExistsException {
        Optional<CommentForKOTSuggestion> optionalCommentOnKot= commentForKOTSuggestionRepository.findById(id);
        if(optionalCommentOnKot.isPresent()){
            commentForKOTSuggestionRepository.delete(optionalCommentOnKot.get());
        }
        else throw new CommentForKOTSuggestionNotExistsException();
    }

    @Override
    public CommentForKOTSuggestion getCommentOnKot(UUID id) throws CommentForKOTSuggestionNotExistsException {
        Optional<CommentForKOTSuggestion> optionalCommentOnKot= commentForKOTSuggestionRepository.findById(id);
        if(optionalCommentOnKot.isPresent()) return optionalCommentOnKot.get();
        else throw new CommentForKOTSuggestionNotExistsException();
    }

    @Override
    public List<CommentForKOTSuggestion> getCommentOnKotUsingMenuItemId(UUID menuItemId) throws CommentForKOTSuggestionNotExistsException {
        List<CommentForKOTSuggestion> comments= commentForKOTSuggestionRepository.findByMenuItemId(menuItemId.toString());
        comments.addAll(commentForKOTSuggestionRepository.findByMenuItemIdComplement(menuItemId.toString()));
        return comments;
    }


}
