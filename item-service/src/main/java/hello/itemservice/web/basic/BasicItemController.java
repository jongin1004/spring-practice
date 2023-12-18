package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final 이 붙은 멤버변수의 생성자를 만들어준다.
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @RequiredArgsConstructor가 자동 적으로 생성자를 만들어주고, 생성자가 하나 뿐이니까 자동적으로 @Autowired가 붙어서
//    의존성 주입까지 완료된다.
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // @ModelAttribute는 model에 자동적으로 넣어주는 역할도 진행한다.
    // 파라미터로 전달받은 "item"을 key값으로 model.addAttribute("item", item)과 동일한 역할
    // 파라미터를 생략하는 경우는, 클래스명의 첫 글자를 소문자로 바꿔서 사용한다. 그리고, Model model도 생략이 가능하다.
    // 또한, @ModelAttribute까지도 생략이 가능하다.
    // 즉, 객체를 생성해서 요청 파라미터의 값을 프로퍼티 접근법으로 입력하는 것과
    // 지정한 객체를 model에 자동으로 넣어주는 역할을 한다.
    @PostMapping("/add")
    public String save(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
//        model.addAttribute("item", savedItem);
        // return 시에 동일한 키값이 있으면 치환해주고, 없으면 나머지는 쿼리 스트링으로 표시해준다.
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true); // 저장해서 넘어온거라는 의미
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        // itemId는 @PathVariable로 받아온 값을 그대로 치환이된다.
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
