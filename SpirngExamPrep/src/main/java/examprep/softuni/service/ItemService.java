package examprep.softuni.service;

import examprep.softuni.service.model.bind.ItemAddServiceModel;
import examprep.softuni.service.model.view.ItemViewModel;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    ItemAddServiceModel addItem(ItemAddServiceModel item);
    List<ItemViewModel> showItems();
    ItemViewModel findById(String id);
    void deleteById(String id);
}
