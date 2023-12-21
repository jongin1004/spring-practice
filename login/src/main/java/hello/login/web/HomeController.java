package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sm;
//    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * HttpServletRequest를 통해서 쿠키를 가져와도 되지만, @CookieValue 애노테이션을 제공하기 때문에 사용
     * 주의할점은, 로그인하지 않은 유저도 요청하기 때문에 required 속성값을 false로 해야한다.
     *
      * @param memberId
     * @param model
     * @return
     */
//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        Member member =  (Member) sm.getSession(request);

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        // false : 아직 로그인하지 않은 유저들에게는 세션을 만들 필요가 없기 때문에
        HttpSession session = request.getSession(false);

        if (session == null) return "home";

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (member == null) return "home";

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
            Model model) {

        if (member == null) return "home";

        model.addAttribute("member", member);
        return "loginHome";
    }
}