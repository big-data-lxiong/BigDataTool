package com.xl.work;

import com.xl.Filter;
import com.xl.imp.IPFilter;
import com.xl.pojo.Message;

import java.util.HashMap;

public class FilterWork {

  private FilterWork INSTANCE = new FilterWork();

  HashMap<String, Filter> map = new HashMap<String, Filter>();

  private FilterWork(){

  }

  private void loadFilter(){
    map.put(IPFilter.class.getName(), new IPFilter());
  }

  public void filterAll(Message message){
    map.forEach((filterName, filter) -> {
      filter.filter(message);
    });
  }

}
