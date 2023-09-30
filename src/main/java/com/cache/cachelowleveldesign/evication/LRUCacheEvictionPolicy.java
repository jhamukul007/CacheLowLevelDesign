package com.cache.cachelowleveldesign.evication;

import com.cache.cachelowleveldesign.exceptions.EvictionNotAllowedException;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheEvictionPolicy<K> implements CacheEvictionPolicy<K> {
    private Map<K, DoublyLinkedListNode<K>> mapper;

    private DoublyLinkedList<K> doublyLinkedList;

    public LRUCacheEvictionPolicy() {
        this.mapper = new HashMap<>();
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public K evictKey() throws EvictionNotAllowedException {
        K evictedKey = doublyLinkedList.deleteFirst();
        if (evictedKey == null)
            throw new EvictionNotAllowedException();
        mapper.remove(evictedKey);
        return evictedKey;
    }

    @Override
    public void keyAccessed(K key) {
        DoublyLinkedListNode<K> node = mapper.get(key);
        if (node == null) {
            node = new DoublyLinkedListNode<>(key);
        } else {
            DoublyLinkedListNode<K> prevNode = node.getPrev();
            DoublyLinkedListNode<K> nextNode = node.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        doublyLinkedList.insertLast(node);
        mapper.put(key, node);
    }


}

