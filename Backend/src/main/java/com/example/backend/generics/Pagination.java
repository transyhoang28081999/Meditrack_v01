package com.example.backend.generics;

import com.example.backend.securities.user.User;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {
    public List<T> reverse(List<T> lists) {
        List<T> revLists = new ArrayList<>();
        for(int i = lists.size() - 1; i >= 0; i--) {
            revLists.add(lists.get(i));
        }
        return revLists;
    }
    public List<T> pagination(List<T> arr, int pageNo, int pageSize) {
        List<T> lists = new ArrayList<T>();
        int startElement = (pageNo - 1)*pageSize;

        // Kiểm tra xem trang đang tìm có lơn hơn tổng số trang không?
        int totalPage = Math.floorDiv(arr.size(), pageSize) + 1 ;
        if(pageNo > totalPage) return null;

        // Chiết tách lists của trang cuối
        if(pageNo == totalPage){
            int i = arr.size() - 1;
            while(i >= startElement){
                T list = arr.get(i);
                lists.add(list);
                i--;
            }
            return reverse(lists);
        }

        //Chiết tách lists của trang đang tìm
        int endElement = pageNo * pageSize;
        for(int i = startElement; i < endElement; i++){
            T list = arr.get(i);
            lists.add(list);
        }
        return lists;
    }
}
