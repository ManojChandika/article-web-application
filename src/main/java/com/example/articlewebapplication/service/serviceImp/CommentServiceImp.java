package com.example.articlewebapplication.service.serviceImp;

import com.example.articlewebapplication.dto.CommentDto;
import com.example.articlewebapplication.entity.Comment;
import com.example.articlewebapplication.entity.Post;
import com.example.articlewebapplication.exception.BlogApiException;
import com.example.articlewebapplication.exception.ResourceNotFoundException;
import com.example.articlewebapplication.repo.CommentRepo;
import com.example.articlewebapplication.repo.PostRepo;
import com.example.articlewebapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentRepo commentRepository;
    @Autowired
    private PostRepo postRepository;
    @Autowired
    private ModelMapper modelMapper;

    // Create Comment
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    // Get All Comments by Post Id
    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        // Get Comments by post id
        List<Comment> comments = commentRepository.findByPostId(postId);
        // Convert list of comment entities to comment Dto's
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    // Get Comment by post id
    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // Review Post by id
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id", postId));
        // Review Comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        // check the particulate comment belongs to post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }
        return mapToDto(comment);
    }

    // Update Comment
    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto requestCommentDto) {
        // Review Post by id
        Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        // Review Comment by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        // check the particulate comment belongs to post or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }
        comment.setName(requestCommentDto.getName());
        comment.setEmail(requestCommentDto.getEmail());
        comment.setBody(requestCommentDto.getBody());

        return mapToDto(commentRepository.save(comment));
    }

    // Delete Comment
    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Review post by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        // Review comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        // check the particulate comment belongs to post or not
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }
        commentRepository.delete(comment);
    }

    // map Dto to Entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
//        Comment comment= new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    // map Entity to Dto
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
//        CommentDto commentDto= new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

}
