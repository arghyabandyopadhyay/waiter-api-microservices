package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.controller;

import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.CommentForKOTSuggestion;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.service.CommentForKOTSuggestionService;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CommentForKOTSuggestionNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/waiterr/commentforkotsuggestion")
public class CommentForKOTSuggestionController {
    @Autowired
    CommentForKOTSuggestionService commentForKOTSuggestionService;
    @GetMapping()
    public ResponseEntity<?> getCustomerDetails(@RequestParam(name="identifier",required = false) UUID id, @RequestParam(name="menuItemId", required = false) UUID menuItemId) {
        CommentForKOTSuggestion customerDetail = null;
        List<CommentForKOTSuggestion> commentForKotSuggestions = null;
        try {
            if(id==null && menuItemId==null)throw new CommentForKOTSuggestionNotExistsException();
            if(id!=null){
                customerDetail = commentForKOTSuggestionService.getCommentOnKot(id);
                return new ResponseEntity<>(customerDetail, HttpStatus.OK);
            }
            else {
                commentForKotSuggestions=commentForKOTSuggestionService.getCommentOnKotUsingMenuItemId(menuItemId);
                return new ResponseEntity<>(commentForKotSuggestions, HttpStatus.OK);
            }
        } catch (CommentForKOTSuggestionNotExistsException e) {
            return new ResponseEntity<>("Comment For KOT Suggestion with specified details not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addCustomerDetails(@RequestBody() CommentForKOTSuggestion commentForKOTSuggestion){
        try {
            CommentForKOTSuggestion newCommentForKOTSuggestion = commentForKOTSuggestionService.addCommentOnKot(commentForKOTSuggestion);
            return new ResponseEntity<CommentForKOTSuggestion>(newCommentForKOTSuggestion,HttpStatus.CREATED);
        } catch (CommentForKOTSuggestionAlreadyExistsException e) {
            return new ResponseEntity<String>("Comment For KOT Suggestion already exists",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCustomerDetails(@RequestParam(name="identifier") UUID id){
        try {
            commentForKOTSuggestionService.deleteCommentOnKot(id);
            return new ResponseEntity<String>("Comment For KOT Suggestion deleted successfully",HttpStatus.OK);
        } catch (CommentForKOTSuggestionNotExistsException e) {
            return new ResponseEntity<String>("Comment For KOT Suggestion with specified details not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

