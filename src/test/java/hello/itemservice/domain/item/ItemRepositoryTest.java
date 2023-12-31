package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item getItem = itemRepository.findById(item.getId());
        assertThat(getItem).isEqualTo(saveItem);
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);

    }
    @Test
    void update(){
        //given
        Item item1 = new Item("itemA", 10000, 10);

        Item saveItem = itemRepository.save(item1);
        Long itemId = saveItem.getId();
        //when
        Item updateItem = new Item("itemB", 20000, 30);
        itemRepository.update(itemId,updateItem);
        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());

    }
}
