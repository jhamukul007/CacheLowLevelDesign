package com.cache.cachelowleveldesign.evication;

import lombok.Data;

@Data
public class DoublyLinkedList<K> {
    private DoublyLinkedListNode<K> deletionEnd;
    private DoublyLinkedListNode<K> insertionEnd;

    public DoublyLinkedList() {
        this.insertionEnd = new DoublyLinkedListNode<>(null);
        this.deletionEnd = new DoublyLinkedListNode<>(null);
        deletionEnd.setNext(insertionEnd);
        insertionEnd.setPrev(deletionEnd);
    }

    public K deleteFirst() {
        DoublyLinkedListNode<K> toBeEvictedNode = deletionEnd.getNext();
        if (toBeEvictedNode == null)
            return null;
        deletionEnd.setNext(toBeEvictedNode.getNext());
        toBeEvictedNode.getNext().setPrev(deletionEnd);
        return toBeEvictedNode.getKey();
    }

    public void insertLast(DoublyLinkedListNode<K> node) {
        DoublyLinkedListNode<K> nodePrev = insertionEnd.getPrev();
        nodePrev.setNext(node);
        node.setPrev(nodePrev);
        node.setNext(insertionEnd);
        insertionEnd.setPrev(node);
    }

}

