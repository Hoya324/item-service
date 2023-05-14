package hello.itemservice.Repository;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemParamDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static 쓴 것과 HashMap 쓴 것 주의
//    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
//    private static AtomicLong sequance = 0L; // static
    private static long sequance = 0L; // static

    public Item save(Item item) {
        item.setId(++sequance);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // ArrayList로 감싼 이유는 store을 unmutable하게 사용하기 위해서이다.
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, ItemParamDto updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
