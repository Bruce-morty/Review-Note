package com.bruce.binarySearchTree;

import com.bruce.node.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Qi Gao
 * Date:2021/7/6
 * Version:1.0.0
 */
public class TreeTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                list.add(root.value);//根
                stack.push(root);
                root=root.left;//左
            }
            root = stack.pop();
            root = root.right;//右
        }
        return list;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root = root.left;//左
            }
            root = stack.pop();
            list.add(root.value);//根
            root = root.right;//右
        }
        return list;
    }

}
