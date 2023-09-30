package com.cache.cachelowleveldesign.evication;

import lombok.Data;

@Data
public class DoublyLinkedListNode<K> {
    private K key;
    private DoublyLinkedListNode<K> prev;
    private DoublyLinkedListNode<K> next;

    public DoublyLinkedListNode(K key) {
        this.key = key;
        prev = next = null;
    }
}
