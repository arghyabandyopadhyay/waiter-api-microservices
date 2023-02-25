package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.repository;

import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.CommentForKOTSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentForKOTSuggestionRepository extends JpaRepository<CommentForKOTSuggestion, UUID> {

    @Query(value ="SELECT comment_for_kot_suggestion FROM CommentForKOTSuggestion comment_for_kot_suggestion WHERE menu_item_id= :menuItemId "
    )
    List<CommentForKOTSuggestion> findByMenuItemId(@Param("menuItemId") String menuItemId);
    @Query(value ="SELECT comment_for_kot_suggestion FROM CommentForKOTSuggestion comment_for_kot_suggestion WHERE menu_item_id!= :menuItemId "
    )
    List<CommentForKOTSuggestion> findByMenuItemIdComplement(@Param("menuItemId") String menuItemId);
}