package com.demo.generic.core.message_broker;

public interface Consumer<M> {
    default void consumeMessage(M message){
         /* This is for cases where same message may
         be needed to consumed by another topic.
          */
    }

    default void consumeSameMessageFromAnotherTopic(M message) {
        /* This is for cases where same message may
         be needed to consumed by another topic.

         having same messages for different topic is not
         unusual or uncommon , this can help consume those
         messages, currently it consumes only from two different topic
         with same message format.
         */
    }

}
