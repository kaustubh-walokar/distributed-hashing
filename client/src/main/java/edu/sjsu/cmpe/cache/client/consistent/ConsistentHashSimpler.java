package edu.sjsu.cmpe.cache.client.consistent;

/**
 * Created by kaustubh on 03/05/15.
 */

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.ArrayList;

public class ConsistentHashSimpler<T> {

    private final HashFunction hashFunction;
    private ArrayList<T> nodeList;

    public ConsistentHashSimpler(ArrayList<T> nodes) {

        this.hashFunction = Hashing.md5();
        nodeList = new ArrayList<T>();
        nodeList.addAll(nodes);

        for (T node : nodes) {
            add(node);
        }

        System.out.println("New Circle : " + nodeList);

    }

    public void add(T node) {

        nodeList.add(node);

    }

    public void remove(T node) {
        nodeList.remove(node);
    }

    public T getCache(Object key) {

        int bucket = Hashing.consistentHash(
                hashFunction.newHasher()
                        .putString(key.toString())
                        .hash()
                , nodeList.size());

        return nodeList.get(bucket);


    }
}