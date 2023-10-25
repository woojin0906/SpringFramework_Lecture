package inhatc.spring.shop.controller;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.dto.MemberFormDto;
import inhatc.spring.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/member/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/member/new")
    public String insertMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
       if(bindingResult.hasErrors()) {
           return "member/memberForm";
       }

       try {
           Member member = Member.createMember(memberFormDto, passwordEncoder);
           memberService.saveMember(member);
       } catch (IllegalStateException e) {
           model.addAttribute("errorMessage", e.getMessage());
           return "member/memberForm";
       }

       return "redirect:/";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "member/memberLoginForm";
    }

    @GetMapping("/member/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "id 또는 pw를 확인해주세요.");
        return "member/memberLoginForm";
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 로그아웃");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}
