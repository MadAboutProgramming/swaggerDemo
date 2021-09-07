package com.demo.generic.core.message_broker;

public interface MessageBroker<M> extends Consumer<M>,Publisher<M>{

    void stop();

    void start();


}
