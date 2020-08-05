package examprep.softuni.service.impl;

import examprep.softuni.dao.entity.Category;
import examprep.softuni.dao.entity.Item;
import examprep.softuni.dao.repository.ItemRepository;
import examprep.softuni.service.CategoryService;
import examprep.softuni.service.ItemService;
import examprep.softuni.service.model.bind.ItemAddServiceModel;
import examprep.softuni.service.model.view.ItemViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapper mapper;
    private final CategoryService categoryService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper mapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    @Override
    public ItemAddServiceModel addItem(ItemAddServiceModel item) {
        Item itemAdd = this.mapper.map(item, Item.class);
        Category byName = this.categoryService.findByName(item.getCategory().name());
        itemAdd.setCategory(this.categoryService.findByName(item.getCategory().name()));
        return this
                .mapper
                .map(this.itemRepository
                                .saveAndFlush(itemAdd),
                        ItemAddServiceModel.class);
    }

    @Override
    public List<ItemViewModel> showItems() {
        return this.itemRepository
                .findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.mapper.map(item, ItemViewModel.class);
                    itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender().toString().toUpperCase(),
                            item.getCategory().getName().toString().toUpperCase()));

                    return itemViewModel;
                })
                .collect(Collectors.toList());

    }

    @Override
    public ItemViewModel findById(String id) {
        return this.itemRepository
                .findById(id)
                .map(item -> {
            ItemViewModel itemViewModel = this.mapper.map(item, ItemViewModel.class);
            itemViewModel.setImgUrl(String.format("/img/%s-%s.jpg", item.getGender().toString().toUpperCase(),
                    item.getCategory().getName().toString().toUpperCase()));

            return itemViewModel;
        })
                .orElse(null);

    }

    @Override
    public void deleteById(String id) {
        this.itemRepository.deleteById(id);
    }
}
