package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/response-view")
public class ResponseViewController {

    @RequestMapping("v1")
    public ModelAndView responseViewV1() {

        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    /**
     * @RequestMapping의 url과 view의 논리적 뷰의 이름과 동일하면, return을 하지 않아도 자동적으로 반환됨
     * @param model
     * @return
     */
    @RequestMapping("v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }
}
