package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.service;


import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.CommentForKOTSuggestion;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionNotExistsException;

import java.util.List;
import java.util.UUID;

public interface CommentForKOTSuggestionService {

    public CommentForKOTSuggestion addCommentOnKot(CommentForKOTSuggestion commentForKOTSuggestion) throws CommentForKOTSuggestionAlreadyExistsException;
    public void updateCommentOnKot(CommentForKOTSuggestion commentForKOTSuggestion) throws CommentForKOTSuggestionNotExistsException;
    public void deleteCommentOnKot(UUID id) throws CommentForKOTSuggestionNotExistsException;
    public CommentForKOTSuggestion getCommentOnKot(UUID id) throws CommentForKOTSuggestionNotExistsException;
    public List<CommentForKOTSuggestion> getCommentOnKotUsingMenuItemId(UUID menuItemId)throws CommentForKOTSuggestionNotExistsException;
}
