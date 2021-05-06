package net.joins.blog.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    final PostService service;

    @GetMapping("index.html")
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC , value = 10) Pageable pageable){
        log.info("index");
/*
        List<Post> list = IntStream.range(1,10).mapToObj(i-> Post.builder().link("post.html")
                .title("제목 - "+i).subtitle("subtitle - "+i).writer("misolob").updated("2021-02-08").build()).collect(Collectors.toList());
 */
        Page<Post> list = service.getPostList(pageable);

        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("post.html")
    public String post(Model model, Long id){
        log.info("post");
        /*
        Post post = Post.builder().title("Man must explore, and")
                .subtitle("Problems look mighty smaill from...")
                .writer("misolab")
                .updated("2021-02-08")
                .content("Welcome!!<img class=\"img-gluid\" src=\"img/post-sample-image.jpg\">")
                .bgImage("img/post-bg.img")
                .build();
         */
        Post post = service.getPost(2L);

        model.addAttribute("post", post);

        return "post";
    }

    @GetMapping("{postId}")
    public String postId(Model model, @PathVariable Long postId){
        log.info("post");

        Post post = service.getPost(postId);

        model.addAttribute("post", post);

        return "post";
    }
}
