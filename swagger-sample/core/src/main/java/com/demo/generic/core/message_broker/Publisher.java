package com.demo.generic.core.message_broker;

public interface Publisher<M> {

    default void publishMessage(M message) {
         /* This is used to publish the message
          */
    }

    default void publishThroughTopicName(M message, String topicName) {
        /* This is used to publish the message
         through the topic name.

         */
    }
}


