package inhatc.spring.shop.service;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.constant.Role;
import inhatc.spring.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public Member saveMember(Member member) {

        validateDuplicateMember(member);  // 중복된 사용자인지 체크하는 메소드
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> member1 = memberRepository.findByEmail(member.getEmail());

        if(member1.isPresent()) {
            System.out.println(member1.get());
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        //member1.orElseThrow(() -> new IllegalStateException("이미 존재하는 회원입니다."));

        // 위에 .orElseThrow랑 같은 코드
//        if(findMember.isPresent()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("사용자를 찾을 수 없습니다." + email));

        log.info("[로그인 된 사용자] : " + member);

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
