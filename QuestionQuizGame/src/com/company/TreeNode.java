package com.company;

//Use a binary tree to write questions for the users can guess and think about
//We need to represent Tree nodes

public class TreeNode {

    enum Type{
        ANSWER, QUESTION//Need these to represent the nodes
    }

    public static final String QUESTION_FLAG = "Q:";
    public static final String ANSWER_FLAG = "A:";
    public String data;
    public Type type;
    public TreeNode yes;//When the treenode is a question they have two children that are choices for the question. Yes or No.
    public TreeNode no;

    public TreeNode(){}

    public TreeNode(String data, Type type){
        this.data = data;
        this.type = type;
    }

    public boolean isQuestion(){
        return Type.QUESTION.equals(type);
    }

    public boolean isAnswer(){
        return Type.ANSWER.equals(type);
    }

    public void setData(String data){

        type = Type.QUESTION;

        if(data.startsWith(ANSWER_FLAG)){
            type = Type.ANSWER;
        }

        this.data = data.substring(2); //We remove question or answer

    }

    public void addYes(TreeNode yes){
        this.yes = yes;
    }

    public void addNo(TreeNode no){
        this.no = no;
    }


}
