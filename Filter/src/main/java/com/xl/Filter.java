package com.xl;

import com.xl.pojo.Message;

public interface Filter {

  public void init();

  public void filter(Message message);

  public void preDestory();

}
