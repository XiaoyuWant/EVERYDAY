package com.xiaoyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.dao.ItemDao;
import com.xiaoyu.entity.DayItems;
import com.xiaoyu.entity.Item;
import com.xiaoyu.entity.User;
import com.xiaoyu.service.ItemService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, Item> implements ItemService {
    @Resource
    ItemDao itemDao;

    @Override
    public List<DayItems> getPublicDayItems() {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<Item>();
        queryWrapper.eq(Item::getIsPublic, 1);
        List<Item> items = itemDao.selectList(queryWrapper);
        return packItemsToDayItems(items);
    }

    @Override
    public List<Item> getAllItemsByUser(User user) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<Item>();
        queryWrapper.eq(Item::getUser, user.getUserName());
        List<Item> items = itemDao.selectList(queryWrapper);
        return items;

    }

    @Override
    public List<DayItems> getDayItemsByUser(User user) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<Item>();
        queryWrapper.eq(Item::getUser, user.getUserName());
        List<Item> items = itemDao.selectList(queryWrapper);
        return packItemsToDayItems(items);
    }

    @Override
    public void saveAItem(Item item) {
        itemDao.insert(item);
    }


    public List<DayItems> packItemsToDayItems(List<Item> items){
        Map<String,List<Item>> map = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        for(Item item: items) {
            String date = formatter.format(item.getTime());
            if(map.containsKey(date)) {
                map.get(date).add(item);
            } else {
                List<Item> list = new ArrayList<>();
                list.add(item);
                map.put(date,list);
            }
        }

        List<DayItems> dayItemsList = new ArrayList<>();
        for(Map.Entry<String,List<Item>> entry : map.entrySet()) {
            DayItems dayItems = new DayItems();
            dayItems.setDate(entry.getKey());
            List<Item> itemList = entry.getValue();
            Collections.sort(itemList, (a,b)->(b.getTime().compareTo(a.getTime())));
            dayItems.setItems(itemList);
            dayItemsList.add(dayItems);
        }
        return dayItemsList;
    }
}

