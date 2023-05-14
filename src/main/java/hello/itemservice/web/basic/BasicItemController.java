package hello.itemservice.web.basic;


import hello.itemservice.Repository.ItemRepository;
import hello.itemservice.domain.item.Item;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // 생성자 생략 가능
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired // 생성자 하나라 안 써도 됨.
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
    @GetMapping
    public String items(Model model) { // Model만 받아서 목록만 받음
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items); // model에 items라는 컬렉션이 담김
        return "basic/items"; // 이 위치에 View 만들 것
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 10000, 20));
    }
}
