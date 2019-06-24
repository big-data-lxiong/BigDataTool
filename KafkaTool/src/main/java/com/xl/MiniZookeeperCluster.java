package com.xl;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

import java.net.InetSocketAddress;

public class MiniZookeeperCluster {

  private NIOServerCnxnFactory factory;

  public void start(){
    try {
      factory = new NIOServerCnxnFactory();
      factory.configure(new InetSocketAddress("localhost", 100000), 1024);
      factory.startup(new ZooKeeperServer(null, null, 1000));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
